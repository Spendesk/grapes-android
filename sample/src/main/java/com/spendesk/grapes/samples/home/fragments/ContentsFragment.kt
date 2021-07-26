package com.spendesk.grapes.samples.home.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.spendesk.grapes.component.content.summary.SummaryBlockTitleView
import com.spendesk.grapes.component.content.summary.noneditable.SummaryBlockContentMapView
import com.spendesk.grapes.extensions.empty
import com.spendesk.grapes.extensions.shortToaster
import com.spendesk.grapes.list.content.summary.SummaryBlockContentModel
import com.spendesk.grapes.list.content.summary.item.InlineKeyValueItemView
import com.spendesk.grapes.samples.R
import kotlinx.android.synthetic.main.fragment_home_contents.*
import kotlinx.android.synthetic.main.view_home_header.*

/**
 * @author danyboucanova
 * @since 23/06/2021
 */
class ContentsFragment : Fragment(R.layout.fragment_home_contents) {

    companion object {
        fun newInstance() = ContentsFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeGenericHeaderTitle.text = context?.getString(R.string.contents)

        setupView()
    }

    private fun setupView() {
        // Map extended
        with(homeButtonSectionMapExtendedBlock) {
            updateConfiguration(
                configuration = SummaryBlockContentMapView.Configuration(
                    titleConfiguration = SummaryBlockTitleView.Configuration(
                        startTitle = "Trip details",
                        middleTitle = "Optional",
                        endTitle = "Action",
                    ),
                    mapImageUrl = String.empty(), // TODO Change this when we actually use Mapbox
                    departureAddress = "Ca part de là mais c'est assez long quand même",
                    arrivalAddress = "Et ça fini ici !",
                    items = listOf(
                        SummaryBlockContentModel.InlineKeyValue(id = "", configuration = InlineKeyValueItemView.Configuration(key = "Key #1", value = "Value #1")),
                        SummaryBlockContentModel.InlineKeyValue(id = "", configuration = InlineKeyValueItemView.Configuration(key = "Key #2", value = "Value #2")),
                        SummaryBlockContentModel.InlineKeyValue(id = "", configuration = InlineKeyValueItemView.Configuration(key = "Key #3", value = "Value #3")),
                        SummaryBlockContentModel.InlineKeyValue(id = "", configuration = InlineKeyValueItemView.Configuration(key = "Key #4", value = "Value #4")),
                        SummaryBlockContentModel.InlineKeyValue(id = "", configuration = InlineKeyValueItemView.Configuration(key = "Key #5", value = "Value #5")),
                        SummaryBlockContentModel.InlineKeyValue(id = "", configuration = InlineKeyValueItemView.Configuration(key = "Key #6", value = "Value #6"))
                    ),
                    buttonCollapsedText = "View more",
                    buttonExpandedText = "View less"
                )
            )
            onEndTitleTextClicked = { requireActivity().shortToaster("Extended action clicked!") }
        }

        // Map compact
        homeButtonSectionMapCompactBlock.updateConfiguration(
            configuration = SummaryBlockContentMapView.Configuration(
                titleConfiguration = SummaryBlockTitleView.Configuration(
                    startTitle = "Route",
                ),
                mapImageUrl = String.empty(), // TODO Change this when we actually use Mapbox
                departureAddress = "Ca part de là mais c'est assez long quand même",
                arrivalAddress = "Et ça fini ici !",
                items = listOf(
                    SummaryBlockContentModel.InlineKeyValue(id = "", configuration = InlineKeyValueItemView.Configuration(key = "Key #1", value = "Value #1")),
                    SummaryBlockContentModel.InlineKeyValue(id = "", configuration = InlineKeyValueItemView.Configuration(key = "Key #2", value = "Value #2")),
                )
            )
        )
    }
}