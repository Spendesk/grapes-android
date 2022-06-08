package com.spendesk.grapes.compose.button

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author : danyboucanova
 * @since : 16/05/2022, Mon
 **/

@Composable
fun GrapesButton(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    text: CharSequence
) {
    Text(
        text = text.toString(),
        color = GrapesTheme.colors.mainWhite,
        modifier = modifier,
        style = GrapesTheme.typography.titleXl
    )
}

@Preview("default", "button")
@Preview("dark theme", "button", uiMode = UI_MODE_NIGHT_NO)
@Preview("large font", "button", fontScale = 2f)
@Composable
private fun ButtonPreview() {
    GrapesTheme {
        GrapesButton(text = "MAIS OUI")
    }
}