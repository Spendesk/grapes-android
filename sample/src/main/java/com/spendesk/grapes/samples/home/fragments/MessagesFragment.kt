package com.spendesk.grapes.samples.home.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.spendesk.grapes.messages.MessageBlockView
import com.spendesk.grapes.samples.R
import com.spendesk.grapes.samples.core.internal.viewBinding
import com.spendesk.grapes.samples.databinding.FragmentHomeMessagesBinding

/**
 * @author : danyboucanova
 * @since : 26/04/2022, Tue
 **/
class MessagesFragment : Fragment(R.layout.fragment_home_messages) {

    companion object {
        fun newInstance() = MessagesFragment()
    }

    private val binding by viewBinding(FragmentHomeMessagesBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.homeHeader.homeGenericHeaderTitle.text = context?.getString(R.string.cards)

        bindView()
    }

    private fun bindView() {
        // Blocks

        binding.homeMessagesSectionBlockAlertMessageBlock.updateConfiguration(
            MessageBlockView.Configuration(
                style = MessageBlockView.Style.ALERT,
                title = requireContext().getString(R.string.homeMessagesAlertTitle),
                drawableStartId = R.drawable.ic_status_error,
                description = requireContext().getString(R.string.homeMessagesBlockDescription),
                signatureDrawablePlaceholderResId = null,
                signatureDrawableUrl = null,
                signatureText = null
            )
        )

        binding.homeMessagesSectionBlockWarningMessageBlock.updateConfiguration(
            MessageBlockView.Configuration(
                style = MessageBlockView.Style.WARNING,
                title = requireContext().getString(R.string.homeMessagesWarningTitle),
                drawableStartId = R.drawable.ic_warning,
                description = requireContext().getString(R.string.homeMessagesBlockDescription),
                signatureDrawablePlaceholderResId = null,
                signatureDrawableUrl = null,
                signatureText = null
            )
        )

        binding.homeMessagesSectionBlockInformationMessageBlock.updateConfiguration(
            MessageBlockView.Configuration(
                style = MessageBlockView.Style.INFO,
                title = requireContext().getString(R.string.homeMessagesInformationTitle),
                drawableStartId = R.drawable.ic_status_info,
                description = requireContext().getString(R.string.homeMessagesBlockDescription),
                signatureDrawablePlaceholderResId = null,
                signatureDrawableUrl = null,
                signatureText = null
            )
        )

        binding.homeMessagesSectionBlockNeutralMessageBlock.updateConfiguration(
            MessageBlockView.Configuration(
                style = MessageBlockView.Style.NEUTRAL,
                title = requireContext().getString(R.string.homeMessagesNeutralTitle),
                drawableStartId = R.drawable.ic_request_type_mileage,
                description = requireContext().getString(R.string.homeMessagesBlockDescription),
                signatureDrawablePlaceholderResId = null,
                signatureDrawableUrl = null,
                signatureText = null
            )
        )

        binding.homeMessagesSectionBlockSuccessMessageBlock.updateConfiguration(
            MessageBlockView.Configuration(
                style = MessageBlockView.Style.SUCCESS,
                title = requireContext().getString(R.string.homeMessagesSuccessTitle),
                drawableStartId = R.drawable.ic_success,
                description = requireContext().getString(R.string.homeMessagesBlockDescription),
                signatureDrawablePlaceholderResId = null,
                signatureDrawableUrl = null,
                signatureText = null
            )
        )

        // Blocks Signature

        binding.homeMessagesSectionBlockSignatureAlertMessageBlock.updateConfiguration(
            MessageBlockView.Configuration(
                style = MessageBlockView.Style.ALERT,
                title = requireContext().getString(R.string.homeMessagesAlertTitle),
                drawableStartId = R.drawable.ic_status_error,
                description = requireContext().getString(R.string.homeMessagesBlockDescription),
                signatureDrawablePlaceholderResId = R.drawable.ic_avatar_placeholder,
                signatureDrawableUrl = "https://avatars.githubusercontent.com/u/9486557?s=60&v=4",
                signatureText = "Jean Michel"
            )
        )

        binding.homeMessagesSectionBlockSignatureWarningMessageBlock.updateConfiguration(
            MessageBlockView.Configuration(
                style = MessageBlockView.Style.WARNING,
                title = requireContext().getString(R.string.homeMessagesWarningTitle),
                drawableStartId = R.drawable.ic_warning,
                description = requireContext().getString(R.string.homeMessagesBlockDescription),
                signatureDrawablePlaceholderResId = R.drawable.ic_avatar_placeholder,
                signatureDrawableUrl = "https://avatars.githubusercontent.com/u/9486557?s=60&v=4",
                signatureText = "Jean Michel"
            )
        )

        binding.homeMessagesSectionBlockSignatureInformationMessageBlock.updateConfiguration(
            MessageBlockView.Configuration(
                style = MessageBlockView.Style.INFO,
                title = requireContext().getString(R.string.homeMessagesInformationTitle),
                drawableStartId = R.drawable.ic_status_info,
                description = requireContext().getString(R.string.homeMessagesBlockDescription),
                signatureDrawablePlaceholderResId = R.drawable.ic_avatar_placeholder,
                signatureDrawableUrl = "https://avatars.githubusercontent.com/u/9486557?s=60&v=4",
                signatureText = "Jean Micheline"
            )
        )

        binding.homeMessagesSectionBlockSignatureNeutralMessageBlock.updateConfiguration(
            MessageBlockView.Configuration(
                style = MessageBlockView.Style.NEUTRAL,
                title = requireContext().getString(R.string.homeMessagesNeutralTitle),
                drawableStartId = R.drawable.ic_request_type_mileage,
                description = requireContext().getString(R.string.homeMessagesBlockDescription),
                signatureDrawablePlaceholderResId = R.drawable.ic_avatar_placeholder,
                signatureDrawableUrl = "https://avatars.githubusercontent.com/u/9486557?s=60&v=4",
                signatureText = "Jean Michel"
            )
        )

        binding.homeMessagesSectionBlockSignatureSuccessMessageBlock.updateConfiguration(
            MessageBlockView.Configuration(
                style = MessageBlockView.Style.SUCCESS,
                title = requireContext().getString(R.string.homeMessagesSuccessTitle),
                drawableStartId = R.drawable.ic_success,
                description = requireContext().getString(R.string.homeMessagesBlockDescription),
                signatureDrawablePlaceholderResId = R.drawable.ic_avatar_placeholder,
                signatureDrawableUrl = "https://avatars.githubusercontent.com/u/9486557?s=60&v=4",
                signatureText = "Jean Micheline"
            )
        )

        // Notifications

        binding.homeMessagesSectionNotificationsBadgeView.text = requireContext().getText(R.string.messagesNotificationsBadgeText)
    }
}