package com.spendesk.grapes.samples.home.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.spendesk.grapes.DeepBlueBucketView
import com.spendesk.grapes.InformativeActionBucketView
import com.spendesk.grapes.messages.MessageInlineView
import com.spendesk.grapes.samples.R
import kotlinx.android.synthetic.main.fragment_home_cards.*
import kotlinx.android.synthetic.main.view_home_header.*

/**
 * @author danyboucanova
 * @since 1/6/21
 */
class CardsFragment : Fragment(R.layout.fragment_home_cards) {

    companion object {
        fun newInstance() = CardsFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeGenericHeaderTitle.text = context?.getString(R.string.cards)

        bindView()
    }

    private fun bindView() {
        homeCardSectionOnTheWayDeepBlueBucketView.updateData(
            DeepBlueBucketView.Configuration(
                title = requireContext().getString(R.string.homePushSectionOnTheWayDeepBlueBucketViewTitle),
                description = requireContext().getString(R.string.homePushSectionOnTheWayDeepBlueBucketViewDescription),
                buttonText = requireContext().getString(R.string.homePushSectionOnTheWayDeepBlueBucketViewButtonText)
            )
        )
        homeCardSectionRecardDeepBlueBucketView.updateData(
            DeepBlueBucketView.Configuration(
                title = requireContext().getString(R.string.homePushSectionRecardDeepBlueBucketViewTitle),
                description = requireContext().getString(R.string.homePushSectionRecardDeepBlueBucketViewDescription),
                buttonText = requireContext().getString(R.string.homePushSectionRecardDeepBlueBucketViewButtonText)
            )
        )

        homeCardSectionPlasticCardNormalInformativeActionBucketView.updateData(
            InformativeActionBucketView.Configuration(
                title = requireContext().getString(R.string.homePushSectionPlasticCardNormalInformativeActionBucketViewTitle),
                smallButtonText = requireContext().getString(R.string.homePushSectionPlasticCardNormalInformativeActionBucketViewSmallButtonText),
                subtitleText = requireContext().getString(R.string.homePushSectionPlasticCardNormalInformativeActionBucketViewSubtitleText),
                shouldShowChip = false,
                messageContent = null
            )
        )

        homeCardSectionPlasticCardWarningInformativeActionBucketView.updateData(
            InformativeActionBucketView.Configuration(
                title = requireContext().getString(R.string.homePushSectionPlasticCardWarningInformativeActionBucketViewTitle),
                smallButtonText = requireContext().getString(R.string.homePushSectionPlasticCardWarningInformativeActionBucketViewSmallButtonText),
                subtitleText = requireContext().getString(R.string.homePushSectionPlasticCardWarningInformativeActionBucketViewSubtitleText),
                shouldShowChip = true,
                messageContent = MessageInlineView.Style.WARNING
            )
        )

        homeCardSectionPlasticCardAlertInformativeActionBucketView.updateData(
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