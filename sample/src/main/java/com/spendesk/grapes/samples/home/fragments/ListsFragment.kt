package com.spendesk.grapes.samples.home.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.spendesk.grapes.component.SimpleEntryItemView
import com.spendesk.grapes.component.SimpleSectionItemView
import com.spendesk.grapes.list.simple.SimpleListAdapter
import com.spendesk.grapes.list.simple.SimpleListModel
import com.spendesk.grapes.messages.MessageInlineView
import com.spendesk.grapes.samples.R
import com.spendesk.grapes.samples.core.internal.viewBinding
import com.spendesk.grapes.samples.databinding.FragmentHomeListsBinding

/**
 * @author danyboucanova
 * @since 23/06/2021
 */
class ListsFragment : Fragment(R.layout.fragment_home_lists) {

    companion object {
        fun newInstance() = ListsFragment()
    }

    private val binding by viewBinding(FragmentHomeListsBinding::bind)
    private val adapter = SimpleListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.homeHeader.homeGenericHeaderTitle.text = context?.getString(R.string.lists)

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
            isGrayedOut = false,
            titleStartDrawable = R.drawable.ic_clock,
        )

        val item3Configuration = SimpleEntryItemView.Configuration(titleStart = "51 Rue de Londres. 75007, Paris")

        val item4Configuration = SimpleEntryItemView.Configuration(
            titleStart = "Number of kebabs per game",
            descriptionStart = "C'est vraiment un n00b.. en plus il a une grande description",
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

        val item7Configuration = SimpleEntryItemView.Configuration(
            placeholderPrimaryImage = R.drawable.ic_pencil,
            shouldCircleCropPrimaryImage = true,
            titleStart = "Full single line",
            titleEndOptional = "$68,34",
            titleEnd = "71,03 €"
        )

        val item8Configuration = SimpleEntryItemView.Configuration(
            titleStart = "Jean Michel de la vega",
            badgeNumber = 42
        )

        val item9Configuration = SimpleEntryItemView.Configuration(
            placeholderPrimaryImage = R.drawable.ic_pencil,
            shouldCircleCropPrimaryImage = true,
            titleStart = "Very expansive",
            titleEnd = "73242534,69 €",
            descriptionStart = "Super longue descritpion qui va passer sous le prix parce qu'elle est trop grosse",
        )

        val item10Configuration = SimpleEntryItemView.Configuration(
            placeholderPrimaryImage = R.drawable.ic_pencil,
            shouldCircleCropPrimaryImage = true,
            titleStart = "Kebab",
            titleEnd = "7,69 €",
            descriptionStart = "Super longue descritpion qui va passer sous le prix parce qu'elle est trop grosse",
        )

        val item11Configuration = SimpleEntryItemView.Configuration(
            placeholderPrimaryImage = R.drawable.ic_pencil,
            shouldCircleCropPrimaryImage = true,
            titleStart = "Very expansive with date",
            titleEnd = "73242534,69 €",
            descriptionStart = "Super longue descritpion qui va passer sous le prix parce qu'elle est trop grosse",
            descriptionEnd = "14 June"

        )

        val item12Configuration = SimpleEntryItemView.Configuration(
            placeholderPrimaryImage = R.drawable.ic_pencil,
            shouldCircleCropPrimaryImage = true,
            titleStart = "Kebab with date in the cold with a mustache doing a backflip",
            titleEnd = "7 €",
            descriptionStart = "Super longue descritpion qui va passer sous le prix parce qu'elle est trop grosse",
            descriptionEnd = "14 November"
        )

        val item13Configuration = SimpleEntryItemView.Configuration(
            placeholderPrimaryImage = R.drawable.ic_pencil,
            shouldCircleCropPrimaryImage = true,
            titleStart = "Kebab with date in the cold with a mustache doing a backflip",
            descriptionStart = "Super longue descritpion qui va passer sous le prix parce qu'elle est trop grosse",
            descriptionEnd = "14 November"
        )

        val item14Configuration = SimpleEntryItemView.Configuration(
            placeholderPrimaryImage = R.drawable.ic_pencil,
            shouldCircleCropPrimaryImage = true,
            titleStart = "Kebab with date in the cold with a mustache doing a backflip",
            titleEnd = "7 €",
            titleEndOptional = "$68,34",
            descriptionStart = "Super longue descritpion qui va passer sous le prix parce qu'elle est trop grosse",
            descriptionEnd = "14 November"
        )

        val item15Configuration = SimpleEntryItemView.Configuration(
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
            messageConfiguration = null
        )

        val item16Configuration = SimpleEntryItemView.Configuration(
            titleStart = "Some title",
            descriptionStart = "Some long description to test drawable end padding",
            imageAltText = "2",
            drawableEnd = R.drawable.ic_arrow_right,
        )

        val item17Configuration = SimpleEntryItemView.Configuration(
            titleStart = "Some title",
            descriptionStart = "Some long description to test drawable end padding",
            imageAltText = "2",
            drawableEnd = R.drawable.ic_arrow_right,
            imageAltBackgroundColor = R.color.mainAlertNormal,
            imageAltTextColor = R.color.black,
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
        binding.homeListsSectionList.adapter = adapter

        adapter.updateList(
            items = listOf(
                SimpleListModel.Section(id = "", configuration = section1Configuration),
                SimpleListModel.Item(id = "1", configuration = item1Configuration),
                SimpleListModel.Item(id = "2", configuration = item2Configuration),
                SimpleListModel.Section(id = "", configuration = section2Configuration),
                SimpleListModel.Item(id = "3", configuration = item3Configuration),
                SimpleListModel.Item(id = "4", configuration = item4Configuration),
                SimpleListModel.Item(id = "5", configuration = item5Configuration),
                SimpleListModel.Item(id = "6", configuration = item6Configuration),
                SimpleListModel.Item(id = "7", configuration = item7Configuration),
                SimpleListModel.Item(id = "8", configuration = item8Configuration),
                SimpleListModel.Item(id = "9", configuration = item9Configuration),
                SimpleListModel.Item(id = "10", configuration = item10Configuration),
                SimpleListModel.Item(id = "11", configuration = item11Configuration),
                SimpleListModel.Item(id = "12", configuration = item12Configuration),
                SimpleListModel.Item(id = "13", configuration = item13Configuration),
                SimpleListModel.Item(id = "14", configuration = item14Configuration),
                SimpleListModel.Item(id = "15", configuration = item15Configuration),
                SimpleListModel.Item(id = "16", configuration = item16Configuration),
                SimpleListModel.Item(id = "17", configuration = item17Configuration),
            )
        )
    }
}
