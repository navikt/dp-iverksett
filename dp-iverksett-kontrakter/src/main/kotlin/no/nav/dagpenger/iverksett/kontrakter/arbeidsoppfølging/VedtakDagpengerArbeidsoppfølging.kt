package no.nav.dagpenger.iverksett.kontrakter.arbeidsoppfølging

import no.nav.dagpenger.iverksett.kontrakter.felles.Barn
import no.nav.dagpenger.kontrakter.utbetaling.StønadType
import java.time.LocalDate
import java.util.UUID

data class VedtakDagpengerArbeidsoppfølging(
    val behanlingId: UUID,
    val personIdent: String,
    val barn: List<Barn>,
    val stønadstype: StønadType,
    val periode: List<Periode>,
    val vedtaksresultat: Vedtaksresultat,
)

data class Periode(
    val fom: LocalDate,
    val tom: LocalDate,
    val periodetype: Periodetype,
    val aktivitetstype: Aktivitetstype,
)

enum class Vedtaksresultat {
    INNVILGET,
    OPPHØRT,
    AVSLÅTT,
}

enum class Periodetype {
    MIGRERING,
    FORLENGELSE,
    HOVEDPERIODE,
    SANKSJON,
    PERIODE_FØR_FØDSEL,
    UTVIDELSE,
    NY_PERIODE_FOR_NYTT_BARN,
}

enum class Aktivitetstype {
    MIGRERING,
    IKKE_AKTIVITETSPLIKT,
    BARN_UNDER_ETT_ÅR,
    FORSØRGER_I_ARBEID,
    FORSØRGER_I_UTDANNING,
    FORSØRGER_REELL_ARBEIDSSØKER,
    FORSØRGER_ETABLERER_VIRKSOMHET,
    BARNET_SÆRLIG_TILSYNSKREVENDE,
    FORSØRGER_MANGLER_TILSYNSORDNING,
    FORSØRGER_ER_SYK,
    BARNET_ER_SYKT,
    UTVIDELSE_FORSØRGER_I_UTDANNING,
    UTVIDELSE_BARNET_SÆRLIG_TILSYNSKREVENDE,
    FORLENGELSE_MIDLERTIDIG_SYKDOM,
    FORLENGELSE_STØNAD_PÅVENTE_ARBEID,
    FORLENGELSE_STØNAD_PÅVENTE_ARBEID_REELL_ARBEIDSSØKER,
    FORLENGELSE_STØNAD_PÅVENTE_OPPSTART_KVALIFISERINGSPROGRAM,
    FORLENGELSE_STØNAD_PÅVENTE_TILSYNSORDNING,
    FORLENGELSE_STØNAD_PÅVENTE_UTDANNING,
    FORLENGELSE_STØNAD_UT_SKOLEÅRET,
}
