package com.spendesk.grapes.selectors

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.widget.Checkable
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.spendesk.grapes.R

/**
 * @author danyboucanova
 * @since 03/06/2021
 */
open class SelectLabelTextView : AppCompatTextView, Checkable {

    //region constructors

    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr)

    init {
        background = ContextCompat.getDrawable(context, R.drawable.selector_select_label)

        // Add ripple effect
        val outValue = TypedValue()
        context.theme.resolveAttribute(android.R.attr.selectableItemBackground, outValue, true)
        foreground = ContextCompat.getDrawable(context, outValue.resourceId)

        isClickable = true
        isFocusable = true
    }

    //endregion constructors

    private var isChecked: Boolean = false

    override fun performClick(): Boolean {
        toggle()
        return super.performClick()
    }

    override fun isChecked(): Boolean = isChecked

    override fun setChecked(checked: Boolean) {
        this.isChecked = checked
    }

    override fun toggle() {
        setChecked(!this.isChecked)
    }

    override fun onCreateDrawableState(extraSpace: Int): IntArray {
        val checkedStateSet = intArrayOf(android.R.attr.state_checked)
        val drawableState: IntArray = super.onCreateDrawableState(extraSpace + 1)

        if (isChecked()) {
            mergeDrawableStates(drawableState, checkedStateSet)
        }
        return drawableState
    }
}