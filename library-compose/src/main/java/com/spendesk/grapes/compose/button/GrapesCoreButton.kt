package com.spendesk.grapes.compose.button

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * @author jean-philippe
 * @since 16/02/2023, Thursday
 **/
@Composable
internal fun GrapesCoreButton(
    modifier: Modifier = Modifier,
    buttonStyle: GrapesButtonStyle = GrapesButtonStyleDefaults.primary,
    enabled: Boolean = true,
    showLoadingIndicator: Boolean = false,
    onClick: (() -> Unit) = {},
    content: @Composable RowScope.() -> Unit,
) {
    val interactionSource = if (showLoadingIndicator) {
        NoRippleInteractionSource()
    } else {
        remember { MutableInteractionSource() }
    }

    ProvideButtonRippleColor(
        rippleColor = buttonStyle.rippleColor
    ) {
        Button(
            modifier = modifier
                .widthIn(
                    min = buttonStyle.minSize.width.dp
                )
                .heightIn(
                    min = buttonStyle.minSize.height.dp
                ),
            shape = buttonStyle.shape,
            border = buttonStyle.borderStroke,
            colors = buttonStyle.colors,
            contentPadding = buttonStyle.contentPadding,
            elevation = null,
            enabled = enabled,
            onClick = onClick.takeUnless { showLoadingIndicator } ?: {},
            interactionSource = interactionSource,
            content = {
                if (showLoadingIndicator) {
                    val contentColor = buttonStyle.colors.contentColor(enabled = enabled)
                    CircularProgressIndicator(
                        modifier = Modifier.size(buttonStyle.iconSize),
                        color = contentColor.value,
                        strokeWidth = GrapesButtonDefaults.CircularIndicatorBorderThickness,
                    )
                } else {
                    ProvideTextStyle(value = buttonStyle.textStyle) {
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
