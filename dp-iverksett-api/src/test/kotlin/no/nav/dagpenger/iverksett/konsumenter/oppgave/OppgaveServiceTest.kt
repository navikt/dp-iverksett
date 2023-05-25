package no.nav.dagpenger.iverksett.konsumenter.oppgave

import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.slot
import io.mockk.unmockkAll
import io.mockk.verify
import no.nav.dagpenger.iverksett.api.IverksettingRepository
import no.nav.dagpenger.iverksett.kontrakter.iverksett.IverksettDagpenger
import no.nav.dagpenger.iverksett.kontrakter.iverksett.VedtaksperiodeDagpenger
import no.nav.dagpenger.iverksett.infrastruktur.FamilieIntegrasjonerClient
import no.nav.dagpenger.iverksett.infrastruktur.repository.findByIdOrThrow
import no.nav.dagpenger.iverksett.kontrakter.felles.BehandlingType
import no.nav.dagpenger.iverksett.kontrakter.felles.BehandlingÅrsak
import no.nav.dagpenger.iverksett.kontrakter.felles.Datoperiode
import no.nav.dagpenger.iverksett.kontrakter.felles.Vedtaksresultat
import no.nav.dagpenger.iverksett.kontrakter.iverksett.AktivitetType
import no.nav.dagpenger.iverksett.kontrakter.iverksett.VedtaksperiodeType
import no.nav.dagpenger.iverksett.lagIverksett
import no.nav.dagpenger.iverksett.lagIverksettData
import no.nav.dagpenger.iverksett.util.mockFeatureToggleService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.util.UUID

internal class OppgaveServiceTest {

    val iverksettRepository = mockk<IverksettingRepository>()
    val familieIntegrasjonerClient = mockk<FamilieIntegrasjonerClient>()
    val oppgaveClient = mockk<OppgaveClient>()
    val featureToggleService = mockFeatureToggleService()
    val oppgaveService =
        OppgaveService(oppgaveClient, familieIntegrasjonerClient, iverksettRepository, featureToggleService)

    @BeforeEach
    internal fun init() {
        mockkObject(OppgaveUtil)
        mockkObject(OppgaveBeskrivelse)
        every { familieIntegrasjonerClient.hentBehandlendeEnhetForOppfølging(any()) } returns mockk()
        every { oppgaveClient.opprettOppgave(any()) } returns 0L
        every { OppgaveUtil.opprettOppgaveRequest(any(), any(), any(), any(), any(), any(), any()) } returns mockk()
    }

    @AfterEach
    internal fun tearDown() {
        unmockkAll()
    }

    @Test
    internal fun `innvilget førstegangsbehandling, forvent skalOpprette true`() {
        val iverksett = lagIverksettData(
            behandlingType = BehandlingType.FØRSTEGANGSBEHANDLING,
            vedtaksresultat = Vedtaksresultat.INNVILGET,
            vedtaksperioder = emptyList(),
        )
        assertThat(oppgaveService.skalOppretteVurderHenvendelseOppgave(iverksett)).isTrue
    }

    @Test
    internal fun `revurdering opphørt, forvent skalOpprette true`() {
        val iverksett = lagIverksettData(
            behandlingType = BehandlingType.REVURDERING,
            vedtaksresultat = Vedtaksresultat.OPPHØRT,
            vedtaksperioder = emptyList(),
        )

        assertThat(oppgaveService.skalOppretteVurderHenvendelseOppgave(iverksett)).isTrue
    }

    @Test
    internal fun `revurdering avslått, forvent skalOpprette false`() {
        val iverksettData = lagIverksettData(
            UUID.randomUUID(),
            BehandlingType.REVURDERING,
            Vedtaksresultat.AVSLÅTT,
            emptyList(),
        )
        every { iverksettRepository.findByIdOrThrow(any()) } returns lagIverksett(iverksettData)
        assertThat(oppgaveService.skalOppretteVurderHenvendelseOppgave(iverksettData)).isFalse
    }

    @Test
    internal fun `revurdering innvilget med kun aktivitetsendring, forvent skalOpprette true`() {
        val iverksett = lagIverksettData(
            UUID.randomUUID(),
            BehandlingType.REVURDERING,
            Vedtaksresultat.INNVILGET,
            listOf(vedtaksPeriode(aktivitet = AktivitetType.FORSØRGER_I_ARBEID)),
        )
        val forrigeBehandlingIverksett = lagIverksettData(
            UUID.randomUUID(),
            BehandlingType.REVURDERING,
            Vedtaksresultat.INNVILGET,
            listOf(vedtaksPeriode(aktivitet = AktivitetType.IKKE_AKTIVITETSPLIKT)),
        )
        every { iverksettRepository.findByIdOrThrow(any()) } returns lagIverksett(forrigeBehandlingIverksett)
        assertThat(oppgaveService.skalOppretteVurderHenvendelseOppgave(iverksett)).isTrue()
    }

