package com.spendesk.grapes.samples.home.fragments

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import com.spendesk.grapes.Button
import com.spendesk.grapes.samples.R
import com.spendesk.grapes.samples.core.internal.viewBinding
import com.spendesk.grapes.samples.databinding.FragmentHomeButtonsBinding
import java.util.*
import kotlin.concurrent.schedule

/**
 * @author danyboucanova
 * @since 12/29/20
 */
class ButtonsFragment : Fragment(R.layout.fragment_home_buttons) {

    companion object {
        fun newInstance() = ButtonsFragment()

        private const val HORIZONTAL_PROGRESS_ANIMATION_DURATION_MS = 1000L
        private const val CIRCULAR_PROGRESS_ANIMATION_DELAY_MS = 2000L
    }

    private val binding by viewBinding(FragmentHomeButtonsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            homeHeader.homeGenericHeaderTitle.text = context?.getString(R.string.buttons)

            // Primary horizontal progress button
            ValueAnimator.ofFloat(0f, 100f)?.apply {
                duration = HORIZONTAL_PROGRESS_ANIMATION_DURATION_MS
                addUpdateListener { anim ->
                    val animatedProgress = (anim.animatedValue as Float).toInt()
                    homeButtonSectionPrimaryHorizontalProgressButton.updateLoaderProgress(progress = animatedProgress)
                }
                interpolator = FastOutSlowInInterpolator()
                repeatMode = ValueAnimator.RESTART
                repeatCount = ValueAnimator.INFINITE
                start()
            }

            // Primary circular progress button
            Timer().schedule(CIRCULAR_PROGRESS_ANIMATION_DELAY_MS) {
                with(requireActivity()) {
                    runOnUiThread {
                        homeButtonSectionPrimaryCircularProgressButton.setLoaderType(loaderType = Button.LoaderType.CIRCULAR)
                    }
                }
            }

            homeButtonSectionPrimaryWithIconHorizontalProgressButton.updateLoaderProgress(progress = 30)
            homeButtonSectionSecondaryHorizontalProgressButton.updateLoaderProgress(progress = 30)
            homeButtonSectionSecondaryWithIconHorizontalProgressButton.updateLoaderProgress(progress = 30)
            homeButtonSectionAlertHorizontalProgressButton.updateLoaderProgress(progress = 30)
            homeButtonSectionWarningHorizontalProgressButton.updateLoaderProgress(progress = 30)
        }
    }
}