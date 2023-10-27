package com.spendesk.grapes.compose.callout.molecules

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author jean-philippe
 * @since 17/10/2023, Tuesday
 **/
@Composable
fun GrapesCalloutContent(
    description: String,
    bottomContent: (@Composable () -> Unit)? = null,
) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = description,
        style = GrapesTheme.typography.bodyS,
    )

    bottomContent?.invoke()
}

@Composable
fun GrapesCalloutContent(
    description: AnnotatedString,
    bottomContent: (@Composable () -> Unit)? = null,
) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = description,
        style = GrapesTheme.typography.bodyS,
    )

    bottomContent?.invoke()
}