    @Test
    internal fun `revurdering innvilget, forrige er opphørt skal opprette opppgave`() {
        val iverksett = lagIverksettData(
            UUID.randomUUID(),
            BehandlingType.REVURDERING,
            Vedtaksresultat.INNVILGET,
            listOf(vedtaksPeriode(aktivitet = AktivitetType.FORSØRGER_I_ARBEID)),
        )
        val forrigeBehandlingIverksett = lagIverksettData(
            UUID.randomUUID(),
            BehandlingType.REVURDERING,
            Vedtaksresultat.OPPHØRT,
            emptyList(),
        )
        every { iverksettRepository.findByIdOrThrow(any()) } returns lagIverksett(forrigeBehandlingIverksett)
        assertThat(oppgaveService.skalOppretteVurderHenvendelseOppgave(iverksett)).isTrue()
    }

    @Test
    internal fun `revurdering innvilget, forrige behandling opphørt og uten perioder, forvent skalOpprette true`() {
        val iverksett = lagIverksettData(
            UUID.randomUUID(),
            BehandlingType.REVURDERING,
            Vedtaksresultat.INNVILGET,
            listOf(vedtaksPeriode(aktivitet = AktivitetType.FORSØRGER_I_ARBEID)),
        )
        val forrigeBehandlingIverksett = lagIverksettData(
            UUID.randomUUID(),
            BehandlingType.REVURDERING,
            Vedtaksresultat.OPPHØRT,
            listOf(vedtaksPeriode(aktivitet = AktivitetType.FORSØRGER_I_ARBEID)),
        )
        every { iverksettRepository.findByIdOrThrow(any()) } returns lagIverksett(forrigeBehandlingIverksett)
        assertThat(oppgaveService.skalOppretteVurderHenvendelseOppgave(iverksett)).isTrue()
    }

    @Test
    internal fun `revurdering innvilget, men avslått f-behandling, forvent skalOpprette true`() {
        val iverksettData = lagIverksettData(
            null,
            BehandlingType.REVURDERING,
            Vedtaksresultat.INNVILGET,
            listOf(vedtaksPeriode(aktivitet = AktivitetType.IKKE_AKTIVITETSPLIKT)),
        )
        every { iverksettRepository.findByIdOrThrow(any()) } returns lagIverksett(iverksettData)
        assertThat(oppgaveService.skalOppretteVurderHenvendelseOppgave(iverksettData)).isTrue()
    }

    @Test
    internal fun `revurdering innvilget, men avslått f-behandling, forvent kall til beskrivelseFørstegangsbehandlingInnvilget`() {
        val iverksett = lagIverksettData(
            null,
            BehandlingType.REVURDERING,
            Vedtaksresultat.INNVILGET,
            listOf(vedtaksPeriode(aktivitet = AktivitetType.IKKE_AKTIVITETSPLIKT)),
        )

        oppgaveService.opprettVurderHenvendelseOppgave(iverksett)
        verify { OppgaveBeskrivelse.beskrivelseFørstegangsbehandlingInnvilget(any(), any()) }
        verify(exactly = 0) { OppgaveBeskrivelse.beskrivelseRevurderingInnvilget(any(), any()) }
    }

    @Test
    internal fun `revurdering innvilget med aktivitetsendring og periodeendring, forvent skalOpprette true`() {
        val iverksett = lagIverksettData(
            UUID.randomUUID(),
            BehandlingType.REVURDERING,
            Vedtaksresultat.INNVILGET,
            listOf(vedtaksPeriode(aktivitet = AktivitetType.FORSØRGER_I_ARBEID)),
        )
        val forrigeBehandlingIverksett = lagIverksettData(
            UUID.randomUUID(),
            BehandlingType.REVURDERING,
            Vedtaksresultat.INNVILGET,
            listOf(
                vedtaksPeriode(
                    aktivitet = AktivitetType.IKKE_AKTIVITETSPLIKT,
                    fraOgMed = LocalDate.now().plusMonths(2),
                    tilOgMed = LocalDate.now().plusMonths(3),
                ),
            ),
        )
        val forrigeBehandlingId = iverksett.behandling.forrigeBehandlingId!!
        every { iverksettRepository.findByIdOrThrow(forrigeBehandlingId) } returns lagIverksett(
            forrigeBehandlingIverksett,
        )
        assertThat(oppgaveService.skalOppretteVurderHenvendelseOppgave(iverksett)).isTrue()

        verify(exactly = 1) { iverksettRepository.findByIdOrThrow(forrigeBehandlingId) }
    }

