package com.spendesk.grapes.selectors

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.transition.TransitionManager
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.ProgressBar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import com.spendesk.grapes.R
import kotlinx.android.synthetic.main.selectors_header_status_indicator.view.*
import kotlin.math.abs

/**
 * @author danyboucanova
 * @since 10/06/2021
 */
class HeaderStatusIndicator : ConstraintLayout {

    companion object {
        private const val INDICATOR_ANIMATION_DURATION_MS = 200L
    }

    // region constructor

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    // endregion constructor

    data class Configuration(
        val pagerNumber: Int
    )

    private val progressBarIds = mutableListOf<Int>()
    private var animatorSet: AnimatorSet? = null
    private var currentIndex: Int = 0

    init {
        View.inflate(context, R.layout.selectors_header_status_indicator, this)

        setBackgroundColor(ContextCompat.getColor(context, R.color.headerStatusIndicatorBackgroundColor))
    }

    fun updateConfiguration(configuration: Configuration, shouldAnimate: Boolean = false) {
        val amountOfProgressBarDelta = configuration.pagerNumber - progressBarIds.size

        if (shouldAnimate) {
            TransitionManager.beginDelayedTransition(this)
        }

        if (amountOfProgressBarDelta > 0) {
            addProgressBars(amountOfProgressBarDelta)
        } else if (amountOfProgressBarDelta < 0) {
            removeProgressBar(abs(amountOfProgressBarDelta))
            if (currentIndex >= configuration.pagerNumber) {
                updateStatusIndex(configuration.pagerNumber - 1)
            }
        }

        selectorsHeaderStatusIndicatorFlow.referencedIds = progressBarIds.toIntArray()
    }

    private fun addProgressBars(amountOfProgressBarToAdd: Int) {
        for (i in 1..amountOfProgressBarToAdd) {
            val progressBar = ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal)

            with(progressBar) {
                progressDrawable = ContextCompat.getDrawable(context, R.drawable.layer_list_header_status_indicator)
                layoutParams = ViewGroup.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT)
                id = generateViewId()
                progress = 0
            }
            addView(progressBar)

            progressBarIds.add(progressBar.id)
        }
    }

    private fun removeProgressBar(amountOfProgressBarToRemove: Int) {
        for (i in progressBarIds.size - 1 downTo progressBarIds.size - amountOfProgressBarToRemove) {
            val progressBarId = progressBarIds[i]
            val progressBar = findViewById<ProgressBar>(progressBarId)
            selectorsHeaderStatusIndicatorFlow.removeView(progressBar)
            progressBarIds.remove(progressBarId)
            removeView(progressBar)
        }
    }

    fun updateStatusIndex(statusIndex: Int, shouldAnimate: Boolean = false) {
        if (progressBarIds.isEmpty()) {
            throw IllegalStateException("You should update the view's configuration before updating the index status")
        }

        animatorSet?.end()
        if (shouldAnimate) {
            animateUpdateStatusIndex(statusIndex)
        } else {
            setUpdateStatusIndex(statusIndex)
        }
        currentIndex = statusIndex
    }

    private fun animateUpdateStatusIndex(statusIndex: Int) {
        val animators: MutableList<Animator> = mutableListOf()

        progressBarIds.forEachIndexed { index, progressBarIdView ->
            val progressBar = findViewById<ProgressBar>(progressBarIdView)

            if (index <= statusIndex && progressBar.progress == 0) {
                animators.add(
                    ObjectAnimator.ofInt(progressBar, "progress", 100)
                )
            } else if (index > statusIndex && progressBar.progress == 100) {
                animators.add(
                    ObjectAnimator.ofInt(progressBar, "progress", 0)
                )
            }
        }

        val sortedAnimators = if (currentIndex > statusIndex) {
            // we need to decrease the status bar progress so we need to play defined animators in a reversed order
            animators.reversed()
        } else {
            animators
        }

        // This interpolator is used by every animators. We can only use a non linear one when there is one animation to be played. Otherwise it would look bumpy.
        val animatorsInterpolator = if (sortedAnimators.size == 1) {
            FastOutSlowInInterpolator()
        } else {
            LinearInterpolator()
        }

        animatorSet = AnimatorSet().apply {
            duration = INDICATOR_ANIMATION_DURATION_MS
            interpolator = animatorsInterpolator
            playSequentially(sortedAnimators)
            start()
        }
    }

    private fun setUpdateStatusIndex(statusIndex: Int) {
        progressBarIds.forEachIndexed { index, progressBarIdView ->
            val progressBar = findViewById<ProgressBar>(progressBarIdView)

            if (index <= statusIndex) {
                progressBar.progress = 100
            } else {
                progressBar.progress = 0
            }
        }
    }
}