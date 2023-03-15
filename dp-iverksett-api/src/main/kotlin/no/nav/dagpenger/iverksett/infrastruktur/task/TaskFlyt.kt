package no.nav.dagpenger.iverksett.infrastruktur.task

import no.nav.dagpenger.iverksett.arbeidsoppfolging.SendVedtakTilArbeidsoppfølgingTask
import no.nav.dagpenger.iverksett.arena.SendFattetVedtakTilArenaTask
import no.nav.dagpenger.iverksett.brev.DistribuerVedtaksbrevTask
import no.nav.dagpenger.iverksett.brev.JournalførVedtaksbrevTask
import no.nav.dagpenger.iverksett.oppgave.OpprettOppfølgingsOppgaveForOvergangsstønadTask
import no.nav.dagpenger.iverksett.tilbakekreving.OpprettTilbakekrevingTask
import no.nav.dagpenger.iverksett.vedtak.PubliserVedtakTilKafkaTask
import no.nav.dagpenger.iverksett.vedtakstatistikk.VedtakstatistikkTask
import no.nav.dagpenger.iverksett.økonomi.IverksettMotOppdragTask
import no.nav.dagpenger.iverksett.økonomi.VentePåStatusFraØkonomiTask
import no.nav.familie.prosessering.domene.Task
import java.time.LocalDateTime

class TaskType(
    val type: String,
    val triggerTidAntallSekunderFrem: Long? = null,
)

fun hovedflyt() = listOf(
    TaskType(OpprettTilbakekrevingTask.TYPE),
    TaskType(IverksettMotOppdragTask.TYPE),
    TaskType(VentePåStatusFraØkonomiTask.TYPE, 20), // går ikke videre ved migrering//korrigering_uten_brev
    TaskType(JournalførVedtaksbrevTask.TYPE),
    TaskType(DistribuerVedtaksbrevTask.TYPE),
)

fun publiseringsflyt() = listOf(
    TaskType(SendFattetVedtakTilArenaTask.TYPE),
    TaskType(PubliserVedtakTilKafkaTask.TYPE),
    TaskType(SendVedtakTilArbeidsoppfølgingTask.TYPE),
    TaskType(OpprettOppfølgingsOppgaveForOvergangsstønadTask.TYPE),
    TaskType(VedtakstatistikkTask.TYPE),
)

fun TaskType.nesteHovedflytTask() = hovedflyt().zipWithNext().first { this.type == it.first.type }.second
fun TaskType.nestePubliseringsflytTask() = publiseringsflyt().zipWithNext().first { this.type == it.first.type }.second

fun Task.opprettNesteTask(): Task {
    val nesteTask = TaskType(this.type).nesteHovedflytTask()
    return lagTask(nesteTask)
}

fun Task.opprettNestePubliseringTask(erMigrering: Boolean = false): Task {
    val nesteTask = TaskType(this.type).nestePubliseringsflytTask()
    return lagTask(nesteTask)
}

private fun Task.lagTask(nesteTask: TaskType): Task {
    return if (nesteTask.triggerTidAntallSekunderFrem != null) {
        Task(
            type = nesteTask.type,
            payload = this.payload,
            properties = this.metadata,
        ).copy(
            triggerTid = LocalDateTime.now()
                .plusSeconds(nesteTask.triggerTidAntallSekunderFrem),
        )
    } else {
        Task(
            type = nesteTask.type,
            payload = this.payload,
            properties = this.metadata,
        )
    }
}
