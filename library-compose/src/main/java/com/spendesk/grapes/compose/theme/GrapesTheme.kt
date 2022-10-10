package com.spendesk.grapes.compose.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
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
    CompositionLocalProvider(
        LocalGrapesColors provides lightColorsPalette(),
        LocalGrapesTypography provides GrapesTypography(),
        LocalGrapesDimensions provides GrapesDimensions(),
        LocalGrapesShapes provides GrapesShapes(),
        content = content
    )
}