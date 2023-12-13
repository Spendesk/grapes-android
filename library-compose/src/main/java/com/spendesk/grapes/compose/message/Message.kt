package com.spendesk.grapes.compose.message

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
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

@Deprecated(
    message = "Message component is deprecated, use GrapesCallout instead",
    replaceWith = ReplaceWith(
        expression = "GrapesNeutralCallout(title, content, modifier)",
        imports = ["com.spendesk.grapes.compose.callout.GrapesCallout"]
    )
)
@Composable
fun GrapesMessage(
    configuration: GrapesConfigurationStatus,
    title: String,
    modifier: Modifier = Modifier,
    description: String? = null,
    showIcon: Boolean = false,
    titleTextAlign: TextAlign? = null,
    descriptionTextAlign: TextAlign? = null,
    contentPadding: PaddingValues = PaddingValues(GrapesTheme.dimensions.spacing3),
) {
    GrapesSurface(
        modifier = modifier.width(IntrinsicSize.Max),
        configuration = configuration,
        shape = GrapesTheme.shapes.shape2,
    ) {
        Column(modifier = Modifier.padding(contentPadding)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (showIcon) {
                    GrapesIcon(configuration = configuration)
                    Spacer(modifier = Modifier.size(GrapesTheme.dimensions.spacing2))
                }
                Text(
                    text = title,
                    style = GrapesTheme.typography.titleM,
                    color = GrapesTheme.colors.contentColorFor(configuration),
                    textAlign = titleTextAlign,
                    modifier = Modifier.fillMaxWidth(),
                )
            }

            if (description != null) {
                Spacer(modifier = Modifier.size(GrapesTheme.dimensions.spacing1))
                Text(
                    text = description,
                    style = GrapesTheme.typography.bodyS,
                    color = GrapesTheme.colors.mainComplementary,
                    textAlign = descriptionTextAlign,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    }
}

@Preview
@Composable
private fun MessageInlineSmallContentPadding() {
    val padding = PaddingValues(
        horizontal = GrapesTheme.dimensions.spacing2,
        vertical = GrapesTheme.dimensions.spacing1,
    )
    GrapesTheme {
        Column(verticalArrangement = Arrangement.spacedBy(GrapesTheme.dimensions.spacing1)) {
            GrapesMessage(
                title = "Message Inline Information",
                configuration = GrapesConfigurationStatus.INFORMATION,
                contentPadding = padding
            )
            GrapesMessage(
                title = "Message Inline Success",
                configuration = GrapesConfigurationStatus.SUCCESS,
                contentPadding = padding
            )
            GrapesMessage(
                title = "Message Inline Neutral",
                configuration = GrapesConfigurationStatus.NEUTRAL,
                contentPadding = padding
            )
            GrapesMessage(
                title = "Message Inline Alert",
                configuration = GrapesConfigurationStatus.ALERT,
                contentPadding = padding
            )
            GrapesMessage(
                title = "Message Inline Warning",
                configuration = GrapesConfigurationStatus.WARNING,
                contentPadding = padding
            )
        }
    }
}

@Preview
@Composable
private fun MessageInline() {
    GrapesTheme {
        Column(verticalArrangement = Arrangement.spacedBy(GrapesTheme.dimensions.spacing1)) {
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
        Column(verticalArrangement = Arrangement.spacedBy(GrapesTheme.dimensions.spacing1)) {
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
        Column(verticalArrangement = Arrangement.spacedBy(GrapesTheme.dimensions.spacing1)) {
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
private fun MessageInlineTextAligned() {
    GrapesTheme {
        Column(modifier = Modifier.width(400.dp)) {
            GrapesMessage(
                modifier = Modifier.fillMaxWidth(),
                title = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                titleTextAlign = TextAlign.Center,
                description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s.",
                descriptionTextAlign = TextAlign.Center,
                configuration = GrapesConfigurationStatus.SUCCESS,
            )
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
                configuration = GrapesConfigurationStatus.SUCCESS
            )
        }
    }
}
