package com.spendesk.grapes.samples.components

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.spendesk.grapes.extensions.setMargins
import com.spendesk.grapes.samples.R

/**
 * @author danyboucanova
 * @since 10/03/2022
 */
class SectionTitleTextView : AppCompatTextView {

    //region constructors
    constructor(context: Context) : super(context, null, R.style.HomeGenericTitle)
    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet, R.style.HomeGenericTitle)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(context, attributeSet, R.style.HomeGenericTitle)
    //endregion constructors

    init {
        val marginVert = resources.getDimensionPixelSize(R.dimen.homeSectionTitleMarginVert)
        val marginHorz = resources.getDimensionPixelSize(R.dimen.homeSectionTitleMarginHorz)

        setMargins(left = marginHorz, top = marginVert, right = marginHorz, bottom = marginVert)
    }
}