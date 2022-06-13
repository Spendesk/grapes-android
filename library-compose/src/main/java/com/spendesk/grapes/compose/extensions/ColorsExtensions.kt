package com.spendesk.grapes.compose.extensions

import androidx.compose.ui.graphics.Color
import com.spendesk.grapes.compose.model.GrapesConfigurationState
import com.spendesk.grapes.compose.theme.GrapesColors

/**
 * @author : danyboucanova
 * @since : 09/06/2022, Wed
 **/
fun GrapesColors.contentColorFor(configurationState: GrapesConfigurationState): Color {
    return when (configurationState) {
        GrapesConfigurationState.SUCCESS -> mainSuccessNormal
        GrapesConfigurationState.INFORMATION -> mainInfoNormal
        GrapesConfigurationState.NEUTRAL -> mainNeutralDarker
        GrapesConfigurationState.ALERT -> mainAlertNormal
        GrapesConfigurationState.WARNING -> mainWarningNormal
        GrapesConfigurationState.BLOCKED -> Color.Unspecified
    }
}

val GrapesColors.unspecified: Color
    get() = Color(0f, 0f, 0f, 0f)