package com.spendesk.grapes.compose.button

import androidx.compose.material.ButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.graphics.Color
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author Kélian CLERC
 * @since 28/09/2022
 */
@Immutable
private class GrapesButtonColors(
    private val backgroundColor: Color,
    private val contentColor: Color,
    private val disabledBackgroundColor: Color,
    private val disabledContentColor: Color
) : ButtonColors {
    @Composable
    override fun backgroundColor(enabled: Boolean): State<Color> {
        return rememberUpdatedState(if (enabled) backgroundColor else disabledBackgroundColor)
    }

    @Composable
    override fun contentColor(enabled: Boolean): State<Color> {
        return rememberUpdatedState(if (enabled) contentColor else disabledContentColor)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as GrapesButtonColors

        if (backgroundColor != other.backgroundColor) return false
        if (contentColor != other.contentColor) return false
        if (disabledBackgroundColor != other.disabledBackgroundColor) return false
        if (disabledContentColor != other.disabledContentColor) return false

        return true
    }

    override fun hashCode(): Int {
        var result = backgroundColor.hashCode()
        result = 31 * result + contentColor.hashCode()
        result = 31 * result + disabledBackgroundColor.hashCode()
        result = 31 * result + disabledContentColor.hashCode()
        return result
    }
}

@Composable
fun primaryButtonColors(): ButtonColors = GrapesButtonColors(
    backgroundColor = GrapesTheme.colors.mainPrimaryNormal,
    contentColor = GrapesTheme.colors.mainWhite,
    disabledBackgroundColor = GrapesTheme.colors.mainNeutralNormal,
    disabledContentColor = GrapesTheme.colors.mainWhite
)

@Composable
fun secondaryButtonColors(): ButtonColors = GrapesButtonColors(
    backgroundColor = GrapesTheme.colors.mainWhite,
    contentColor = GrapesTheme.colors.mainNeutralDarkest,
    disabledBackgroundColor = GrapesTheme.colors.mainNeutralNormal,
    disabledContentColor = GrapesTheme.colors.mainWhite
)

@Composable
fun alertButtonColors(): ButtonColors = GrapesButtonColors(
    backgroundColor = GrapesTheme.colors.mainAlertNormal,
    contentColor = GrapesTheme.colors.mainWhite,
    disabledBackgroundColor = GrapesTheme.colors.mainNeutralNormal,
    disabledContentColor = GrapesTheme.colors.mainWhite
)

@Composable
fun warningButtonColors(): ButtonColors = GrapesButtonColors(
    backgroundColor = GrapesTheme.colors.mainWarningNormal,
    contentColor = GrapesTheme.colors.mainWhite,
    disabledBackgroundColor = GrapesTheme.colors.mainNeutralNormal,
    disabledContentColor = GrapesTheme.colors.mainWhite
)

@Composable
fun textButtonColors(): ButtonColors = GrapesButtonColors(
    backgroundColor = Color.Transparent,
    contentColor = GrapesTheme.colors.mainComplementary,
    disabledBackgroundColor = Color.Transparent,
    disabledContentColor = GrapesTheme.colors.mainNeutralNormal
)

@Composable
fun secondaryTextButtonColors(): ButtonColors = GrapesButtonColors(
    backgroundColor = Color.Transparent,
    contentColor = GrapesTheme.colors.mainWhite,
    disabledBackgroundColor = Color.Transparent,
    disabledContentColor = GrapesTheme.colors.mainNeutralNormal
)
