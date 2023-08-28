package com.spendesk.grapes.compose.listitem

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author Kélian CLERC
 * @since 27/02/2023
 */
@Composable
fun TextDivider(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = GrapesTheme.colors.mainNeutralNormal
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(GrapesTheme.dimensions.paddingLarge),
        modifier = modifier.fillMaxWidth()
    ) {
        GrapesDivider(modifier = Modifier.weight(1f), color = color)
        Text(text = text, maxLines = 1, overflow = TextOverflow.Ellipsis, style = GrapesTheme.typography.bodyRegular, color = color)
        GrapesDivider(modifier = Modifier.weight(1f), color = color)
    }
}

@Preview(name = "Text divider", group = "List item")
@Composable
internal fun TextDividerPreview() {
    GrapesTheme {
        Column(
            modifier = Modifier
                .background(GrapesTheme.colors.mainBackground)
                .padding(GrapesTheme.dimensions.paddingLarge),
            verticalArrangement = Arrangement.spacedBy(GrapesTheme.dimensions.paddingLarge)
        ) {
            TextDivider(text = "OR")
        }
    }
}
