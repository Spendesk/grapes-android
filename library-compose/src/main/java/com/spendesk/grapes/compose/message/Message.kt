package com.spendesk.grapes.compose.message

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author : danyboucanova
 * @since : 19/05/2022, Thu
 **/

enum class Configuration {
    ALERT,
    WARNING,
    INFORMATION,
    NEUTRAL,
    SUCCESS
}

@Composable
fun GrapesMessage(
    modifier: Modifier = Modifier,
    text: String,
    configuration: Configuration,
) {
    Surface(
        shape = RoundedCornerShape(size = GrapesTheme.dimensions.radiusNormal),
        color = when (configuration) {
            Configuration.ALERT -> GrapesTheme.colors.mainAlertLightest
            Configuration.WARNING -> GrapesTheme.colors.mainWarningLightest
            Configuration.INFORMATION -> GrapesTheme.colors.mainInfoLightest
            Configuration.NEUTRAL -> GrapesTheme.colors.mainNeutralLighter
            Configuration.SUCCESS -> GrapesTheme.colors.mainSuccessLightest
        },
        contentColor = when (configuration) {
            Configuration.ALERT -> GrapesTheme.colors.mainAlertLightest
            Configuration.WARNING -> GrapesTheme.colors.mainWarningLightest
            Configuration.INFORMATION -> GrapesTheme.colors.mainInfoLightest
            Configuration.NEUTRAL -> GrapesTheme.colors.mainNeutralLighter
            Configuration.SUCCESS -> GrapesTheme.colors.mainSuccessLightest
        },
        border = BorderStroke(
            width = 1.dp,
            color = when (configuration) {
                Configuration.ALERT -> GrapesTheme.colors.mainAlertLightest
                Configuration.WARNING -> GrapesTheme.colors.mainWarningLightest
                Configuration.INFORMATION -> GrapesTheme.colors.mainInfoLightest
                Configuration.NEUTRAL -> GrapesTheme.colors.mainNeutralLighter
                Configuration.SUCCESS -> GrapesTheme.colors.mainSuccessLightest
            }
        )
    ) {
        Text(
            text = text, color = when (configuration) {
                Configuration.ALERT -> GrapesTheme.colors.mainAlertNormal
                Configuration.WARNING -> GrapesTheme.colors.mainWarningNormal
                Configuration.INFORMATION -> GrapesTheme.colors.mainInfoNormal
                Configuration.NEUTRAL -> GrapesTheme.colors.mainNeutralDarkest
                Configuration.SUCCESS -> GrapesTheme.colors.mainSuccessNormal
            }
        )
    }
}

@Preview
@Composable
private fun MessageInline() {
    GrapesTheme {
        Column(verticalArrangement = Arrangement.spacedBy(GrapesTheme.dimensions.paddingSmall)) {
            GrapesMessage(text = "Message Inline Alert", configuration = Configuration.ALERT)
            GrapesMessage(text = "Message Inline Alert", configuration = Configuration.WARNING)
            GrapesMessage(text = "Message Inline Alert", configuration = Configuration.INFORMATION)
            GrapesMessage(text = "Message Inline Alert", configuration = Configuration.NEUTRAL)
            GrapesMessage(text = "Message Inline Alert", configuration = Configuration.SUCCESS)
        }
    }
}

@Preview
@Composable
private fun MessageInlineIcon() {
    GrapesTheme {
        GrapesMessage(text = "Message Inline Warning", configuration = Configuration.WARNING)
    }
}