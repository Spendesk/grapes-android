package com.spendesk.grapes.samples.home.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.spendesk.grapes.component.SimpleEntryItemView
import com.spendesk.grapes.component.SimpleSectionItemView
import com.spendesk.grapes.messages.MessageInlineView
import com.spendesk.grapes.samples.R
import kotlinx.android.synthetic.main.fragment_home_lists.*
import kotlinx.android.synthetic.main.view_home_header.*

/**
 * @author danyboucanova
 * @since 23/06/2021
 */
class ListsFragment : Fragment(R.layout.fragment_home_lists) {

    companion object {
        fun newInstance() = ListsFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeGenericHeaderTitle.text = context?.getString(R.string.lists)

        setupView()
    }

    private fun setupView() {
        homeListsSectionItemOneListItem.updateConfiguration(
            SimpleEntryItemView.Configuration(
                primaryImageUrl = "https://avatars.githubusercontent.com/u/9486557?s=60&v=4",
                placeholderPrimaryImage = R.drawable.ic_launcher_foreground,
                secondaryImageUrl = "https://avatars.githubusercontent.com/u/596985?v=4",
                placeholderSecondaryImage = R.drawable.ic_launcher_foreground,
                shouldCircleCropSecondaryImage = true,
                titleStart = "Supplier",
                descriptionStart = "Employee name • Description",
                titleEnd = "71,03 €",
                descriptionEnd = "14 June",
                titleEndOptional = "$68,34",
                messageConfiguration = MessageInlineView.Configuration(
                    style = MessageInlineView.Style.WARNING,
                    title = "Missing receipt",
                    drawableStartId = R.drawable.ic_warning
                ),
                isSelected = true
            )
        )

        homeListsSectionItemTwoListItem.updateConfiguration(
            SimpleEntryItemView.Configuration(
                primaryImageUrl = "https://avatars.githubusercontent.com/u/9486557?s=60&v=4",
                placeholderPrimaryImage = R.drawable.ic_launcher_foreground,
                shouldCircleCropPrimaryImage = true,
                titleStart = "Dany Bouca Nova",
                isEnabled = false,
                titleStartDrawable = R.drawable.ic_clock
            )
        )

        homeListsSectionItemThreeListItem.updateConfiguration(SimpleEntryItemView.Configuration(titleStart = "51 Rue de Londres. 75007, Paris"))

        homeListsSectionItemFourListItem.updateConfiguration(
            SimpleEntryItemView.Configuration(
                titleStart = "Number of kebabs per game",
                descriptionStart = "C'est vraiment un n00b..",
                imageAltText = "10"
            )
        )

        homeListsSectionItemFifthListItem.updateConfiguration(
            SimpleEntryItemView.Configuration(
                primaryImageUrl = "https://avatars.githubusercontent.com/u/596985?v=4",
                placeholderPrimaryImage = R.drawable.ic_launcher_foreground,
                shouldCircleCropPrimaryImage = true,
                titleStart = "Vivien Mahé",
                descriptionStart = "OIAU OIEJF ODIJF ",
                isSelected = true
            )
        )

        homeListsSectionItemListOne.updateConfiguration(
            SimpleSectionItemView.Configuration(
                iconStart = R.drawable.ic_crop,
                startText = "This is right",
                endText = "This is Left"
            )
        )

        homeListsSectionItemListTwo.updateConfiguration(
            SimpleSectionItemView.Configuration(
                startText = "This is right",
                style = SimpleSectionItemView.Style.SECONDARY
            )
        )
    }
}