package com.spendesk.grapes.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.spendesk.grapes.R
import com.spendesk.grapes.databinding.ComponentSimpleEntryItemBinding
import com.spendesk.grapes.extensions.*
import com.spendesk.grapes.internal.libs.glide.loadFromUrl
import com.spendesk.grapes.messages.MessageInlineView
import java.io.Serializable

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

    data class Configuration(
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
    ) : Serializable

    private val primaryImageRoundedCornerRadius = resources.getDimensionPixelOffset(R.dimen.simpleEntryItemBPrimaryImageCornerRadius)
    private val secondaryImageRoundedCornerRadius = resources.getDimensionPixelOffset(R.dimen.simpleEntryItemBSecondaryImageCornerRadius)

    private val binding: ComponentSimpleEntryItemBinding = ComponentSimpleEntryItemBinding.inflate(LayoutInflater.from(context), this)

    init {
        addRippleEffect()
    }

    override fun setSelected(selected: Boolean) {
        super.setSelected(selected)

        setBackgroundColor(ContextCompat.getColor(context, if (selected) R.color.simpleEntryItemBackgroundColorSelected else R.color.simpleEntryItemBackgroundColorDefault))

        with(binding) {
            simpleEntryItemSelectionMarker.visibleOrInvisible(selected)

            simpleEntryItemTitleStart.isSelected = selected
            simpleEntryItemTitleEnd.isSelected = selected
            simpleEntryItemDescriptionStart.isSelected = selected
            simpleEntryItemDescriptionEnd.isSelected = selected
        }
    }

    fun updateConfiguration(configuration: Configuration) {
        configuration.primaryImageUrl
            ?.let {
                binding.simpleEntryItemPrimaryImage.visible()
                binding.simpleEntryItemPrimaryImage.loadFromUrl(
                    url = configuration.primaryImageUrl,
                    errorResId = configuration.placeholderPrimaryImage,
                    shouldCircleCrop = configuration.shouldCircleCropPrimaryImage,
                    roundedCorners = if (configuration.shouldCircleCropPrimaryImage) 0 else primaryImageRoundedCornerRadius
                )
            }
            ?: run {
                when (configuration.placeholderPrimaryImage != ResourcesCompat.ID_NULL) {
                    true -> {
                        binding.simpleEntryItemPrimaryImage.visible()
                        binding.simpleEntryItemPrimaryImage.setImageResource(configuration.placeholderPrimaryImage)
                    }
                    false -> {
                        binding.simpleEntryItemPrimaryImage.gone()
                        binding.simpleEntryItemImageAltText.visibleWithTextOrGone(configuration.imageAltText)
                    }
                }
            }

        configuration.secondaryImageUrl
            ?.let {
                binding.simpleEntryItemSecondaryImage.visible()
                binding.simpleEntryItemSecondaryImage.loadFromUrl(
                    url = configuration.secondaryImageUrl,
                    errorResId = configuration.placeholderSecondaryImage,
                    shouldCircleCrop = configuration.shouldCircleCropSecondaryImage,
                    roundedCorners = if (configuration.shouldCircleCropSecondaryImage) 0 else secondaryImageRoundedCornerRadius
                )
            }
            ?: run {
                when (configuration.placeholderSecondaryImage != ResourcesCompat.ID_NULL) {
                    true -> {
                        binding.simpleEntryItemSecondaryImage.visible()
                        binding.simpleEntryItemSecondaryImage.setImageResource(configuration.placeholderSecondaryImage)
                    }
                    false -> binding.simpleEntryItemSecondaryImage.gone()
                }
            }

        with(binding) {
            simpleEntryItemTitleStart.visibleWithTextOrGone(configuration.titleStart)
            simpleEntryItemDescriptionStart.visibleWithTextOrGone(configuration.descriptionStart)
            simpleEntryItemTitleEnd.visibleWithTextOrGone(configuration.titleEnd)
            simpleEntryItemDescriptionEnd.visibleWithTextOrGone(configuration.descriptionEnd)
            simpleEntryItemBadge.visibleWithTextOrGone(configuration.badgeNumber?.toString())
        }

        configuration.messageConfiguration
            ?.let {
                binding.simpleEntryItemMessage.visible()
                binding.simpleEntryItemMessage.updateConfiguration(configuration = it)
            }
            ?: run {
                binding.simpleEntryItemMessage.gone()
            }

        setGrayedOut(isGrayedOut = configuration.isGrayedOut)
        isSelected = configuration.isSelected

        when (configuration.titleStartDrawable != ResourcesCompat.ID_NULL) {
            true -> binding.simpleEntryItemTitleStart.setDrawableRight(configuration.titleStartDrawable)
            false -> binding.simpleEntryItemTitleStart.removeDrawables()
        }

        configuration.titleEndOptional
            ?.let {
                binding.simpleEntryItemTitleEndOptional.text = String.format(context.getString(R.string.simpleEntryItemTitleEndOptionalFormat), it)
                binding.simpleEntryItemTitleEndOptional.visible()
            }
            ?: run { binding.simpleEntryItemTitleEndOptional.gone() }
    }

    fun setGrayedOut(isGrayedOut: Boolean) {
        with(binding) {
            simpleEntryItemPrimaryImage.alpha = if (isGrayedOut) IMAGE_ALPHA_REDUCED else IMAGE_ALPHA_DEFAULT
            simpleEntryItemSecondaryImage.alpha = if (isGrayedOut) IMAGE_ALPHA_REDUCED else IMAGE_ALPHA_DEFAULT
            simpleEntryItemTitleStart.isEnabled = isGrayedOut.not()
            simpleEntryItemTitleEnd.isEnabled = isGrayedOut.not()
        }
    }
}
