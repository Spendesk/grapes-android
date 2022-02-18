package com.spendesk.grapes.samples.home.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.spendesk.grapes.NumberKeyboard
import com.spendesk.grapes.extensions.shortToaster
import com.spendesk.grapes.samples.R
import kotlinx.android.synthetic.main.fragment_home_keyboards.*
import kotlinx.android.synthetic.main.view_home_header.*

/**
 * @author Vivien Mahe
 * @since 19/01/2022
 */
class KeyboardsFragment : Fragment(R.layout.fragment_home_keyboards) {

    companion object {
        fun newInstance() = KeyboardsFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeGenericHeaderTitle.text = context?.getString(R.string.inputs)

        with(homeKeyboardsSectionOne) {
            updateConfiguration(
                configuration = NumberKeyboard.Configuration(
                    style = NumberKeyboard.Style.LIGHT,
                    extraButton = NumberKeyboard.ExtraButton.FINGERPRINT
                )
            )

            onTextChanged = { activity?.shortToaster("Key pressed: $it") }
            onRequestedBiometricAuthentication = { activity?.shortToaster("Biometric key pressed") }
        }

        with(homeKeyboardsSectionTwo) {
            // FYI: Configuration is set via XML attrs
            onTextChanged = { activity?.shortToaster("Key pressed: $it") }
            onRequestedBiometricAuthentication = { activity?.shortToaster("Separator key pressed") }
        }

        with(homeKeyboardsSectionThree) {
            // FYI: Configuration is set via XML attrs
            onTextChanged = { activity?.shortToaster("Key pressed: $it") }
            onRequestedBiometricAuthentication = { activity?.shortToaster("Separator key pressed") }
        }
    }
}