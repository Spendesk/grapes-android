package com.spendesk.grapes.samples.home.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
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
    }

    private val binding by viewBinding(FragmentHomeButtonsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            homeHeader.homeGenericHeaderTitle.text = context?.getString(R.string.buttons)

            homeButtonSectionPrimaryProgressButton.updateLoaderProgress(progress = 50)
        }
    }
}