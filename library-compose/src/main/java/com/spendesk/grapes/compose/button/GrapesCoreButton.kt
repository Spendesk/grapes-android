package com.spendesk.grapes.compose.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.spendesk.grapes.compose.theme.GrapesTheme

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
    fillMaxWidthContent: Boolean = false,
    leadingIcon: (@Composable () -> Unit)? = null,
    onClick: (() -> Unit) = {},
    content: @Composable RowScope.() -> Unit,
) {
    val interactionSource = if (showLoadingIndicator) {
        NoRippleInteractionSource()
    } else {
        remember { MutableInteractionSource() }
    }

    val iconColor = colors.contentColor(enabled = enabled).value
    val decoratedLeading: @Composable (() -> Unit)? = leadingIcon?.let {
        @Composable {
            Decoration(contentColor = iconColor, content = it)
        }
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
                val contentColor = colors.contentColor(enabled = enabled)

                if (showLoadingIndicator) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(iconSize),
                        color = contentColor.value,
                        strokeWidth = GrapesButtonDefaults.CircularIndicatorBorderThickness,
                    )
                } else {
                    DecoratedContent(
                        fillMaxWidthContent = fillMaxWidthContent,
                        iconSize = iconSize,
                        leadingIcon = decoratedLeading,
                        textStyle = style,
                        contentColor = contentColor.value,
                    ) {
                        content()
                    }
                }
            }
        )
    }
}

@Composable
private fun RowScope.DecoratedContent(
    fillMaxWidthContent: Boolean,
    iconSize: Dp,
    leadingIcon: (@Composable () -> Unit)? = null,
    textStyle: TextStyle,
    contentColor: Color,
    content: @Composable () -> Unit
) {
    if (leadingIcon != null) {
        ContentWithIcon(
            fillMaxWidthContent = fillMaxWidthContent,
            iconSize = iconSize,
            textStyle = textStyle,
            contentColor = contentColor,
            icon = leadingIcon
        ) {
            content()
        }
    } else {
        Decoration(
            contentColor = contentColor,
            typography = textStyle
        ) {
            content()
        }
    }
}

@Composable
private fun RowScope.ContentWithIcon(
    fillMaxWidthContent: Boolean,
    iconSize: Dp,
    textStyle: TextStyle,
    contentColor: Color,
    icon: @Composable () -> Unit,
    content: @Composable () -> Unit
) {
    if (fillMaxWidthContent) {
        FillContentWithIcon(
            iconSize = iconSize,
            icon = icon,
            textStyle = textStyle,
            contentColor = contentColor,
            content = content
        )
    } else {
        RowContentWithIcon(
            iconSize = iconSize,
            icon = icon,
            textStyle = textStyle,
            contentColor = contentColor,
            content = content
        )
    }
}

@Composable
private fun FillContentWithIcon(
    iconSize: Dp,
    icon: @Composable () -> Unit,
    textStyle: TextStyle,
    contentColor: Color,
    content: @Composable () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BoxedIcon(
            iconSize = iconSize,
            icon = icon
        )

        Spacer(modifier = Modifier.weight(1f))
        Decoration(
            contentColor = contentColor,
            typography = textStyle,
            content = content
        )
        Spacer(modifier = Modifier.weight(1f))
        Spacer(modifier = Modifier.width(iconSize))
    }
}

@Composable
private fun RowScope.RowContentWithIcon(
    iconSize: Dp,
    icon: @Composable () -> Unit,
    textStyle: TextStyle,
    contentColor: Color,
    content: @Composable () -> Unit
) {
    BoxedIcon(
        iconSize = iconSize,
        icon = icon
    )

    Decoration(
        contentColor = contentColor,
        typography = textStyle,
        content = content
    )
}

@Composable
private fun RowScope.BoxedIcon(
    iconSize: Dp,
    icon: @Composable () -> Unit,
) {
    val paddingValues = PaddingValues(end = GrapesTheme.dimensions.paddingSmall)
    val direction = LocalLayoutDirection.current
    Box(
        modifier = Modifier
            .size(
                width = iconSize + paddingValues.calculateEndPadding(direction),
                height = iconSize,
            )
            .padding(paddingValues)
            .align(Alignment.CenterVertically),
        contentAlignment = Alignment.Center,
    ) {
        icon()
    }
}

@Composable
private fun Decoration(
    contentColor: Color,
    typography: TextStyle? = null,
    content: @Composable () -> Unit
) {
    val contentWithColor: @Composable () -> Unit = @Composable {
        CompositionLocalProvider(
            LocalContentColor provides contentColor,
            content = content
        )
    }
    if (typography != null) ProvideTextStyle(typography, contentWithColor) else contentWithColor()
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
