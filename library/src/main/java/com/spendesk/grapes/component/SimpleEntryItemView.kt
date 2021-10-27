package com.spendesk.grapes.component

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
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
        val isGrayedOut: Boolean = false,
        val isSelected: Boolean = false,
        @DrawableRes val titleStartDrawable: Int = ResourcesCompat.ID_NULL,
        val titleEndOptional: CharSequence? = null,
        val badgeNumber: Int? = null,
    )

    private val primaryImageRoundedCornerRadius = resources.getDimensionPixelOffset(R.dimen.simpleEntryItemBPrimaryImageCornerRadius)
    private val secondaryImageRoundedCornerRadius = resources.getDimensionPixelOffset(R.dimen.simpleEntryItemBSecondaryImageCornerRadius)

    init {
        View.inflate(context, R.layout.component_simple_entry_item, this)

        addRippleEffect()
    }

    override fun setSelected(selected: Boolean) {
        super.setSelected(selected)

        setBackgroundColor(ContextCompat.getColor(context, if (selected) R.color.simpleEntryItemBackgroundColorSelected else R.color.simpleEntryItemBackgroundColorDefault))

        simpleEntryItemSelectionMarker.visibleOrInvisible(selected)

        simpleEntryItemTitleStart.isSelected = selected
        simpleEntryItemTitleEnd.isSelected = selected
        simpleEntryItemDescriptionStart.isSelected = selected
        simpleEntryItemDescriptionEnd.isSelected = selected
    }

    fun updateConfiguration(configuration: Configuration) {
        configuration.primaryImageUrl
            ?.let {
                simpleEntryItemPrimaryImage.visible()
                simpleEntryItemPrimaryImage.loadFromUrl(
                    url = configuration.primaryImageUrl,
                    errorResId = configuration.placeholderPrimaryImage,
                    shouldCircleCrop = configuration.shouldCircleCropPrimaryImage,
                    roundedCorners = if (configuration.shouldCircleCropPrimaryImage) 0 else primaryImageRoundedCornerRadius
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
                    shouldCircleCrop = configuration.shouldCircleCropSecondaryImage,
                    roundedCorners = if (configuration.shouldCircleCropSecondaryImage) 0 else secondaryImageRoundedCornerRadius
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
        simpleEntryItemBadge.visibleWithTextOrGone(configuration.badgeNumber?.toString())

        configuration.messageConfiguration
            ?.let {
                simpleEntryItemMessage.visible()
                simpleEntryItemMessage.updateConfiguration(configuration = it)
            }
            ?: run {
                simpleEntryItemMessage.gone()
            }

        setGrayedOut(isGrayedOut = configuration.isGrayedOut)
        isSelected = configuration.isSelected

        when (configuration.titleStartDrawable != ResourcesCompat.ID_NULL) {
            true -> simpleEntryItemTitleStart.setDrawableRight(configuration.titleStartDrawable)
            false -> simpleEntryItemTitleStart.removeDrawables()
        }

        configuration.titleEndOptional
            ?.let {
                simpleEntryItemTitleEndOptional.text = String.format(context.getString(R.string.simpleEntryItemTitleEndOptionalFormat), it)
                simpleEntryItemTitleEndOptional.visible()
            }
            ?: run { simpleEntryItemTitleEndOptional.gone() }
    }

    fun setGrayedOut(isGrayedOut: Boolean) {
        simpleEntryItemPrimaryImage.alpha = if (isGrayedOut) IMAGE_ALPHA_REDUCED else IMAGE_ALPHA_DEFAULT
        simpleEntryItemSecondaryImage.alpha = if (isGrayedOut) IMAGE_ALPHA_REDUCED else IMAGE_ALPHA_DEFAULT
        simpleEntryItemTitleStart.isEnabled = isGrayedOut.not()
        simpleEntryItemTitleEnd.isEnabled = isGrayedOut.not()
    }
}