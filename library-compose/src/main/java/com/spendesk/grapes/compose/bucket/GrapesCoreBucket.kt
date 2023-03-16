package com.spendesk.grapes.compose.bucket

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author jean-philippe
 * @since 15/03/2023, Wednesday
 **/
@Composable
internal fun GrapesBucketCore(
    modifier: Modifier = Modifier,
    headline: @Composable (() -> Unit)? = null,
    content: @Composable (() -> Unit)? = null,
    decorationBox: @Composable (innerContent: @Composable () -> Unit) -> Unit =
        @Composable { innerContent -> innerContent() }
) {
    Box(modifier = modifier) {
        decorationBox {
            Column {
                if (headline != null) {
                    val paddingValues = PaddingValues(
                        start = 0.dp,
                        top = 0.dp,
                        end = 0.dp,
                        bottom = when {
                            content != null -> GrapesTheme.dimensions.paddingLarge
                            else -> 0.dp
                        },
                    )

                    Box(modifier = Modifier.padding(paddingValues)) {
                        headline()
                    }
                }

                content?.invoke()
            }
        }
    }
}
