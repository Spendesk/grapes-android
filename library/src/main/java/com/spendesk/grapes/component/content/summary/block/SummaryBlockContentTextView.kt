package com.spendesk.grapes.component.content.summary.block

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.DrawableRes
import com.spendesk.grapes.R
import com.spendesk.grapes.component.content.summary.block.definition.SummaryBlockTitleView
import com.spendesk.grapes.component.content.summary.block.definition.SummaryBlockView
import com.spendesk.grapes.databinding.SummaryBlockContentTextBinding
import com.spendesk.grapes.extensions.visibleOrGone
import com.spendesk.grapes.internal.libs.glide.loadFromUrl

/**
 * @author Vivien Mahe
 * @since 07/01/2021
 */
class SummaryBlockContentTextView : SummaryBlockView {

    //region constructors
    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr)
    //endregion constructors

    data class Configuration(
        override val titleConfiguration: SummaryBlockTitleView.Configuration,
        val value: CharSequence? = null,
        val imageUrl: String? = null,
        @DrawableRes val imagePlaceholderResId: Int? = null,
        val shouldCircleCropImage: Boolean = false,
        val isEnabled: Boolean = true
    ) : SummaryBlockView.Configuration(titleConfiguration)

    private var binding = SummaryBlockContentTextBinding.inflate(LayoutInflater.from(context), this, true)
    private val imageRoundedCornerRadius = resources.getDimensionPixelOffset(R.dimen.inlineBlockViewImageCornerRadius)

    override fun getSummaryBlockTitleView(): SummaryBlockTitleView = binding.summaryBlockContentTextTitle

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)

        binding.summaryBlockContentValueText.isEnabled = enabled
    }

    fun updateConfiguration(configuration: Configuration) {
        super.updateConfiguration(configuration)

        binding.summaryBlockContentValueText.visibleOrGone(configuration.value != null)
        configuration.value?.let { binding.summaryBlockContentValueText.text = it }

        val shouldDisplayImage = configuration.imageUrl != null || configuration.imagePlaceholderResId != null
        binding.summaryBlockContentImage.visibleOrGone(shouldDisplayImage)
        if (shouldDisplayImage) {
            setImage(url = configuration.imageUrl, placeholderResId = configuration.imagePlaceholderResId, shouldCircleCrop = configuration.shouldCircleCropImage)
        }

        isEnabled = configuration.isEnabled
    }

    fun setImage(url: String?, placeholderResId: Int? = 0, shouldCircleCrop: Boolean) {
        val cornerRadius = if (shouldCircleCrop) 0 else imageRoundedCornerRadius
        binding.summaryBlockContentImage.loadFromUrl(url = url, errorResId = placeholderResId, shouldCircleCrop = shouldCircleCrop, roundedCorners = cornerRadius)
    }
}