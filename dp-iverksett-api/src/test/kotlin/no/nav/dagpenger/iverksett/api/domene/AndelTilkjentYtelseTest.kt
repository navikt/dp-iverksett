package no.nav.dagpenger.iverksett.api.domene

import no.nav.dagpenger.iverksett.konsumenter.økonomi.lagAndelTilkjentYtelse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate

internal class AndelTilkjentYtelseTest {

    @Test
    internal fun `utbetalingsgrad - skal runde riktig`() {
        assertThat(
            lagTY(
                beløp = 337,
                inntektsreduksjon = 1000 - 337,
                samordningsfradrag = 0,
            ).utbetalingsgrad(),
        )
            .withFailMessage("Skal runde opp x.7")
            .isEqualTo(34)

        assertThat(
            lagTY(
                beløp = 3349,
                inntektsreduksjon = 10000 - 3349,
                samordningsfradrag = 0,
            ).utbetalingsgrad(),
        )
            .withFailMessage("Skal runde ned under x.5")
            .isEqualTo(33)

        assertThat(
            lagTY(
                beløp = 555,
                inntektsreduksjon = 1000 - 555,
                samordningsfradrag = 0,
            ).utbetalingsgrad(),
        )
            .withFailMessage("Skal runde opp x.5")
            .isEqualTo(56)
    }

    @Test
    internal fun `utbetalingsgrad - skal kalkulere utbetalingsgrad`() {
        assertThat(
            lagTY(
                beløp = 400,
                inntektsreduksjon = 300,
                samordningsfradrag = 300,
            ).utbetalingsgrad(),
        )
            .isEqualTo(40)

        assertThat(
            lagTY(
                beløp = 400,
                inntektsreduksjon = 0,
                samordningsfradrag = 0,
            ).utbetalingsgrad(),
        )
            .isEqualTo(100)

        assertThat(
            lagTY(
                beløp = 1,
                inntektsreduksjon = 50,
                samordningsfradrag = 49,
            ).utbetalingsgrad(),
        )
            .isEqualTo(1)
    }

    private fun lagTY(beløp: Int, inntektsreduksjon: Int = 0, samordningsfradrag: Int = 0) =
        lagAndelTilkjentYtelse(
            beløp = beløp,
            fraOgMed = LocalDate.now(),
            tilOgMed = LocalDate.now(),
            inntektsreduksjon = inntektsreduksjon,
            samordningsfradrag = samordningsfradrag,
        )
}
