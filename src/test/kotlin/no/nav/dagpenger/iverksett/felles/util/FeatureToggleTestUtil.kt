package no.nav.dagpenger.iverksett.util

import io.mockk.every
import io.mockk.mockk
import no.nav.dagpenger.iverksett.felles.konfig.FeatureToggleConfig
import no.nav.dagpenger.iverksett.felles.featuretoggle.FeatureToggleService

fun mockFeatureToggleService(enabled: Boolean = true): FeatureToggleService {
    val mockk = mockk<FeatureToggleService>()
    every { mockk.isEnabled(any(), any()) } returns enabled
    every { mockk.isEnabled(any()) } returns enabled
    every { mockk.isEnabled(FeatureToggleConfig.STOPP_IVERKSETTING) } answers {
        false
    }
    return mockk
}
