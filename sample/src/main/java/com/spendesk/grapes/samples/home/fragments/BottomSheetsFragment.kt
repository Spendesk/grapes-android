package com.spendesk.grapes.samples.home.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.spendesk.grapes.bottomsheet.searchable.SearchableBottomSheetDialogFragment
import com.spendesk.grapes.bottomsheet.searchable.SearchableBottomSheetDialogFragmentViewState
import com.spendesk.grapes.component.SimpleEntryItemView
import com.spendesk.grapes.component.SimpleSectionItemView
import com.spendesk.grapes.extensions.shortToaster
import com.spendesk.grapes.list.simple.SimpleListModel
import com.spendesk.grapes.samples.R
import com.spendesk.grapes.samples.core.internal.viewBinding
import com.spendesk.grapes.samples.databinding.FragmentHomeBottomSheetsBinding

/**
 * @author Vivien Mahe
 * @since 07/10/2021
 */
class BottomSheetsFragment : Fragment(R.layout.fragment_home_bottom_sheets) {

    companion object {
        fun newInstance() = BottomSheetsFragment()
    }

    private val binding by viewBinding(FragmentHomeBottomSheetsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.homeHeader.homeGenericHeaderTitle.text = context?.getString(R.string.bottomSheets)

        setupView()
    }

    private fun setupView() {
        // Searchable with ViewState.Content
        binding.homeBottomSheetsSectionSearchableContentButton.setOnClickListener {
            val sectionConfiguration = SimpleSectionItemView.Configuration(
                startText = "This is a secondary section",
                style = SimpleSectionItemView.Style.SECONDARY
            )
            val item1Configuration = SimpleEntryItemView.Configuration(titleStart = "51 Rue de Londres. 75007, Paris")
            val item2Configuration = SimpleEntryItemView.Configuration(titleStart = "Random text")
            val item3Configuration = SimpleEntryItemView.Configuration(titleStart = "C LA KIFFANCE")
            val item4Configuration = SimpleEntryItemView.Configuration(titleStart = "C LE KEBAB")

            createSearchableBottomSheetFragment().apply {
                updateViewState(
                    viewState = SearchableBottomSheetDialogFragmentViewState.Content(
                        items = listOf(
                            SimpleListModel.Section(id = "", configuration = sectionConfiguration),
                            SimpleListModel.Item(id = "1", configuration = item1Configuration),
                            SimpleListModel.Item(id = "2", configuration = item2Configuration),
                            SimpleListModel.Item(id = "3", configuration = item3Configuration),
                            SimpleListModel.Item(id = "4", configuration = item4Configuration)
                        )
                    )
                )

                show(this@BottomSheetsFragment.requireActivity().supportFragmentManager, SearchableBottomSheetDialogFragment::class.java.name)
            }
        }

        // Searchable with ViewState.Empty
        binding.homeBottomSheetsSectionSearchableEmptyButton.setOnClickListener {
            createSearchableBottomSheetFragment().apply {
                updateViewState(
                    viewState = SearchableBottomSheetDialogFragmentViewState.Empty(
                        title = "This is an empty state",
                        buttonText = "Click me"
                    )
                )

                show(this@BottomSheetsFragment.requireActivity().supportFragmentManager, SearchableBottomSheetDialogFragment::class.java.name)
            }
        }
    }

    private fun createSearchableBottomSheetFragment(): SearchableBottomSheetDialogFragment =
        SearchableBottomSheetDialogFragment
            .newInstance(
                configuration = SearchableBottomSheetDialogFragment.Configuration(
                    title = "The Searchable BottomSheet",
                    hintText = "Type something to search..."
                )
            )
            .apply {
                onItemClicked = { requireActivity().shortToaster("Search result clicked: $it"); dismiss() }
                onSearchInputChanged = { requireActivity().shortToaster("Search input: $it") }
            }
}