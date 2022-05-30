package com.spendesk.grapes.samples.home.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.spendesk.grapes.ActionMessageBottomSheetDialogFragment
import com.spendesk.grapes.bottomsheet.editabletext.EditableTextBottomSheetDialogFragment
import com.spendesk.grapes.bottomsheet.searchable.SearchableBottomSheetDialogFragment
import com.spendesk.grapes.bottomsheet.searchable.SearchableBottomSheetDialogFragmentViewState
import com.spendesk.grapes.component.SimpleEntryItemView
import com.spendesk.grapes.component.SimpleSectionItemView
import com.spendesk.grapes.extensions.shortToaster
import com.spendesk.grapes.list.simple.SimpleListModel
import com.spendesk.grapes.samples.R
import com.spendesk.grapes.samples.core.internal.viewBinding
import com.spendesk.grapes.samples.databinding.FragmentHomeBottomSheetsBinding
import com.spendesk.spendesk.presentation.view.bottomsheet.editabletext.EditableTextBottomSheetDialogFragmentViewState

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

        // ActionMessage with small content
        binding.homeBottomSheetsSectionActionMessageSmallContentButton.setOnClickListener {
            val smallConfiguration = ActionMessageBottomSheetDialogFragment.Configuration(
                imageResourceId = R.drawable.ic_supplier_placeholder,
                title = "Small content action message",
                description = "A small description of two lines or three\nIn fact it depends how large your screen is, because this sentence is a bit long it might take several lines",
                primaryButtonText = "Primary",
                secondaryButtonText = "Secondary",
                shouldShowHandle = true
            )
            ActionMessageBottomSheetDialogFragment.newInstance().apply {
                updateConfiguration(smallConfiguration)
                show(this@BottomSheetsFragment.requireActivity().supportFragmentManager, ActionMessageBottomSheetDialogFragment::class.java.name)
            }
        }

        // ActionMessage with large content
        binding.homeBottomSheetsSectionActionMessageLargeContentButton.setOnClickListener {
            val longConfiguration = ActionMessageBottomSheetDialogFragment.Configuration(
                imageResourceId = R.drawable.ic_supplier_placeholder,
                title = "Large content action message",
                description = "A large description of many many lines\n".repeat(40),
                primaryButtonText = "Primary",
                secondaryButtonText = "Secondary",
                shouldShowHandle = true
            )
            ActionMessageBottomSheetDialogFragment.newInstance().apply {
                updateConfiguration(longConfiguration)
                show(this@BottomSheetsFragment.requireActivity().supportFragmentManager, ActionMessageBottomSheetDialogFragment::class.java.name)
            }
        }

        // EditableText with text
        binding.homeBottomSheetsSectionEditableTextContentButton.setOnClickListener {
            EditableTextBottomSheetDialogFragment.newInstance(
                configuration = EditableTextBottomSheetDialogFragment.Configuration(
                    title = "The EditableText BottomSheet with text",
                    hintText = "This is some description",
                    buttonText = "Validate"
                )
            ).apply {
                onValidateButtonClicked = { requireActivity().shortToaster("Validate button clicked with editable text: $it"); dismiss() }

                updateViewState(viewState = EditableTextBottomSheetDialogFragmentViewState.Content(text = "Some value"))
                show(this@BottomSheetsFragment.requireActivity().supportFragmentManager, EditableTextBottomSheetDialogFragment::class.java.name)
            }
        }

        // EditableText with empty text
        binding.homeBottomSheetsSectionEditableTextEmptyButton.setOnClickListener {
            EditableTextBottomSheetDialogFragment.newInstance(
                configuration = EditableTextBottomSheetDialogFragment.Configuration(
                    title = "The EditableText BottomSheet no text",
                    hintText = "This is some description",
                    buttonText = "Validate"
                )
            ).apply {
                onValidateButtonClicked = { requireActivity().shortToaster("Validate button clicked with editable text: $it"); dismiss() }

                updateViewState(viewState = EditableTextBottomSheetDialogFragmentViewState.Content())
                show(this@BottomSheetsFragment.requireActivity().supportFragmentManager, EditableTextBottomSheetDialogFragment::class.java.name)
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