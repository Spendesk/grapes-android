package com.spendesk.grapes.compose.badge

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
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
@Composable
fun GrapesBadge(
    content: String,
    configuration: GrapesConfigurationStatus,
    modifier: Modifier = Modifier
) {
    val backgroundColor = GrapesTheme.colors.contentColorFor(configuration)

    Text(
        text = content,
        modifier = modifier
            .background(backgroundColor, RoundedCornerShape(100))
            .padding(horizontal = GrapesTheme.dimensions.paddingLarge, vertical = GrapesTheme.dimensions.paddingXSmall),
        style = GrapesTheme.typography.titleM,
        color = GrapesTheme.colors.mainWhite
    )
}

@Preview
@Composable
fun GrapesBadgePreview() {
    GrapesTheme {
        Column(verticalArrangement = Arrangement.spacedBy(GrapesTheme.dimensions.paddingXSmall)) {
            GrapesBadge(content = "Message Inline Success", configuration = GrapesConfigurationStatus.SUCCESS)
            GrapesBadge(content = "Message Inline Information", configuration = GrapesConfigurationStatus.INFORMATION)
            GrapesBadge(content = "Message Inline Neutral", configuration = GrapesConfigurationStatus.NEUTRAL)
            GrapesBadge(content = "Message Inline Alert", configuration = GrapesConfigurationStatus.ALERT)
            GrapesBadge(content = "Message Inline Warning", configuration = GrapesConfigurationStatus.WARNING)
        }
    }
}
