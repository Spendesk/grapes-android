package com.spendesk.grapes.compose.textfield

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.ContentAlpha
import androidx.compose.material.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author jean-philippe
 * @since 09/01/2023, Mon
 **/
@Immutable
object GrapesTextFieldDefaults {
    private val TextFieldPaddingVertical = 14.dp
    private val TextFieldPaddingHorizontal = 16.dp

    private val BorderThickness = 0.5.dp

    val MinWidth = 344.dp
    val MinHeight = 40.dp

    val Elevation = 1.dp

    val TextFieldShape: Shape
        @Composable
        @ReadOnlyComposable
        get() = GrapesTheme.shapes.small


    fun textFieldPadding(
        start: Dp = TextFieldPaddingHorizontal,
        top: Dp = TextFieldPaddingVertical,
        end: Dp = TextFieldPaddingHorizontal,
        bottom: Dp = TextFieldPaddingVertical
    ): PaddingValues = PaddingValues(start, top, end, bottom)

    @Composable
    fun BorderBox(
        enabled: Boolean,
        isError: Boolean,
        interactionSource: InteractionSource,
        colors: TextFieldColors,
        shape: Shape = TextFieldShape,
    ) {
        val borderStroke = rememberBorderStrokeState(
            enabled = enabled,
            isError = isError,
            interactionSource = interactionSource,
            colors = colors
        )

        Box(
            Modifier
                .border(borderStroke.value, shape)
        )
    }

    @Composable
    private fun rememberBorderStrokeState(
        enabled: Boolean,
        isError: Boolean,
        interactionSource: InteractionSource,
        colors: TextFieldColors,
        borderThickness: Dp = BorderThickness,
    ): State<BorderStroke> {
        val indicatorColor = colors.indicatorColor(enabled, isError, interactionSource)

        return rememberUpdatedState(
            BorderStroke(borderThickness, SolidColor(indicatorColor.value))
        )
    }

    @Composable
    @Suppress("LongParameterList")
    fun textFieldColors(
        textColor: Color = GrapesTheme.colors.mainNeutralDarkest,
        disabledTextColor: Color = GrapesTheme.colors.mainNeutralDarkest,
        backgroundColor: Color = GrapesTheme.colors.mainWhite,
        disabledBackgroundColor: Color = GrapesTheme.colors.mainNeutralLighter,
        cursorColor: Color = GrapesTheme.colors.mainPrimaryLight,
        errorCursorColor: Color = GrapesTheme.colors.mainAlertNormal,
        focusedBorderColor: Color = GrapesTheme.colors.mainNeutralLight,
        unfocusedBorderColor: Color = GrapesTheme.colors.mainNeutralLight,
        disabledBorderColor: Color = GrapesTheme.colors.mainNeutralLight,
        errorBorderColor: Color = GrapesTheme.colors.mainAlertNormal,
        leadingIconColor: Color = GrapesTheme.colors.mainNeutralDarker,
        disabledLeadingIconColor: Color = leadingIconColor.copy(alpha = ContentAlpha.disabled),
        errorLeadingIconColor: Color = leadingIconColor, // Todo replace
        trailingIconColor: Color = GrapesTheme.colors.mainNeutralDarker,
        disabledTrailingIconColor: Color = trailingIconColor.copy(alpha = ContentAlpha.disabled),
        errorTrailingIconColor: Color = Color.Yellow, // Todo replace
        focusedLabelColor: Color = Color.Yellow, // Todo replace
        unfocusedLabelColor: Color = Color.Yellow, // Todo replace
        disabledLabelColor: Color = unfocusedLabelColor.copy(ContentAlpha.disabled), // Todo replace
        errorLabelColor: Color = GrapesTheme.colors.mainAlertNormal,
        placeholderColor: Color = GrapesTheme.colors.mainNeutralDark,
        disabledPlaceholderColor: Color = GrapesTheme.colors.mainNeutralNormal,
        helperTextColor: Color = GrapesTheme.colors.mainNeutralDark,
        errorHelperTextColor: Color = GrapesTheme.colors.mainAlertNormal,
        disabledHelperTextColor: Color = helperTextColor.copy(ContentAlpha.disabled),
    ): GrapesTextFieldColors = DefaultGrapesGrapesTextFieldColors(
        textColor = textColor,
        disabledTextColor = disabledTextColor,
        cursorColor = cursorColor,
        errorCursorColor = errorCursorColor,
        focusedIndicatorColor = focusedBorderColor,
        unfocusedIndicatorColor = unfocusedBorderColor,
        errorIndicatorColor = errorBorderColor,
        disabledIndicatorColor = disabledBorderColor,
        leadingIconColor = leadingIconColor,
        disabledLeadingIconColor = disabledLeadingIconColor,
        errorLeadingIconColor = errorLeadingIconColor,
        trailingIconColor = trailingIconColor,
        disabledTrailingIconColor = disabledTrailingIconColor,
        errorTrailingIconColor = errorTrailingIconColor,
        backgroundColor = backgroundColor,
        disabledBackgroundColor = disabledBackgroundColor,
        focusedLabelColor = focusedLabelColor,
        unfocusedLabelColor = unfocusedLabelColor,
        disabledLabelColor = disabledLabelColor,
        errorLabelColor = errorLabelColor,
        placeholderColor = placeholderColor,
        disabledPlaceholderColor = disabledPlaceholderColor,
        helperTextColor = helperTextColor,
        errorHelperTextColor = errorHelperTextColor,
        disabledHelperTextColor = disabledHelperTextColor,
    )
}

