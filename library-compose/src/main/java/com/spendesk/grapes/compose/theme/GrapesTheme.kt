package com.spendesk.grapes.compose.theme

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.LocalContentColor
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable

/**
 * @author : danyboucanova
 * @since : 03/05/2022, Tue
 **/

object GrapesTheme {
    val colors: GrapesColors
        @Composable
        @ReadOnlyComposable
        get() = LocalGrapesColors.current
    val typography: GrapesTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalGrapesTypography.current
    val dimensions: GrapesDimensions
        @Composable
        @ReadOnlyComposable
        get() = LocalGrapesDimensions.current
    val shapes: GrapesShapes
        @Composable
        @ReadOnlyComposable
        get() = LocalGrapesShapes.current
}

@Composable
fun GrapesTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val rippleIndication = rememberRipple()

    CompositionLocalProvider(
        LocalGrapesColors provides lightColorsPalette(),
        LocalGrapesTypography provides GrapesTypography(),
        LocalGrapesDimensions provides GrapesDimensions(),
        LocalGrapesShapes provides GrapesShapes(),
        LocalIndication provides rippleIndication,
        LocalRippleTheme provides GrapesRippleTheme,
        content = content
    )
}

@Immutable
private object GrapesRippleTheme : RippleTheme {
    @Composable
    override fun defaultColor() = RippleTheme.defaultRippleColor(
        contentColor = LocalContentColor.current,
        lightTheme = GrapesTheme.colors.isLight
    )

    @Composable
    override fun rippleAlpha() = RippleTheme.defaultRippleAlpha(
        contentColor = LocalContentColor.current,
        lightTheme = GrapesTheme.colors.isLight
    )
}
