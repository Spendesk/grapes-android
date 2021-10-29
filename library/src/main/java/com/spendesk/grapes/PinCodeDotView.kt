package com.spendesk.grapes

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.spendesk.grapes.extensions.setDrawable

/**
 * @author danyboucanova
 * @since 3/9/21
 */
class PinCodeDotView : LinearLayout {

    //region constructors

    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet, 0)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr)

    //endregion constructors

    data class Configuration(
        val pinCodeLength: Int
    )

    private var maxCodeLength: Int? = null

    init {
        orientation = HORIZONTAL
        showDividers = SHOW_DIVIDER_MIDDLE
        dividerDrawable = ContextCompat.getDrawable(context, R.drawable.divider_24dp)
    }

    fun updateConfiguration(configuration: Configuration) {
        maxCodeLength = configuration.pinCodeLength

        repeat(configuration.pinCodeLength) { addView(addOvalShapeWith(R.color.colorPrimaryDark)) }
    }

    fun updateViewFromString(text: String) {
        if (maxCodeLength == null) throw IllegalStateException("Error: The ${javaClass.simpleName} class must be update by its configuration before use.")

        for (pos in 0 until maxCodeLength!!) {
            replaceViewColorAtPosition(pos, if (pos in text.indices) R.color.white else R.color.colorPrimaryDark)
        }
    }

    private fun addOvalShapeWith(@ColorRes color: Int): View =
        View(context)
            .apply {
                layoutParams = LayoutParams(
                    context.resources.getDimensionPixelOffset(R.dimen.pinCodeDotViewItemSize),
                    context.resources.getDimensionPixelOffset(R.dimen.pinCodeDotViewItemSize)
                )
                setDrawable(colorId = color, shape = GradientDrawable.OVAL)
            }

    private fun replaceViewColorAtPosition(pos: Int, @ColorRes color: Int) =
        getChildAt(pos).setDrawable(colorId = color, shape = GradientDrawable.OVAL)
}