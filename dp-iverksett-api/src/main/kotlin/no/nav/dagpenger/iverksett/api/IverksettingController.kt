package no.nav.dagpenger.iverksett.api

import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import no.nav.dagpenger.iverksett.api.domene.Brev
import no.nav.dagpenger.iverksett.kontrakter.iverksett.IverksettDagpenger
import no.nav.dagpenger.iverksett.infrastruktur.advice.ApiFeil
import no.nav.dagpenger.iverksett.infrastruktur.configuration.FeatureToggleConfig
import no.nav.dagpenger.iverksett.infrastruktur.featuretoggle.FeatureToggleService
import no.nav.dagpenger.iverksett.infrastruktur.transformer.toDomain
import no.nav.dagpenger.iverksett.konsumenter.tilbakekreving.validerTilbakekreving
import no.nav.dagpenger.iverksett.kontrakter.felles.VedtakType
import no.nav.dagpenger.iverksett.kontrakter.felles.Vedtaksresultat
import no.nav.dagpenger.iverksett.kontrakter.iverksett.IverksettDagpengerdDto
import no.nav.dagpenger.iverksett.kontrakter.iverksett.IverksettStatus
import no.nav.security.token.support.core.api.ProtectedWithClaims
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.util.UUID

@RestController
@RequestMapping(
    path = ["/api/iverksetting"],
    produces = [MediaType.APPLICATION_JSON_VALUE],
)
@ProtectedWithClaims(issuer = "azuread")
class IverksettingController(
    private val iverksettingService: IverksettingService,
    private val featureToggleService: FeatureToggleService,
) {
    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    @Tag(name = "Iverksetting")
    @ApiResponse(responseCode = "202", description = "iverksetting er mottatt")
    @ApiResponse(responseCode = "400", description = "ugyldig iverksetting")
    fun iverksettUtenBrev(
        @RequestBody iverksettDto: IverksettDagpengerdDto,
    ): ResponseEntity<Void> {
        val iverksett = iverksettDto.toDomain()
        valider(iverksett)
        validerUtenBrev(iverksett)
        iverksettingService.startIverksetting(iverksett, null)
        return ResponseEntity.accepted().build()
    }

    @PostMapping(consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    @Tag(name = "Iverksetting")
    @ApiResponse(responseCode = "202", description = "iverksetting er mottatt")
    @ApiResponse(responseCode = "400", description = "ugyldig iverksetting")
    fun iverksett(
        @RequestPart("data") iverksettDto: IverksettDagpengerdDto,
        @RequestPart("fil", required = false) fil: MultipartFile?,
    ): ResponseEntity<Void> {
        val brev = fil?.let { opprettBrev(it) }

        val iverksett = iverksettDto.toDomain()
        valider(iverksett)
        validerBrev(iverksett, brev)
        iverksettingService.startIverksetting(iverksett, brev)
        return ResponseEntity.accepted().build()
    }

    @GetMapping("{behandlingId}/status", produces = ["application/json"])
    @Tag(name = "Iverksetting")
    fun hentStatus(@PathVariable behandlingId: UUID): ResponseEntity<IverksettStatus> {
        val status = iverksettingService.utledStatus(behandlingId)
        return status?.let { ResponseEntity(status, HttpStatus.OK) } ?: ResponseEntity(null, HttpStatus.NOT_FOUND)
    }

    @PostMapping("/start-grensesnittavstemming")
    fun startGrensesnittavstemming(): ResponseEntity<Void> {
        iverksettingService.lagreGrensesnittavstemmingTask()
        return ResponseEntity.accepted().build()
    }

    private fun opprettBrev(fil: MultipartFile): Brev {
        return Brev(fil.bytes)
    }

    private fun validerBrev(iverksettData: IverksettDagpenger, brev: Brev?) {
        when (brev) {
            null -> validerUtenBrev(iverksettData)
            else -> validerSkalHaBrev(iverksettData)
        }
    }

    private fun validerUtenBrev(iverksettData: IverksettDagpenger) {
        if (featureToggleService.isEnabled(FeatureToggleConfig.SKAL_SENDE_BREV, false) &&
            !iverksettData.skalIkkeSendeBrev()
        ) {
            throw ApiFeil(
                "Kan ikke ha iverksetting uten brev når det ikke er en migrering, " +
                    "g-omregning eller korrigering uten brev ",
                HttpStatus.BAD_REQUEST,
            )
        }
    }

    private fun validerSkalHaBrev(iverksettData: IverksettDagpenger) {
        if (featureToggleService.isEnabled(FeatureToggleConfig.SKAL_SENDE_BREV, false) &&
            iverksettData.skalIkkeSendeBrev()
        ) {
            throw ApiFeil(
                "Kan ikke ha iverksetting med brev når det er migrering, g-omregning eller korrigering uten brev",
                HttpStatus.BAD_REQUEST,
            )
        }
    }

    private fun valider(iverksett: IverksettDagpenger) {
        if (iverksett.vedtak.tilkjentYtelse == null &&
            iverksett.vedtak.vedtaksresultat != Vedtaksresultat.AVSLÅTT &&
            iverksett.vedtak.vedtakstype != VedtakType.RAMMEVEDTAK
        ) {
            throw ApiFeil(
                "Kan ikke ha iverksetting uten tilkjentYtelse " +
                    "for vedtak med resultat=${iverksett.vedtak.vedtaksresultat}",
                HttpStatus.BAD_REQUEST,
            )
        }
        if (iverksett.vedtak.tilkjentYtelse != null && iverksett.vedtak.vedtaksresultat == Vedtaksresultat.AVSLÅTT) {
            throw ApiFeil(
                "Kan ikke ha iverksetting med tilkjentYtelse " +
                    "for vedtak med resultat=${iverksett.vedtak.vedtaksresultat}",
                HttpStatus.BAD_REQUEST,
            )
        }

        if (!iverksett.vedtak.tilbakekreving.validerTilbakekreving()) {
            throw ApiFeil("Tilbakekreving er ikke gyldig", HttpStatus.BAD_REQUEST)
        }
    }
}
