package no.nav.dagpenger.iverksett.utbetaling.tilstand

import no.nav.dagpenger.iverksett.ServerTest
import no.nav.dagpenger.iverksett.utbetaling.domene.Iverksetting
import no.nav.dagpenger.iverksett.utbetaling.domene.behandlingId
import no.nav.dagpenger.iverksett.utbetaling.domene.sakId
import no.nav.dagpenger.iverksett.utbetaling.lagIverksettingEntitet
import no.nav.dagpenger.iverksett.utbetaling.lagIverksettingsdata
import no.nav.dagpenger.kontrakter.felles.somString
import no.nav.dagpenger.kontrakter.felles.somUUID
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.time.LocalDate
import java.util.UUID

class IverksettingRepositoryTest : ServerTest() {
    @Autowired
    private lateinit var iverksettingRepository: IverksettingRepository

    @Test
    fun `lagre og hent iverksett på fagsakId, forvent likhet`() {
        val iverksettingData: Iverksetting =
            lagIverksettingsdata(
                sakId = UUID.randomUUID(),
                behandlingId = UUID.randomUUID(),
                andelsdatoer = listOf(LocalDate.now(), LocalDate.now().minusDays(15)),
            )

        val fagsakId = iverksettingData.fagsak.fagsakId
        val iverksettListe1 = iverksettingRepository.findByFagsakId(fagsakId.somString)
        assertEquals(0, iverksettListe1.size)

        val iverksett = iverksettingRepository.insert(lagIverksettingEntitet(iverksettingData))

        val iverksettListe2 = iverksettingRepository.findByFagsakId(fagsakId.somString)
        assertEquals(1, iverksettListe2.size)
        assertThat(iverksett).usingRecursiveComparison().isEqualTo(iverksettListe2[0])
    }

    @Test
    fun `lagre og hent iverksett på behandlingId og iverksettingId, forvent likhet`() {
        val tmp: Iverksetting =
            lagIverksettingsdata(
                sakId = UUID.randomUUID(),
                behandlingId = UUID.randomUUID(),
                andelsdatoer = listOf(LocalDate.now(), LocalDate.now().minusDays(15)),
            )
        val iverksettingData = tmp.copy(behandling = tmp.behandling.copy(iverksettingId = "TEST123"))

        val iverksetting =
            iverksettingRepository.findByFagsakAndBehandlingAndIverksetting(
                fagsakId = iverksettingData.sakId.somString,
                behandlingId = iverksettingData.behandlingId.somUUID,
                iverksettingId = iverksettingData.behandling.iverksettingId,
            )
        assertTrue(iverksetting.isEmpty())

        val iverksett = iverksettingRepository.insert(lagIverksettingEntitet(iverksettingData))

        val iverksetting2 =
            iverksettingRepository.findByFagsakAndBehandlingAndIverksetting(
                fagsakId = iverksettingData.sakId.somString,
                behandlingId = iverksettingData.behandlingId.somUUID,
                iverksettingId = iverksettingData.behandling.iverksettingId,
            )
        assertTrue(iverksetting2.isNotEmpty())
        assertEquals(iverksett, iverksetting2.first())
    }

    @Test
    fun `lagre og hent iverksett på behandlingId og tom iverksettingId, forvent likhet`() {
        val iverksettingData: Iverksetting =
            lagIverksettingsdata(
                sakId = UUID.randomUUID(),
                behandlingId = UUID.randomUUID(),
                andelsdatoer = listOf(LocalDate.now(), LocalDate.now().minusDays(15)),
            )

        val iverksetting =
            iverksettingRepository.findByFagsakAndBehandlingAndIverksetting(
                fagsakId = iverksettingData.sakId.somString,
                behandlingId = iverksettingData.behandlingId.somUUID,
                iverksettingId = iverksettingData.behandling.iverksettingId,
            )
        assertTrue(iverksetting.isEmpty())

        val iverksett = iverksettingRepository.insert(lagIverksettingEntitet(iverksettingData))

        val iverksetting2 =
            iverksettingRepository.findByFagsakAndBehandlingAndIverksetting(
                fagsakId = iverksettingData.sakId.somString,
                behandlingId = iverksettingData.behandlingId.somUUID,
                iverksettingId = iverksettingData.behandling.iverksettingId,
            )
        assertTrue(iverksetting2.isNotEmpty())
        assertEquals(iverksett, iverksetting2.first())
    }

    @Test
    fun `lagre og hent iverksett på fagsakId, behandlingId og tom iverksettingId, forvent 1 iverksetting per fagsak`() {
        val sakId1 = UUID.randomUUID()
        val sakId2 = UUID.randomUUID()
        val behandlingId = UUID.randomUUID()
        val iverksettingData1 =
            lagIverksettingsdata(
                sakId = sakId1,
                behandlingId = behandlingId,
                andelsdatoer = listOf(LocalDate.now(), LocalDate.now().minusDays(15)),
            )

        val iverksettingData2 =
            lagIverksettingsdata(
                sakId = sakId2,
                behandlingId = behandlingId,
                andelsdatoer = listOf(LocalDate.now(), LocalDate.now().minusDays(15)),
            )

        val iverksett1 = iverksettingRepository.insert(lagIverksettingEntitet(iverksettingData1))
        val iverksett2 = iverksettingRepository.insert(lagIverksettingEntitet(iverksettingData2))

        val iverksetting1 =
            iverksettingRepository.findByFagsakAndBehandlingAndIverksetting(
                fagsakId = iverksettingData1.sakId.somString,
                behandlingId = iverksettingData1.behandlingId.somUUID,
                iverksettingId = iverksettingData1.behandling.iverksettingId,
            )
        val iverksetting2 =
            iverksettingRepository.findByFagsakAndBehandlingAndIverksetting(
                fagsakId = iverksettingData2.sakId.somString,
                behandlingId = iverksettingData2.behandlingId.somUUID,
                iverksettingId = iverksettingData2.behandling.iverksettingId,
            )

        assertEquals(1, iverksetting1.size)
        assertEquals(iverksett1, iverksetting1.first())
        assertEquals(1, iverksetting2.size)
        assertEquals(iverksett2, iverksetting2.first())
    }
}
