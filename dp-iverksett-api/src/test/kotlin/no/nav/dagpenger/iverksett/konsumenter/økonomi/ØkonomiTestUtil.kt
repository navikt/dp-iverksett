package no.nav.dagpenger.iverksett.konsumenter.økonomi

import no.nav.dagpenger.iverksett.kontrakter.iverksett.AndelTilkjentYtelse
import no.nav.dagpenger.iverksett.kontrakter.felles.Datoperiode
import no.nav.dagpenger.iverksett.kontrakter.iverksett.DatoperiodeDto
import no.nav.dagpenger.iverksett.kontrakter.iverksett.UtbetalingDto
import no.nav.dagpenger.kontrakter.utbetaling.Ferietilllegg
import no.nav.dagpenger.kontrakter.utbetaling.StønadType
import java.time.LocalDate
import java.util.UUID

fun lagAndelTilkjentYtelse(
    beløp: Int,
    fraOgMed: LocalDate,
    tilOgMed: LocalDate,
    periodeId: Long? = null,
    forrigePeriodeId: Long? = null,
    kildeBehandlingId: UUID? = UUID.randomUUID(),
    inntekt: Int = 0,
    samordningsfradrag: Int = 0,
    inntektsreduksjon: Int = 0,
    stønadstype: StønadType = StønadType.DAGPENGER_ARBEIDSSOKER_ORDINAER,
    ferietilllegg: Ferietilllegg? = null,
) =
    AndelTilkjentYtelse(
        beløp = beløp,
        periode = Datoperiode(fraOgMed, tilOgMed),
        inntekt = inntekt,
        samordningsfradrag = samordningsfradrag,
        inntektsreduksjon = inntektsreduksjon,
        periodeId = periodeId,
        forrigePeriodeId = forrigePeriodeId,
        kildeBehandlingId = kildeBehandlingId,
        stønadstype = stønadstype,
        ferietilllegg = ferietilllegg,
    )

fun lagAndelTilkjentYtelseDto(
    beløp: Int,
    fraOgMed: LocalDate = LocalDate.of(2021, 1, 1),
    tilOgMed: LocalDate = LocalDate.of(2021, 1, 31),
    kildeBehandlingId: UUID = UUID.randomUUID(),
    inntekt: Int = 0,
    samordningsfradrag: Int = 0,
    inntektsreduksjon: Int = 0,
) =
    UtbetalingDto(
        beløp = beløp,
        periode = DatoperiodeDto(fraOgMed, tilOgMed),
        fraOgMedDato = fraOgMed,
        tilOgMedDato = tilOgMed,
        inntekt = inntekt,
        samordningsfradrag = samordningsfradrag,
        inntektsreduksjon = inntektsreduksjon,
        kildeBehandlingId = kildeBehandlingId,
    )
