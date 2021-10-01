package com.spendesk.grapes.samples.home.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.spendesk.grapes.component.SimpleEntryItemView
import com.spendesk.grapes.component.SimpleSectionItemView
import com.spendesk.grapes.extensions.empty
import com.spendesk.grapes.list.simple.SimpleListAdapter
import com.spendesk.grapes.list.simple.SimpleListModel
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

    private val adapter = SimpleListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeGenericHeaderTitle.text = context?.getString(R.string.lists)

        setupView()
    }

    private fun setupView() {
        // Items
        val item1Configuration = SimpleEntryItemView.Configuration(
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
            )
        )

        val item2Configuration = SimpleEntryItemView.Configuration(
            primaryImageUrl = "https://avatars.githubusercontent.com/u/9486557?s=60&v=4",
            placeholderPrimaryImage = R.drawable.ic_launcher_foreground,
            shouldCircleCropPrimaryImage = true,
            titleStart = "Dany Bouca Nova",
            isEnabled = false,
            titleStartDrawable = R.drawable.ic_clock,
        )

        val item3Configuration = SimpleEntryItemView.Configuration(titleStart = "51 Rue de Londres. 75007, Paris")

        val item4Configuration = SimpleEntryItemView.Configuration(
            titleStart = "Number of kebabs per game",
            descriptionStart = "C'est vraiment un n00b..",
            imageAltText = "10"
        )

        val item5Configuration = SimpleEntryItemView.Configuration(
            primaryImageUrl = "https://avatars.githubusercontent.com/u/596985?v=4",
            placeholderPrimaryImage = R.drawable.ic_launcher_foreground,
            shouldCircleCropPrimaryImage = true,
            titleStart = "Vivien Mahé",
            descriptionStart = "Hé les gars, j'suis SSL !",
            isSelected = true
        )

        val item6Configuration = SimpleEntryItemView.Configuration(
            placeholderPrimaryImage = R.drawable.ic_pencil,
            shouldCircleCropPrimaryImage = true,
            titleStart = "A single line title",
            titleEnd = "71,03 €",
            descriptionEnd = "14 June"
        )

        // Sections
        val section1Configuration = SimpleSectionItemView.Configuration(
            iconStart = R.drawable.ic_crop,
            startText = "This is a primary section",
            endText = "Right text"
        )

        val section2Configuration = SimpleSectionItemView.Configuration(
            startText = "This is a secondary section",
            style = SimpleSectionItemView.Style.SECONDARY
        )

        // List
        homeListsSectionList.adapter = adapter

        adapter.updateList(
            items = listOf(
                SimpleListModel.Section(id = String.empty(), configuration = section1Configuration),
                SimpleListModel.Item(id = "1", configuration = item1Configuration),
                SimpleListModel.Item(id = "2", configuration = item2Configuration),
                SimpleListModel.Section(id = String.empty(), configuration = section2Configuration),
                SimpleListModel.Item(id = "3", configuration = item3Configuration),
                SimpleListModel.Item(id = "4", configuration = item4Configuration),
                SimpleListModel.Item(id = "5", configuration = item5Configuration),
                SimpleListModel.Item(id = "6", configuration = item6Configuration),
            )
        )
    }
}