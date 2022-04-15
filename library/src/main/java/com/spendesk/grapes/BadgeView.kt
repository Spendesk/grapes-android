package com.spendesk.grapes

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.textview.MaterialTextView

/**
 * @author danyboucanova
 * @since 18/11/2020
 */
class BadgeView : MaterialTextView {

    //region constructors
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    //endregion constructors

    init {
        if (isInEditMode.not()) setTypeface(ResourcesCompat.getFont(context, R.font.gt_america_bold), Typeface.NORMAL)

        gravity = Gravity.CENTER
        background = ContextCompat.getDrawable(context, R.drawable.shape_solidmainstatuserror_paddingvert2horz8_corner30)
        setTextSize(TypedValue.COMPLEX_UNIT_PX, resources.getDimensionPixelOffset(R.dimen.badgeViewTextSize).toFloat())
        setTextColor(ContextCompat.getColor(context, R.color.badgeViewTextColor))
    }
}