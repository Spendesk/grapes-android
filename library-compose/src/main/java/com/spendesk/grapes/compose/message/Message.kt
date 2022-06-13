package com.spendesk.grapes.compose.message

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spendesk.grapes.compose.icons.GrapesIcon
import com.spendesk.grapes.compose.icons.GrapesSurface
import com.spendesk.grapes.compose.model.GrapesConfigurationStatus
import com.spendesk.grapes.compose.theme.GrapesTheme
import com.spendesk.grapes.compose.theme.extensions.contentColorFor

/**
 * @author : danyboucanova
 * @since : 19/05/2022, Thu
 **/
@Composable
fun GrapesMessage(
    modifier: Modifier = Modifier,
    configuration: GrapesConfigurationStatus,
    title: String,
    description: String? = null,
    showIcon: Boolean = false
) {
    GrapesSurface(modifier = Modifier.wrapContentSize(), configuration = configuration, shape = RoundedCornerShape(size = GrapesTheme.dimensions.radiusNormal)) {
        Column(modifier = Modifier.padding(GrapesTheme.dimensions.paddingNormal)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (showIcon) {
                    GrapesIcon(configuration = configuration)
                    Spacer(modifier = Modifier.size(GrapesTheme.dimensions.paddingSmall))
                }
                Text(
                    text = title,
                    style = GrapesTheme.typography.titleS,
                    color = GrapesTheme.colors.contentColorFor(configuration)
                )
            }

            if (description != null) {
                Spacer(modifier = Modifier.size(GrapesTheme.dimensions.paddingSmall))
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
            GrapesMessage(title = "Message Inline Success", configuration = GrapesConfigurationStatus.SUCCESS)
            GrapesMessage(title = "Message Inline Information", configuration = GrapesConfigurationStatus.INFORMATION)
            GrapesMessage(title = "Message Inline Neutral", configuration = GrapesConfigurationStatus.NEUTRAL)
            GrapesMessage(title = "Message Inline Alert", configuration = GrapesConfigurationStatus.ALERT)
            GrapesMessage(title = "Message Inline Warning", configuration = GrapesConfigurationStatus.WARNING)
        }
    }
}

@Preview
@Composable
private fun MessageInlineIcon() {
    GrapesTheme {
        Column(verticalArrangement = Arrangement.spacedBy(GrapesTheme.dimensions.paddingSmall)) {
            GrapesMessage(title = "Message Inline Success", configuration = GrapesConfigurationStatus.SUCCESS, showIcon = true)
            GrapesMessage(title = "Message Inline Information", configuration = GrapesConfigurationStatus.INFORMATION, showIcon = true)
            GrapesMessage(title = "Message Inline Neutral", configuration = GrapesConfigurationStatus.NEUTRAL, showIcon = true)
            GrapesMessage(title = "Message Inline Alert", configuration = GrapesConfigurationStatus.ALERT, showIcon = true)
            GrapesMessage(title = "Message Inline Warning", configuration = GrapesConfigurationStatus.WARNING, showIcon = true)
        }
    }
}

@Preview
@Composable
private fun MessageInlineIconBlock() {
    GrapesTheme {
        Column(verticalArrangement = Arrangement.spacedBy(GrapesTheme.dimensions.paddingSmall)) {
            GrapesMessage(
                title = "Message Inline Icon Block Success",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla nec lectus sed sem porttitor viverra. Vestibulum magna leo.",
                configuration = GrapesConfigurationStatus.SUCCESS,
                showIcon = true
            )
            GrapesMessage(
                title = "Message Inline Icon Block Information",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla nec lectus sed sem porttitor viverra. Vestibulum magna leo.",
                configuration = GrapesConfigurationStatus.INFORMATION,
                showIcon = true
            )
            GrapesMessage(
                title = "Message Inline Icon Block Neutral",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla nec lectus sed sem porttitor viverra. Vestibulum magna leo.",
                configuration = GrapesConfigurationStatus.NEUTRAL,
                showIcon = true
            )
            GrapesMessage(
                title = "Message Inline Icon Block Alert",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla nec lectus sed sem porttitor viverra. Vestibulum magna leo.",
                configuration = GrapesConfigurationStatus.ALERT,
                showIcon = true
            )
            GrapesMessage(
                title = "Message Inline Icon Block Warning",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla nec lectus sed sem porttitor viverra. Vestibulum magna leo.",
                configuration = GrapesConfigurationStatus.WARNING,
                showIcon = true
            )
        }
    }
}

@Preview
@Composable
private fun MessageInlineFillingMaxWidth() {
    GrapesTheme {
        Column(modifier = Modifier.width(400.dp)) {
            GrapesMessage(modifier = Modifier.fillMaxWidth(), title = "aaaa", configuration = GrapesConfigurationStatus.SUCCESS)
        }
    }
}

@Preview
@Composable
private fun MessageInlineFillingLongTitleAndDescription() {
    GrapesTheme {
        Column(modifier = Modifier.width(100.dp)) {
            GrapesMessage(
                title = "Very very very very very very very very very very very long title",
                showIcon = true,
                description = "Very very very very very very very very very very very long description",
                configuration = GrapesConfigurationStatus.SUCCESS)
        }
    }
}