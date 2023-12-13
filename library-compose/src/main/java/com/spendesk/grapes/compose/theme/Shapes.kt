package com.spendesk.grapes.compose.theme

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
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
    val shape4: CornerBasedShape = RoundedCornerShape(cornerRadiusRoundedPercent),

    @Deprecated("Grapes shape deprecated", ReplaceWith("borderRadius1"))
    val xSmall: CornerBasedShape = RoundedCornerShape(XSmallShapeCornerRadius),

    /**
     * Shape used by small components like [Button] or [Snackbar]. Components like
     * [FloatingActionButton], [ExtendedFloatingActionButton] use this shape, but override
     * the corner size to be 50%. [TextField] uses this shape with overriding the bottom corners
     * to zero.
     */
    @Deprecated("Grapes shape deprecated", ReplaceWith("borderRadius2"))
    val small: CornerBasedShape = RoundedCornerShape(SmallShapeCornerRadius),
    /**
     * Shape used by medium components like [Card] or [AlertDialog].
     */
    @Deprecated("Grapes shape deprecated - no longer use 12dp border radius.")
    val medium: CornerBasedShape = RoundedCornerShape(MediumShapeCornerRadius),
    /**
     * Shape used by large components like [ModalDrawer] or [ModalBottomSheetLayout].
     */
    @Deprecated("Grapes shape deprecated", ReplaceWith("borderRadius0"))
    val large: CornerBasedShape = RoundedCornerShape(LargeShapeCornerRadius)
)

private val cornerRadius0Dp = 0.dp
private val cornerRadius1Dp = 4.dp
private val cornerRadius2Dp = 8.dp
private val cornerRadius3Dp = 16.dp
private const val cornerRadiusRoundedPercent = 50

private val XSmallShapeCornerRadius = 4.dp
private val SmallShapeCornerRadius = 8.dp
private val MediumShapeCornerRadius = 12.dp
private val LargeShapeCornerRadius = 0.dp

internal val LocalGrapesShapes = staticCompositionLocalOf { GrapesShapes() }