    @Test
    internal fun `revurdering innvilget med kun periodeendring, forvent skalOpprette true`() {
        val iverksett = lagIverksettData(
            UUID.randomUUID(),
            BehandlingType.REVURDERING,
            Vedtaksresultat.INNVILGET,
            listOf(vedtaksPeriode(aktivitet = AktivitetType.FORSØRGER_I_ARBEID)),
        )
        val forrigeBehandlingIverksett = lagIverksettData(
            UUID.randomUUID(),
            BehandlingType.REVURDERING,
            Vedtaksresultat.INNVILGET,
            listOf(
                vedtaksPeriode(
                    aktivitet = AktivitetType.FORSØRGER_I_ARBEID,
                    fraOgMed = LocalDate.now().plusMonths(2),
                    tilOgMed = LocalDate.now().plusMonths(3),
                ),
            ),
        )
        every { iverksettRepository.findByIdOrThrow(any()) } returns lagIverksett(forrigeBehandlingIverksett)
        assertThat(oppgaveService.skalOppretteVurderHenvendelseOppgave(iverksett)).isTrue()
    }

    @Test
    internal fun `revurdering innvilget med kun endring i fom dato, forvent skalOpprette false`() {
        val iverksett = lagIverksettData(
            UUID.randomUUID(),
            BehandlingType.REVURDERING,
            Vedtaksresultat.INNVILGET,
            listOf(vedtaksPeriode(aktivitet = AktivitetType.FORSØRGER_I_ARBEID)),
        )
        val forrigeBehandlingIverksett = lagIverksettData(
            UUID.randomUUID(),
            BehandlingType.REVURDERING,
            Vedtaksresultat.INNVILGET,
            listOf(
                vedtaksPeriode(
                    aktivitet = AktivitetType.FORSØRGER_I_ARBEID,
                    fraOgMed = LocalDate.now().minusMonths(3),
                ),
            ),
        )
        every { iverksettRepository.findByIdOrThrow(any()) } returns lagIverksett(forrigeBehandlingIverksett)
        assertThat(oppgaveService.skalOppretteVurderHenvendelseOppgave(iverksett)).isFalse()
    }

    @Test
    internal fun `innvilget førstegangsbehandling, forvent kall til beskrivelseFørstegangsbehandlingInnvilget`() {
        val iverksett = lagIverksettData(
            UUID.randomUUID(),
            BehandlingType.FØRSTEGANGSBEHANDLING,
            Vedtaksresultat.INNVILGET,
            listOf(vedtaksPeriode(aktivitet = AktivitetType.FORSØRGER_I_ARBEID)),
        )

        oppgaveService.opprettVurderHenvendelseOppgave(iverksett)
        verify { OppgaveBeskrivelse.beskrivelseFørstegangsbehandlingInnvilget(any(), any()) }
    }

    @Test
    internal fun `avslått førstegangsbehandling, forvent kall til beskrivelseFørstegangsbehandlingAvslått`() {
        val iverksett = lagIverksettData(
            UUID.randomUUID(),
            BehandlingType.FØRSTEGANGSBEHANDLING,
            Vedtaksresultat.AVSLÅTT,
            listOf(vedtaksPeriode(aktivitet = AktivitetType.FORSØRGER_I_ARBEID)),
        )

        oppgaveService.opprettVurderHenvendelseOppgave(iverksett)
        verify { OppgaveBeskrivelse.beskrivelseFørstegangsbehandlingAvslått(any()) }
    }

    @Test
    internal fun `innvilget revurdering, forvent kall til beskrivelseRevurderingInnvilget`() {
        val iverksett = lagIverksettData(
            UUID.randomUUID(),
            BehandlingType.REVURDERING,
            Vedtaksresultat.INNVILGET,
            listOf(vedtaksPeriode(aktivitet = AktivitetType.FORSØRGER_I_ARBEID)),
        )

        oppgaveService.opprettVurderHenvendelseOppgave(iverksett)
        verify { OppgaveBeskrivelse.beskrivelseRevurderingInnvilget(any(), any()) }
    }

    @Test
    internal fun `Hvis iverksetting av sanksjon, lag sanksjonsbeskrivelse på oppgave`() {
        val førsteFebruar23 = LocalDate.of(2023, 2, 1)
        val iverksett = lagIverksettDagpengerSanksjon(førsteFebruar23)

        val oppgavebeskrivelse = oppgaveService.lagOppgavebeskrivelse(iverksett)

        assertThat(oppgavebeskrivelse).isEqualTo("Bruker har fått vedtak om sanksjon 1 mnd: 01 februar 2023")
    }

