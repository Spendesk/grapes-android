package com.spendesk.grapes.compose.button

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author : danyboucanova
 * @since : 16/05/2022, Mon
 **/

enum class Style {
    PRIMARY,
    SECONDARY,
    WARNING,
    ALERT
}

@Composable
fun GrapesButton(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    styleState: State<Style> = mutableStateOf(Style.PRIMARY),
    text: CharSequence
) {
    val style = remember { styleState }
    Text(
        text = text.toString(),
        color = GrapesTheme.colors.mainWhite,
        modifier = modifier
    )
}

@Preview("default", "round")
@Preview("dark theme", "round", uiMode = UI_MODE_NIGHT_NO)
@Preview("large font", "round", fontScale = 2f)
@Composable
private fun ButtonPreview() {
    GrapesTheme {
        GrapesButton(text = "MAIS OUI")
    }
}