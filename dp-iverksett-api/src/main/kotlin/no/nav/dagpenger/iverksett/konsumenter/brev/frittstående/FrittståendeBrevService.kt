package no.nav.dagpenger.iverksett.konsumenter.brev.frittstående

import no.nav.dagpenger.iverksett.infrastruktur.advice.ApiFeil
import no.nav.dagpenger.iverksett.infrastruktur.transformer.toDomain
import no.nav.dagpenger.iverksett.konsumenter.brev.JournalpostClient
import no.nav.dagpenger.iverksett.kontrakter.brev.Brevmottakere
import no.nav.dagpenger.iverksett.konsumenter.brev.domain.FrittståendeBrev
import no.nav.dagpenger.iverksett.konsumenter.brev.stønadstypeTilDokumenttype
import no.nav.dagpenger.iverksett.kontrakter.felles.FrittståendeBrevDto
import no.nav.dagpenger.iverksett.kontrakter.journalføring.dokarkiv.ArkiverDokumentRequest
import no.nav.dagpenger.iverksett.kontrakter.journalføring.dokarkiv.Dokument
import no.nav.dagpenger.iverksett.kontrakter.journalføring.dokarkiv.Filtype
import no.nav.dagpenger.iverksett.kontrakter.journalføring.dokdist.Distribusjonstype
import no.nav.familie.prosessering.domene.Task
import no.nav.familie.prosessering.internal.TaskService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class FrittståendeBrevService(
    private val frittståendeBrevRepository: FrittståendeBrevRepository,
    private val taskService: TaskService,
    private val journalpostClient: JournalpostClient,
) {

    private val logger = LoggerFactory.getLogger(javaClass)
    private val secureLogger = LoggerFactory.getLogger("secureLogger")

    fun journalførOgDistribuerBrev(data: FrittståendeBrevDto) {
        val journalpostId = journalpostClient.arkiverDokument(
            ArkiverDokumentRequest(
                fnr = data.personIdent,
                forsøkFerdigstill = true,
                hoveddokumentvarianter = listOf(
                    Dokument(
                        data.fil,
                        Filtype.PDFA,
                        dokumenttype = stønadstypeTilDokumenttype(data.stønadType),
                        tittel = data.brevtype.tittel,
                    ),
                ),
                fagsakId = data.eksternFagsakId.toString(),
                journalførendeEnhet = data.journalførendeEnhet,
            ),
            data.saksbehandlerIdent,
        ).journalpostId
        try {
            val bestillingId = journalpostClient.distribuerBrev(journalpostId, Distribusjonstype.VIKTIG)
            logger.info("Sendt frittstående brev journalpost=$journalpostId bestillingId=$bestillingId")
        } catch (e: Exception) {
            logger.error("Feilet distribuering av $journalpostId")
            secureLogger.error("Feilet distribuering av $journalpostId", e)
            // kaster ApiFeil som ikke logger då vi allerede logger feilet
            throw ApiFeil("Feilet journalføring av $journalpostId", HttpStatus.BAD_REQUEST)
        }
    }

    @Transactional
    fun opprettTask(data: FrittståendeBrevDto) {
        val mottakere = data.mottakere
        if (mottakere == null || mottakere.isEmpty()) {
            throw IllegalArgumentException("Liste med brevmottakere kan ikke være tom")
        }

        val brev = frittståendeBrevRepository.insert(
            FrittståendeBrev(
                personIdent = data.personIdent,
                eksternFagsakId = data.eksternFagsakId,
                journalførendeEnhet = data.journalførendeEnhet,
                saksbehandlerIdent = data.saksbehandlerIdent,
                stønadstype = data.stønadType,
                mottakere = Brevmottakere(mottakere.map { it.toDomain() }),
                fil = data.fil,
                brevtype = data.brevtype,
            ),
        )
        taskService.save(Task(JournalførFrittståendeBrevTask.TYPE, brev.id.toString()))
    }
}