@Immutable
private data class DefaultGrapesGrapesTextFieldColors(
    private val textColor: Color,
    private val disabledTextColor: Color,
    private val cursorColor: Color,
    private val errorCursorColor: Color,
    private val focusedIndicatorColor: Color,
    private val unfocusedIndicatorColor: Color,
    private val errorIndicatorColor: Color,
    private val disabledIndicatorColor: Color,
    private val leadingIconColor: Color,
    private val disabledLeadingIconColor: Color,
    private val errorLeadingIconColor: Color,
    private val trailingIconColor: Color,
    private val disabledTrailingIconColor: Color,
    private val errorTrailingIconColor: Color,
    private val backgroundColor: Color,
    private val disabledBackgroundColor: Color,
    private val focusedLabelColor: Color,
    private val unfocusedLabelColor: Color,
    private val disabledLabelColor: Color,
    private val errorLabelColor: Color,
    private val placeholderColor: Color,
    private val disabledPlaceholderColor: Color,
    private val helperTextColor: Color,
    private val disabledHelperTextColor: Color,
    private val errorHelperTextColor: Color,
) : GrapesTextFieldColors {

    companion object {
        private const val AnimationDuration = 150
    }

    @Composable
    override fun helperTextColor(enabled: Boolean, isError: Boolean): State<Color> {
        return rememberUpdatedState(
            when {
                !enabled -> disabledHelperTextColor
                isError -> errorHelperTextColor
                else -> helperTextColor
            }
        )
    }

    @Composable
    override fun leadingIconColor(enabled: Boolean, isError: Boolean): State<Color> {
        return rememberUpdatedState(
            when {
                !enabled -> disabledLeadingIconColor
                isError -> errorLeadingIconColor
                else -> leadingIconColor
            }
        )
    }

    @Composable
    override fun trailingIconColor(enabled: Boolean, isError: Boolean): State<Color> {
        return rememberUpdatedState(
            when {
                !enabled -> disabledTrailingIconColor
                isError -> errorTrailingIconColor
                else -> trailingIconColor
            }
        )
    }

    @Composable
    override fun indicatorColor(
        enabled: Boolean,
        isError: Boolean,
        interactionSource: InteractionSource
    ): State<Color> {
        val focused by interactionSource.collectIsFocusedAsState()

        val targetValue = when {
            !enabled -> disabledIndicatorColor
            isError -> errorIndicatorColor
            focused -> focusedIndicatorColor
            else -> unfocusedIndicatorColor
        }
        return if (enabled) {
            animateColorAsState(targetValue, tween(durationMillis = AnimationDuration))
        } else {
            rememberUpdatedState(targetValue)
        }
    }

    @Composable
    override fun backgroundColor(enabled: Boolean): State<Color> {
        return rememberUpdatedState(if (enabled) backgroundColor else disabledBackgroundColor)
    }

    @Composable
    override fun placeholderColor(enabled: Boolean): State<Color> {
        return rememberUpdatedState(if (enabled) placeholderColor else disabledPlaceholderColor)
    }

    @Composable
    override fun labelColor(
        enabled: Boolean,
        error: Boolean,
        interactionSource: InteractionSource
    ): State<Color> {
        val focused by interactionSource.collectIsFocusedAsState()

        val targetValue = when {
            !enabled -> disabledLabelColor
            error -> errorLabelColor
            focused -> focusedLabelColor
            else -> unfocusedLabelColor
        }
        return rememberUpdatedState(targetValue)
    }

    @Composable
    override fun textColor(enabled: Boolean): State<Color> {
        return rememberUpdatedState(if (enabled) textColor else disabledTextColor)
    }

    @Composable
    override fun cursorColor(isError: Boolean): State<Color> {
        return rememberUpdatedState(if (isError) errorCursorColor else cursorColor)
    }
}

/**
 * Represent the colors of a the input text
 */
@Stable
interface GrapesTextFieldColors : TextFieldColors {

    @Composable
    fun helperTextColor(enabled: Boolean, isError: Boolean): State<Color>
}
