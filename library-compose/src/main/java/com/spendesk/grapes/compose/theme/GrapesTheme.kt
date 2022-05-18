package com.spendesk.grapes.compose.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
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
        get() = LocalColors.current
    val typography: GrapesTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current
    val dimensions: GrapesDimensions
        @Composable
        @ReadOnlyComposable
        get() = LocalDimensions.current
}

@Composable
fun GrapesTheme(
    colors: GrapesColors = GrapesTheme.colors,
    typography: GrapesTypography = GrapesTheme.typography,
    dimensions: GrapesDimensions = GrapesTheme.dimensions,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme
    CompositionLocalProvider(
        LocalColors provides lightColorsPalette(),
        LocalTypography provides GrapesTypography(),
    ) {
        content()
    }
}