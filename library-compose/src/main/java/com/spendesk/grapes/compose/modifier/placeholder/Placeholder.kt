package com.spendesk.grapes.compose.modifier.placeholder

import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.spring
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.isSpecified
import com.spendesk.grapes.compose.theme.GrapesColors
import com.spendesk.grapes.compose.theme.GrapesShapes
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * Returns the value used as the the `highlightColor` parameter value of [PlaceholderHighlight.Companion.fade].
 *
 * @param backgroundColor The current background color of the layout. Defaults to [GrapesColors.structureComplementary].
 * @param alpha The alpha component to set on [backgroundColor]. Defaults to `0.16f`.
 */
@Composable
fun PlaceholderDefaults.fadeHighlightColor(
    backgroundColor: Color = GrapesTheme.colors.structureComplementary,
    alpha: Float = 0.16f,
): Color = backgroundColor.copy(alpha = alpha)

/**
 * Returns the value used as the the `highlightColor` parameter value of [PlaceholderHighlight.Companion.shimmer].
 *
 * @param backgroundColor The current background color of the layout. Defaults to [GrapesColors.structureComplementary].
 * @param alpha The alpha component to set on [backgroundColor]. Defaults to `0.16f`.
 */
@Composable
fun PlaceholderDefaults.shimmerHighlightColor(
    backgroundColor: Color = GrapesTheme.colors.structureComplementary,
    alpha: Float = 0.16f,
): Color {
    return backgroundColor.copy(alpha = alpha)
}

/**
 * Draws some skeleton UI which is typically used whilst content is 'loading'.
 *
 * To customize the color and shape of the placeholder, you can use the foundation version of
 * [Modifier.placeholder], along with the values provided by [PlaceholderDefaults].
 *
 * A cross-fade transition will be applied to the content and placeholder UI when the [visible]
 * value changes. The transition can be customized via the [contentFadeTransitionSpec] and
 * [placeholderFadeTransitionSpec] parameters.
 *
 * You can provide a [PlaceholderHighlight] which runs an highlight animation on the placeholder.
 * The [shimmer] and [fade] implementations are provided for easy usage.
 *
 * You can find more information on the pattern at the Material Theming
 * [Placeholder UI](https://material.io/design/communication/launch-screen.html#placeholder-ui)
 * guidelines.
 *
 * @param visible whether the placeholder should be visible or not.
 * @param color the color used to draw the placeholder UI. If [Color.Unspecified] is provided,
 * the placeholder will use [GrapesColors.neutralLighter].
 * @param shape desired shape of the placeholder. If null is provided the placeholder
 * will use the shape [GrapesShapes.shape2].
 * @param highlight optional highlight animation.
 * @param placeholderFadeTransitionSpec The transition spec to use when fading the placeholder
 * on/off screen. The boolean parameter defined for the transition is [visible].
 * @param contentFadeTransitionSpec The transition spec to use when fading the content
 * on/off screen. The boolean parameter defined for the transition is [visible].
 */
fun Modifier.placeholder(
    visible: Boolean,
    color: Color = Color.Unspecified,
    shape: Shape? = null,
    highlight: PlaceholderHighlight? = null,
    placeholderFadeTransitionSpec: @Composable Transition.Segment<Boolean>.() -> FiniteAnimationSpec<Float> = { spring() },
    contentFadeTransitionSpec: @Composable Transition.Segment<Boolean>.() -> FiniteAnimationSpec<Float> = { spring() },
): Modifier = composed {
    Modifier.placeholder(
        visible = visible,
        color = if (color.isSpecified) color else GrapesTheme.colors.structureComplementary.copy(alpha = 0.08f),
        shape = shape ?: GrapesTheme.shapes.shape2,
        highlight = highlight,
        placeholderFadeTransitionSpec = placeholderFadeTransitionSpec,
        contentFadeTransitionSpec = contentFadeTransitionSpec,
    )
}
