package com.spendesk.grapes.component

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import com.spendesk.grapes.R
import com.spendesk.grapes.extensions.*
import com.spendesk.grapes.internal.libs.glide.loadFromUrl
import com.spendesk.grapes.messages.MessageInlineView
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

    companion object {
        private const val IMAGE_ALPHA_DEFAULT = 1f
        private const val IMAGE_ALPHA_REDUCED = 0.6f
    }

    class Configuration(
        val primaryImageUrl: String? = null,
        val shouldCircleCropPrimaryImage: Boolean = false,
        @DrawableRes val placeholderPrimaryImage: Int = ResourcesCompat.ID_NULL,
        val secondaryImageUrl: String? = null,
        val shouldCircleCropSecondaryImage: Boolean = false,
        @DrawableRes val placeholderSecondaryImage: Int = ResourcesCompat.ID_NULL,
        val imageAltText: CharSequence? = null,
        val titleStart: CharSequence,
        val descriptionStart: CharSequence? = null,
        val titleEnd: CharSequence? = null,
        val descriptionEnd: CharSequence? = null,
        val messageConfiguration: MessageInlineView.Configuration? = null,
        val isEnabled: Boolean = true,
        @DrawableRes val titleStartDrawable: Int = ResourcesCompat.ID_NULL
    )

    init {
        View.inflate(context, R.layout.component_simple_entry_item, this)

        val paddingStart = resources.getDimensionPixelOffset(R.dimen.listItemPaddingStart)
        val paddingEnd = resources.getDimensionPixelOffset(R.dimen.listItemPaddingEnd)
        val paddingVert = resources.getDimensionPixelOffset(R.dimen.listItemPaddingVert)
        setPadding(paddingStart, paddingVert, paddingEnd, paddingVert)
    }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)

        simpleEntryItemPrimaryImage.alpha = if (enabled) IMAGE_ALPHA_DEFAULT else IMAGE_ALPHA_REDUCED
        simpleEntryItemSecondaryImage.alpha = if (enabled) IMAGE_ALPHA_DEFAULT else IMAGE_ALPHA_REDUCED
        simpleEntryItemTitleStart.isEnabled = enabled
        simpleEntryItemTitleEnd.isEnabled = enabled
    }

    fun updateConfiguration(configuration: Configuration) {
        // Add ripple to the view
        setBackgroundResource(R.drawable.shape_ripple_rect_solidwhite)

        configuration.primaryImageUrl
            ?.let {
                simpleEntryItemPrimaryImage.visible()
                simpleEntryItemPrimaryImage.loadFromUrl(
                    url = configuration.primaryImageUrl,
                    errorResId = configuration.placeholderPrimaryImage,
                    shouldCircleCrop = configuration.shouldCircleCropPrimaryImage
                )
            }
            ?: run {
                when (configuration.placeholderPrimaryImage != ResourcesCompat.ID_NULL) {
                    true -> {
                        simpleEntryItemPrimaryImage.visible()
                        simpleEntryItemPrimaryImage.setImageResource(configuration.placeholderPrimaryImage)
                    }
                    false -> {
                        simpleEntryItemPrimaryImage.gone()
                        simpleEntryItemImageAltText.visibleWithTextOrGone(configuration.imageAltText)
                    }
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

        configuration.messageConfiguration
            ?.let {
                simpleEntryItemMessage.visible()
                simpleEntryItemMessage.updateConfiguration(configuration = it)
            }
            ?: run {
                simpleEntryItemMessage.gone()
            }

        isEnabled = configuration.isEnabled

        when (configuration.titleStartDrawable != ResourcesCompat.ID_NULL) {
            true -> simpleEntryItemTitleStart.setDrawableRight(configuration.titleStartDrawable)
            false -> simpleEntryItemTitleStart.removeDrawables()
        }
    }
}