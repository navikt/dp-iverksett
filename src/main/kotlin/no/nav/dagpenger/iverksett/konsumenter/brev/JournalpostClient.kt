package no.nav.dagpenger.iverksett.konsumenter.brev

import no.nav.dagpenger.iverksett.infrastruktur.advice.Ressurs
import no.nav.dagpenger.iverksett.infrastruktur.advice.getDataOrThrow
import no.nav.dagpenger.iverksett.infrastruktur.util.medContentTypeJsonUTF8
import no.nav.dagpenger.kontrakter.felles.Fagsystem
import no.nav.dagpenger.kontrakter.iverksett.journalføring.Journalpost
import no.nav.dagpenger.kontrakter.iverksett.journalføring.JournalposterForBrukerRequest
import no.nav.dagpenger.kontrakter.iverksett.journalføring.dokarkiv.ArkiverDokumentRequest
import no.nav.dagpenger.kontrakter.iverksett.journalføring.dokarkiv.ArkiverDokumentResponse
import no.nav.dagpenger.kontrakter.iverksett.journalføring.dokdist.DistribuerJournalpostRequest
import no.nav.dagpenger.kontrakter.iverksett.journalføring.dokdist.Distribusjonstype
import no.nav.familie.http.client.AbstractPingableRestClient
import no.nav.familie.kontrakter.felles.getDataOrThrow
import no.nav.familie.log.NavHttpHeaders
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import org.springframework.web.client.RestOperations
import org.springframework.web.util.UriComponentsBuilder
import java.net.URI

@Component
class JournalpostClient(
    @Qualifier("azure") restOperations: RestOperations,
    @Value("\${FAMILIE_INTEGRASJONER_API_URL}")
    private val integrasjonUri: URI,
) : AbstractPingableRestClient(restOperations, "journalpost") {

    val logger: Logger = LoggerFactory.getLogger(this::class.java)

    override val pingUri: URI = URI("/ping")
    private val dokarkivUri: URI = UriComponentsBuilder.fromUri(integrasjonUri).pathSegment("api/arkiv").build().toUri()
    private val journalPostUri: URI = UriComponentsBuilder.fromUri(integrasjonUri).pathSegment("api/journalpost").build().toUri()
    private val distribuerDokumentUri: URI =
        UriComponentsBuilder.fromUri(integrasjonUri).pathSegment("api/dist/v1").build().toUri()

    fun finnJournalposter(journalposterForBrukerRequest: JournalposterForBrukerRequest): List<Journalpost> {
        return postForEntity<Ressurs<List<Journalpost>>>(journalPostUri, journalposterForBrukerRequest).data
            ?: error("Kunne ikke journalposter for for ${journalposterForBrukerRequest.brukerId.id}")
    }

    fun arkiverDokument(arkiverDokumentRequest: ArkiverDokumentRequest, saksbehandler: String?): ArkiverDokumentResponse {
        return postForEntity<Ressurs<ArkiverDokumentResponse>>(
            URI.create("$dokarkivUri/v4"),
            arkiverDokumentRequest,
            headerMedSaksbehandler(saksbehandler),
        ).data
            ?: error("Kunne ikke arkivere dokument med fagsakid ${arkiverDokumentRequest.fagsakId}")
    }

    fun distribuerBrev(journalpostId: String, distribusjonstype: Distribusjonstype): String {
        logger.info("Kaller dokdist-tjeneste for journalpost=$journalpostId")

        val journalpostRequest = DistribuerJournalpostRequest(
            journalpostId = journalpostId,
            bestillendeFagsystem = Fagsystem.Dagpenger,
            dokumentProdApp = "DP_SAK",
            distribusjonstype = distribusjonstype,
        )

        return postForEntity<Ressurs<String>>(
            distribuerDokumentUri,
            journalpostRequest,
            HttpHeaders().medContentTypeJsonUTF8(),
        ).getDataOrThrow()
    }

    private fun headerMedSaksbehandler(saksbehandler: String?): HttpHeaders {
        val httpHeaders = HttpHeaders()
        if (saksbehandler != null) {
            httpHeaders.set(NavHttpHeaders.NAV_USER_ID.asString(), saksbehandler)
        }
        return httpHeaders
    }
}