package com.spendesk.grapes.compose.callout.molecules

import androidx.compose.foundation.layout.ColumnScope
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
fun ColumnScope.GrapesCalloutContent(
    description: String,
    bottomContent: (@Composable ColumnScope.() -> Unit)? = null,
) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = description,
        style = GrapesTheme.typography.bodyS,
    )

    bottomContent?.invoke(this)
}

@Composable
fun ColumnScope.GrapesCalloutContent(
    description: AnnotatedString,
    bottomContent: (@Composable ColumnScope.() -> Unit)? = null,
) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = description,
        style = GrapesTheme.typography.bodyS,
    )

    bottomContent?.invoke(this)
}
