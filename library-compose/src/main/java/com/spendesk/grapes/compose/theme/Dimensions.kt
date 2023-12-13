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

    val borderRadiusSmall: Dp = 2.dp,
    val borderRadiusNormal: Dp = 4.dp,

    @Deprecated("Grapes dimension deprecated", ReplaceWith("spacing1"))
    val paddingXSmall: Dp = 4.dp,
    @Deprecated("Grapes dimension deprecated", ReplaceWith("spacing2"))
    val paddingSmall: Dp = 8.dp,
    @Deprecated("Grapes dimension deprecated, new design shouldn't use 12.dp anymore")
    val paddingMedium: Dp = 12.dp,
    @Deprecated("Grapes dimension deprecated", ReplaceWith("spacing3"))
    val paddingLarge: Dp = 16.dp,
    @Deprecated("Grapes dimension deprecated", ReplaceWith("spacing4"))
    val paddingXLarge: Dp = 24.dp,

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

    val iconNormal: Dp = 16.dp,
    val iconLarge: Dp = 32.dp,

    val gaugeHeight: Dp = 16.dp,
    val gaugeDelimiterWidth: Dp = 2.dp
)

internal val LocalGrapesDimensions = staticCompositionLocalOf { GrapesDimensions() }
