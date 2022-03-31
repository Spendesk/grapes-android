package com.spendesk.grapes.samples.home.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.spendesk.grapes.NumberKeyboard
import com.spendesk.grapes.extensions.shortToaster
import com.spendesk.grapes.samples.R
import com.spendesk.grapes.samples.core.internal.viewBinding
import com.spendesk.grapes.samples.databinding.FragmentHomeKeyboardsBinding

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

        with(binding.homeKeyboardsSectionWithSuggestion) {
            onTextChanged = { activity?.shortToaster("Key pressed: $it") }

            updateSuggestions(
                listOf(
                    NumberKeyboard.Suggestion(label = "50$", rawValue = "50"),
                    NumberKeyboard.Suggestion(label = "100$", rawValue = "100"),
                    NumberKeyboard.Suggestion(label = "200$", rawValue = "200"),
                    NumberKeyboard.Suggestion(label = "500$", rawValue = "500"),
                )
            )
        }

        with(binding.homeKeyboardsSectionWithSuggestionDark) {
            onTextChanged = { activity?.shortToaster("Key pressed: $it") }

            updateSuggestions(
                listOf(
                    NumberKeyboard.Suggestion(label = "50$", rawValue = "50"),
                    NumberKeyboard.Suggestion(label = "100$", rawValue = "100"),
                    NumberKeyboard.Suggestion(label = "200$", rawValue = "200"),
                    NumberKeyboard.Suggestion(label = "500$", rawValue = "500"),
                    NumberKeyboard.Suggestion(label = "700$", rawValue = "700"),
                    NumberKeyboard.Suggestion(label = "1000$", rawValue = "1000"),
                    NumberKeyboard.Suggestion(label = "2000$", rawValue = "2000"),
                )
            )
        }
    }
}