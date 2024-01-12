package no.nav.dagpenger.iverksett.utbetaling.domene.transformer

 import java.time.LocalDate
import no.nav.dagpenger.iverksett.utbetaling.domene.Behandlingsdetaljer
import no.nav.dagpenger.iverksett.utbetaling.domene.Fagsakdetaljer
import no.nav.dagpenger.iverksett.utbetaling.domene.Iverksetting
import no.nav.dagpenger.iverksett.utbetaling.domene.Søker
import no.nav.dagpenger.iverksett.utbetaling.domene.Vedtaksdetaljer
import no.nav.dagpenger.iverksett.utbetaling.domene.Vedtaksperiode
import no.nav.dagpenger.kontrakter.felles.Datoperiode
import no.nav.dagpenger.kontrakter.felles.SakIdentifikator
import no.nav.dagpenger.kontrakter.iverksett.IverksettDto
import no.nav.dagpenger.kontrakter.iverksett.VedtaksdetaljerDto
import no.nav.dagpenger.kontrakter.iverksett.VedtaksperiodeDto

fun VedtaksperiodeDto.toDomain(): Vedtaksperiode {
    return Vedtaksperiode(
        periode = Datoperiode(this.fraOgMedDato, this.tilOgMedDato ?: LocalDate.MAX),
        periodeType = this.periodeType,
    )
}

fun VedtaksdetaljerDto.toDomain(): Vedtaksdetaljer {
    return Vedtaksdetaljer(
        vedtaksresultat = this.resultat,
        vedtakstidspunkt = this.vedtakstidspunkt,
        saksbehandlerId = this.saksbehandlerId,
        beslutterId = this.beslutterId,
        brukersNavKontor = this.brukersNavKontor,
        tilkjentYtelse = this.utbetalinger.tilTilkjentYtelse(),
        vedtaksperioder = this.vedtaksperioder.map { it.toDomain() }
    )
}

fun IverksettDto.toDomain(): Iverksetting {
    return Iverksetting(
        fagsak = this.tilFagsak(),
        søker = this.personident.verdi.tilSøker(),
        behandling = this.tilBehandling(),
        vedtak = this.vedtak.toDomain(),
        forrigeIverksettingBehandlingId = this.forrigeIverksetting?.behandlingId,
    )
}

fun IverksettDto.tilFagsak(): Fagsakdetaljer {
    return Fagsakdetaljer(
        fagsakId = this.sakId,
        saksreferanse = this.saksreferanse
    )
}

fun IverksettDto.tilSakIdentifikator(): SakIdentifikator {
    return SakIdentifikator(this.sakId, this.saksreferanse)
}

fun String.tilSøker(): Søker = Søker(personident = this)

fun IverksettDto.tilBehandling(): Behandlingsdetaljer = Behandlingsdetaljer(
    behandlingId = this.behandlingId,
    forrigeBehandlingId = this.forrigeIverksetting?.behandlingId,
)
