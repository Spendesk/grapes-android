package com.spendesk.grapes.samples.home.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.spendesk.grapes.DeepBlueBucketView
import com.spendesk.grapes.InformativeActionBucketView
import com.spendesk.grapes.messages.MessageInlineView
import com.spendesk.grapes.samples.R
import com.spendesk.grapes.samples.core.internal.viewBinding
import com.spendesk.grapes.samples.databinding.FragmentHomeCardsBinding

/**
 * @author danyboucanova
 * @since 1/6/21
 */
class CardsFragment : Fragment(R.layout.fragment_home_cards) {

    companion object {
        fun newInstance() = CardsFragment()
    }

    private val binding by viewBinding(FragmentHomeCardsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.homeHeader.homeGenericHeaderTitle.text = context?.getString(R.string.cards)

        bindView()
    }

    private fun bindView() {
        binding.homeCardSectionOnTheWayDeepBlueBucketView.updateData(
            DeepBlueBucketView.Configuration(
                title = requireContext().getString(R.string.homePushSectionOnTheWayDeepBlueBucketViewTitle),
                description = requireContext().getString(R.string.homePushSectionOnTheWayDeepBlueBucketViewDescription),
                buttonText = requireContext().getString(R.string.homePushSectionOnTheWayDeepBlueBucketViewButtonText)
            )
        )
        binding.homeCardSectionRecardDeepBlueBucketView.updateData(
            DeepBlueBucketView.Configuration(
                title = requireContext().getString(R.string.homePushSectionRecardDeepBlueBucketViewTitle),
                description = requireContext().getString(R.string.homePushSectionRecardDeepBlueBucketViewDescription),
                buttonText = requireContext().getString(R.string.homePushSectionRecardDeepBlueBucketViewButtonText)
            )
        )

        binding.homeCardSectionPlasticCardNormalInformativeActionBucketView.updateData(
            InformativeActionBucketView.Configuration(
                title = requireContext().getString(R.string.homePushSectionPlasticCardNormalInformativeActionBucketViewTitle),
                smallButtonText = requireContext().getString(R.string.homePushSectionPlasticCardNormalInformativeActionBucketViewSmallButtonText),
                subtitleText = requireContext().getString(R.string.homePushSectionPlasticCardNormalInformativeActionBucketViewSubtitleText),
                shouldShowChip = false,
                messageContent = null
            )
        )

        binding.homeCardSectionPlasticCardWarningInformativeActionBucketView.updateData(
            InformativeActionBucketView.Configuration(
                title = requireContext().getString(R.string.homePushSectionPlasticCardWarningInformativeActionBucketViewTitle),
                smallButtonText = requireContext().getString(R.string.homePushSectionPlasticCardWarningInformativeActionBucketViewSmallButtonText),
                subtitleText = requireContext().getString(R.string.homePushSectionPlasticCardWarningInformativeActionBucketViewSubtitleText),
                shouldShowChip = true,
                messageContent = MessageInlineView.Style.WARNING
            )
        )

        binding.homeCardSectionPlasticCardAlertInformativeActionBucketView.updateData(
            InformativeActionBucketView.Configuration(
                title = requireContext().getString(R.string.homePushSectionPlasticCardAlertInformativeActionBucketViewTitle),
                smallButtonText = requireContext().getString(R.string.homePushSectionPlasticCardAlertInformativeActionBucketViewSmallButtonText),
                subtitleText = requireContext().getString(R.string.homePushSectionPlasticCardAlertInformativeActionBucketViewSubtitleText),
                shouldShowChip = true,
                messageContent = MessageInlineView.Style.ALERT
            )
        )
    }
}