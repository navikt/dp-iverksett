package no.nav.dagpenger.iverksett.utbetaling.utbetalingsoppdrag.cucumber

import io.cucumber.datatable.DataTable
import io.cucumber.java.no.Gitt
import io.cucumber.java.no.Når
import io.cucumber.java.no.Så
import no.nav.dagpenger.iverksett.utbetaling.domene.StønadsdataDagpenger
import no.nav.dagpenger.iverksett.utbetaling.domene.StønadsdataTiltakspenger
import no.nav.dagpenger.iverksett.utbetaling.utbetalingsoppdrag.Utbetalingsgenerator
import no.nav.dagpenger.iverksett.utbetaling.utbetalingsoppdrag.cucumber.ValideringUtil.assertSjekkBehandlingIder
import no.nav.dagpenger.iverksett.utbetaling.utbetalingsoppdrag.cucumber.domeneparser.Domenebegrep
import no.nav.dagpenger.iverksett.utbetaling.utbetalingsoppdrag.cucumber.domeneparser.DomenebegrepUtbetalingsoppdrag
import no.nav.dagpenger.iverksett.utbetaling.utbetalingsoppdrag.cucumber.domeneparser.DomeneparserUtil.groupByBehandlingId
import no.nav.dagpenger.iverksett.utbetaling.utbetalingsoppdrag.cucumber.domeneparser.ForventetUtbetalingsoppdrag
import no.nav.dagpenger.iverksett.utbetaling.utbetalingsoppdrag.cucumber.domeneparser.ForventetUtbetalingsperiode
import no.nav.dagpenger.iverksett.utbetaling.utbetalingsoppdrag.cucumber.domeneparser.IdTIlUUIDHolder.behandlingIdTilUUID
import no.nav.dagpenger.iverksett.utbetaling.utbetalingsoppdrag.cucumber.domeneparser.OppdragParser
import no.nav.dagpenger.iverksett.utbetaling.utbetalingsoppdrag.cucumber.domeneparser.OppdragParser.mapAndeler
import no.nav.dagpenger.iverksett.utbetaling.utbetalingsoppdrag.cucumber.domeneparser.parseLong
import no.nav.dagpenger.iverksett.utbetaling.utbetalingsoppdrag.cucumber.domeneparser.parseString
import no.nav.dagpenger.iverksett.utbetaling.utbetalingsoppdrag.cucumber.domeneparser.parseValgfriLong
import no.nav.dagpenger.iverksett.utbetaling.utbetalingsoppdrag.domene.AndelData
import no.nav.dagpenger.iverksett.utbetaling.utbetalingsoppdrag.domene.AndelMedPeriodeId
import no.nav.dagpenger.iverksett.utbetaling.utbetalingsoppdrag.domene.Behandlingsinformasjon
import no.nav.dagpenger.iverksett.utbetaling.utbetalingsoppdrag.domene.BeregnetUtbetalingsoppdrag
import no.nav.dagpenger.iverksett.utbetaling.utbetalingsoppdrag.domene.uten0beløp
import no.nav.dagpenger.kontrakter.felles.Fagsystem
import no.nav.dagpenger.kontrakter.felles.GeneriskId
import no.nav.dagpenger.kontrakter.felles.GeneriskIdSomUUID
import no.nav.dagpenger.kontrakter.felles.StønadTypeDagpenger
import no.nav.dagpenger.kontrakter.felles.StønadTypeTiltakspenger
import no.nav.dagpenger.kontrakter.oppdrag.Utbetalingsoppdrag
import no.nav.dagpenger.kontrakter.oppdrag.Utbetalingsperiode
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.assertThrows
import org.slf4j.LoggerFactory
import java.time.LocalDate
import java.util.UUID

val FAGSAK_ID = GeneriskIdSomUUID(UUID.randomUUID())

class OppdragSteg {
    private val logger = LoggerFactory.getLogger(javaClass)

