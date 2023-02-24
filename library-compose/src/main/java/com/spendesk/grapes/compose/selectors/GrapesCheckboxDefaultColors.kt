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
object GrapesCheckboxDefaultColors {

    val checkedColor: Color @Composable get() = GrapesTheme.colors.mainPrimaryNormal
    val uncheckedColor: Color @Composable get() = GrapesTheme.colors.mainNeutralDark
    val checkmarkColor: Color @Composable get() = GrapesTheme.colors.mainWhite
    val disabledCheckedColor: Color @Composable get() = GrapesTheme.colors.mainNeutralLight
    val disabledUncheckedColor: Color @Composable get() = GrapesTheme.colors.mainNeutralLight
    val disabledIndeterminateColor: Color @Composable get() = GrapesTheme.colors.mainNeutralNormal
}
