package com.spendesk.grapes.compose.badge

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.spendesk.grapes.compose.theme.GrapesTheme
@Stable
interface GrapesBadgeColors {

    @Composable
    fun textColor(): Color

    @Composable
    fun backgroundColor(): Color
}

@Immutable
class DefaultBadgeColors(
    private val textColor: Color,
    private val backgroundColor: Color,
) : GrapesBadgeColors {

    @Composable
    override fun textColor(): Color = textColor

    @Composable
    override fun backgroundColor(): Color = backgroundColor
}

@Immutable
object GrapesBadgeDefaults {

    const val MAX_VALUE = 99

    const val MORE_INDICATOR = "+"

    val MinWidth = 24.dp
    val Height = 24.dp
    val HorizontalPadding = 4.dp

    @Composable
    fun backgroundShape() = GrapesTheme.shapes.shape4

    @Composable
    fun textTypography(textColor: Color) = GrapesTheme.typography.titleM.copy(color = textColor)

    @Composable
    fun neutralBadgeColors(): GrapesBadgeColors = DefaultBadgeColors(
        textColor = GrapesTheme.colors.neutralDark,
        backgroundColor = GrapesTheme.colors.neutralLightest,
    )

    @Composable
    fun primaryBadgeColors(): GrapesBadgeColors = DefaultBadgeColors(
        textColor = GrapesTheme.colors.structureSurface,
        backgroundColor = GrapesTheme.colors.primaryNormal,
    )

    @Composable
    fun alertBadgeColors(): GrapesBadgeColors = DefaultBadgeColors(
        textColor = GrapesTheme.colors.structureSurface,
        backgroundColor = GrapesTheme.colors.alertNormal,
    )
}
