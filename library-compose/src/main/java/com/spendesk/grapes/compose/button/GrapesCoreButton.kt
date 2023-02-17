package com.spendesk.grapes.compose.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * @author jean-philippe
 * @since 16/02/2023, Thursday
 **/
@Composable
internal fun GrapesCoreButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    rippleColor: Color = GrapesButtonStyleDefaults.primary.rippleColor,
    colors: ButtonColors = GrapesButtonStyleDefaults.primary.colors,
    minSize: Size = GrapesButtonStyleDefaults.primary.minSize,
    iconSize: Dp = GrapesButtonStyleDefaults.primary.iconSize,
    contentPaddingValues: PaddingValues = GrapesButtonStyleDefaults.primary.contentPadding,
    shape: Shape = GrapesButtonStyleDefaults.primary.shape,
    borderStroke: BorderStroke? = GrapesButtonStyleDefaults.primary.borderStroke,
    style: TextStyle = GrapesButtonStyleDefaults.primary.textStyle,
    showLoadingIndicator: Boolean = false,
    onClick: (() -> Unit) = {},
    content: @Composable RowScope.() -> Unit,
) {
    val interactionSource = if (showLoadingIndicator) {
        NoRippleInteractionSource()
    } else {
        remember { MutableInteractionSource() }
    }

    ProvideButtonRippleColor(rippleColor = rippleColor) {
        Button(
            modifier = modifier
                .widthIn(
                    min = minSize.width.dp
                )
                .heightIn(
                    min = minSize.height.dp
                ),
            shape = shape,
            border = borderStroke,
            colors = colors,
            contentPadding = contentPaddingValues,
            elevation = null,
            enabled = enabled,
            onClick = onClick.takeUnless { showLoadingIndicator } ?: {},
            interactionSource = interactionSource,
            content = {
                if (showLoadingIndicator) {
                    val contentColor = colors.contentColor(enabled = enabled)
                    CircularProgressIndicator(
                        modifier = Modifier.size(iconSize),
                        color = contentColor.value,
                        strokeWidth = GrapesButtonDefaults.CircularIndicatorBorderThickness,
                    )
                } else {
                    ProvideTextStyle(value = style) {
                        content()
                    }
                }
            }
        )
    }
}

/**
 * Default ripple theme use [LocalContentColor] as Ripple color but we require different
 * colors depending on button.
 * We need to provide [LocalRippleTheme] with specific color.
 */
@Composable
private fun ProvideButtonRippleColor(rippleColor: Color, content: @Composable () -> Unit) {
    CompositionLocalProvider(
        LocalRippleTheme provides GrapesButtonRippleTheme(rippleColor = rippleColor),
        content = content
    )
}
