package com.spendesk.grapes.samples.home.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.spendesk.grapes.component.SimpleEntryItemView
import com.spendesk.grapes.component.content.summary.SummaryCardLinkView
import com.spendesk.grapes.component.content.summary.SummaryHeaderView
import com.spendesk.grapes.component.content.summary.block.SummaryBlockContentMapView
import com.spendesk.grapes.component.content.summary.block.SummaryBlockContentTextView
import com.spendesk.grapes.component.content.summary.block.definition.SummaryBlockTitleView
import com.spendesk.grapes.extensions.shortToaster
import com.spendesk.grapes.list.content.summary.SummaryBlockContentModel
import com.spendesk.grapes.list.content.summary.item.InlineKeyValueItemView
import com.spendesk.grapes.messages.MessageBlockView
import com.spendesk.grapes.messages.MessageInlineView
import com.spendesk.grapes.samples.R
import com.spendesk.grapes.samples.core.internal.viewBinding
import com.spendesk.grapes.samples.databinding.FragmentHomeContentsBinding

/**
 * @author danyboucanova
 * @since 23/06/2021
 */
class ContentsFragment : Fragment(R.layout.fragment_home_contents) {

    companion object {
        fun newInstance() = ContentsFragment()
    }

    private val binding by viewBinding(FragmentHomeContentsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.homeHeader.homeGenericHeaderTitle.text = context?.getString(R.string.contents)

        setupView()
    }

    private fun setupView() {
        setupBlockMap()
        setupBlockText()
        setupCard()
    }

    private fun setupBlockMap() {
        // Header
        with(binding.homeButtonSectionHeaderBlock) {
            val simpleEntryConfiguration = SimpleEntryItemView.Configuration(
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

            updateConfiguration(
                configuration = SummaryHeaderView.Configuration(
                    simpleEntryItemViewConfiguration = simpleEntryConfiguration,
                    amount = "130 €",
                    amountSubtitle = "This is the subtitle of the amount",
                    description = "Some description to explain why this header exists, and its existence is that questionable that it might require many many many lines to describe everything.",
                    descriptionSubtitle = "And if the description is not enough, there is also this field to explain more",
                    typeConfiguration = MessageInlineView.Configuration(
                        style = MessageInlineView.Style.WARNING,
                        title = "Missing receipt",
                        drawableStartId = R.drawable.ic_warning
                    ),
                    messageBlockConfiguration = MessageBlockView.Configuration(
                        style = MessageBlockView.Style.ALERT,
                        title = "Deny reason",
                        drawableStartId = R.drawable.ic_status_error,
                        description = "Oui oui oui t'es deny",
                        signatureText = "Par tout le monde",
                        signatureDrawableUrl = "https://avatars.githubusercontent.com/u/9486557?s=60&v=4",
                        signatureDrawablePlaceholderResId = R.drawable.ic_launcher_foreground
                    )
                )
            )
        }

        // Map extended
        with(binding.homeButtonSectionMapExtendedBlock) {
            updateConfiguration(
                configuration = SummaryBlockContentMapView.Configuration(
                    titleConfiguration = SummaryBlockTitleView.Configuration(
                        startTitle = "Trip details",
                        middleTitle = " • Optional",
                        endTitle = "Action",
                        drawableEnd = R.drawable.ic_warning,
                        isActivated = true
                    ),
                    mapImageUrl = "", // TODO Change this when we actually use Mapbox
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
            onEndTitleTextClicked = { requireActivity().shortToaster("Edit Map extended") }
        }

        // Map compact
        binding.homeButtonSectionMapCompactBlock.updateConfiguration(
            configuration = SummaryBlockContentMapView.Configuration(
                titleConfiguration = SummaryBlockTitleView.Configuration(
                    startTitle = "Route"
                ),
                mapImageUrl = "", // TODO Change this when we actually use Mapbox
                departureAddress = "Ca part de là mais c'est assez long quand même",
                arrivalAddress = "Et ça fini ici !",
                items = listOf(
                    SummaryBlockContentModel.InlineKeyValue(id = "", configuration = InlineKeyValueItemView.Configuration(key = "Key #1", value = "Value #1")),
                    SummaryBlockContentModel.InlineKeyValue(id = "", configuration = InlineKeyValueItemView.Configuration(key = "Key #2", value = "Value #2")),
                )
            )
        )
    }

    private fun setupBlockText() {
        // Text with value and image
        with(binding.homeButtonSectionTextWithValueAndImageBlock) {
            updateConfiguration(
                configuration = SummaryBlockContentTextView.Configuration(
                    titleConfiguration = SummaryBlockTitleView.Configuration(
                        startTitle = "Supplier",
                        endTitle = "Edit"
                    ),
                    value = "Dany is grave a supplier",
                    imageUrl = "https://avatars.githubusercontent.com/u/9486557?s=60&v=4",
                    imagePlaceholderResId = R.drawable.ic_launcher_foreground
                )
            )
            onEndTitleTextClicked = { requireActivity().shortToaster("Edit Text with value and image") }
        }

        // Text with value disabled
        with(binding.homeButtonSectionTextWithValueBlock) {
            updateConfiguration(
                configuration = SummaryBlockContentTextView.Configuration(
                    titleConfiguration = SummaryBlockTitleView.Configuration(
                        startTitle = "Description",
                        endTitle = "Saving",
                        isEnabled = false,
                        showProgressBar = true
                    ),
                    value = "Room booked in Lyon for 3 days to Le Progrès and organize a team building with Lyon’s team",
                    isEnabled = false
                )
            )
            onEndTitleTextClicked = { requireActivity().shortToaster("Edit Text with value") }
        }

        // Text with value selected
        with(binding.homeButtonSectionTextWithValueSelectedBlock) {
            updateConfiguration(
                configuration = SummaryBlockContentTextView.Configuration(
                    titleConfiguration = SummaryBlockTitleView.Configuration(
                        startTitle = "Description",
                        endTitle = "Error",
                        isSelected = true,
                        drawableEnd = R.drawable.ic_warning
                    ),
                    value = "Another example of a text content block but this time with a selected value, which will change the color of the title's end text"
                )
            )
            onEndTitleTextClicked = { requireActivity().shortToaster("Edit Text with value") }
        }

        // Text without value
        with(binding.homeButtonSectionTextWithoutValueBlock) {
            updateConfiguration(
                configuration = SummaryBlockContentTextView.Configuration(
                    titleConfiguration = SummaryBlockTitleView.Configuration(
                        startTitle = "Very very long Description to see what's happening when the description is long",
                        endTitle = "Add",
                        isActivated = true
                    )
                )
            )
            onEndTitleTextClicked = { requireActivity().shortToaster("Add Text without value") }
        }
    }

    private fun setupCard() {
        with(binding.homeButtonSectionCardLinkBlock) {
            updateConfiguration(
                configuration = SummaryCardLinkView.Configuration(
                    title = "Get virtual card details",
                    startImage = R.drawable.ic_crop
                )
            )
        }
    }
}