package com.spendesk.grapes.compose.badge

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.spendesk.grapes.compose.model.GrapesConfigurationStatus
import com.spendesk.grapes.compose.theme.GrapesTheme
import com.spendesk.grapes.compose.theme.extensions.contentColorFor

/**
 * @author Kélian CLERC
 * @since 02/12/2022
 */

private const val CORNER_SHAPE_CONST = 100

@Deprecated("Replaced by GrapesTag or new GrapesBadge")
@Composable
fun LegacyGrapesBadge(
    content: String,
    configuration: GrapesConfigurationStatus,
    modifier: Modifier = Modifier
) {
    val backgroundColor = GrapesTheme.colors.contentColorFor(configuration)
    Text(
        text = content,
        modifier = modifier
            .background(backgroundColor, RoundedCornerShape(CORNER_SHAPE_CONST))
            .padding(horizontal = GrapesTheme.dimensions.spacing3, vertical = GrapesTheme.dimensions.spacing1),
        style = GrapesTheme.typography.titleL,
        color = GrapesTheme.colors.mainWhite
    )
}

@Preview
@Composable
fun GrapesBadgePreview() {
    GrapesTheme {
        Column(verticalArrangement = Arrangement.spacedBy(GrapesTheme.dimensions.spacing1)) {
            LegacyGrapesBadge(content = "Message Inline Success", configuration = GrapesConfigurationStatus.SUCCESS)
            LegacyGrapesBadge(content = "Message Inline Information", configuration = GrapesConfigurationStatus.INFORMATION)
            LegacyGrapesBadge(content = "Message Inline Neutral", configuration = GrapesConfigurationStatus.NEUTRAL)
            LegacyGrapesBadge(content = "Message Inline Alert", configuration = GrapesConfigurationStatus.ALERT)
            LegacyGrapesBadge(content = "Message Inline Warning", configuration = GrapesConfigurationStatus.WARNING)
        }
    }
}
