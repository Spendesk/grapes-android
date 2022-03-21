package com.spendesk.grapes.samples.components

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.spendesk.grapes.internal.itemdecoration.MarginDecoration
import com.spendesk.grapes.samples.R

/**
 * @author danyboucanova
 * @since 09/03/2022
 */
open class CoverListView : RecyclerView {

    //region constructors
    constructor(context: Context) : super(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr)
    //endregion constructors

    init {
        layoutManager = LinearLayoutManager(context, HORIZONTAL, false)

        // Snapping
        LinearSnapHelper().attachToRecyclerView(this)

        val marginVert = context.resources.getDimensionPixelOffset(R.dimen.coverListMarginVertical)
        val marginHorz = context.resources.getDimensionPixelOffset(R.dimen.coverListMarginHorizontal)
        addItemDecoration(MarginDecoration(marginTop = marginVert, marginEnd = marginHorz, marginBottom = marginVert, marginStart = 0))

        // padding for first element of the list
        clipToPadding = false
        this.setPadding(marginHorz, 0, 0, 0)
    }
}