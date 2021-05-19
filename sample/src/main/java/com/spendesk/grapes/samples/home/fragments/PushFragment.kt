package com.spendesk.grapes.samples.home.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.spendesk.grapes.DeepBlueBucketView
import com.spendesk.grapes.InformativeActionBucketView
import com.spendesk.grapes.MessageInlineView
import com.spendesk.grapes.samples.R
import kotlinx.android.synthetic.main.fragment_home_push.*
import kotlinx.android.synthetic.main.view_home_header.*

/**
 * @author danyboucanova
 * @since 1/6/21
 */
class PushFragment : Fragment(R.layout.fragment_home_push) {

    companion object {
        fun newInstance() = PushFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeGenericHeaderTitle.text = context?.getString(R.string.pushs)

        bindView()
    }

    private fun bindView() {
        homePushSectionOnTheWayDeepBlueBucketView.updateData(
            DeepBlueBucketView.Configuration(
                title = requireContext().getString(R.string.homePushSectionOnTheWayDeepBlueBucketViewTitle),
                description = requireContext().getString(R.string.homePushSectionOnTheWayDeepBlueBucketViewDescription),
                buttonText = requireContext().getString(R.string.homePushSectionOnTheWayDeepBlueBucketViewButtonText)
            )
        )
        homePushSectionRecardDeepBlueBucketView.updateData(
            DeepBlueBucketView.Configuration(
                title = requireContext().getString(R.string.homePushSectionRecardDeepBlueBucketViewTitle),
                description = requireContext().getString(R.string.homePushSectionRecardDeepBlueBucketViewDescription),
                buttonText = requireContext().getString(R.string.homePushSectionRecardDeepBlueBucketViewButtonText)
            )
        )

        homePushSectionPlasticCardNormalInformativeActionBucketView.updateData(
            InformativeActionBucketView.Configuration(
                title = requireContext().getString(R.string.homePushSectionPlasticCardNormalInformativeActionBucketViewTitle),
                smallButtonText = requireContext().getString(R.string.homePushSectionPlasticCardNormalInformativeActionBucketViewSmallButtonText),
                subtitleText = requireContext().getString(R.string.homePushSectionPlasticCardNormalInformativeActionBucketViewSubtitleText),
                shouldShowChip = false,
                messageContent = null
            )
        )

        homePushSectionPlasticCardWarningInformativeActionBucketView.updateData(
            InformativeActionBucketView.Configuration(
                title = requireContext().getString(R.string.homePushSectionPlasticCardWarningInformativeActionBucketViewTitle),
                smallButtonText = requireContext().getString(R.string.homePushSectionPlasticCardWarningInformativeActionBucketViewSmallButtonText),
                subtitleText = requireContext().getString(R.string.homePushSectionPlasticCardWarningInformativeActionBucketViewSubtitleText),
                shouldShowChip = true,
                messageContent = MessageInlineView.Style.WARNING
            )
        )

        homePushSectionPlasticCardAlertInformativeActionBucketView.updateData(
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