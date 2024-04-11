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
    val dividerThickness: Dp = 1.0.dp,

    val borderLarge: Dp = 2.0.dp,

    val spacing0: Dp = 0.dp,
    val spacing1: Dp = 4.dp,
    val spacing2: Dp = 8.dp,
    val spacing3: Dp = 16.dp,
    val spacing4: Dp = 24.dp,
    val spacing5: Dp = 32.dp,
    val spacing6: Dp = 40.dp,
    val spacing7: Dp = 48.dp,
    val spacing8: Dp = 56.dp,
    val spacing9: Dp = 64.dp,

    val elevationNormal: Dp = 8.dp,

    val sizing1: Dp = 12.dp,
    val sizing2: Dp = 16.dp,
    val sizing3: Dp = 20.dp,
    val sizing4: Dp = 24.dp,
    val sizing5: Dp = 32.dp,
    val sizing6: Dp = 40.dp,
    val sizing7: Dp = 56.dp,

    val gaugeHeight: Dp = 16.dp,
    val gaugeDelimiterWidth: Dp = 2.dp
)

internal val LocalGrapesDimensions = staticCompositionLocalOf { GrapesDimensions() }
