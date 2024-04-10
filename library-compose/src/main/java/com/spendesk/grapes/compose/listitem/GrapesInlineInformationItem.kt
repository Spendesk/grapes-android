package com.spendesk.grapes.compose.listitem

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author : RomainGF
 * @since : 18/08/2023
 **/
@Composable
fun GrapesInlineInformationItem(
    title: String,
    value: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(GrapesTheme.dimensions.spacing1),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = title,
            color = GrapesTheme.colors.neutralDark,
            style = GrapesTheme.typography.bodyL,
            modifier = Modifier.weight(1f),
        )
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.CenterEnd,
        ) {
            Text(
                text = value,
                style = GrapesTheme.typography.bodyL,
                textAlign = TextAlign.End
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun Preview(
    @PreviewParameter(ItemParameterProvider::class) item: Pair<String, String>,
) {
    GrapesTheme {
        GrapesInlineInformationItem(
            title = item.first,
            value = item.second,
        )
    }
}

private class ItemParameterProvider : PreviewParameterProvider<Pair<String, String>> {
    override val values = sequenceOf(
        "This is an example of a very long key" to "Short value",
        "Short key" to "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s.",
        "This is an example of a very long key" to "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s.",
    )
}
