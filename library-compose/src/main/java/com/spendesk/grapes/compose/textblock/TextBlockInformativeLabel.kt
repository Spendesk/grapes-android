package com.spendesk.grapes.compose.textblock

/**
 * @author Kélian CLERC
 * @since 16/01/2024
 */
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.spendesk.grapes.compose.theme.GrapesTheme

@Composable
fun TextBlockInformativeLabel(label: String, color: Color) {
    Text(
        text = label,
        style = GrapesTheme.typography.bodyM,
        color = color,
        maxLines = 1,
    )
}

@Preview
@Composable
private fun TextBlockOptionalTitlePreview() {
    GrapesTheme {
        Column(
            modifier = Modifier.padding(GrapesTheme.dimensions.paddingLarge),
            verticalArrangement = Arrangement.spacedBy(GrapesTheme.dimensions.paddingLarge)
        ) {
            TextBlockInformativeLabel("• Missing", color = GrapesTheme.colors.warningNormal)
            TextBlockInformativeLabel("• Optional", color = GrapesTheme.colors.neutralNormal)
        }
    }
}
