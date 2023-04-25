package no.nav.dagpenger.iverksett.konsumenter.økonomi

import no.nav.dagpenger.iverksett.kontrakter.oppdrag.GrensesnittavstemmingRequest
import no.nav.dagpenger.iverksett.kontrakter.oppdrag.KonsistensavstemmingUtbetalingsoppdrag
import no.nav.dagpenger.iverksett.kontrakter.oppdrag.OppdragId
import no.nav.dagpenger.iverksett.kontrakter.oppdrag.Utbetalingsoppdrag
import no.nav.dagpenger.iverksett.kontrakter.simulering.DetaljertSimuleringResultat
import no.nav.familie.http.client.AbstractPingableRestClient
import no.nav.familie.kontrakter.felles.Ressurs
import no.nav.familie.kontrakter.felles.getDataOrThrow
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestOperations
import org.springframework.web.util.UriComponentsBuilder
import java.net.URI
import java.util.*

@Service
class OppdragClient(
    @Value("\${DP_OPPDRAG_API_URL}")
    private val dagepngerOppdragUri: URI,
    @Qualifier("azure")
    restOperations: RestOperations,
) : AbstractPingableRestClient(restOperations, "dp.oppdrag") {

    private val postOppdragUri: URI = UriComponentsBuilder.fromUri(dagepngerOppdragUri).pathSegment("oppdrag").build().toUri()

    private val getStatusUri: URI = UriComponentsBuilder.fromUri(dagepngerOppdragUri).pathSegment("status").build().toUri()

    private val grensesnittavstemmingUri: URI =
        UriComponentsBuilder.fromUri(dagepngerOppdragUri).pathSegment("grensesnittavstemming").build().toUri()

    private val konsistensavstemmingUri: URI =
        UriComponentsBuilder.fromUri(dagepngerOppdragUri).pathSegment("konsistensavstemming").build().toUri()

    private val postSimuleringUri: URI =
        UriComponentsBuilder.fromUri(dagepngerOppdragUri).pathSegment("simulering/v1").build().toUri()

    fun iverksettOppdrag(utbetalingsoppdrag: Utbetalingsoppdrag): String {
        return postForEntity(postOppdragUri, utbetalingsoppdrag)
    }

    fun hentStatus(oppdragId: OppdragId): OppdragStatusMedMelding {
        return postForEntity(getStatusUri, oppdragId)
    }

    fun grensesnittavstemming(grensesnittavstemmingRequest: GrensesnittavstemmingRequest): String {
        return postForEntity(grensesnittavstemmingUri, grensesnittavstemmingRequest)
    }

    fun konsistensavstemming(
        konsistensavstemmingUtbetalingsoppdrag: KonsistensavstemmingUtbetalingsoppdrag,
        sendStartmelding: Boolean = true,
        sendAvsluttmelding: Boolean = true,
        transaksjonId: UUID? = null,
    ): String {
        val url = UriComponentsBuilder.fromUri(konsistensavstemmingUri)
            .queryParam("sendStartmelding", sendStartmelding)
            .queryParam("sendAvsluttmelding", sendAvsluttmelding)
            .queryParam("transaksjonId", transaksjonId.toString())
            .build().toUri()
        return postForEntity<Ressurs<String>>(url, konsistensavstemmingUtbetalingsoppdrag).getDataOrThrow()
    }

    fun hentSimuleringsresultat(utbetalingsoppdrag: Utbetalingsoppdrag): DetaljertSimuleringResultat {
        return postForEntity<Ressurs<DetaljertSimuleringResultat>>(postSimuleringUri, utbetalingsoppdrag).getDataOrThrow()
    }

    override val pingUri = postOppdragUri

    override fun ping() {
        operations.optionsForAllow(pingUri)
    }
}
