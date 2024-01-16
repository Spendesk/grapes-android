package com.spendesk.grapes.compose.textblock.actions

/**
 * @author Kélian CLERC
 * @since 15/01/2024
 */
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.spendesk.grapes.compose.theme.GrapesTheme

@Composable
fun LoadingTextBlockAction(loadingLabel: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(GrapesTheme.dimensions.spacing2)
    ) {
        Text(text = loadingLabel, style = GrapesTheme.typography.bodyM, color = GrapesTheme.colors.neutralNormal)
        LinearProgressIndicator(modifier = Modifier.size(GrapesTheme.dimensions.spacing3))
    }
}

@Preview
@Composable
fun LoadingTextBlockActionPreview() {
    GrapesTheme {
        Column(
            modifier = Modifier.padding(GrapesTheme.dimensions.spacing3),
            verticalArrangement = Arrangement.spacedBy(GrapesTheme.dimensions.spacing3)
        ) {
            LoadingTextBlockAction("Saving")
        }
    }
}
