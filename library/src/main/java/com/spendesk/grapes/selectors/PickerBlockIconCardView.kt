package com.spendesk.grapes.selectors

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.spendesk.grapes.R
import kotlinx.android.synthetic.main.selector_picker_block_icon.view.*

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

    init {
        View.inflate(context, R.layout.selector_picker_block_icon, this)

        isClickable = true
        isFocusable = true

        minimumHeight = resources.getDimensionPixelOffset(R.dimen.pickerBlockIconCardViewSize)
        minimumWidth = resources.getDimensionPixelOffset(R.dimen.pickerBlockIconCardViewSize)
    }

    fun updateConfiguration(configuration: Configuration) {
        isChecked = configuration.isSelected

        pickerBlockIconImageView.setImageResource(configuration.icon)
        pickerBlockIconImageView.setColorFilter(
            ContextCompat.getColor(context, if (configuration.isSelected) R.color.pickerBlockIconCardViewIconSelectedTint else R.color.pickerBlockIconCardViewIconUnselectedTint)
        )
    }
}