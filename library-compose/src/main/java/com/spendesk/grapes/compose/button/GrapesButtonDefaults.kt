package com.spendesk.grapes.compose.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.unit.dp
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author jean-philippe
 * @since 19/01/2023, Thursday
 **/
@Immutable
object GrapesButtonDefaults {
    private val PaddingHorizontal = 16.dp
    private val PaddingVertical = 16.dp

    private val SmallPaddingHorizontal = 24.dp
    private val SmallPaddingVertical = 8.dp

    val PaddingValues: PaddingValues = PaddingValues(
        horizontal = PaddingHorizontal,
        vertical = PaddingVertical,
    )

    val PaddingValuesSmall: PaddingValues = PaddingValues(
        horizontal = SmallPaddingHorizontal,
        vertical = SmallPaddingVertical,
    )

    val MinWidth = 340.dp
    val MinHeight = 48.dp

    val MinWidthSmall = 149.dp
    val MinHeightSmall = 32.dp

    val iconSize = 16.dp
    val iconSizeSmall = 14.dp

    private val BorderThickness = 1.dp
    val CircularIndicatorBorderThickness = 2.dp

    //region Ripple
    /**
     *  values based on [androidx.compose.material.ripple.LightThemeLowContrastRippleAlpha]
     */
    private val LightLowContrastRippleAlpha = RippleAlpha(
        pressedAlpha = 0.74f,
        focusedAlpha = 0.12f,
        draggedAlpha = 0.08f,
        hoveredAlpha = 0.04f
    )

    /**
     * values based on [androidx.compose.material.ripple.LightThemeHighContrastRippleAlpha]
     */
    private val LightHighContrastRippleAlpha = RippleAlpha(
        pressedAlpha = 0.84f,
        focusedAlpha = 0.24f,
        draggedAlpha = 0.16f,
        hoveredAlpha = 0.08f
    )

    fun buttonRippleAlpha(contentColor: Color, lightTheme: Boolean): RippleAlpha {
        //fixme DarkTheme not implemented yet
        return if (contentColor.luminance() > 0.5) {
            LightHighContrastRippleAlpha
        } else {
            LightLowContrastRippleAlpha
        }
    }
    //endregion Ripple

    //region BorderStroke
    @Immutable
    object BorderStroke {
        val secondary: androidx.compose.foundation.BorderStroke?
            @Composable
            @ReadOnlyComposable
            get() = BorderStroke(
                BorderThickness,
                GrapesTheme.colors.mainNeutralNormal
            )
    }
    //endregion BorderStroke
}