    private var behandlingsinformasjon = mutableMapOf<UUID, Behandlingsinformasjon>()
    private var andelerPerBehandlingId = mapOf<UUID, List<AndelData>>()
    private var beregnetUtbetalingsoppdrag = mutableMapOf<UUID, BeregnetUtbetalingsoppdrag>()

    @Gitt("følgende behandlingsinformasjon")
    fun følgendeBehandlinger(dataTable: DataTable) {
        opprettBehandlingsinformasjon(dataTable)
    }

    @Gitt("følgende tilkjente ytelser")
    fun følgendeTilkjenteYtelser(dataTable: DataTable) {
        genererBehandlingsinformasjonForDeSomMangler(dataTable)
        andelerPerBehandlingId = mapAndeler(dataTable)
        if (
            andelerPerBehandlingId.flatMap { it.value }
                .any { it.periodeId != null || it.forrigePeriodeId != null }
        ) {
            error("Kildebehandling/periodeId/forrigePeriodeId skal ikke settes på input, denne settes fra utbetalingsgeneratorn")
        }
    }

    @Når("beregner utbetalingsoppdrag")
    fun `beregner utbetalingsoppdrag`() {
        andelerPerBehandlingId.entries.fold(emptyList<Pair<UUID, List<AndelData>>>()) { acc, andelPåBehandlingId ->
            val behandlingId = andelPåBehandlingId.key
            try {
                val beregnUtbetalingsoppdrag = beregnUtbetalingsoppdrag(acc, andelPåBehandlingId)
                beregnetUtbetalingsoppdrag[behandlingId] = beregnUtbetalingsoppdrag
                val oppdaterteAndeler = oppdaterAndelerMedPeriodeId(beregnUtbetalingsoppdrag, andelPåBehandlingId)

                acc + (behandlingId to oppdaterteAndeler)
            } catch (e: Throwable) {
                logger.error("Feilet beregning av oppdrag for behandling=$behandlingId")
                throw e
            }
        }
    }

    @Når("beregner utbetalingsoppdrag kjøres kastes exception")
    fun `lagTilkjentYtelseMedUtbetalingsoppdrag kjøres kastes exception`(dataTable: DataTable) {
        val throwable =
            assertThrows<Throwable> {
                `beregner utbetalingsoppdrag`()
            }
        dataTable.asMaps().let { rader ->
            if (rader.size > 1) {
                error("Kan maks inneholde en rad")
            }
            rader.firstOrNull()!!.let { rad ->
                assertEquals(rad["Exception"], throwable::class.java.simpleName)
                assertTrue(throwable.message!!.contains(rad["Melding"]!!))
            }
        }
    }

    @Så("forvent følgende utbetalingsoppdrag")
    fun `forvent følgende utbetalingsoppdrag`(dataTable: DataTable) {
        validerForventetUtbetalingsoppdrag(dataTable, beregnetUtbetalingsoppdrag)
        assertSjekkBehandlingIder(dataTable, beregnetUtbetalingsoppdrag)
    }

    @Så("forvent følgende andeler med periodeId")
    fun `forvent følgende andeler med periodeId`(dataTable: DataTable) {
        val groupByBehandlingId = dataTable.groupByBehandlingId()

        groupByBehandlingId.forEach { (behandlingId, rader) ->
            val beregnedeAndeler =
                beregnetUtbetalingsoppdrag.getValue(behandlingIdTilUUID[behandlingId.toInt()]!!).andeler
            val forventedeAndeler =
                rader.map { rad ->
                    AndelMedPeriodeId(
                        id = parseString(Domenebegrep.ID, rad),
                        periodeId = parseLong(DomenebegrepUtbetalingsoppdrag.PERIODE_ID, rad),
                        forrigePeriodeId = parseValgfriLong(DomenebegrepUtbetalingsoppdrag.FORRIGE_PERIODE_ID, rad),
                    )
                }
            assertEquals(forventedeAndeler, beregnedeAndeler)
        }

        val ikkeTommeUtbetalingsoppdrag =
            beregnetUtbetalingsoppdrag.values.map { it.andeler }.filter { it.isNotEmpty() }
        assertEquals(groupByBehandlingId.size, ikkeTommeUtbetalingsoppdrag.size)
    }

