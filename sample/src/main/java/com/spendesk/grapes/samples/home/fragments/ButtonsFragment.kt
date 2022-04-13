package com.spendesk.grapes.samples.home.fragments

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import com.spendesk.grapes.samples.R
import com.spendesk.grapes.samples.core.internal.viewBinding
import com.spendesk.grapes.samples.databinding.FragmentHomeButtonsBinding

/**
 * @author danyboucanova
 * @since 12/29/20
 */
class ButtonsFragment : Fragment(R.layout.fragment_home_buttons) {

    companion object {
        fun newInstance() = ButtonsFragment()

        private const val PROGRESS_ANIMATION_DURATION_MS = 1000L
    }

    private val binding by viewBinding(FragmentHomeButtonsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            homeHeader.homeGenericHeaderTitle.text = context?.getString(R.string.buttons)

            ValueAnimator.ofFloat(0f, 100f)?.apply {
                duration = PROGRESS_ANIMATION_DURATION_MS
                addUpdateListener { anim ->
                    val animatedProgress = (anim.animatedValue as Float).toInt()
                    homeButtonSectionPrimaryHorizontalProgressButton.updateLoaderProgress(progress = animatedProgress)
                }
                interpolator = FastOutSlowInInterpolator()
                repeatMode = ValueAnimator.RESTART
                repeatCount = ValueAnimator.INFINITE
                start()
            }
        }
    }
}