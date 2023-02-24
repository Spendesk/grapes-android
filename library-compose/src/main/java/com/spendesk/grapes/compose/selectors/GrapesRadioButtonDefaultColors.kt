package com.spendesk.grapes.compose.selectors

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author : dany
 * @since : 23/02/2023, Thu
 **/
@Immutable
object GrapesRadioButtonDefaultColors {

    val selectedColor: Color @Composable get() = GrapesTheme.colors.mainPrimaryNormal
    val unselectedColor: Color @Composable get() = GrapesTheme.colors.mainNeutralDark
    val disabledSelectedColor: Color @Composable get() = GrapesTheme.colors.mainPrimaryLighter
    val disabledUnselectedColor: Color @Composable get() = GrapesTheme.colors.mainNeutralLight
}