    @Test
    internal fun `opphørt revurdering, forvent kall til beskrivelseRevurderingOpphørt`() {
        val iverksett = lagIverksettData(
            UUID.randomUUID(),
            BehandlingType.REVURDERING,
            Vedtaksresultat.OPPHØRT,
            listOf(vedtaksPeriode(aktivitet = AktivitetType.FORSØRGER_I_ARBEID)),
            andelsdatoer = listOf(LocalDate.now()),
        )

        oppgaveService.opprettVurderHenvendelseOppgave(iverksett)
        verify { OppgaveBeskrivelse.beskrivelseRevurderingOpphørt(any()) }
    }

    @Test
    internal fun `revurdering opphør, forvent at andel med maks tom dato blir sendt som arg til beskrivelse`() {
        val opphørsdato = slot<LocalDate>()
        val iverksett = lagIverksettData(
            UUID.randomUUID(),
            BehandlingType.REVURDERING,
            Vedtaksresultat.OPPHØRT,
            listOf(vedtaksPeriode(aktivitet = AktivitetType.FORSØRGER_I_ARBEID)),
            andelsdatoer = listOf(LocalDate.now().minusMonths(2), LocalDate.now(), LocalDate.now().minusMonths(1)),
        )

        oppgaveService.opprettVurderHenvendelseOppgave(iverksett)
        verify { OppgaveBeskrivelse.beskrivelseRevurderingOpphørt(capture(opphørsdato)) }
        assertThat(opphørsdato.captured).isEqualTo(LocalDate.now().minusMonths(1))
    }

    @Test
    internal fun `av migreringssak, revurdering opphør, forvent at skalOppretteVurderHendelseOppgave er lik true`() {
        val forrigeBehandlingId = UUID.randomUUID()
        val vedtaksperioder = listOf(vedtaksPeriode(aktivitet = AktivitetType.FORSØRGER_I_ARBEID))
        val iverksett = lagIverksettData(
            forrigeBehandlingId,
            BehandlingType.REVURDERING,
            Vedtaksresultat.OPPHØRT,
            vedtaksperioder,
        )
        val forrigeIverksett = lagIverksettData(
            null,
            BehandlingType.FØRSTEGANGSBEHANDLING,
            Vedtaksresultat.INNVILGET,
            vedtaksperioder,
            erMigrering = true,
        )
        every { iverksettRepository.findByIdOrThrow(forrigeBehandlingId) } returns lagIverksett(forrigeIverksett)
        assertThat(oppgaveService.skalOppretteVurderHenvendelseOppgave(iverksett)).isTrue()
    }

    @Test
    internal fun `revurdering, forrige behandling er gOmregnet, periodetype migrering, forvent skalOppretteVurderHendelseOppgave lik false`() {
        val forrigeBehandlingId = UUID.randomUUID()
        val vedtaksperioder = listOf(vedtaksPeriode(aktivitet = AktivitetType.FORSØRGER_I_ARBEID))
        val forrigeVedtaksperioder = listOf(
            vedtaksPeriode(
                fraOgMed = LocalDate.of(LocalDate.now().year, 5, 1),
                tilOgMed = LocalDate.of(LocalDate.now().year.plus(2), 10, 31),
                aktivitet = AktivitetType.MIGRERING,
                periodeType = VedtaksperiodeType.MIGRERING,
            ),
        )
        val iverksett = lagIverksettData(
            forrigeBehandlingId,
            BehandlingType.REVURDERING,
            Vedtaksresultat.INNVILGET,
            vedtaksperioder = vedtaksperioder,
        )
        val forrigeIverksett = lagIverksettData(
            null,
            BehandlingType.FØRSTEGANGSBEHANDLING,
            Vedtaksresultat.INNVILGET,
            vedtaksperioder = forrigeVedtaksperioder,
            årsak = BehandlingÅrsak.G_OMREGNING,
        )
        every { iverksettRepository.findByIdOrThrow(forrigeBehandlingId) } returns lagIverksett(forrigeIverksett)
        assertThat(oppgaveService.skalOppretteVurderHenvendelseOppgave(iverksett)).isFalse()
    }

