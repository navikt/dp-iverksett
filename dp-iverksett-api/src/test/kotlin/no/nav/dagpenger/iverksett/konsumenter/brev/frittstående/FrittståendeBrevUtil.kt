package no.nav.dagpenger.iverksett.konsumenter.brev.frittstående

import no.nav.dagpenger.iverksett.kontrakter.brev.Brevmottakere
import no.nav.dagpenger.iverksett.konsumenter.brev.domain.FrittståendeBrev
import no.nav.dagpenger.iverksett.kontrakter.felles.FrittståendeBrevType
import no.nav.dagpenger.iverksett.kontrakter.iverksett.Brevmottaker
import no.nav.dagpenger.kontrakter.utbetaling.StønadType

object FrittståendeBrevUtil {

    fun opprettFrittståendeBrev() = FrittståendeBrev(
        personIdent = "11",
        eksternFagsakId = 1L,
        journalførendeEnhet = "enhet",
        saksbehandlerIdent = "saksbehandlerIdent",
        stønadstype = StønadType.DAGPENGER_ARBEIDSSOKER_ORDINAER,
        mottakere = Brevmottakere(
            listOf(
                no.nav.dagpenger.iverksett.kontrakter.brev.Brevmottaker(
                    "11",
                    "navn1",
                    Brevmottaker.IdentType.PERSONIDENT,
                    Brevmottaker.MottakerRolle.BRUKER,
                ),
                no.nav.dagpenger.iverksett.kontrakter.brev.Brevmottaker(
                    "22",
                    "navn2",
                    Brevmottaker.IdentType.PERSONIDENT,
                    Brevmottaker.MottakerRolle.BRUKER,
                ),
            ),
        ),
        fil = byteArrayOf(13),
        brevtype = FrittståendeBrevType.INFORMASJONSBREV,
    )
}
