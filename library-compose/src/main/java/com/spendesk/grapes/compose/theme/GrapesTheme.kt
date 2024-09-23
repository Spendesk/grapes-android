package com.spendesk.grapes.compose.theme

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.RippleConfiguration
import androidx.compose.material3.RippleDefaults.RippleAlpha
import androidx.compose.material3.ripple
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GrapesTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val rippleIndication = ripple()

    CompositionLocalProvider(
        LocalGrapesColors provides lightColorsPalette(),
        LocalGrapesTypography provides GrapesTypography(),
        LocalGrapesDimensions provides GrapesDimensions(),
        LocalGrapesShapes provides GrapesShapes(),
        LocalIndication provides rippleIndication,
        LocalRippleConfiguration provides grapesRippleConfiguration(),
        content = content
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun grapesRippleConfiguration() = RippleConfiguration(
    color = LocalContentColor.current,
    rippleAlpha = RippleAlpha
)
