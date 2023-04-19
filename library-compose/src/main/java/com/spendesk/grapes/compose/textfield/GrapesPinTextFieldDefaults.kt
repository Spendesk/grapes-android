package com.spendesk.grapes.compose.textfield

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author Kélian CLERC
 * @since 18/04/2023
 */
@Immutable
object GrapesPintTextFieldDefaults {
    val EnabledAlpha = 1f
    val DisabledAlpha = 1f

    val PinCharWidth = 48.dp
    val PinCharHeight = 62.dp
    val PinCharBorderWidth = 1.dp
    val PinCharPadding = 2.dp

    @Composable
    fun computePinCharBorderColor(
        isEnabled: Boolean,
        isError: Boolean,
        isFocused: Boolean
    ): Color =
        when {
            isEnabled.not() -> GrapesTheme.colors.mainPrimaryLightest
            isFocused && isError -> GrapesTheme.colors.mainAlertNormal
            isError -> GrapesTheme.colors.mainAlertLightest
            isFocused && isEnabled -> GrapesTheme.colors.mainPrimaryLight
            else -> GrapesTheme.colors.mainPrimaryLighter
        }

    @Composable
    fun computePinCharTextColor(
        isEnabled: Boolean,
        isError: Boolean,
        isFocused: Boolean
    ): Color =
        when {
            isEnabled.not() -> GrapesTheme.colors.mainPrimaryLighter
            isError -> GrapesTheme.colors.mainAlertNormal
            isFocused && isEnabled -> GrapesTheme.colors.mainPrimaryDark
            else -> GrapesTheme.colors.mainPrimaryNormal
        }
}
