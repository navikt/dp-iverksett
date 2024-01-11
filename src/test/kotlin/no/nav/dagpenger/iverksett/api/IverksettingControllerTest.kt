package no.nav.dagpenger.iverksett.api

import no.nav.dagpenger.iverksett.ServerTest
import no.nav.dagpenger.iverksett.infrastruktur.advice.Ressurs
import no.nav.dagpenger.iverksett.infrastruktur.util.opprettIverksettDto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.web.client.exchange
import org.springframework.http.*
import java.util.*

class IverksettingControllerTest : ServerTest() {

    private val behandlingId = UUID.randomUUID()
    private val sakId = UUID.randomUUID()

    @Value("\${BESLUTTER_GRUPPE}")
    private lateinit var beslutterGruppe: String

    @BeforeEach
    fun setUp() {
        headers.setBearerAuth(lokalTestToken(grupper = listOf(beslutterGruppe)))
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
    }

    @Test
    internal fun `starte iverksetting gir 202 Accepted`() {
        val iverksettJson = opprettIverksettDto(behandlingId = behandlingId, sakId = sakId)

        val respons: ResponseEntity<Any> = restTemplate.exchange(
            localhostUrl("/api/iverksetting"),
            HttpMethod.POST,
            HttpEntity(iverksettJson, headers),
        )
        assertThat(respons.statusCode.value()).isEqualTo(202)
    }

    @Test
    internal fun `Innvilget rammevedtak uten tilkjent ytelse gir 202 Accepted`() {
        val iverksettJson = opprettIverksettDto(behandlingId = behandlingId, sakId = sakId)
        val rammevedtak = iverksettJson.copy(
            vedtak = iverksettJson.vedtak.copy(
                utbetalinger = emptyList(),
            ),
        )

        val responsRammevedtak: ResponseEntity<Ressurs<Nothing>> = restTemplate.exchange(
            localhostUrl("/api/iverksetting"),
            HttpMethod.POST,
            HttpEntity(rammevedtak, headers),
        )
        assertThat(responsRammevedtak.statusCode.value()).isEqualTo(202)
    }
}
