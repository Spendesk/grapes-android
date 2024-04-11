package com.spendesk.grapes.compose.theme

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp

/**
 * @author Kélian CLERC
 * @since 29/09/2022
 */
@Immutable
data class GrapesShapes(
    val shape0: CornerBasedShape = RoundedCornerShape(cornerRadius0Dp),
    val shape1: CornerBasedShape = RoundedCornerShape(cornerRadius1Dp),
    val shape2: CornerBasedShape = RoundedCornerShape(cornerRadius2Dp),
    val shape3: CornerBasedShape = RoundedCornerShape(cornerRadius3Dp),
    val shape4: CornerBasedShape = RoundedCornerShape(CornerRadiusRoundedPercent),
)

private val cornerRadius0Dp = 0.dp
private val cornerRadius1Dp = 4.dp
private val cornerRadius2Dp = 8.dp
private val cornerRadius3Dp = 16.dp
private const val CornerRadiusRoundedPercent = 50

internal val LocalGrapesShapes = staticCompositionLocalOf { GrapesShapes() }
