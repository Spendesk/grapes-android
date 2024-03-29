package com.spendesk.grapes.compose.textblock

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spendesk.grapes.compose.textblock.actions.GrapesRetryTextBlockAction
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author Kélian CLERC
 * @since 15/01/2024
 */
@Composable
fun GrapesTextBlock(
    modifier: Modifier = Modifier,
    header: (@Composable () -> Unit)? = null,
    content: (@Composable () -> Unit)? = null,
) {
    Column(
        modifier = modifier
            .background(GrapesTheme.colors.structureSurface, GrapesTheme.shapes.shape2)
            .border(BorderStroke(0.5.dp, GrapesTheme.colors.neutralLighter), GrapesTheme.shapes.shape2)
    ) {
        header?.invoke()
        content?.invoke()
    }
}

@Preview
@Composable
private fun TextBlockPreview() {
    GrapesTheme {
        GrapesTextBlock(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            header = {
                GrapesTextBlockHeader(
                    title = "Title which is very long and need to be truncated",
                    informativeLabel = {
                        GrapesTextBlockInformativeLabel(
                            label = "• Missing",
                            color = GrapesTheme.colors.warningDark,
                        )
                    },
                    action = {
                        GrapesRetryTextBlockAction(retryLabel = "Retry", onRetryClicked = { /*TODO*/ })
                    },
                    modifier = Modifier
                        .padding(horizontal = GrapesTheme.dimensions.spacing3)
                        .padding(top = GrapesTheme.dimensions.spacing3)
                )
            },
            content = {
                Text(
                    text = "Content",
                    style = GrapesTheme.typography.bodyM,
                    color = GrapesTheme.colors.neutralDark,
                    modifier = Modifier.padding(GrapesTheme.dimensions.spacing3)
                )
            },
        )
    }
}
