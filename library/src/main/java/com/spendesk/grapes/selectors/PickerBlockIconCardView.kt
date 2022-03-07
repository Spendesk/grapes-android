package com.spendesk.grapes.selectors

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.spendesk.grapes.R
import com.spendesk.grapes.databinding.SelectorPickerBlockIconBinding

/**
 * @author danyboucanova
 * @since 03/06/2021
 */
class PickerBlockIconCardView : SelectBlockCardView {

    //region constructors

    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr)

    //endregion constructors

    data class Configuration(
        val isSelected: Boolean,
        @DrawableRes val icon: Int
    )

    private val binding: SelectorPickerBlockIconBinding = SelectorPickerBlockIconBinding.inflate(LayoutInflater.from(context), this)

    init {
        isClickable = true
        isFocusable = true

        minimumHeight = resources.getDimensionPixelOffset(R.dimen.pickerBlockIconCardViewSize)
        minimumWidth = resources.getDimensionPixelOffset(R.dimen.pickerBlockIconCardViewSize)
    }

    fun updateConfiguration(configuration: Configuration) {
        isChecked = configuration.isSelected

        with(binding.pickerBlockIconImageView) {
            setImageResource(configuration.icon)
            setColorFilter(
                ContextCompat.getColor(context, if (configuration.isSelected) R.color.pickerBlockIconCardViewIconSelectedTint else R.color.pickerBlockIconCardViewIconUnselectedTint)
            )
        }
    }
}