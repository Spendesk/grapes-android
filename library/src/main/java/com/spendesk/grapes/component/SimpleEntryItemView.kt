package com.spendesk.grapes.component

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import com.spendesk.grapes.R
import com.spendesk.grapes.extensions.gone
import com.spendesk.grapes.extensions.visible
import com.spendesk.grapes.extensions.visibleWithTextOrGone
import com.spendesk.grapes.internal.libs.glide.loadFromUrl
import kotlinx.android.synthetic.main.component_simple_entry_item.view.*

/**
 * @author danyboucanova
 * @since 22/06/2021
 */
class SimpleEntryItemView : ConstraintLayout {

    //region constructors

    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr)

    //endregion constructors

    class Configuration(
        val primaryImageUrl: String? = null,
        val shouldCircleCropPrimaryImage: Boolean = false,
        @DrawableRes val placeholderPrimaryImage: Int = ResourcesCompat.ID_NULL,
        val secondaryImageUrl: String? = null,
        val shouldCircleCropSecondaryImage: Boolean = false,
        @DrawableRes val placeholderSecondaryImage: Int = ResourcesCompat.ID_NULL,
        val titleStart: CharSequence,
        val descriptionStart: CharSequence? = null,
        val titleEnd: CharSequence? = null,
        val descriptionEnd: CharSequence? = null
    )

    init {
        View.inflate(context, R.layout.component_simple_entry_item, this)

        val paddingStart = resources.getDimensionPixelOffset(R.dimen.listItemPaddingStart)
        val paddingEnd = resources.getDimensionPixelOffset(R.dimen.listItemPaddingEnd)
        val paddingVert = resources.getDimensionPixelOffset(R.dimen.listItemPaddingVert)
        setPadding(paddingStart, paddingVert, paddingEnd, paddingVert)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val desiredHeight = resources.getDimensionPixelSize(R.dimen.simpleEntryItemViewHeight)
        val desiredWidth = MeasureSpec.getSize(widthMeasureSpec)

        super.onMeasure(
            MeasureSpec.makeMeasureSpec(desiredWidth, MeasureSpec.EXACTLY),
            MeasureSpec.makeMeasureSpec(desiredHeight, MeasureSpec.EXACTLY)
        )
    }

    fun updateConfiguration(configuration: Configuration) {
        // Add ripple to the view
        setBackgroundResource(R.drawable.shape_ripple_rect_solidwhite)

        configuration.primaryImageUrl
            ?.let {
                simpleEnryItemPrimaryImage.visible()
                simpleEnryItemPrimaryImage.loadFromUrl(
                    url = configuration.primaryImageUrl,
                    errorResId = configuration.placeholderPrimaryImage,
                    shouldCircleCrop = configuration.shouldCircleCropPrimaryImage
                )
            }
            ?: run {
                when (configuration.placeholderPrimaryImage != ResourcesCompat.ID_NULL) {
                    true -> {
                        simpleEnryItemPrimaryImage.visible()
                        simpleEnryItemPrimaryImage.setImageResource(configuration.placeholderPrimaryImage)
                    }
                    false -> simpleEnryItemPrimaryImage.gone()
                }
            }

        configuration.secondaryImageUrl
            ?.let {
                simpleEntryItemSecondaryImage.visible()
                simpleEntryItemSecondaryImage.loadFromUrl(
                    url = configuration.secondaryImageUrl,
                    errorResId = configuration.placeholderSecondaryImage,
                    shouldCircleCrop = configuration.shouldCircleCropSecondaryImage
                )
            }
            ?: run {
                when (configuration.placeholderSecondaryImage != ResourcesCompat.ID_NULL) {
                    true -> {
                        simpleEntryItemSecondaryImage.visible()
                        simpleEntryItemSecondaryImage.setImageResource(configuration.placeholderSecondaryImage)
                    }
                    false -> simpleEntryItemSecondaryImage.gone()
                }
            }

        simpleEntryItemTitleStart.visibleWithTextOrGone(configuration.titleStart)
        simpleEntryItemDescriptionStart.visibleWithTextOrGone(configuration.descriptionStart)
        simpleEntryItemTitleEnd.visibleWithTextOrGone(configuration.titleEnd)
        simpleEntryItemDescriptionEnd.visibleWithTextOrGone(configuration.descriptionEnd)
    }
}