    private fun opprettBehandlingsinformasjon(dataTable: DataTable) {
        dataTable.groupByBehandlingId().map { (behandlingId, _) ->
            val behandlingIdAsUUID = behandlingIdTilUUID[behandlingId.toInt()]!!
            behandlingsinformasjon[behandlingIdAsUUID] =
                lagBehandlingsinformasjon(
                    behandlingId = GeneriskIdSomUUID(behandlingIdAsUUID),
                )
        }
    }

    private fun genererBehandlingsinformasjonForDeSomMangler(dataTable: DataTable) {
        dataTable.groupByBehandlingId().forEach { (behandlingId, _) ->
            val behandlingIdAsUUID = behandlingIdTilUUID[behandlingId.toInt()]!!
            if (!behandlingsinformasjon.containsKey(behandlingIdAsUUID)) {
                behandlingsinformasjon[behandlingIdAsUUID] =
                    lagBehandlingsinformasjon(GeneriskIdSomUUID(behandlingIdAsUUID))
            }
        }
    }

    private fun lagBehandlingsinformasjon(behandlingId: GeneriskId) =
        Behandlingsinformasjon(
            fagsakId = FAGSAK_ID,
            fagsystem = Fagsystem.DAGPENGER,
            saksbehandlerId = "saksbehandlerId",
            behandlingId = behandlingId,
            personident = "1",
            vedtaksdato = LocalDate.now(),
            iverksettingId = null,
        )

    private fun beregnUtbetalingsoppdrag(
        acc: List<Pair<UUID, List<AndelData>>>,
        andeler: Map.Entry<UUID, List<AndelData>>,
    ): BeregnetUtbetalingsoppdrag {
        val forrigeKjeder = acc.lastOrNull()?.second ?: emptyList()
        val behandlingId = andeler.key
        val sisteOffsetPerIdent = gjeldendeForrigeOffsetForKjede(acc)
        val behandlingsinformasjon1 = behandlingsinformasjon.getValue(behandlingId)

        return Utbetalingsgenerator.lagUtbetalingsoppdrag(
            behandlingsinformasjon = behandlingsinformasjon1,
            nyeAndeler = andeler.value,
            forrigeAndeler = forrigeKjeder,
            sisteAndelPerKjede = sisteOffsetPerIdent,
        )
    }

    /**
     * Når vi henter forrige offset for en kjede så må vi hente max periodeId, men den første hendelsen av den typen
     * Dette då vi i noen tilfeller opphører en peride, som beholder den samme periodeId'n
     */
    private fun gjeldendeForrigeOffsetForKjede(forrigeKjeder: List<Pair<UUID, List<AndelData>>>) =
        forrigeKjeder.flatMap { it.second }
            .uten0beløp()
            .groupBy { it.stønadsdata }
            .mapValues { (_, value) ->
                value.sortedWith(compareByDescending<AndelData> { it.periodeId!! }.thenByDescending { it.tom }).first()
            }

    private fun oppdaterAndelerMedPeriodeId(
        beregnUtbetalingsoppdrag: BeregnetUtbetalingsoppdrag,
        andelPåBehandlingId: Map.Entry<UUID, List<AndelData>>,
    ): List<AndelData> {
        val andelerPerId = beregnUtbetalingsoppdrag.andeler.associateBy { it.id }

        return andelPåBehandlingId.value.map {
            if (it.beløp == 0) {
                it
            } else {
                val andelMedPeriodeId = andelerPerId[it.id]!!
                it.copy(
                    periodeId = andelMedPeriodeId.periodeId,
                    forrigePeriodeId = andelMedPeriodeId.forrigePeriodeId,
                )
            }
        }
    }

