package com.spendesk.grapes.samples.home.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.spendesk.grapes.NumberKeyboard
import com.spendesk.grapes.extensions.shortToaster
import com.spendesk.grapes.samples.R
import com.spendesk.grapes.samples.core.internal.viewBinding
import com.spendesk.grapes.samples.databinding.FragmentHomeKeyboardsBinding
import com.spendesk.grapes.selectors.KeyboardPropositionsView

/**
 * @author Vivien Mahe
 * @since 19/01/2022
 */
class KeyboardsFragment : Fragment(R.layout.fragment_home_keyboards) {

    companion object {
        fun newInstance() = KeyboardsFragment()
    }

    private val binding by viewBinding(FragmentHomeKeyboardsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.homeHeader.homeGenericHeaderTitle.text = context?.getString(R.string.inputs)

        with(binding.homeKeyboardsSectionOne) {
            updateConfiguration(
                configuration = NumberKeyboard.Configuration(
                    style = NumberKeyboard.Style.LIGHT,
                    extraButton = NumberKeyboard.ExtraButton.FINGERPRINT
                )
            )

            onTextChanged = { activity?.shortToaster("Key pressed: $it") }
            onRequestedBiometricAuthentication = { activity?.shortToaster("Biometric key pressed") }
        }

        with(binding.homeKeyboardsSectionTwo) {
            // FYI: Configuration is set via XML attrs
            onTextChanged = { activity?.shortToaster("Key pressed: $it") }
            onRequestedBiometricAuthentication = { activity?.shortToaster("Separator key pressed") }
        }

        with(binding.homeKeyboardsSectionThree) {
            // FYI: Configuration is set via XML attrs
            onTextChanged = { activity?.shortToaster("Key pressed: $it") }
            onRequestedBiometricAuthentication = { activity?.shortToaster("Separator key pressed") }
        }

        with(binding.propositions) {
            setListener {
                activity?.shortToaster("Proposition pressed: ${it.text}")
            }

            updateData(
                KeyboardPropositionsView.Configuration(
                    listOf(
                        KeyboardPropositionsView.Item("1", "50$"),
                        KeyboardPropositionsView.Item("2", "100$"),
                        KeyboardPropositionsView.Item("3", "200$"),
                        KeyboardPropositionsView.Item("4", "500$"),
                    )
                )
            )
        }
    }
}