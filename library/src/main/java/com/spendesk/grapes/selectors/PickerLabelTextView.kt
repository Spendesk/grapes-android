package com.spendesk.grapes.selectors

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.setPadding
import com.spendesk.grapes.R

/**
 * @author danyboucanova
 * @since 08/06/2021
 */
class PickerLabelTextView : SelectLabelTextView {

    //region constructors

    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr)

    //endregion constructors

    init {
        isClickable = true
        gravity = Gravity.CENTER

        setTypeface(ResourcesCompat.getFont(context, R.font.gt_america), Typeface.NORMAL)
        setTextSize(TypedValue.COMPLEX_UNIT_PX, resources.getDimensionPixelSize(R.dimen.pickerLabelTextSize).toFloat())
    }

    data class Configuration(
        val isSelected: Boolean,
        val text: CharSequence,
        val isPadded: Boolean
    )

    fun updateConfiguration(configuration: Configuration) {
        text = configuration.text
        isChecked = configuration.isSelected

        if (configuration.isPadded) setPadding(resources.getDimensionPixelOffset(R.dimen.pickerLabelPadding))
        setTextColor(ContextCompat.getColor(context, if (configuration.isSelected) R.color.pickerLabelSelectedTextColor else R.color.pickerLabelUnselectedTextColor))
    }
}
