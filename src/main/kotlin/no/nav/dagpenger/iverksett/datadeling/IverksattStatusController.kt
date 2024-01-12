package no.nav.dagpenger.iverksett.datadeling

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import no.nav.dagpenger.kontrakter.datadeling.DatadelingRequest
import no.nav.dagpenger.kontrakter.datadeling.DatadelingResponse
import no.nav.dagpenger.kontrakter.iverksett.VedtaksstatusDto
import no.nav.security.token.support.core.api.ProtectedWithClaims
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(
    path = ["/api"],
    produces = [MediaType.APPLICATION_JSON_VALUE],
)
@ProtectedWithClaims(issuer = "azuread")
class IverksattStatusController(
    private val vedtakStatusService: VedtakStatusService,
) {
    @GetMapping("/vedtakstatus/{personId}", produces = [MediaType.APPLICATION_JSON_VALUE])
    @Tag(name = "Datadeling")
    @Operation(summary = "Få vedtaksstatus for person")
    fun hentStatusForPerson(@PathVariable personId: String): ResponseEntity<VedtaksstatusDto> {
        val status = vedtakStatusService.getVedtakStatus(personId)
        return status?.let { ResponseEntity(status, HttpStatus.OK) } ?: ResponseEntity(null, HttpStatus.NOT_FOUND)
    }

    @PostMapping("/dagpengerperioder", consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    @Tag(name = "Datadeling")
    @Operation(summary = "Få vedtaksstatus for person og periode")
    fun hentVedtaksperioderForPersonOgPeriode(@RequestBody request: DatadelingRequest): ResponseEntity<DatadelingResponse> {
        val response = vedtakStatusService.hentVedtaksperioderForPersonOgPeriode(request)
        return ResponseEntity(response, HttpStatus.OK)
    }
}
