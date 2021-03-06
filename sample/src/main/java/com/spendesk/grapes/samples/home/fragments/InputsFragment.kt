package com.spendesk.grapes.samples.home.fragments

import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import com.spendesk.grapes.extensions.shortToaster
import com.spendesk.grapes.samples.R
import com.spendesk.grapes.samples.core.internal.viewBinding
import com.spendesk.grapes.samples.databinding.FragmentHomeInputsBinding

/**
 * @author danyboucanova
 * @since 15/06/2021
 */
class InputsFragment : Fragment(R.layout.fragment_home_inputs) {

    companion object {
        fun newInstance() = InputsFragment()
    }

    private val binding by viewBinding(FragmentHomeInputsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            homeHeader.homeGenericHeaderTitle.text = context?.getString(R.string.inputs)

            // TextInput
            homeInputsSectionTextInputPrimary.getEditText().doAfterTextChanged { requireActivity().shortToaster("Text changed: $it") }
            homeInputsSectionTextInputPrimaryFulfilled.getEditText().setText("This is a text")
            homeInputsSectionTextInputPrimaryWithIcon.getEditText().setText("This is a text")
            homeInputsSectionTextInputSecondaryFulfilled.getEditText().setText("This is a text")

            // SearchInput
            homeInputsSectionSearchInputPrimaryLoading.showProgressBar(true)
            homeInputsSectionSearchInputSecondaryLoading.showProgressBar(true)

            // Password
            homeInputsSectionPasswordInputPrimary.onEndTextClicked = { requireActivity().shortToaster("Forgot password clicked!") }
        }
    }
}