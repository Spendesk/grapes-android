package com.spendesk.grapes.samples.home.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.spendesk.grapes.samples.R
import com.spendesk.grapes.selectors.*
import kotlinx.android.synthetic.main.fragment_home_selectors.*
import kotlinx.android.synthetic.main.view_home_header.*

/**
 * @author danyboucanova
 * @since 14/06/2021
 */
class SelectorFragment : Fragment(R.layout.fragment_home_selectors) {

    companion object {
        fun newInstance() = SelectorFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeGenericHeaderTitle.text = context?.getString(R.string.selectors)

        bindView()
    }

    private fun bindView() {
        // Header Pager Indicator
        homeSelectorsSectionHeaderStatusIndicatorFirstHeaderStatusIndicator.updateConfiguration(HeaderStatusIndicator.Configuration(5))
        homeSelectorsSectionHeaderStatusIndicatorFirstHeaderStatusIndicator.updateStatusIndex(0)

        homeSelectorsSectionHeaderStatusIndicatorSecondHeaderStatusIndicator.updateConfiguration(HeaderStatusIndicator.Configuration(5))
        homeSelectorsSectionHeaderStatusIndicatorSecondHeaderStatusIndicator.updateStatusIndex(2)

        homeSelectorsSectionHeaderStatusIndicatorThirdHeaderStatusIndicator.updateConfiguration(HeaderStatusIndicator.Configuration(5))
        homeSelectorsSectionHeaderStatusIndicatorThirdHeaderStatusIndicator.updateStatusIndex(5)

        // Picker List
        val pickerListViewAdapter = PickerAdapter()
        val pickerListModelAdapter = listOf<PickerModel>(
            PickerModel.Block(id = "lel", configuration = PickerBlockIconCardView.Configuration(isSelected = false, android.R.drawable.ic_media_previous)),
            PickerModel.Block(id = "lol", configuration = PickerBlockIconCardView.Configuration(isSelected = true, android.R.drawable.ic_media_play)),
            PickerModel.Block(id = "lille", configuration = PickerBlockIconCardView.Configuration(isSelected = false, android.R.drawable.ic_media_next))
        )

        homeSelectorsSectionPickerListView.adapter = pickerListViewAdapter
        pickerListViewAdapter.updateList(pickerListModelAdapter)


        // Picker Card
        homeSelectorsSectionPickerCardView.updateConfiguration(
            PickerCardView.Configuration(
                listOf<PickerModel>(
                    PickerModel.Label(id = "hoy", configuration = PickerLabelTextView.Configuration(isSelected = false, "level 1")),
                    PickerModel.Label(id = "hey", configuration = PickerLabelTextView.Configuration(isSelected = false, "level 2")),
                    PickerModel.Label(id = "hoyjoy", configuration = PickerLabelTextView.Configuration(isSelected = false, "level 3")),
                    PickerModel.Label(id = "uesh", configuration = PickerLabelTextView.Configuration(isSelected = false, "level 4")),
                )
            )
        )

        // SwitchCard
        homeSelectorsSectionSwitchCard.updateConfiguration(SwitchCardView.Configuration(text = "I AM the subtitle", isChecked = false))
    }
}