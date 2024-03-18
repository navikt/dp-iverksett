package no.nav.dagpenger.iverksett.utbetaling.tilstand

import no.nav.dagpenger.iverksett.utbetaling.domene.IverksettingEntitet
import no.nav.dagpenger.iverksett.utbetaling.domene.behandlingId
import no.nav.dagpenger.iverksett.utbetaling.task.IverksettMotOppdragTask
import no.nav.dagpenger.iverksett.utbetaling.task.tilTaskPayload
import no.nav.dagpenger.kontrakter.felles.objectMapper
import no.nav.familie.prosessering.internal.TaskService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class SettMottattTidspunktForEksisterendeIverksettinger(
    private val iverksettingRepository: IverksettingRepository,
    private val taskService: TaskService,
) {
    fun migrer(): Int {
        logger.info("Starter jobb for å sette mottatt tidspunkt på iverksettinger som mangler det")
        val iverksettingerUtenTimestamp = iverksettingRepository.findByEmptyMottattTidspunkt()
        logger.info("Fant ${iverksettingerUtenTimestamp.size} iverksettinger uten mottatt tidspunkt")
        var antallmigrerte = 0
        iverksettingerUtenTimestamp.forEach {
            logger.info("Finner task-tidspunkt for iverksetting ${it.data}")
            val taskTimestamp = hentOpprettetTidspunktForIverksettingstask(it)
            iverksettingRepository.settMottattTidspunktForIverksetting(it.copy(mottattTidspunkt = taskTimestamp))
            logger.info("Satt mottatt tidspunkt $taskTimestamp for iverksetting ${it.behandlingId}")
            antallmigrerte += 1
        }
        logger.info("Fullført jobb for å sette manglende mottatt tidspunkt")
        return antallmigrerte
    }

    private fun hentOpprettetTidspunktForIverksettingstask(iverksetting: IverksettingEntitet): LocalDateTime? {
        logger.info("Finner task for iverksetting")
        val iverksettTaskForIverksetting =
            taskService.finnTaskMedPayloadOgType(
                payload = objectMapper.writeValueAsString(iverksetting.data.tilTaskPayload()),
                type = IverksettMotOppdragTask.TYPE,
            ) ?: taskService.finnTaskMedPayloadOgType(
                payload = objectMapper.writeValueAsString(iverksetting.data.behandlingId),
                type = IverksettMotOppdragTask.TYPE,
            ) ?: taskService.finnTaskMedPayloadOgType(
                payload = iverksetting.behandlingId.toString(),
                type = IverksettMotOppdragTask.TYPE,
            )

        return iverksettTaskForIverksetting?.opprettetTid
    }

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }
}