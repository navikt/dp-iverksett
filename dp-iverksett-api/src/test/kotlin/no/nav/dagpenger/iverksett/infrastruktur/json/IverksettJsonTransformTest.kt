package no.nav.dagpenger.iverksett.infrastruktur.json

import com.fasterxml.jackson.databind.node.ObjectNode
import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import com.fasterxml.jackson.module.kotlin.readValue
import no.nav.dagpenger.iverksett.ResourceLoaderTestUtil
import no.nav.dagpenger.iverksett.api.domene.IverksettBarnetilsyn
import no.nav.dagpenger.iverksett.api.domene.IverksettData
import no.nav.dagpenger.iverksett.api.domene.IverksettOvergangsstønad
import no.nav.dagpenger.iverksett.api.domene.IverksettSkolepenger
import no.nav.dagpenger.iverksett.infrastruktur.transformer.toDomain
import no.nav.dagpenger.iverksett.infrastruktur.util.ObjectMapperProvider.objectMapper
import no.nav.dagpenger.iverksett.kontrakter.felles.StønadType
import no.nav.dagpenger.iverksett.kontrakter.iverksett.IverksettBarnetilsynDto
import no.nav.dagpenger.iverksett.kontrakter.iverksett.IverksettDto
import no.nav.dagpenger.iverksett.kontrakter.iverksett.IverksettOvergangsstønadDto
import no.nav.dagpenger.iverksett.kontrakter.iverksett.IverksettSkolepengerDto
import no.nav.dagpenger.iverksett.util.opprettIverksettOvergangsstønad
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import java.util.UUID

/**
 * Dersom testene i denne filen feiler i maven-bygg, men ikke når det kjøres i IntelliJ,
 * så hjelper det sannsynligvis å reloade maven dependencies.
 */
class IverksettJsonTransformTest {

    @Test
    fun `deserialiser overgangsstønad JSON til IverksettDtoJson, kall toDomain, forvent likhet`() {
        val json: String = ResourceLoaderTestUtil.readResource("json/IverksettDtoEksempel.json")
        val iverksettJson = objectMapper.readValue<IverksettDto>(json)
        val iverksett = iverksettJson.toDomain()

        assertThat(iverksettJson).isInstanceOf(IverksettOvergangsstønadDto::class.java)
        assertThat(iverksett).isInstanceOf(IverksettOvergangsstønad::class.java)

        assertThat(iverksett).isNotNull
        assertThat(objectMapper.readTree(json))
            .isEqualTo(objectMapper.readTree(objectMapper.writeValueAsString(iverksettJson)))
    }

    @Test
    fun `deserialiser barnetilsyn JSON til IverksettDtoJson, kall toDomain, forvent likhet`() {
        val json: String = ResourceLoaderTestUtil.readResource("json/IverksettBarnetilsynDtoEksempel.json")
        val iverksettJson = objectMapper.readValue<IverksettDto>(json)
        val iverksett = iverksettJson.toDomain()

        assertThat(iverksettJson).isInstanceOf(IverksettBarnetilsynDto::class.java)
        assertThat(iverksett).isInstanceOf(IverksettBarnetilsyn::class.java)

        assertThat(iverksett).isNotNull
        assertThat(objectMapper.readTree(json))
            .isEqualTo(objectMapper.readTree(objectMapper.writeValueAsString(iverksettJson)))
    }

    @Test
    fun `deserialiser skolepenger JSON til IverksettDtoJson, kall toDomain, forvent likhet`() {
        val json: String = ResourceLoaderTestUtil.readResource("json/IverksettSkolepengerDtoEksempel.json")
        val iverksettJson = objectMapper.readValue<IverksettDto>(json)
        val iverksett = iverksettJson.toDomain()

        assertThat(iverksettJson).isInstanceOf(IverksettSkolepengerDto::class.java)
        assertThat(iverksett).isInstanceOf(IverksettSkolepenger::class.java)

        assertThat(iverksett).isNotNull
        assertThat(objectMapper.readTree(json))
            .isEqualTo(objectMapper.readTree(objectMapper.writeValueAsString(iverksettJson)))
    }

    @Test
    fun `deserialiser overgangsstønad JSON med feil stønadtype`() {
        val stønadType = StønadType.BARNETILSYN
        val filnavn = "json/IverksettDtoEksempel.json"
        parseJsonMedFeilStønadstype(filnavn, stønadType)
    }

    @Test
    fun `deserialiser barnetilsyn JSON med feil stønadtype`() {
        val stønadType = StønadType.OVERGANGSSTØNAD
        val filnavn = "json/IverksettBarnetilsynDtoEksempel.json"
        parseJsonMedFeilStønadstype(filnavn, stønadType)
    }

    @Test
    fun `deserialiser skolepenger JSON med feil stønadtype`() {
        val stønadType = StønadType.OVERGANGSSTØNAD
        val filnavn = "json/IverksettSkolepengerDtoEksempel.json"
        parseJsonMedFeilStønadstype(filnavn, stønadType)
    }

    private fun parseJsonMedFeilStønadstype(filnavn: String, stønadType: StønadType) {
        val json: String = ResourceLoaderTestUtil.readResource(filnavn)
        val tree = objectMapper.readTree(json)
        (tree.get("fagsak") as ObjectNode).put("stønadstype", stønadType.name)
        val jsonMedFeilStønadstype = objectMapper.writeValueAsString(tree)
        assertThatThrownBy { objectMapper.readValue<IverksettDto>(jsonMedFeilStønadstype) }
            .isInstanceOf(MissingKotlinParameterException::class.java)
    }

    @Test
    fun `deserialiser JSON til Iverksett, forvent ingen unntak`() {
        val json: String = ResourceLoaderTestUtil.readResource("json/IverksettEksempel.json")
        val iverksett = objectMapper.readValue<IverksettData>(json)
        assertThat(iverksett).isNotNull
    }

    @Test
    internal fun `deserialiser iverksettOvergangsstønad til json og serialiser tilbake til object, forvent likhet`() {
        val behandlingId = UUID.randomUUID()
        val iverksett = opprettIverksettOvergangsstønad(behandlingId)
        val parsetIverksett = objectMapper.readValue<IverksettData>(objectMapper.writeValueAsString(iverksett))
        assertThat(iverksett).isEqualTo(parsetIverksett)
    }
}
