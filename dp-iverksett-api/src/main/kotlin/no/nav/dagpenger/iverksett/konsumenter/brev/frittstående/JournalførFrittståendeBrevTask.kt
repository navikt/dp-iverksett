package no.nav.dagpenger.iverksett.konsumenter.brev.frittstående

import no.nav.dagpenger.iverksett.infrastruktur.repository.findByIdOrThrow
import no.nav.dagpenger.iverksett.konsumenter.brev.JournalpostClient
import no.nav.dagpenger.iverksett.kontrakter.brev.Brevmottaker
import no.nav.dagpenger.iverksett.konsumenter.brev.domain.FrittståendeBrev
import no.nav.dagpenger.iverksett.konsumenter.brev.domain.JournalpostResultat
import no.nav.dagpenger.iverksett.konsumenter.brev.domain.JournalpostResultatMap
import no.nav.dagpenger.iverksett.kontrakter.brev.tilIdType
import no.nav.dagpenger.iverksett.konsumenter.brev.stønadstypeTilDokumenttype
import no.nav.dagpenger.iverksett.kontrakter.journalføring.dokarkiv.ArkiverDokumentRequest
import no.nav.dagpenger.iverksett.kontrakter.journalføring.dokarkiv.AvsenderMottaker
import no.nav.dagpenger.iverksett.kontrakter.journalføring.dokarkiv.Dokument
import no.nav.dagpenger.iverksett.kontrakter.journalføring.dokarkiv.Filtype
import no.nav.familie.prosessering.AsyncTaskStep
import no.nav.familie.prosessering.TaskStepBeskrivelse
import no.nav.familie.prosessering.domene.Task
import no.nav.familie.prosessering.internal.TaskService
import org.springframework.stereotype.Service
import java.util.UUID

@Service
@TaskStepBeskrivelse(
    taskStepType = JournalførFrittståendeBrevTask.TYPE,
    maxAntallFeil = 5,
    triggerTidVedFeilISekunder = 15,
    beskrivelse = "Journalfører frittstående brev.",
)
class JournalførFrittståendeBrevTask(
    private val journalpostClient: JournalpostClient,
    private val taskService: TaskService,
    private val frittståendeBrevRepository: FrittståendeBrevRepository,
) : AsyncTaskStep {

    override fun doTask(task: Task) {
        val callId = task.callId
        val frittståendeBrevId = UUID.fromString(task.payload)
        var frittståendeBrev = frittståendeBrevRepository.findByIdOrThrow(frittståendeBrevId)

        require(frittståendeBrev.mottakere.mottakere.isNotEmpty()) {
            "Mottakere forventes å inneholde mottakere"
        }

        frittståendeBrev.mottakere.mottakere.forEachIndexed { index, brevmottaker ->
            if (frittståendeBrev.journalpostResultat.map.containsKey(brevmottaker.ident)) {
                return@forEachIndexed
            }
            val journalpostId = journalpostClient.arkiverDokument(
                opprettArkiverDokumentRequest(frittståendeBrev, callId, index, brevmottaker),
                saksbehandler = frittståendeBrev.saksbehandlerIdent,
            ).journalpostId
            val oppdatertJournalposter =
                frittståendeBrev.journalpostResultat.map + mapOf(brevmottaker.ident to JournalpostResultat(journalpostId))
            frittståendeBrev =
                frittståendeBrev.copy(journalpostResultat = JournalpostResultatMap(oppdatertJournalposter))
            frittståendeBrevRepository.oppdaterJournalpostResultat(
                frittståendeBrevId,
                frittståendeBrev.journalpostResultat,
            )
        }
    }

    override fun onCompletion(task: Task) {
        taskService.save(Task(DistribuerFrittståendeBrevTask.TYPE, task.payload, task.metadata))
    }

    private fun opprettArkiverDokumentRequest(
        frittståendeBrev: FrittståendeBrev,
        callId: String,
        index: Int,
        brevmottaker: Brevmottaker,
    ) = ArkiverDokumentRequest(
        fnr = frittståendeBrev.personIdent,
        forsøkFerdigstill = true,
        hoveddokumentvarianter = listOf(
            Dokument(
                frittståendeBrev.fil,
                Filtype.PDFA,
                dokumenttype = stønadstypeTilDokumenttype(frittståendeBrev.stønadstype),
                tittel = frittståendeBrev.brevtype.tittel,
            ),
        ),
        fagsakId = frittståendeBrev.eksternFagsakId.toString(),
        journalførendeEnhet = frittståendeBrev.journalførendeEnhet,
        eksternReferanseId = "$callId-$index",
        avsenderMottaker = avsenderMottaker(frittståendeBrev, brevmottaker),
    )

    private fun avsenderMottaker(
        frittståendeBrev: FrittståendeBrev,
        brevmottaker: Brevmottaker,
    ) =
        if (frittståendeBrev.personIdent != brevmottaker.ident) {
            AvsenderMottaker(
                brevmottaker.ident,
                brevmottaker.identType.tilIdType(),
                brevmottaker.navn,
            )
        } else {
            null
        }

    companion object {

        const val TYPE = "journalførFrittståendeBrev"
    }
}