    @Test
    internal fun `revurdering, forrige behandling er gOmregnet, periodetype ikke migrering, forvent skalOppretteVurderHendelseOppgave lik true`() {
        val forrigeBehandlingId = UUID.randomUUID()
        val vedtaksperioder = listOf(vedtaksPeriode(aktivitet = AktivitetType.FORSØRGER_I_ARBEID))
        val forrigeVedtaksperioder = listOf(
            vedtaksPeriode(
                fraOgMed = LocalDate.of(LocalDate.now().year, 5, 1),
                tilOgMed = LocalDate.of(LocalDate.now().year.plus(2), 10, 31),
                aktivitet = AktivitetType.FORSØRGER_I_ARBEID,
                periodeType = VedtaksperiodeType.HOVEDPERIODE,
            ),
        )
        val iverksett = lagIverksettData(
            forrigeBehandlingId,
            BehandlingType.REVURDERING,
            Vedtaksresultat.INNVILGET,
            vedtaksperioder = vedtaksperioder,
        )
        val forrigeIverksett = lagIverksettData(
            null,
            BehandlingType.FØRSTEGANGSBEHANDLING,
            Vedtaksresultat.INNVILGET,
            vedtaksperioder = forrigeVedtaksperioder,
        )
        every { iverksettRepository.findByIdOrThrow(forrigeBehandlingId) } returns lagIverksett(forrigeIverksett)
        assertThat(oppgaveService.skalOppretteVurderHenvendelseOppgave(iverksett)).isTrue()
    }

    @Test
    internal fun `av migreringssak, revurdering innvilget, forvent at skalOppretteVurderHendelseOppgave er lik false`() {
        val iverksett = lagIverksettData(
            UUID.randomUUID(),
            BehandlingType.REVURDERING,
            Vedtaksresultat.INNVILGET,
            listOf(vedtaksPeriode(aktivitet = AktivitetType.FORSØRGER_I_ARBEID)),
            andelsdatoer = listOf(LocalDate.now().minusMonths(2), LocalDate.now(), LocalDate.now().minusMonths(1)),
        )
        val forrigeBehandlingIverksett = lagMigreringsIverksetting()
        every { iverksettRepository.findByIdOrThrow(any()) } returns lagIverksett(forrigeBehandlingIverksett)
        assertThat(oppgaveService.skalOppretteVurderHenvendelseOppgave(iverksett)).isFalse()
    }

    @Test
    internal fun `Forrige behandling er migreringssak, iverksettbehandling er av type sanksjon - skal opprette vurder hendelse oppgave`() {
        val iverksett = lagIverksettDagpengerSanksjon()
        val forrigeBehandlingIverksett = lagMigreringsIverksetting()

        every { iverksettRepository.findByIdOrThrow(any()) } returns lagIverksett(forrigeBehandlingIverksett)

        assertThat(oppgaveService.skalOppretteVurderHenvendelseOppgave(iverksett)).isTrue
    }

    private fun lagMigreringsIverksetting() = lagIverksettData(
        UUID.randomUUID(),
        BehandlingType.REVURDERING,
        Vedtaksresultat.INNVILGET,
        listOf(
            vedtaksPeriode(
                aktivitet = AktivitetType.FORSØRGER_I_ARBEID,
                fraOgMed = LocalDate.now().minusMonths(3),
                periodeType = VedtaksperiodeType.MIGRERING,
            ),
        ),
    )

    private fun lagIverksettDagpengerSanksjon(sanksjonsdato: LocalDate = LocalDate.now()): IverksettDagpenger {
        val datoperiode = Datoperiode(fom = sanksjonsdato, tom = sanksjonsdato)
        val vedtaksPeriode = VedtaksperiodeDagpenger(
            periode = datoperiode,
            periodeType = VedtaksperiodeType.SANKSJON,
            aktivitet = AktivitetType.IKKE_AKTIVITETSPLIKT,
        )
        val andeler = listOf(sanksjonsdato.minusMonths(1), sanksjonsdato.plusMonths(1))
        return lagIverksettData(
            UUID.randomUUID(),
            BehandlingType.REVURDERING,
            Vedtaksresultat.INNVILGET,
            listOf(vedtaksPeriode),
            andelsdatoer = andeler,
            årsak = BehandlingÅrsak.SANKSJON_1_MND,
        )
    }

    private fun vedtaksPeriode(
        aktivitet: AktivitetType,
        fraOgMed: LocalDate = LocalDate.now(),
        tilOgMed: LocalDate = LocalDate.now(),
        periodeType: VedtaksperiodeType = VedtaksperiodeType.HOVEDPERIODE,
    ): VedtaksperiodeDagpenger {
        return VedtaksperiodeDagpenger(
            periode = Datoperiode(fraOgMed, tilOgMed),
            aktivitet = aktivitet,
            periodeType = periodeType,
        )
    }
}
