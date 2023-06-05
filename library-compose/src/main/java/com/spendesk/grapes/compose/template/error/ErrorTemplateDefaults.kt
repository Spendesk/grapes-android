package com.spendesk.grapes.compose.template.error

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable

/**
 * @author Kélian CLERC
 * @since 02/06/2023
 */
@Immutable
object ErrorTemplateDefaults {

    private const val DefaultStartingDelayMs = 200L
    private const val DefaultTitleAnimationDurationMs = 500
    private const val DefaultTitleAppearanceDelayMs = 400
    private const val DefaultDescriptionAnimationDurationMs = 500
    private const val DefaultDescriptionAppearanceDelayMs = 500
    private const val DefaultRetryButtonAnimationDurationMs = 300
    private const val DefaultRetryButtonAppearanceDelayMs = 800

    @Composable
    fun defaultConfiguration(): Configuration = DefaultConfiguration(
        startingDelayMs = DefaultStartingDelayMs,
        titleAnimationDurationMs = DefaultTitleAnimationDurationMs,
        titleAppearanceDelayMs = DefaultTitleAppearanceDelayMs,
        descriptionAnimationDurationMs = DefaultDescriptionAnimationDurationMs,
        descriptionAppearanceDelayMs = DefaultDescriptionAppearanceDelayMs,
        retryButtonAnimationDurationMs = DefaultRetryButtonAnimationDurationMs,
        retryButtonAppearanceDelayMs = DefaultRetryButtonAppearanceDelayMs,
    )

    @OptIn(ExperimentalAnimationApi::class)
    data class DefaultConfiguration(
        override val startingDelayMs: Long,
        val titleAnimationDurationMs: Int,
        val titleAppearanceDelayMs: Int,
        val descriptionAnimationDurationMs: Int,
        val descriptionAppearanceDelayMs: Int,
        val retryButtonAnimationDurationMs: Int,
        val retryButtonAppearanceDelayMs: Int,
    ): Configuration {
        override val iconEnterAnimation: EnterTransition
            get() = scaleIn()

        override val titleEnterAnimation: EnterTransition
            get() = fadeIn(animationSpec = tween(durationMillis = titleAnimationDurationMs, delayMillis = titleAppearanceDelayMs))

        override val descriptionEnterAnimation: EnterTransition
            get() = fadeIn(animationSpec = tween(durationMillis = descriptionAnimationDurationMs, delayMillis = descriptionAppearanceDelayMs))

        override val retryButtonEnterAnimation: EnterTransition
            get() = fadeIn(animationSpec = tween(durationMillis = retryButtonAnimationDurationMs, delayMillis = retryButtonAppearanceDelayMs))
    }

    @Immutable
    interface Configuration {
        val startingDelayMs: Long
        val iconEnterAnimation: EnterTransition
        val titleEnterAnimation: EnterTransition
        val descriptionEnterAnimation: EnterTransition
        val retryButtonEnterAnimation: EnterTransition
    }
}

