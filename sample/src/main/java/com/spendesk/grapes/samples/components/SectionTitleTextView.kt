package com.spendesk.grapes.samples.components

import android.content.Context
import android.graphics.Typeface
import android.text.TextUtils
import android.util.AttributeSet
import android.util.TypedValue
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.spendesk.grapes.samples.R

/**
 * @author danyboucanova
 * @since 10/03/2022
 */
class SectionTitleTextView : AppCompatTextView {

    //region constructors

    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr)

    //endregion constructors

    init {
        maxLines = 1
        ellipsize = TextUtils.TruncateAt.END
        setTypeface(ResourcesCompat.getFont(context, R.font.gt_america), Typeface.NORMAL)
        setTextSize(TypedValue.COMPLEX_UNIT_PX, resources.getDimensionPixelSize(R.dimen.sectionTitleTextSize).toFloat())
        setTextColor(ContextCompat.getColor(context, R.color.sectionTitleTextColor))
    }
}