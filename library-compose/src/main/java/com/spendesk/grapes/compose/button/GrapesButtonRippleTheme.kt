package com.spendesk.grapes.compose.button

import androidx.compose.material3.LocalContentColor
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author jean-philippe
 * @since 19/01/2023, Thursday
 **/
@Immutable
internal class GrapesButtonRippleTheme(val rippleColor: Color) : RippleTheme {
    @Composable
    override fun defaultColor() = RippleTheme.defaultRippleColor(
        contentColor = rippleColor,
        lightTheme = GrapesTheme.colors.isLight
    )

    @Composable
    override fun rippleAlpha() = GrapesButtonDefaults.buttonRippleAlpha(
        contentColor = LocalContentColor.current,
        lightTheme = GrapesTheme.colors.isLight
    )
}
