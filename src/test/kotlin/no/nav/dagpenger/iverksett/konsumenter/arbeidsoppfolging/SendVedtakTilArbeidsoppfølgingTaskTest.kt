package no.nav.dagpenger.iverksett.konsumenter.arbeidsoppfolging

import io.mockk.CapturingSlot
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import no.nav.dagpenger.iverksett.api.IverksettingRepository
import no.nav.dagpenger.iverksett.infrastruktur.repository.findByIdOrThrow
import no.nav.dagpenger.iverksett.infrastruktur.transformer.toDomain
import no.nav.dagpenger.iverksett.infrastruktur.util.opprettIverksettDto
import no.nav.dagpenger.iverksett.konsumenter.oppgave.OpprettOppfølgingsOppgaveForDagpengerTask
import no.nav.dagpenger.iverksett.lagIverksett
import no.nav.dagpenger.kontrakter.felles.StønadType
import no.nav.familie.prosessering.domene.Task
import no.nav.familie.prosessering.internal.TaskService
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.*

class SendVedtakTilArbeidsoppfølgingTaskTest {

    private val taskService = mockk<TaskService>()
    private val iverksettingRepository = mockk<IverksettingRepository>()
    private val arbeidsoppfølgingService = mockk<ArbeidsoppfølgingService>()

    private val task = SendVedtakTilArbeidsoppfølgingTask(taskService, iverksettingRepository, arbeidsoppfølgingService)

    private val taskSlot = CapturingSlot<Task>()

    @BeforeEach
    internal fun setUp() {
        every { iverksettingRepository.findByIdOrThrow(any()) }
            .returns(lagIverksett(opprettIverksettDto(behandlingId = UUID.randomUUID(), sakId = UUID.randomUUID()).toDomain()))
        every { taskService.save(capture(taskSlot)) } answers { firstArg() }
    }

    @Test
    internal fun `doTask - skal publisere vedtak til kafka`() {
        justRun { arbeidsoppfølgingService.sendTilKafka(any()) }

        task.doTask(lagTask())

        verify(exactly = 1) { arbeidsoppfølgingService.sendTilKafka(any()) }
    }

    @Test
    internal fun `onCompletion - skal opprette neste task`() {
        task.onCompletion(lagTask())

        verify(exactly = 1) { taskService.save(any()) }
        Assertions.assertThat(taskSlot.captured.type).isEqualTo(OpprettOppfølgingsOppgaveForDagpengerTask.TYPE)
    }

    @Test
    internal fun `skal mappe stønadstyper`() {
        Assertions.assertThat(StønadType.values().map { it.name }).isEqualTo(StønadType.values().map { it.name })
    }

    private fun lagTask() = Task(SendVedtakTilArbeidsoppfølgingTask.TYPE, UUID.randomUUID().toString())
}
