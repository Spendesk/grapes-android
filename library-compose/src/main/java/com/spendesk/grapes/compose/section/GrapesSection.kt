package com.spendesk.grapes.compose.section

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spendesk.grapes.compose.internal.Slot
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author : RomainGF
 * @since : 03/01/2024
 **/
@Composable
fun GrapesSection(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Card(
        modifier = modifier,
        shape = GrapesTheme.shapes.shape3,
        colors = CardDefaults.cardColors(
            containerColor = GrapesTheme.colors.structureSurface,
            contentColor = GrapesTheme.colors.neutralDarker,
        ),
    ) {
        Column(
            modifier = Modifier.padding(vertical = GrapesTheme.dimensions.spacing2),
        ) {
            Text(
                text = title,
                style = GrapesTheme.typography.titleM,
                color = GrapesTheme.colors.neutralDark,
                modifier = Modifier.padding(
                    horizontal = GrapesTheme.dimensions.spacing3,
                    vertical = GrapesTheme.dimensions.spacing2,
                ),
            )
            content()
        }
    }
}

@Preview
@Composable
private fun GrapesSectionPreview() {
    GrapesTheme {
        GrapesSection(
            title = "Title",
        ) {
            Slot(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )
        }
    }
}
