package com.spendesk.grapes.compose.button.atoms

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author jean-philippe
 * @since 17/02/2023, Friday
 **/
@Composable
internal fun GrapesButtonContentText(
    text: String,
) {
    Text(
        text = text,
        style = GrapesTheme.typography.titleM,
    )
}
