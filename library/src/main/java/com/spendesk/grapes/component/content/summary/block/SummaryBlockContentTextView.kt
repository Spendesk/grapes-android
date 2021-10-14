package com.spendesk.grapes.component.content.summary.block

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.annotation.DrawableRes
import com.spendesk.grapes.R
import com.spendesk.grapes.component.content.summary.block.definition.SummaryBlockTitleView
import com.spendesk.grapes.component.content.summary.block.definition.SummaryBlockView
import com.spendesk.grapes.extensions.visibleOrGone
import com.spendesk.grapes.internal.libs.glide.loadFromUrl
import kotlinx.android.synthetic.main.summary_block_content_text.view.*

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

    class Configuration(
        titleConfiguration: SummaryBlockTitleView.Configuration,
        val value: CharSequence? = null,
        val imageUrl: String? = null,
        @DrawableRes val imagePlaceholderResId: Int? = null,
        val shouldCircleCropImage: Boolean = false,
    ) : SummaryBlockView.Configuration(titleConfiguration)

    private val imageRoundedCornerRadius = resources.getDimensionPixelOffset(R.dimen.inlineBlockViewImageCornerRadius)

    init {
        View.inflate(context, R.layout.summary_block_content_text, this)
    }

    override fun getSummaryBlockTitleView(): SummaryBlockTitleView = summaryBlockContentTextTitle

    fun updateConfiguration(configuration: Configuration) {
        super.updateConfiguration(configuration)

        summaryBlockContentValueText.visibleOrGone(configuration.value != null)
        configuration.value?.let { summaryBlockContentValueText.text = it }

        val shouldDisplayImage = configuration.imageUrl != null || configuration.imagePlaceholderResId != null
        summaryBlockContentImage.visibleOrGone(shouldDisplayImage)
        if (shouldDisplayImage) {
            setImage(url = configuration.imageUrl, placeholderResId = configuration.imagePlaceholderResId, shouldCircleCrop = configuration.shouldCircleCropImage)
        }
    }

    fun setImage(url: String?, placeholderResId: Int? = 0, shouldCircleCrop: Boolean) {
        val cornerRadius = if (shouldCircleCrop) 0 else imageRoundedCornerRadius
        summaryBlockContentImage.loadFromUrl(url = url, errorResId = placeholderResId, shouldCircleCrop = shouldCircleCrop, roundedCorners = cornerRadius)
    }
}