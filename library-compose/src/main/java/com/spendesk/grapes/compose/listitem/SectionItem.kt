package com.spendesk.grapes.compose.listitem

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spendesk.grapes.compose.theme.GrapesTheme

private val iconSize = 32.dp

/**
 * @author : RomainGF
 * @since : 18/08/2023
 **/
@Composable
fun SectionItem(
    title: String,
    description: String,
    modifier: Modifier = Modifier,
    icon: @Composable (Modifier) -> Unit = {},
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(GrapesTheme.dimensions.paddingLarge),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(
            horizontal = GrapesTheme.dimensions.paddingLarge,
            vertical = GrapesTheme.dimensions.paddingMedium,
        ),
    ) {
        icon(Modifier.size(iconSize))
        Text(
            text = title,
            style = GrapesTheme.typography.bodyRegular,
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = description,
            style = GrapesTheme.typography.bodyRegular,
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun Preview() {
    GrapesTheme {
        SectionItem(
            title = "Frichti",
            icon = { modifier ->
                Box(
                    modifier
                        .clip(GrapesTheme.shapes.small)
                        .background(Color.Blue))
            },
            description = "85.99€ per month",
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
