package com.spendesk.grapes.compose.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author Kélian CLERC
 * @since 01/11/2022
 */

data class SuggestionsContent(
    val label: String,
    val rawValue: String
) {
    val id: String
        get() = "suggestionId_${rawValue}_${label}"
}

@Composable
fun Suggestions(
    suggestions: List<SuggestionsContent>,
    onSuggestionClicked: (SuggestionsContent) -> Unit
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        suggestions.forEach {
            Text(
                text = it.label,
                modifier = Modifier
                    .clickable { onSuggestionClicked(it) }
                    .weight(1f)
                    .padding(12.dp),
                style = GrapesTheme.typography.bodyRegular,
                color = GrapesTheme.colors.mainNeutralDarker,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
fun SuggestionsPreview() {
    val content = listOf(
        SuggestionsContent("50 €", "50"),
        SuggestionsContent("100 €", "100"),
        SuggestionsContent("200 €", "200"),
        SuggestionsContent("500 €", "500"),
    )
    GrapesTheme {
        Column(modifier = Modifier.width(400.dp)) {
            Suggestions(suggestions = content, onSuggestionClicked = {})
        }
    }
}