    private fun validerForventetUtbetalingsoppdrag(
        dataTable: DataTable,
        beregnetUtbetalingsoppdrag: MutableMap<UUID, BeregnetUtbetalingsoppdrag>,
    ) {
        val forventedeUtbetalingsoppdrag = OppdragParser.mapForventetUtbetalingsoppdrag(dataTable)

        forventedeUtbetalingsoppdrag.forEach { forventetUtbetalingsoppdrag ->
            val behandlingId = behandlingIdTilUUID[forventetUtbetalingsoppdrag.behandlingId.toInt()]
            val utbetalingsoppdrag =
                beregnetUtbetalingsoppdrag[behandlingId]
                    ?: error("Mangler utbetalingsoppdrag for $behandlingId")
            try {
                assertUtbetalingsoppdrag(forventetUtbetalingsoppdrag, utbetalingsoppdrag.utbetalingsoppdrag)
            } catch (e: Throwable) {
                logger.error("Feilet validering av behandling $behandlingId")
                throw e
            }
        }
    }

    private fun assertUtbetalingsoppdrag(
        forventetUtbetalingsoppdrag: ForventetUtbetalingsoppdrag,
        utbetalingsoppdrag: Utbetalingsoppdrag,
    ) {
        assertEquals(forventetUtbetalingsoppdrag.erFørsteUtbetalingPåSak, utbetalingsoppdrag.erFørsteUtbetalingPåSak)
        forventetUtbetalingsoppdrag.utbetalingsperiode.forEachIndexed { index, forventetUtbetalingsperiode ->
            val utbetalingsperiode = utbetalingsoppdrag.utbetalingsperiode[index]
            try {
                assertUtbetalingsperiode(utbetalingsperiode, forventetUtbetalingsperiode)
            } catch (e: Throwable) {
                logger.error("Feilet validering av rad $index for oppdrag=${forventetUtbetalingsoppdrag.behandlingId}")
                throw e
            }
        }

        assertEquals(forventetUtbetalingsoppdrag.utbetalingsperiode.size, utbetalingsoppdrag.utbetalingsperiode.size)
    }
}

private fun assertUtbetalingsperiode(
    utbetalingsperiode: Utbetalingsperiode,
    forventetUtbetalingsperiode: ForventetUtbetalingsperiode,
) {
    val forventetStønadsdata =
        if (forventetUtbetalingsperiode.ytelse is StønadTypeDagpenger) {
            StønadsdataDagpenger(forventetUtbetalingsperiode.ytelse)
        } else {
            StønadsdataTiltakspenger(forventetUtbetalingsperiode.ytelse as StønadTypeTiltakspenger, false)
        }

    assertEquals(
        forventetUtbetalingsperiode.erEndringPåEksisterendePeriode,
        utbetalingsperiode.erEndringPåEksisterendePeriode,
    )
    assertEquals(forventetStønadsdata.tilKlassifisering(), utbetalingsperiode.klassifisering)
    assertEquals(forventetUtbetalingsperiode.periodeId, utbetalingsperiode.periodeId)
    assertEquals(forventetUtbetalingsperiode.forrigePeriodeId, utbetalingsperiode.forrigePeriodeId)
    assertEquals(forventetUtbetalingsperiode.sats, utbetalingsperiode.sats.toInt())
    assertEquals(forventetUtbetalingsperiode.satstype, utbetalingsperiode.satstype)
    assertEquals(forventetUtbetalingsperiode.fom, utbetalingsperiode.fom)
    assertEquals(forventetUtbetalingsperiode.tom, utbetalingsperiode.tom)
    assertEquals(forventetUtbetalingsperiode.opphør, utbetalingsperiode.opphør?.fom)
}
