package com.spendesk.grapes.compose.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.ButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author jean-philippe
 * @since 19/01/2023, Thursday
 **/
@Immutable
object GrapesButtonStyleDefaults {

    val primary: GrapesButtonStyle
        @Composable
        @ReadOnlyComposable
        get() =
            GrapesButtonStyle(
                textStyle = GrapesTheme.typography.titleM,
                minSize = Size(
                    width = GrapesButtonDefaults.MinWidth.value,
                    height = GrapesButtonDefaults.MinHeight.value
                ),
                shape = GrapesTheme.shapes.small,
                rippleColor = GrapesTheme.colors.mainPrimaryDark,
                colors = ButtonColorDefaults.primary,
                contentPadding = GrapesButtonDefaults.PaddingValues,
                borderStroke = null
            )

    val primarySmall: GrapesButtonStyle
        @Composable
        @ReadOnlyComposable
        get() = GrapesButtonStyle(
            textStyle = GrapesTheme.typography.titleS,
            minSize = Size(
                width = GrapesButtonDefaults.MinWidthSmall.value,
                height = GrapesButtonDefaults.MinHeightSmall.value
            ),
            shape = GrapesTheme.shapes.small,
            rippleColor = GrapesTheme.colors.mainPrimaryDark,
            colors = ButtonColorDefaults.primary,
            contentPadding = GrapesButtonDefaults.PaddingValuesSmall,
            borderStroke = null
        )

    val secondary: GrapesButtonStyle
        @Composable
        @ReadOnlyComposable
        get() = GrapesButtonStyle(
            textStyle = GrapesTheme.typography.titleM,
            minSize = Size(
                width = GrapesButtonDefaults.MinWidth.value,
                height = GrapesButtonDefaults.MinHeight.value
            ),
            shape = GrapesTheme.shapes.small,
            rippleColor = GrapesTheme.colors.mainNeutralNormal,
            colors = ButtonColorDefaults.secondary,
            contentPadding = GrapesButtonDefaults.PaddingValues,
            borderStroke = GrapesButtonDefaults.BorderStroke.secondary
        )

    val text: GrapesButtonStyle
        @Composable
        @ReadOnlyComposable
        get() = GrapesButtonStyle(
            textStyle = GrapesTheme.typography.titleM,
            minSize = Size(
                width = GrapesButtonDefaults.MinWidth.value,
                height = GrapesButtonDefaults.MinHeight.value
            ),
            shape = GrapesTheme.shapes.small,
            rippleColor = GrapesTheme.colors.mainPrimaryLight,
            colors = ButtonColorDefaults.text,
            contentPadding = GrapesButtonDefaults.PaddingValues,
            borderStroke = null
        )

    val textSmall: GrapesButtonStyle
        @Composable
        @ReadOnlyComposable
        get() = GrapesButtonStyle(
            textStyle = GrapesTheme.typography.titleS,
            minSize = Size(
                width = GrapesButtonDefaults.MinWidthSmall.value,
                height = GrapesButtonDefaults.MinHeightSmall.value
            ),
            shape = GrapesTheme.shapes.small,
            rippleColor = GrapesTheme.colors.mainPrimaryLight,
            colors = ButtonColorDefaults.text,
            contentPadding = GrapesButtonDefaults.PaddingValuesSmall,
            borderStroke = null
        )

    val secondarySmall: GrapesButtonStyle
        @Composable
        @ReadOnlyComposable
        get() = GrapesButtonStyle(
            textStyle = GrapesTheme.typography.titleS,
            minSize = Size(
                width = GrapesButtonDefaults.MinWidthSmall.value,
                height = GrapesButtonDefaults.MinHeightSmall.value
            ),
            shape = GrapesTheme.shapes.small,
            rippleColor = GrapesTheme.colors.mainNeutralNormal,
            colors = ButtonColorDefaults.secondary,
            contentPadding = GrapesButtonDefaults.PaddingValuesSmall,
            borderStroke = GrapesButtonDefaults.BorderStroke.secondary
        )

    val alert: GrapesButtonStyle
        @Composable
        @ReadOnlyComposable
        get() = GrapesButtonStyle(
            textStyle = GrapesTheme.typography.titleM,
            minSize = Size(
                width = GrapesButtonDefaults.MinWidth.value,
                height = GrapesButtonDefaults.MinHeight.value
            ),
            shape = GrapesTheme.shapes.small,
            rippleColor = GrapesTheme.colors.mainAlertDark,
            colors = ButtonColorDefaults.alert,
            contentPadding = GrapesButtonDefaults.PaddingValues,
            borderStroke = null
        )

    val warning: GrapesButtonStyle
        @Composable
        @ReadOnlyComposable
        get() = GrapesButtonStyle(
            textStyle = GrapesTheme.typography.titleM,
            minSize = Size(
                width = GrapesButtonDefaults.MinWidth.value,
                height = GrapesButtonDefaults.MinHeight.value
            ),
            shape = GrapesTheme.shapes.small,
            rippleColor = GrapesTheme.colors.mainWarningDark,
            colors = ButtonColorDefaults.warning,
            contentPadding = GrapesButtonDefaults.PaddingValues,
            borderStroke = null
        )

    val linkPrimary: GrapesButtonStyle
        @Composable
        @ReadOnlyComposable
        get() = GrapesButtonStyle(
            textStyle = GrapesTheme.typography.titleM,
            minSize = Size(
                width = GrapesButtonDefaults.MinWidthSmall.value,
                height = GrapesButtonDefaults.MinHeightSmall.value
            ),
            shape = GrapesTheme.shapes.small,
            rippleColor = GrapesTheme.colors.mainNeutralLight,
            colors = ButtonColorDefaults.linkPrimary,
            contentPadding = GrapesButtonDefaults.PaddingValuesSmall,
            borderStroke = null
        )

    val linkSecondary: GrapesButtonStyle
        @Composable
        @ReadOnlyComposable
        get() = GrapesButtonStyle(
            textStyle = GrapesTheme.typography.titleM,
            minSize = Size(
                width = GrapesButtonDefaults.MinWidthSmall.value,
                height = GrapesButtonDefaults.MinHeightSmall.value
            ),
            shape = GrapesTheme.shapes.small,
            rippleColor = GrapesTheme.colors.mainNeutralLighter,
            colors = ButtonColorDefaults.linkSecondary,
            contentPadding = GrapesButtonDefaults.PaddingValuesSmall,
            borderStroke = null
        )
}

@Immutable
data class GrapesButtonStyle(
    val textStyle: TextStyle,
    val minSize: Size,
    val shape: Shape,
    val rippleColor: Color,
    val colors: ButtonColors,
    val contentPadding: PaddingValues,
    val borderStroke: BorderStroke?,
)
