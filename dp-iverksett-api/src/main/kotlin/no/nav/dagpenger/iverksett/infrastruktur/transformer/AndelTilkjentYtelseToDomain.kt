package no.nav.dagpenger.iverksett.infrastruktur.transformer

import no.nav.dagpenger.iverksett.api.domene.AndelTilkjentYtelse
import no.nav.dagpenger.iverksett.kontrakter.iverksett.UtbetalingDto

fun UtbetalingDto.toDomain(): AndelTilkjentYtelse {
    return AndelTilkjentYtelse(
        beløp = this.beløp,
        periode = this.periode,
        inntekt = this.inntekt,
        samordningsfradrag = this.samordningsfradrag,
        inntektsreduksjon = this.inntektsreduksjon,
        kildeBehandlingId = this.kildeBehandlingId,
    )
}
