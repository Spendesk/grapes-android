package com.spendesk.grapes.compose.button.atoms

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author jean-philippe
 * @since 17/02/2023, Friday
 **/
@Composable
internal fun GrapesButtonContentText(
    text: String,
    textStyle: TextStyle,
) {
    Text(
        text = text,
        style = textStyle,
    )
}
