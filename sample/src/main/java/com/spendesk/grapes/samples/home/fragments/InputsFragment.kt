package com.spendesk.grapes.samples.home.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.spendesk.grapes.samples.R
import kotlinx.android.synthetic.main.fragment_home_inputs.*
import kotlinx.android.synthetic.main.view_home_header.*

/**
 * @author danyboucanova
 * @since 15/06/2021
 */
class InputsFragment : Fragment(R.layout.fragment_home_inputs) {

    companion object {
        fun newInstance() = InputsFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeGenericHeaderTitle.text = context?.getString(R.string.inputs)

        homeInputsSectionSearchMainPrimaryLoading.showProgressBar(true)
        homeInputsSectionSearchMainSecondaryLoading.showProgressBar(true)
    }
}