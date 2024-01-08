package com.spendesk.grapes.compose.modifier.placeholder

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spendesk.grapes.compose.theme.GrapesTheme

@Composable
private fun PlaceholderContentPreview(
    modifier: Modifier = Modifier,
    placeholderModifier: Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize(),
    ) {
        Text(
            text = "Hello world",
            modifier = placeholderModifier,
        )
        Text(
            text = "",
            modifier = Modifier
                .widthIn(min = 80.dp)
                .then(placeholderModifier),
        )
        Image(
            imageVector = Icons.Default.Image,
            contentDescription = null,
            modifier = Modifier
                .size(64.dp)
                .then(placeholderModifier),
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun PlaceholderPreview() {
    GrapesTheme {
        val shimmerPlaceholderModifier = Modifier.placeholder(
            visible = true,
            highlight = PlaceholderHighlight.shimmer(
                highlightColor = PlaceholderDefaults.shimmerHighlightColor(),
            ),
        )
        val fadePlaceholderModifier = Modifier.placeholder(
            visible = true,
            highlight = PlaceholderHighlight.fade(
                highlightColor = PlaceholderDefaults.fadeHighlightColor(),
            )
        )
        Row {
            PlaceholderContentPreview(
                placeholderModifier = shimmerPlaceholderModifier,
                modifier = Modifier.weight(1f),
            )
            PlaceholderContentPreview(
                placeholderModifier = fadePlaceholderModifier,
                modifier = Modifier.weight(1f),
            )
        }
    }
}
