package com.spendesk.grapes.samples.home.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.spendesk.grapes.list.item.ListItemView
import com.spendesk.grapes.list.item.SectionListItemView
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
            ListItemView.Configuration(
                primaryImage = "https://avatars.githubusercontent.com/u/9486557?s=60&v=4",
                placeholderPrimaryImage = R.drawable.ic_launcher_background,
                secondaryImage = "https://avatars.githubusercontent.com/u/596985?v=4",
                placeholderSecondaryImage = R.drawable.ic_launcher_background,
                shouldCircleCropSecondaryImage = true,
                titleStart = "Supplier",
                descriptionStart = "Employee name • Description",
                titleEnd = "71,03 €",
                descriptionEnd = "14 June"
            )
        )

        homeListsSectionItemTwoListItem.updateConfiguration(
            ListItemView.Configuration(
                primaryImage = "https://avatars.githubusercontent.com/u/9486557?s=60&v=4",
                placeholderPrimaryImage = R.drawable.ic_launcher_background,
                shouldCircleCropPrimaryImage = true,
                titleStart = "Dany Bouca Nova",
            )
        )

        homeListsSectionItemThreeListItem.updateConfiguration(ListItemView.Configuration(titleStart = "51 Rue de Londres. 75007, Paris"))

        homeListsSectionItemListOne.updateConfiguration(
            SectionListItemView.Configuration(
                iconStart = R.drawable.ic_crop,
                startText = "This is right",
                endText = "This is Left"
            )
        )

        homeListsSectionItemListTwo.updateConfiguration(
            SectionListItemView.Configuration(startText = "This is right")
        )
    }
}