package com.spendesk.grapes.compose.message

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.spendesk.grapes.compose.extensions.GrapesDefault
import com.spendesk.grapes.compose.theme.extensions.contentColorFor
import com.spendesk.grapes.compose.icons.GrapesSurface
import com.spendesk.grapes.compose.model.GrapesConfigurationState
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author : danyboucanova
 * @since : 19/05/2022, Thu
 **/

@Composable
fun GrapesMessage(
    modifier: Modifier = Modifier,
    configuration: GrapesConfigurationState,
    text: String,
    description: String? = null,
    showIcon: Boolean = false
) {
    GrapesSurface(modifier = modifier, configuration = configuration, shape = RoundedCornerShape(size = GrapesTheme.dimensions.radiusNormal)) {
        Column(modifier = Modifier.padding(GrapesTheme.dimensions.paddingNormal)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (showIcon) {
                    Image(painter = painterResource(GrapesDefault.iconFor(configuration)), contentDescription = null)
                    Spacer(modifier = Modifier.padding(GrapesTheme.dimensions.paddingSmall))
                }
                Text(
                    text = text,
                    style = GrapesTheme.typography.titleS,
                    color = GrapesTheme.colors.contentColorFor(configuration)
                )
            }
            if (description != null) {

                Spacer(modifier = Modifier.padding(GrapesTheme.dimensions.paddingSmall))
                Text(
                    text = description,
                    style = GrapesTheme.typography.bodyS,
                    color = GrapesTheme.colors.mainComplementary
                )
            }
        }
    }
}

@Preview
@Composable
private fun MessageInline() {
    GrapesTheme {
        Column(verticalArrangement = Arrangement.spacedBy(GrapesTheme.dimensions.paddingSmall)) {
            GrapesMessage(text = "Message Inline Success", configuration = GrapesConfigurationState.SUCCESS)
            GrapesMessage(text = "Message Inline Information", configuration = GrapesConfigurationState.INFORMATION)
            GrapesMessage(text = "Message Inline Neutral", configuration = GrapesConfigurationState.NEUTRAL)
            GrapesMessage(text = "Message Inline Alert", configuration = GrapesConfigurationState.ALERT)
            GrapesMessage(text = "Message Inline Warning", configuration = GrapesConfigurationState.WARNING)
        }
    }
}

@Preview
@Composable
private fun MessageInlineIcon() {
    GrapesTheme {
        Column(verticalArrangement = Arrangement.spacedBy(GrapesTheme.dimensions.paddingSmall)) {
            GrapesMessage(text = "Message Inline Success", configuration = GrapesConfigurationState.SUCCESS, showIcon = true)
            GrapesMessage(text = "Message Inline Information", configuration = GrapesConfigurationState.INFORMATION, showIcon = true)
            GrapesMessage(text = "Message Inline Neutral", configuration = GrapesConfigurationState.NEUTRAL, showIcon = true)
            GrapesMessage(text = "Message Inline Alert", configuration = GrapesConfigurationState.ALERT, showIcon = true)
            GrapesMessage(text = "Message Inline Warning", configuration = GrapesConfigurationState.WARNING, showIcon = true)
        }
    }
}

@Preview
@Composable
private fun MessageInlineIconBlock() {
    GrapesTheme {
        Column(verticalArrangement = Arrangement.spacedBy(GrapesTheme.dimensions.paddingSmall)) {
            GrapesMessage(
                text = "Message Inline Icon Block Success",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla nec lectus sed sem porttitor viverra. Vestibulum magna leo.",
                configuration = GrapesConfigurationState.SUCCESS,
                showIcon = true
            )
            GrapesMessage(
                text = "Message Inline Icon Block Information",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla nec lectus sed sem porttitor viverra. Vestibulum magna leo.",
                configuration = GrapesConfigurationState.INFORMATION,
                showIcon = true
            )
            GrapesMessage(
                text = "Message Inline Icon Block Neutral",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla nec lectus sed sem porttitor viverra. Vestibulum magna leo.",
                configuration = GrapesConfigurationState.NEUTRAL,
                showIcon = true
            )
            GrapesMessage(
                text = "Message Inline Icon Block Alert",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla nec lectus sed sem porttitor viverra. Vestibulum magna leo.",
                configuration = GrapesConfigurationState.ALERT,
                showIcon = true
            )
            GrapesMessage(
                text = "Message Inline Icon Block Warning",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla nec lectus sed sem porttitor viverra. Vestibulum magna leo.",
                configuration = GrapesConfigurationState.WARNING,
                showIcon = true
            )
        }
    }
}