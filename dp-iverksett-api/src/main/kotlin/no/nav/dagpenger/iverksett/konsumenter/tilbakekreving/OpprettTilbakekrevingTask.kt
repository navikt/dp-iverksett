package no.nav.dagpenger.iverksett.konsumenter.tilbakekreving

import no.nav.dagpenger.iverksett.api.IverksettingRepository
import no.nav.dagpenger.iverksett.kontrakter.iverksett.IverksettDagpenger
import no.nav.dagpenger.iverksett.api.domene.TilbakekrevingResultat
import no.nav.dagpenger.iverksett.api.domene.tilSimulering
import no.nav.dagpenger.iverksett.api.tilstand.IverksettResultatService
import no.nav.dagpenger.iverksett.infrastruktur.FamilieIntegrasjonerClient
import no.nav.dagpenger.iverksett.infrastruktur.repository.findByIdOrThrow
import no.nav.dagpenger.iverksett.konsumenter.opprettNesteTask
import no.nav.dagpenger.iverksett.konsumenter.økonomi.simulering.SimuleringService
import no.nav.dagpenger.iverksett.konsumenter.økonomi.simulering.harFeilutbetaling
import no.nav.dagpenger.iverksett.kontrakter.felles.BehandlingType
import no.nav.dagpenger.iverksett.kontrakter.simulering.BeriketSimuleringsresultat
import no.nav.dagpenger.iverksett.kontrakter.tilbakekreving.OpprettTilbakekrevingRequest
import no.nav.familie.prosessering.AsyncTaskStep
import no.nav.familie.prosessering.TaskStepBeskrivelse
import no.nav.familie.prosessering.domene.Task
import no.nav.familie.prosessering.internal.TaskService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.UUID

@Service
@TaskStepBeskrivelse(
    taskStepType = OpprettTilbakekrevingTask.TYPE,
    maxAntallFeil = 50,
    settTilManuellOppfølgning = true,
    triggerTidVedFeilISekunder = 15 * 60L,
    beskrivelse = "Opprett tilbakekrevingsbehandling",
)
class OpprettTilbakekrevingTask(
    private val iverksettingRepository: IverksettingRepository,
    private val taskService: TaskService,
    private val tilbakekrevingClient: TilbakekrevingClient,
    private val iverksettResultatService: IverksettResultatService,
    private val simuleringService: SimuleringService,
    private val familieIntegrasjonerClient: FamilieIntegrasjonerClient,
) : AsyncTaskStep {

    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    override fun doTask(task: Task) {
        val behandlingId = UUID.fromString(task.payload)
        val iverksett = iverksettingRepository.findByIdOrThrow(behandlingId).data
        if (skalOppretteTilbakekreving(iverksett, behandlingId)) {
            val nyBeriketSimuleringsresultat = hentBeriketSimulering(iverksett)
            val nyIverksett = iverksett.oppfriskTilbakekreving(nyBeriketSimuleringsresultat)
            loggForskjell(nyIverksett, iverksett, behandlingId)
            if (nyBeriketSimuleringsresultat.harFeilutbetaling()) {
                opprettTilbakekreving(behandlingId, nyIverksett)
            } else {
                logger.info("Behandling=$behandlingId har ikke (lenger) positiv feilutbetaling i simuleringen")
            }
        }
    }

    private fun opprettTilbakekreving(
        behandlingId: UUID,
        iverksettData: IverksettDagpenger,
    ) {
        logger.info("Det kreves tilbakekrevingsbehandling for behandling=$behandlingId")
        val opprettTilbakekrevingRequest = lagTilbakekrevingRequest(iverksettData)
        tilbakekrevingClient.opprettBehandling(opprettTilbakekrevingRequest)
        iverksettResultatService.oppdaterTilbakekrevingResultat(
            behandlingId = behandlingId,
            TilbakekrevingResultat(opprettTilbakekrevingRequest),
        )

        // Burde iverksett oppdateres i DBen, siden tilbakekreving potensielt er endret?
        // iverksettingRepository.lagreIverksett(behandlingId,nyIverksett)
        logger.info("Opprettet tilbakekrevingsbehandling for behandling=$behandlingId")
    }

    private fun skalOppretteTilbakekreving(
        iverksettData: IverksettDagpenger,
        behandlingId: UUID?,
    ): Boolean {
        if (iverksettData.behandling.behandlingType == BehandlingType.FØRSTEGANGSBEHANDLING) {
            logger.info("Førstegangsbehandling trenger ikke tilbakekreving behandlingId=$behandlingId")
            return false
        } else if (!iverksettData.vedtak.tilbakekreving.skalTilbakekreves) {
            logger.info(
                "Tilbakekreving ikke valgt for behandlingId=$behandlingId. Oppretter derfor ikke tilbakekrevingsbehandling.",
            )
            return false
        } else if (iverksettData.vedtak.tilkjentYtelse == null) {
            logger.warn("OpprettTilbakekrevingTask ikke utført - tilkjentYtelse er null, Behandling: $behandlingId")
            return false
        } else if (finnesÅpenTilbakekrevingsbehandling(iverksettData)) {
            logger.info("Det finnnes allerede tilbakekrevingsbehandling for behandling=$behandlingId")
            return false
        }
        return true
    }

    private fun loggForskjell(
        nyIverksettData: IverksettDagpenger,
        iverksettData: IverksettDagpenger,
        behandlingId: UUID?,
    ) {
        if (nyIverksettData != iverksettData) {
            logger.info("Grunnlaget for tilbakekreving for behandling=$behandlingId har endret seg siden saksbehandlingen")
        }
    }

    override fun onCompletion(task: Task) {
        taskService.save(task.opprettNesteTask())
    }

    private fun hentBeriketSimulering(originalIverksett: IverksettDagpenger): BeriketSimuleringsresultat {
        val simuleringRequest = originalIverksett.tilSimulering()
        return simuleringService.hentBeriketSimulering(simuleringRequest)
    }

    private fun lagTilbakekrevingRequest(iverksett: IverksettDagpenger): OpprettTilbakekrevingRequest {
        // Henter ut på nytt, selv om noe finnes i iverksett-dto'en
        val enhet = familieIntegrasjonerClient.hentBehandlendeEnhetForBehandling(iverksett.søker.personIdent)!!
        return iverksett.tilOpprettTilbakekrevingRequest(enhet)
    }

    private fun finnesÅpenTilbakekrevingsbehandling(nyIverksett: IverksettDagpenger): Boolean =
        tilbakekrevingClient.finnesÅpenBehandling(nyIverksett.fagsak.fagsakId)

    companion object {

        const val TYPE = "opprettTilbakekrevingsbehandling"
    }
}
