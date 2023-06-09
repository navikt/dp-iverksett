package no.nav.dagpenger.iverksett.infrastruktur.transformer

import no.nav.dagpenger.iverksett.api.domene.AndelTilkjentYtelse
import no.nav.dagpenger.kontrakter.felles.Datoperiode
import no.nav.dagpenger.kontrakter.iverksett.UtbetalingDto

fun UtbetalingDto.toDomain(): AndelTilkjentYtelse {
    return AndelTilkjentYtelse(
        beløp = this.beløp ?: this.belopPerDag ?: throw IllegalStateException("Mangler feltet belopPerDag"),
        periode = Datoperiode(this.fraOgMedDato, this.tilOgMedDato),
        stønadstype = this.stønadstype ?: stonadstype,
        ferietillegg = this.ferietillegg,
    )
}