package com.spendesk.grapes.compose.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * @author : danyboucanova
 * @since : 16/05/2022, Mon
 **/

@Immutable
data class GrapesDimensions(
    val paddingSmall: Dp = 4.dp,
    val paddingMedium: Dp = 8.dp,
    val paddingNormal: Dp = 16.dp,
    val paddingLarge: Dp = 24.dp
)

internal val LocalDimensions = staticCompositionLocalOf { GrapesDimensions() }