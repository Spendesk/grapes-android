package com.spendesk.grapes.compose_testing.text

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.android.ide.common.rendering.api.SessionParams
import com.spendesk.grapes.compose.text.HeaderText
import com.spendesk.grapes.compose.theme.GrapesTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized


/**
 * @author Kélian CLERC
 * @since 02/06/2022
 */
@RunWith(Parameterized::class)
class HeaderTextTest(
    private val headerContent: String
) {
    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig = DeviceConfig.NEXUS_5.copy(softButtons = false, screenHeight = 1),
        renderingMode = SessionParams.RenderingMode.V_SCROLL,
    )

    @Test
    fun compose() {
        paparazzi.snapshot(name = "HeaderText_content_length_${headerContent.length}", composable = {
            GrapesTheme {
                HeaderText(content = headerContent)
            }
        })
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        internal fun params() = listOf(
            "Small test",
            "Loooooooooooooooooooooooooooooooooooooooooooooooooooog test",
        )
    }
}