package com.spendesk.grapes.compose.button

import androidx.compose.material.ButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.graphics.Color
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author Kélian CLERC
 * @since 28/09/2022
 */
@Immutable
object ButtonColorDefaults {
    val primary: ButtonColors
        @Composable
        @ReadOnlyComposable
        get() = GrapesButtonColors(
            backgroundColor = GrapesTheme.colors.mainPrimaryNormal,
            contentColor = GrapesTheme.colors.mainWhite,
            disabledBackgroundColor = GrapesTheme.colors.mainNeutralNormal,
            disabledContentColor = GrapesTheme.colors.mainWhite
        )

    val secondary: ButtonColors
        @Composable
        @ReadOnlyComposable
        get() = GrapesButtonColors(
            backgroundColor = GrapesTheme.colors.mainWhite,
            contentColor = GrapesTheme.colors.mainNeutralDarkest,
            disabledBackgroundColor = GrapesTheme.colors.mainNeutralNormal,
            disabledContentColor = GrapesTheme.colors.mainWhite
        )

    val text: ButtonColors
        @Composable
        @ReadOnlyComposable
        get() = GrapesButtonColors(
            backgroundColor = Color.Transparent,
            contentColor = GrapesTheme.colors.mainWhite,
            disabledBackgroundColor = Color.Transparent,
            disabledContentColor = GrapesTheme.colors.mainNeutralDark
        )

    val alert: ButtonColors
        @Composable
        @ReadOnlyComposable
        get() = GrapesButtonColors(
            backgroundColor = GrapesTheme.colors.mainAlertNormal,
            contentColor = GrapesTheme.colors.mainWhite,
            disabledBackgroundColor = GrapesTheme.colors.mainNeutralNormal,
            disabledContentColor = GrapesTheme.colors.mainWhite
        )

    val warning: ButtonColors
        @Composable
        @ReadOnlyComposable
        get() = GrapesButtonColors(
            backgroundColor = GrapesTheme.colors.mainWarningNormal,
            contentColor = GrapesTheme.colors.mainWhite,
            disabledBackgroundColor = GrapesTheme.colors.mainNeutralNormal,
            disabledContentColor = GrapesTheme.colors.mainWhite
        )

    val linkPrimary: ButtonColors
        @Composable
        @ReadOnlyComposable
        get() = GrapesButtonColors(
            backgroundColor = Color.Transparent,
            contentColor = GrapesTheme.colors.mainComplementary,
            disabledBackgroundColor = Color.Transparent,
            disabledContentColor = GrapesTheme.colors.mainNeutralDark
        )

    val linkSecondary: ButtonColors
        @Composable
        @ReadOnlyComposable
        get() = GrapesButtonColors(
            backgroundColor = Color.Transparent,
            contentColor = GrapesTheme.colors.mainNeutralDarker,
            disabledBackgroundColor = Color.Transparent,
            disabledContentColor = GrapesTheme.colors.mainNeutralDark
        )
}

@Immutable
private data class GrapesButtonColors(
    private val backgroundColor: Color,
    private val contentColor: Color,
    private val disabledBackgroundColor: Color,
    private val disabledContentColor: Color
) : ButtonColors {
    @Composable
    override fun backgroundColor(enabled: Boolean): State<Color> {
        val computedColor = if (enabled) backgroundColor else disabledBackgroundColor
        return rememberUpdatedState(computedColor)
    }

    @Composable
    override fun contentColor(enabled: Boolean): State<Color> {
        val computedColor = if (enabled) contentColor else disabledContentColor
        return rememberUpdatedState(computedColor)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as GrapesButtonColors

        return backgroundColor == other.backgroundColor &&
                contentColor == other.contentColor &&
                disabledBackgroundColor == other.disabledBackgroundColor &&
                disabledContentColor == other.disabledContentColor
    }

    override fun hashCode(): Int {
        var result = backgroundColor.hashCode()
        result = 31 * result + contentColor.hashCode()
        result = 31 * result + disabledBackgroundColor.hashCode()
        result = 31 * result + disabledContentColor.hashCode()
        return result
    }
}
