package com.spendesk.grapes.compose.textblock

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.spendesk.grapes.compose.textblock.actions.GrapesRetryTextBlockAction
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author Kélian CLERC
 * @since 16/01/2024
 */
@Composable
fun GrapesTextBlockHeader(
    title: String,
    modifier: Modifier = Modifier,
    informativeLabel: (@Composable () -> Unit)? = null,
    action: (@Composable () -> Unit)? = null,
) {
    Row(modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        HeaderText(
            title = title,
            modifier = Modifier
                .weight(1f),
            informativeLabel = informativeLabel
        )
        Spacer(modifier = Modifier.width(GrapesTheme.dimensions.spacing3))
        action?.invoke()
    }
}

@Composable
private fun HeaderText(
    title: String,
    modifier: Modifier = Modifier,
    informativeLabel: (@Composable () -> Unit)? = null,
) {
    Row(modifier = modifier.wrapContentWidth(align = Alignment.Start), verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = title,
            style = GrapesTheme.typography.titleS,
            color = GrapesTheme.colors.structureComplementary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f, fill = false)
        )
        informativeLabel?.let {
            Spacer(modifier = Modifier.width(GrapesTheme.dimensions.spacing1))
            informativeLabel.invoke()
        }
    }
}

@Preview
@Composable
private fun TextBlockHeaderPreview(
    @PreviewParameter(TextBlockHeaderProvider::class) item: Triple<String, String?, String?>,
) {
    val informativeLabel: (@Composable () -> Unit)? = item.second?.let {
        {
            GrapesTextBlockInformativeLabel(
                label = "• $it",
                color = GrapesTheme.colors.warningDark,
            )
        }
    }
    val action: (@Composable () -> Unit)? = item.third?.let {
        {
            GrapesRetryTextBlockAction(retryLabel = it, onRetryClicked = { /*TODO*/ })
        }
    }

    GrapesTextBlockHeader(title = item.first, informativeLabel = informativeLabel, action = action, modifier = Modifier.padding(GrapesTheme.dimensions.spacing3))
}

private class TextBlockHeaderProvider : PreviewParameterProvider<Triple<String, String?, String?>> {
    override val values = sequenceOf(
        Triple("Title", null, null),
        Triple("This is an example of a very long key very long key very long long very key", null, null),
        Triple("Title", "Short value", null),
        Triple("This is an example of a very long key very long key very long long very key", "Short value", null),
        Triple("Title", null, "Short action"),
        Triple("This is an example of a very long key very long key very long long very key", null, "Short action"),
        Triple("Title", "Short value", "Short action"),
        Triple("This is an example of a very long key", "Short value", "Short action"),
        Triple("This is an example of a very long key", "Short value", "Short action"),
        Triple("This is an example of a very long key", "Long value of a very long value", "Short action"),
        Triple("This is an example of a very long key", "Long value of a very long value", "Long action with a very long action"),
        Triple("Title", "Long value very long key very long key very long long very key", null),
        Triple("This is an example of a very long key very long key very long long very key", "Long value very long key very long key very long long very key", null),
    )
}
