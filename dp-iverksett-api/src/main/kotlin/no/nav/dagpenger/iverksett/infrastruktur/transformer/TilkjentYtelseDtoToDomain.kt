package no.nav.dagpenger.iverksett.infrastruktur.transformer

import no.nav.dagpenger.iverksett.kontrakter.iverksett.TilkjentYtelse
import no.nav.dagpenger.iverksett.kontrakter.iverksett.TilkjentYtelseMedMetaData
import no.nav.dagpenger.iverksett.kontrakter.iverksett.TilkjentYtelseDto
import no.nav.dagpenger.iverksett.kontrakter.iverksett.UtbetalingDto
import no.nav.dagpenger.iverksett.kontrakter.iverksett.UtbetalingerMedMetadataDto
import java.time.LocalDate

fun TilkjentYtelseDto.toDomain(): TilkjentYtelse {
    return TilkjentYtelse(
        andelerTilkjentYtelse = this.utbetalinger.map { it.toDomain() },
        startdato = this.startdato,
    )
}

fun UtbetalingerMedMetadataDto.toDomain(): TilkjentYtelseMedMetaData {
    return TilkjentYtelseMedMetaData(
        tilkjentYtelse = this.utbetalinger.tilTilkjentYtelse() ?: tomTilkjentYtelse(),
        saksbehandlerId = this.saksbehandlerId,
        stønadstype = this.stønadstype,
        sakId = this.sakId,
        personIdent = this.personIdent,
        behandlingId = this.behandlingId,
        vedtaksdato = this.vedtaksdato,
    )
}

fun Iterable<UtbetalingDto>.tilTilkjentYtelse(): TilkjentYtelse? {
    val andeler = this.map { it.toDomain() }
    val startdato = andeler.minOfOrNull { it.periode.fom } ?: LocalDate.now()

    return when (andeler.size) {
        0 -> null
        else -> TilkjentYtelse(andelerTilkjentYtelse = andeler, startdato = startdato)
    }
}

fun tomTilkjentYtelse() = TilkjentYtelse(
    andelerTilkjentYtelse = emptyList(),
    startdato = LocalDate.now(),
)
