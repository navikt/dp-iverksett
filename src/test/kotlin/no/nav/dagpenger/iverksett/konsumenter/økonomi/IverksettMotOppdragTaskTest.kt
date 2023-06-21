package no.nav.dagpenger.iverksett.konsumenter.økonomi

import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.slot
import io.mockk.verify
import no.nav.dagpenger.iverksett.api.IverksettingService
import no.nav.dagpenger.iverksett.api.domene.IverksettResultat
import no.nav.dagpenger.iverksett.api.tilstand.IverksettResultatService
import no.nav.dagpenger.iverksett.infrastruktur.transformer.toDomain
import no.nav.dagpenger.iverksett.util.lagForrigeIverksetting
import no.nav.dagpenger.iverksett.util.opprettIverksettDto
import no.nav.dagpenger.kontrakter.felles.Fagsystem
import no.nav.dagpenger.kontrakter.oppdrag.Utbetalingsoppdrag
import no.nav.familie.prosessering.domene.Task
import no.nav.familie.prosessering.internal.TaskService
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.Properties
import java.util.UUID

internal class IverksettMotOppdragTaskTest {

    private val oppdragClient = mockk<OppdragClient>()
    val taskService = mockk<TaskService>()
    val iverksettingService = mockk<IverksettingService>()
    val iverksettResultatService = mockk<IverksettResultatService>()
    val behandlingId: UUID = UUID.randomUUID()
    val sakId: UUID = UUID.randomUUID()
    private val iverksettMotOppdragTask =
        IverksettMotOppdragTask(
            iverksettingService = iverksettingService,
            oppdragClient = oppdragClient,
            taskService = taskService,
            iverksettResultatService = iverksettResultatService,
        )

    @Test
    internal fun `skal sende utbetaling til oppdrag`() {
        val oppdragSlot = slot<Utbetalingsoppdrag>()
        every { iverksettingService.hentIverksetting(any()) } returns
            opprettIverksettDto(behandlingId, sakId).toDomain()
        every { oppdragClient.iverksettOppdrag(capture(oppdragSlot)) } returns "abc"
        every { iverksettResultatService.oppdaterTilkjentYtelseForUtbetaling(behandlingId, any()) } returns Unit
        every { iverksettResultatService.hentTilkjentYtelse(any<UUID>()) } returns null
        iverksettMotOppdragTask.doTask(Task(IverksettMotOppdragTask.TYPE, behandlingId.toString(), Properties()))
        verify(exactly = 1) { oppdragClient.iverksettOppdrag(any()) }
        verify(exactly = 1) { iverksettResultatService.oppdaterTilkjentYtelseForUtbetaling(behandlingId, any()) }
        assertThat(oppdragSlot.captured.fagSystem).isEqualTo(Fagsystem.Dagpenger)
        assertThat(oppdragSlot.captured.kodeEndring).isEqualTo(Utbetalingsoppdrag.KodeEndring.NY)
    }

    @Test
    internal fun `skal ikke iverksette utbetaling til oppdrag når det ikke er noen utbetalinger`() {
        every { iverksettingService.hentIverksetting(any()) } returns
            opprettIverksettDto(behandlingId, sakId).toDomain()
        every { iverksettResultatService.oppdaterTilkjentYtelseForUtbetaling(behandlingId, any()) } just runs
        every { iverksettResultatService.hentTilkjentYtelse(any<UUID>()) } returns null
        every { iverksettingService.hentIverksetting(any()) }
            .returns(opprettIverksettDto(behandlingId, sakId, andelsbeløp = 0).toDomain())
        iverksettMotOppdragTask.doTask(Task(IverksettMotOppdragTask.TYPE, behandlingId.toString(), Properties()))
        verify(exactly = 1) { iverksettResultatService.oppdaterTilkjentYtelseForUtbetaling(behandlingId, any()) }
        verify(exactly = 0) { oppdragClient.iverksettOppdrag(any()) }
    }

    @Test
    internal fun `skal opprette ny task når den er ferdig`() {
        val taskSlot = slot<Task>()
        val task = Task(IverksettMotOppdragTask.TYPE, behandlingId.toString(), Properties())
        every { iverksettingService.hentIverksetting(any()) } returns
            opprettIverksettDto(behandlingId, sakId).toDomain()
        every { taskService.save(capture(taskSlot)) } returns task
        iverksettMotOppdragTask.onCompletion(task)
        assertThat(taskSlot.captured.payload).isEqualTo(behandlingId.toString())
        assertThat(taskSlot.captured.type).isEqualTo(VentePåStatusFraØkonomiTask.TYPE)
    }

    @Test
    internal fun `skal feile hvis det er inkonsistens mellom ny og forrige iverksetting`() {
        val forrigeBehandlingId = UUID.randomUUID()
        val forrigeIverksetting = lagForrigeIverksetting(forrigeBehandlingId)
        // Iverksetting som sier at forrige iverksetting hadde utbetalinger
        val iverksetting = opprettIverksettDto(behandlingId, sakId).copy(
            forrigeIverksetting = forrigeIverksetting,
        )

        every { iverksettingService.hentIverksetting(any()) } returns
            iverksetting.toDomain()
        every { iverksettResultatService.hentIverksettResultat(forrigeBehandlingId) } returns
            // IverksettResultat som sier forrige iverksetting IKKE hadde utbetalinger (er tom)
            IverksettResultat(forrigeBehandlingId)

        Assertions.assertThatExceptionOfType(IllegalStateException::class.java).isThrownBy {
            iverksettMotOppdragTask.doTask(Task(IverksettMotOppdragTask.TYPE, behandlingId.toString(), Properties()))
        }.withMessage("Lagret forrige tilkjent ytelse stemmer ikke med mottatt forrige tilkjent ytelse")
    }
}
