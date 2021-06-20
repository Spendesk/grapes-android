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
        // Picker List
        val pickerListViewAdapter = PickerAdapter()
        val pickerListModelAdapter = listOf<PickerModel>(
            PickerModel.Block(PickerBlockIconCardView.Configuration(isSelected = false, android.R.drawable.ic_media_previous)),
            PickerModel.Block(PickerBlockIconCardView.Configuration(isSelected = true, android.R.drawable.ic_media_play)),
            PickerModel.Block(PickerBlockIconCardView.Configuration(isSelected = false, android.R.drawable.ic_media_next))
        )

        homeSelectorsSectionPickerListView.adapter = pickerListViewAdapter
        pickerListViewAdapter.updateList(pickerListModelAdapter)


        // Picker Card
        homeSelectorsSectionPickerCardView.updateConfiguration(
            PickerCardView.Configuration(
                listOf<PickerModel>(
                    PickerModel.Label(PickerLabelTextView.Configuration(isSelected = false, "level 1")),
                    PickerModel.Label(PickerLabelTextView.Configuration(isSelected = false, "level 2")),
                    PickerModel.Label(PickerLabelTextView.Configuration(isSelected = false, "level 3")),
                    PickerModel.Label(PickerLabelTextView.Configuration(isSelected = false, "level 4")),
                )
            )
        )

        // SwitchCard
        homeSelectorsSectionSwitchCard.updateConfiguration(SwitchCardView.Configuration(text = "I AM the subtitle", isChecked = false))
    }
}