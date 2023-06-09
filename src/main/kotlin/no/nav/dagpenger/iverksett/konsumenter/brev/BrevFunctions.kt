package no.nav.dagpenger.iverksett.konsumenter.brev

import no.nav.dagpenger.iverksett.api.domene.IverksettDagpenger
import no.nav.dagpenger.kontrakter.felles.StønadType
import no.nav.dagpenger.kontrakter.iverksett.BehandlingType
import no.nav.dagpenger.kontrakter.iverksett.BehandlingÅrsak
import no.nav.dagpenger.kontrakter.iverksett.Vedtaksresultat
import no.nav.dagpenger.kontrakter.iverksett.journalføring.dokarkiv.Dokumenttype

fun stønadstypeTilDokumenttype(stønadType: StønadType) =
    when (stønadType) {
        StønadType.DAGPENGER_ARBEIDSSOKER_ORDINAER,
        StønadType.DAGPENGER_PERMITTERING_ORDINAER,
        StønadType.DAGPENGER_PERMITTERING_FISKEINDUSTRI,
        StønadType.DAGPENGER_EOS,
        -> Dokumenttype.DAGPENGER_FRITTSTÅENDE_BREV
    }

fun vedtaksbrevForStønadType(stønadType: StønadType): Dokumenttype =
    when (stønadType) {
        StønadType.DAGPENGER_ARBEIDSSOKER_ORDINAER,
        StønadType.DAGPENGER_PERMITTERING_ORDINAER,
        StønadType.DAGPENGER_PERMITTERING_FISKEINDUSTRI,
        StønadType.DAGPENGER_EOS,
        -> Dokumenttype.VEDTAKSBREV_DAGPENGER
    }

fun lagStønadtypeTekst(stønadstype: StønadType): String =
    when (stønadstype) {
        StønadType.DAGPENGER_ARBEIDSSOKER_ORDINAER,
        StønadType.DAGPENGER_PERMITTERING_ORDINAER,
        StønadType.DAGPENGER_PERMITTERING_FISKEINDUSTRI,
        StønadType.DAGPENGER_EOS,
        -> "dagpenger"
    }

fun lagVedtakstekst(iverksettData: IverksettDagpenger): String =
    when {
        iverksettData.behandling.behandlingType === BehandlingType.FØRSTEGANGSBEHANDLING ->
            lagVedtakstekstFørstegangsbehandling(iverksettData)

        iverksettData.behandling.behandlingÅrsak === BehandlingÅrsak.SANKSJON_1_MND -> "Vedtak om sanksjon av "
        iverksettData.vedtak.vedtaksresultat === Vedtaksresultat.AVSLÅTT -> "Vedtak om avslått "
        iverksettData.vedtak.vedtaksresultat === Vedtaksresultat.OPPHØRT -> "Vedtak om opphørt "
        iverksettData.vedtak.vedtaksresultat === Vedtaksresultat.INNVILGET &&
            iverksettData.behandling.behandlingÅrsak === BehandlingÅrsak.SØKNAD -> "Vedtak om innvilget "

        else -> "Vedtak om revurdert "
    }

private fun lagVedtakstekstFørstegangsbehandling(iverksettData: IverksettDagpenger) =
    when (iverksettData.vedtak.vedtaksresultat) {
        Vedtaksresultat.INNVILGET -> "Vedtak om innvilget "
        Vedtaksresultat.AVSLÅTT -> "Vedtak om avslått "
        Vedtaksresultat.OPPHØRT -> error("Førstegangsbehandling kan ikke ha resultat Opphørt")
    }