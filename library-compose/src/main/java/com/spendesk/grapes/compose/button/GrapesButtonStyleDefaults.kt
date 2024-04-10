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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
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
                textStyle = GrapesTheme.typography.titleL,
                minSize = Size(
                    width = GrapesButtonDefaults.MinWidth.value,
                    height = GrapesButtonDefaults.MinHeight.value
                ),
                iconSize = GrapesButtonDefaults.iconSize,
                shape = GrapesTheme.shapes.shape2,
                rippleColor = GrapesTheme.colors.mainPrimaryDark,
                colors = ButtonColorDefaults.primary,
                contentPadding = GrapesButtonDefaults.PaddingValues,
                borderStroke = null,
                isFillMaxWidthWithContent = true,
            )

    val primarySmall: GrapesButtonStyle
        @Composable
        @ReadOnlyComposable
        get() = GrapesButtonStyle(
            textStyle = GrapesTheme.typography.titleM,
            minSize = Size(
                width = GrapesButtonDefaults.MinWidthSmall.value,
                height = GrapesButtonDefaults.MinHeightSmall.value
            ),
            iconSize = GrapesButtonDefaults.iconSizeSmall,
            shape = GrapesTheme.shapes.shape2,
            rippleColor = GrapesTheme.colors.mainPrimaryDark,
            colors = ButtonColorDefaults.primary,
            contentPadding = GrapesButtonDefaults.PaddingValuesSmall,
            borderStroke = null,
            isFillMaxWidthWithContent = false,
        )

    val secondary: GrapesButtonStyle
        @Composable
        @ReadOnlyComposable
        get() = GrapesButtonStyle(
            textStyle = GrapesTheme.typography.titleL,
            minSize = Size(
                width = GrapesButtonDefaults.MinWidth.value,
                height = GrapesButtonDefaults.MinHeight.value
            ),
            iconSize = GrapesButtonDefaults.iconSize,
            shape = GrapesTheme.shapes.shape2,
            rippleColor = GrapesTheme.colors.mainNeutralNormal,
            colors = ButtonColorDefaults.secondary,
            contentPadding = GrapesButtonDefaults.PaddingValues,
            borderStroke = GrapesButtonDefaults.BorderStroke.secondary,
            isFillMaxWidthWithContent = true,
        )

    val secondarySmall: GrapesButtonStyle
        @Composable
        @ReadOnlyComposable
        get() = GrapesButtonStyle(
            textStyle = GrapesTheme.typography.titleM,
            minSize = Size(
                width = GrapesButtonDefaults.MinWidthSmall.value,
                height = GrapesButtonDefaults.MinHeightSmall.value
            ),
            iconSize = GrapesButtonDefaults.iconSizeSmall,
            shape = GrapesTheme.shapes.shape2,
            rippleColor = GrapesTheme.colors.mainNeutralNormal,
            colors = ButtonColorDefaults.secondary,
            contentPadding = GrapesButtonDefaults.PaddingValuesSmall,
            borderStroke = GrapesButtonDefaults.BorderStroke.secondary,
            isFillMaxWidthWithContent = false,
        )

    val tertiary: GrapesButtonStyle
        @Composable
        @ReadOnlyComposable
        get() = GrapesButtonStyle(
            textStyle = GrapesTheme.typography.titleL,
            minSize = Size(
                width = GrapesButtonDefaults.MinWidth.value,
                height = GrapesButtonDefaults.MinHeight.value
            ),
            iconSize = GrapesButtonDefaults.iconSize,
            shape = GrapesTheme.shapes.shape2,
            rippleColor = GrapesTheme.colors.primaryLighter,
            colors = ButtonColorDefaults.tertiary,
            contentPadding = GrapesButtonDefaults.PaddingValues,
            borderStroke = null,
            isFillMaxWidthWithContent = true,
        )

    val text: GrapesButtonStyle
        @Composable
        @ReadOnlyComposable
        get() = GrapesButtonStyle(
            textStyle = GrapesTheme.typography.titleL,
            minSize = Size(
                width = GrapesButtonDefaults.MinWidth.value,
                height = GrapesButtonDefaults.MinHeight.value
            ),
            iconSize = GrapesButtonDefaults.iconSize,
            shape = GrapesTheme.shapes.shape2,
            rippleColor = GrapesTheme.colors.mainPrimaryLight,
            colors = ButtonColorDefaults.text,
            contentPadding = GrapesButtonDefaults.PaddingValues,
            borderStroke = null,
            isFillMaxWidthWithContent = false,
        )

    val textSmall: GrapesButtonStyle
        @Composable
        @ReadOnlyComposable
        get() = GrapesButtonStyle(
            textStyle = GrapesTheme.typography.titleM,
            minSize = Size(
                width = GrapesButtonDefaults.MinWidthSmall.value,
                height = GrapesButtonDefaults.MinHeightSmall.value
            ),
            iconSize = GrapesButtonDefaults.iconSizeSmall,
            shape = GrapesTheme.shapes.shape2,
            rippleColor = GrapesTheme.colors.mainPrimaryLight,
            colors = ButtonColorDefaults.text,
            contentPadding = GrapesButtonDefaults.PaddingValuesSmall,
            borderStroke = null,
            isFillMaxWidthWithContent = false,
        )

    val alert: GrapesButtonStyle
        @Composable
        @ReadOnlyComposable
        get() = GrapesButtonStyle(
            textStyle = GrapesTheme.typography.titleL,
            minSize = Size(
                width = GrapesButtonDefaults.MinWidth.value,
                height = GrapesButtonDefaults.MinHeight.value
            ),
            iconSize = GrapesButtonDefaults.iconSize,
            shape = GrapesTheme.shapes.shape2,
            rippleColor = GrapesTheme.colors.alertDark,
            colors = ButtonColorDefaults.alert,
            contentPadding = GrapesButtonDefaults.PaddingValues,
            borderStroke = null,
            isFillMaxWidthWithContent = true,
        )

    val alertOutlined: GrapesButtonStyle
        @Composable
        @ReadOnlyComposable
        get() = GrapesButtonStyle(
            textStyle = GrapesTheme.typography.titleL,
            minSize = Size(
                width = GrapesButtonDefaults.MinWidth.value,
                height = GrapesButtonDefaults.MinHeight.value
            ),
            iconSize = GrapesButtonDefaults.iconSize,
            shape = GrapesTheme.shapes.shape2,
            rippleColor = GrapesTheme.colors.alertDark,
            colors = ButtonColorDefaults.alertOutlined,
            contentPadding = GrapesButtonDefaults.PaddingValues,
            borderStroke = GrapesButtonDefaults.BorderStroke.alert,
            isFillMaxWidthWithContent = true,
        )

    val warning: GrapesButtonStyle
        @Composable
        @ReadOnlyComposable
        get() = GrapesButtonStyle(
            textStyle = GrapesTheme.typography.titleL,
            minSize = Size(
                width = GrapesButtonDefaults.MinWidth.value,
                height = GrapesButtonDefaults.MinHeight.value
            ),
            iconSize = GrapesButtonDefaults.iconSize,
            shape = GrapesTheme.shapes.shape2,
            rippleColor = GrapesTheme.colors.mainWarningDark,
            colors = ButtonColorDefaults.warning,
            contentPadding = GrapesButtonDefaults.PaddingValues,
            borderStroke = null,
            isFillMaxWidthWithContent = true,
        )

    val linkPrimary: GrapesButtonStyle
        @Composable
        @ReadOnlyComposable
        get() = GrapesButtonStyle(
            textStyle = GrapesTheme.typography.titleL,
            minSize = Size(
                width = GrapesButtonDefaults.MinWidthSmall.value,
                height = GrapesButtonDefaults.MinHeightSmall.value
            ),
            iconSize = GrapesButtonDefaults.iconSizeSmall,
            shape = GrapesTheme.shapes.shape2,
            rippleColor = GrapesTheme.colors.mainNeutralLight,
            colors = ButtonColorDefaults.linkPrimary,
            contentPadding = GrapesButtonDefaults.PaddingValuesSmall,
            borderStroke = null,
            isFillMaxWidthWithContent = false,
        )

    val linkSecondary: GrapesButtonStyle
        @Composable
        @ReadOnlyComposable
        get() = GrapesButtonStyle(
            textStyle = GrapesTheme.typography.titleL,
            minSize = Size(
                width = GrapesButtonDefaults.MinWidthSmall.value,
                height = GrapesButtonDefaults.MinHeightSmall.value
            ),
            iconSize = GrapesButtonDefaults.iconSizeSmall,
            shape = GrapesTheme.shapes.shape2,
            rippleColor = GrapesTheme.colors.mainNeutralLighter,
            colors = ButtonColorDefaults.linkSecondary,
            contentPadding = GrapesButtonDefaults.PaddingValuesSmall,
            borderStroke = null,
            isFillMaxWidthWithContent = false,
        )

    val google: GrapesButtonStyle
        @Composable
        @ReadOnlyComposable
        get() = GrapesButtonStyle(
            textStyle = GrapesTheme.typography.titleL,
            minSize = Size(
                width = GrapesButtonDefaults.MinWidthSmall.value,
                height = GrapesButtonDefaults.MinHeightSmall.value
            ),
            iconSize = 40.dp,
            shape = GrapesTheme.shapes.shape2,
            rippleColor = GrapesTheme.colors.mainNeutralLighter,
            colors = ButtonColorDefaults.google,
            contentPadding = GrapesButtonDefaults.PaddingValuesBrand,
            borderStroke = null,
            isFillMaxWidthWithContent = true,
        )

    val microsoft: GrapesButtonStyle
        @Composable
        @ReadOnlyComposable
        get() = GrapesButtonStyle(
            textStyle = GrapesTheme.typography.titleL,
            minSize = Size(
                width = GrapesButtonDefaults.MinWidthSmall.value,
                height = GrapesButtonDefaults.MinHeightSmall.value
            ),
            iconSize = 40.dp,
            shape = GrapesTheme.shapes.shape2,
            rippleColor = GrapesTheme.colors.mainNeutralLighter,
            colors = ButtonColorDefaults.secondary,
            contentPadding = GrapesButtonDefaults.PaddingValuesBrand,
            borderStroke = GrapesButtonDefaults.BorderStroke.secondary,
            isFillMaxWidthWithContent = true,
        )

    val saml: GrapesButtonStyle
        @Composable
        @ReadOnlyComposable
        get() = GrapesButtonStyle(
            textStyle = GrapesTheme.typography.titleL,
            minSize = Size(
                width = GrapesButtonDefaults.MinWidthSmall.value,
                height = GrapesButtonDefaults.MinHeightSmall.value
            ),
            iconSize = 40.dp,
            shape = GrapesTheme.shapes.shape2,
            rippleColor = GrapesTheme.colors.mainNeutralLighter,
            colors = ButtonColorDefaults.secondary,
            contentPadding = GrapesButtonDefaults.PaddingValuesBrand,
            borderStroke = GrapesButtonDefaults.BorderStroke.secondary,
            isFillMaxWidthWithContent = true,
        )
}

@Immutable
data class GrapesButtonStyle(
    val textStyle: TextStyle,
    val minSize: Size,
    val iconSize: Dp,
    val shape: Shape,
    val rippleColor: Color,
    val colors: ButtonColors,
    val contentPadding: PaddingValues,
    val borderStroke: BorderStroke?,
    internal val isFillMaxWidthWithContent: Boolean,
)
