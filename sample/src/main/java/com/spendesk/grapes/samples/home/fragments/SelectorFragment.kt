package com.spendesk.grapes.samples.home.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.spendesk.grapes.extensions.shortToaster
import com.spendesk.grapes.samples.R
import com.spendesk.grapes.selectors.*
import kotlinx.android.synthetic.main.fragment_home_selectors.*
import kotlinx.android.synthetic.main.view_home_header.*
import kotlin.random.Random

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

    private var currentAnimatedStatusIndex = 0
    private var maxAnimatedStatusIndex = 5

    private fun bindView() {
        // Header Pager Indicator
        homeSelectorsSectionHeaderStatusIndicatorFirstHeaderStatusIndicator.updateConfiguration(HeaderStatusIndicator.Configuration(5))
        homeSelectorsSectionHeaderStatusIndicatorFirstHeaderStatusIndicator.updateStatusIndex(0)

        homeSelectorsSectionHeaderStatusIndicatorSecondHeaderStatusIndicator.updateConfiguration(HeaderStatusIndicator.Configuration(5))
        homeSelectorsSectionHeaderStatusIndicatorSecondHeaderStatusIndicator.updateStatusIndex(2)

        homeSelectorsSectionHeaderStatusIndicatorThirdHeaderStatusIndicator.updateConfiguration(HeaderStatusIndicator.Configuration(5))
        homeSelectorsSectionHeaderStatusIndicatorThirdHeaderStatusIndicator.updateStatusIndex(5)

        homeSelectorsSectionHeaderStatusIndicatorFourthHeaderStatusIndicator.updateConfiguration(HeaderStatusIndicator.Configuration(maxAnimatedStatusIndex))
        homeSelectorsSectionHeaderStatusIndicatorFourthHeaderStatusIndicator.updateStatusIndex(currentAnimatedStatusIndex)

        homeSelectorsSectionHeaderStatusIndicatorFourthSubtitle.setOnClickListener {
            currentAnimatedStatusIndex = (currentAnimatedStatusIndex - 1) % maxAnimatedStatusIndex
            homeSelectorsSectionHeaderStatusIndicatorFourthHeaderStatusIndicator.updateStatusIndex(currentAnimatedStatusIndex, true)
        }

        homeSelectorsSectionHeaderStatusIndicatorFourthRandomButton.setOnClickListener {
            currentAnimatedStatusIndex = Random.nextInt(0, maxAnimatedStatusIndex)
            activity?.shortToaster("Show index $currentAnimatedStatusIndex")
            homeSelectorsSectionHeaderStatusIndicatorFourthHeaderStatusIndicator.updateStatusIndex(currentAnimatedStatusIndex, true)
        }

        homeSelectorsSectionHeaderStatusIndicatorFourthHeaderStatusIndicator.setOnClickListener {
            currentAnimatedStatusIndex = (currentAnimatedStatusIndex + 1) % maxAnimatedStatusIndex
            homeSelectorsSectionHeaderStatusIndicatorFourthHeaderStatusIndicator.updateStatusIndex(currentAnimatedStatusIndex, true)
        }

        homeSelectorsSectionHeaderStatusIndicatorFifthHeaderStatusIndicator.updateConfiguration(HeaderStatusIndicator.Configuration(5))
        homeSelectorsSectionHeaderStatusIndicatorFifthHeaderStatusIndicator.updateStatusIndex(3)

        homeSelectorsSectionHeaderStatusIndicatorFifthRandomButton.setOnClickListener {
            val newMaxItems = Random.nextInt(1, 5)
            activity?.shortToaster("Show max item $newMaxItems")
            homeSelectorsSectionHeaderStatusIndicatorFifthHeaderStatusIndicator.updateConfiguration(HeaderStatusIndicator.Configuration(newMaxItems), shouldAnimate = true)
        }

        // Picker Cards List
        val pickerCardListViewAdapter = PickerAdapter()
        val pickerCardListModel = listOf<PickerModel>(
            PickerModel.Block(id = "lel", configuration = PickerBlockIconCardView.Configuration(isSelected = false, android.R.drawable.ic_media_previous)),
            PickerModel.Block(id = "lol", configuration = PickerBlockIconCardView.Configuration(isSelected = true, android.R.drawable.ic_media_play)),
            PickerModel.Block(id = "lille", configuration = PickerBlockIconCardView.Configuration(isSelected = false, android.R.drawable.ic_media_next))
        )

        homeSelectorsSectionPickerListView.adapter = pickerCardListViewAdapter
        pickerCardListViewAdapter.onItemSelected = { _, _ -> activity?.shortToaster("Picker Card Item List Checked !") }
        pickerCardListViewAdapter.updateList(pickerCardListModel)


        // Picker Texts List
        val pickerTextListViewAdapter = PickerAdapter()
        val pickerTextListModel = listOf<PickerModel>(
            PickerModel.Label(id = "hoy", configuration = PickerLabelTextView.Configuration(isSelected = false, "level 1")),
            PickerModel.Label(id = "hey", configuration = PickerLabelTextView.Configuration(isSelected = false, "level 2")),
            PickerModel.Label(id = "hoyjoy", configuration = PickerLabelTextView.Configuration(isSelected = false, "level 3")),
            PickerModel.Label(id = "uesh", configuration = PickerLabelTextView.Configuration(isSelected = false, "level 4"))
        )
        homeSelectorsSectionPickerTextListView.adapter = pickerTextListViewAdapter
        pickerTextListViewAdapter.onItemSelected = { _, _ -> activity?.shortToaster("Picker Card Item List Checked !") }
        pickerTextListViewAdapter.updateList(pickerTextListModel)

        // SwitchCard
        homeSelectorsSectionSwitchCard.updateConfiguration(SwitchCardView.Configuration(text = "I AM the subtitle", isChecked = false))

        // Selectors
        // The other selectors are init via XML
        homeSelectorsSectionSelectorOne.updateConfiguration(
            configuration = SelectorView.Configuration(
                text = "Spendesk FR",
                notificationText = null,
                shouldShowDrawableEnd = true
            )
        )
    }
}