package com.spendesk.grapes.compose.textblock

/**
 * @author Kélian CLERC
 * @since 16/01/2024
 */
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spendesk.grapes.compose.theme.GrapesTheme

@Composable
fun GrapesTextBlockInformativeLabel(
    label: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(GrapesTheme.dimensions.spacing2),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(
            modifier = Modifier
                .size(4.dp)
                .background(color, shape = GrapesTheme.shapes.shape4)
        )
        Text(
            text = label,
            style = GrapesTheme.typography.bodyM,
            color = color,
            maxLines = 1,
        )
    }
}

@Preview
@Composable
private fun TextBlockOptionalTitlePreview() {
    GrapesTheme {
        Column(
            modifier = Modifier.padding(GrapesTheme.dimensions.paddingLarge),
            verticalArrangement = Arrangement.spacedBy(GrapesTheme.dimensions.paddingLarge)
        ) {
            GrapesTextBlockInformativeLabel("Missing", color = GrapesTheme.colors.warningNormal)
            GrapesTextBlockInformativeLabel("Optional", color = GrapesTheme.colors.neutralNormal)
        }
    }
}
