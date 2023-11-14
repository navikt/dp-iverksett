package no.nav.dagpenger.iverksett.infrastruktur.transformer

import java.time.LocalDate
import no.nav.dagpenger.iverksett.api.domene.Behandlingsdetaljer
import no.nav.dagpenger.iverksett.api.domene.Fagsakdetaljer
import no.nav.dagpenger.iverksett.api.domene.Iverksett
import no.nav.dagpenger.iverksett.api.domene.Søker
import no.nav.dagpenger.iverksett.api.domene.Vedtaksdetaljer
import no.nav.dagpenger.iverksett.api.domene.Vedtaksperiode
import no.nav.dagpenger.kontrakter.felles.Datoperiode
import no.nav.dagpenger.kontrakter.felles.SakIdentifikator
import no.nav.dagpenger.kontrakter.iverksett.ForrigeIverksettingDto
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
        vedtakstype = this.vedtakstype,
        vedtaksresultat = this.resultat,
        vedtakstidspunkt = this.vedtakstidspunkt,
        saksbehandlerId = this.saksbehandlerId,
        beslutterId = this.beslutterId,
        enhet = this.enhet,
        tilkjentYtelse = this.utbetalinger.tilTilkjentYtelse(),
        vedtaksperioder = this.vedtaksperioder.map { it.toDomain() }
    )
}

fun IverksettDto.toDomain(): Iverksett {
    return Iverksett(
        fagsak = this.tilFagsak(),
        søker = this.personIdent.tilSøker(),
        behandling = this.tilBehandling(),
        vedtak = this.vedtak.toDomain(),
        forrigeIverksetting = this.tilForrigeIverksetting(),
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


fun String.tilSøker(): Søker = Søker(personIdent = this)

fun IverksettDto.tilBehandling(): Behandlingsdetaljer = Behandlingsdetaljer(
    behandlingId = this.behandlingId,
    forrigeBehandlingId = this.forrigeIverksetting?.behandlingId,
)

fun ForrigeIverksettingDto.tilBehandling(): Behandlingsdetaljer = Behandlingsdetaljer(
    behandlingId = this.behandlingId,
)

fun IverksettDto.tilForrigeIverksetting(): Iverksett? {
    return when (this.forrigeIverksetting) {
        null -> null
        else -> Iverksett(
            fagsak = this.tilFagsak(),
            søker = this.personIdent.tilSøker(),
            behandling = this.forrigeIverksetting!!.tilBehandling(),
            vedtak = this.forrigeIverksetting!!.tilVedtaksdetaljer(),
            forrigeIverksetting = null,
        )
    }
}