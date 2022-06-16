package com.spendesk.grapes.compose.theme.extensions

import androidx.compose.ui.graphics.Color
import com.spendesk.grapes.compose.model.GrapesConfigurationStatus
import com.spendesk.grapes.compose.theme.GrapesColors

/**
 * @author : danyboucanova
 * @since : 09/06/2022, Wed
 **/
fun GrapesColors.contentColorFor(configurationState: GrapesConfigurationStatus): Color {
    return when (configurationState) {
        GrapesConfigurationStatus.SUCCESS -> mainSuccessNormal
        GrapesConfigurationStatus.INFORMATION -> mainInfoNormal
        GrapesConfigurationStatus.NEUTRAL -> mainNeutralDarker
        GrapesConfigurationStatus.ALERT -> mainAlertNormal
        GrapesConfigurationStatus.WARNING -> mainWarningNormal
        GrapesConfigurationStatus.BLOCKED -> mainPrimaryNormal
    }
}

val GrapesColors.unspecified: Color
    get() = Color(0f, 0f, 0f, 0f)