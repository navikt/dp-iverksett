package no.nav.dagpenger.iverksett.kontrakter.iverksett

import no.nav.dagpenger.iverksett.kontrakter.felles.TilkjentYtelseStatus
import no.nav.dagpenger.kontrakter.utbetaling.StønadType
import no.nav.dagpenger.kontrakter.utbetaling.Utbetalingsoppdrag
import java.time.LocalDate
import java.util.UUID

data class TilkjentYtelse(
    val id: UUID = UUID.randomUUID(),
    val utbetalingsoppdrag: Utbetalingsoppdrag? = null,
    val status: TilkjentYtelseStatus = TilkjentYtelseStatus.IKKE_KLAR,
    val andelerTilkjentYtelse: List<AndelTilkjentYtelse>,
    val sisteAndelIKjede: AndelTilkjentYtelse? = null,
    val startdato: LocalDate,
) {

    fun toMedMetadata(
        saksbehandlerId: String,
        stønadType: StønadType,
        sakId: UUID,
        personIdent: String,
        behandlingId: UUID,
        vedtaksdato: LocalDate,
    ): TilkjentYtelseMedMetaData {
        return TilkjentYtelseMedMetaData(
            tilkjentYtelse = this,
            saksbehandlerId = saksbehandlerId,
            stønadstype = stønadType,
            sakId = sakId,
            personIdent = personIdent,
            behandlingId = behandlingId,
            vedtaksdato = vedtaksdato,
        )
    }
}

fun TilkjentYtelse?.stemmerMed(annenTilkjentYtelse: TilkjentYtelse?): Boolean {
    val andeler = lagNormaliserteAndeler()
    val andreAndeler = annenTilkjentYtelse.lagNormaliserteAndeler()

    return andeler == andreAndeler
}

private fun TilkjentYtelse?.lagNormaliserteAndeler(): List<AndelTilkjentYtelse> {
    return this?.andelerTilkjentYtelse
        ?.sortedBy { it.periode.fom }
        ?.map {
            AndelTilkjentYtelse(
                beløp = it.beløp,
                periode = it.periode,
                inntekt = it.inntekt,
                stønadstype = it.stønadstype,
                samordningsfradrag = it.samordningsfradrag,
                inntektsreduksjon = it.inntektsreduksjon,
                ferietillegg = it.ferietillegg,
            )
        } ?: emptyList()
}
