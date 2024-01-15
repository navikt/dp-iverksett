package no.nav.dagpenger.iverksett.utbetaling.domene

import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID
import no.nav.dagpenger.kontrakter.felles.BrukersNavKontor
import no.nav.dagpenger.kontrakter.felles.SakIdentifikator
import no.nav.dagpenger.kontrakter.felles.StønadType
import no.nav.dagpenger.kontrakter.felles.StønadTypeDagpenger

data class Iverksetting(
        val fagsak: Fagsakdetaljer,
        val behandling: Behandlingsdetaljer,
        val søker: Søker,
        val vedtak: Vedtaksdetaljer,
        val forrigeIverksettingBehandlingId: UUID? = null,
)

data class Fagsakdetaljer(
    val fagsakId: UUID? = null,
    val saksreferanse: String? = null,
    val stønadstype: StønadType = StønadTypeDagpenger.DAGPENGER_ARBEIDSSØKER_ORDINÆR,
) {
    init {
        SakIdentifikator.valider(fagsakId, saksreferanse)
    }
}

data class Søker(
    val personident: String,
)

data class Vedtaksdetaljer(
        val vedtakstidspunkt: LocalDateTime,
        val saksbehandlerId: String,
        val beslutterId: String,
        val brukersNavKontor: BrukersNavKontor? = null,
        val tilkjentYtelse: TilkjentYtelse,
)

data class Behandlingsdetaljer(
    val forrigeBehandlingId: UUID? = null,
    val behandlingId: UUID,
    val relatertBehandlingId: UUID? = null,
    val aktivitetspliktInntrefferDato: LocalDate? = null,
    val kravMottatt: LocalDate? = null,
)

val Iverksetting.sakId get() = this.fagsak.fagsakId
val Iverksetting.personident get() = this.søker.personident

val Iverksetting.behandlingId get() = this.behandling.behandlingId
