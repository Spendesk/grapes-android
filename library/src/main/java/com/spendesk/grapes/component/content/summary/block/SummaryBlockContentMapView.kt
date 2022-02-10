package com.spendesk.grapes.component.content.summary.block

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.core.view.isVisible
import com.spendesk.grapes.component.content.summary.block.definition.SummaryBlockTitleView
import com.spendesk.grapes.component.content.summary.block.definition.SummaryBlockView
import com.spendesk.grapes.databinding.SummaryBlockContentMapBinding
import com.spendesk.grapes.extensions.afterMeasured
import com.spendesk.grapes.extensions.gone
import com.spendesk.grapes.extensions.visible
import com.spendesk.grapes.internal.libs.glide.loadFromUrl
import com.spendesk.grapes.list.content.summary.SummaryBlockContentAdapter
import com.spendesk.grapes.list.content.summary.SummaryBlockContentModel

/**
 * @author Vivien Mahe
 * @since 07/01/2021
 */
class SummaryBlockContentMapView : SummaryBlockView {

    companion object {
        private const val MAP_IMAGE_HEIGHT_RATIO = 0.68 // Height is smaller than the width
    }

    //region constructors
    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr)
    //endregion constructors

    data class Configuration(
        override val titleConfiguration: SummaryBlockTitleView.Configuration,
        val mapImageUrl: String,
        val departureAddress: CharSequence,
        val arrivalAddress: CharSequence,
        val items: List<SummaryBlockContentModel.InlineKeyValue>,
        val buttonCollapsedText: CharSequence? = null, // Text appearing when the block is collapsed
        val buttonExpandedText: CharSequence? = null // Text appearing when the block is expanded
    ) : SummaryBlockView.Configuration(titleConfiguration)

    private val binding: SummaryBlockContentMapBinding = SummaryBlockContentMapBinding.inflate(LayoutInflater.from(context), this, true)
    private val adapter = SummaryBlockContentAdapter()
    private var buttonCollapsedText: CharSequence? = null
    private var buttonExpandedText: CharSequence? = null

    init {
        setupView()
    }

    override fun getSummaryBlockTitleView(): SummaryBlockTitleView = binding.summaryBlockContentMapTitle

    fun updateConfiguration(configuration: Configuration) {
        super.updateConfiguration(configuration)

        this.buttonCollapsedText = configuration.buttonCollapsedText
        this.buttonExpandedText = configuration.buttonExpandedText

        with(binding) {
            summaryBlockContentMapDepartureTitle.text = configuration.departureAddress
            summaryBlockContentMapArrivalTitle.text = configuration.arrivalAddress

            adapter.updateList(configuration.items)

            if (shouldDisplayViewMoreButton()) {
                summaryBlockContentViewMoreButton.visible()
                summaryBlockContentViewMoreButton.text = buttonCollapsedText
            } else {
                summaryBlockContentMapList.visible()
            }

            afterMeasured {
                // Wait for this view to be measured, because we need the dimensions of the imageView to use with Glide.
                with(summaryBlockContentMapImage) {
                    // Apply a ratio for the height of the image, the width is the full width of the view
                    val imageWidth = measuredWidth
                    val imageHeight = (imageWidth.toFloat() * MAP_IMAGE_HEIGHT_RATIO).toInt()

                    layoutParams.width = imageWidth
                    layoutParams.height = imageHeight

                    loadFromUrl(url = configuration.mapImageUrl, size = Pair(imageWidth, imageHeight)) {
                        // If we couldn't load the image, we just hide the imageView
                        gone()
                    }
                }
            }
        }
    }

    private fun setupView() {
        with(binding) {
            summaryBlockContentMapList.gone()
            summaryBlockContentMapList.adapter = adapter

            summaryBlockContentViewMoreButton.setOnClickListener {
                when {
                    summaryBlockContentMapList.isVisible -> {
                        summaryBlockContentMapList.gone()
                        summaryBlockContentViewMoreButton.text = buttonCollapsedText
                    }

                    summaryBlockContentMapList.isVisible.not() -> {
                        summaryBlockContentMapList.visible()
                        summaryBlockContentViewMoreButton.text = buttonExpandedText
                    }
                }
            }
        }
    }

    private fun shouldDisplayViewMoreButton() = buttonCollapsedText != null && buttonExpandedText != null
}