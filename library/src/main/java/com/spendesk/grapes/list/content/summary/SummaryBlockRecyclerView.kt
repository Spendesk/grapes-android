package com.spendesk.grapes.list.content.summary

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.spendesk.grapes.R
import com.spendesk.grapes.list.WrapContentLinearLayoutManager
import com.spendesk.grapes.internal.itemdecoration.MarginDecoration

/**
 * @author Vivien Mahe
 * @since 07/01/2021
 */
class SummaryBlockRecyclerView : RecyclerView {

    //region constructors
    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr)
    //endregion constructors

    init {
        layoutManager = WrapContentLinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        val margin = context.resources.getDimensionPixelSize(R.dimen.inlineBlockContentListItemDecorationMarginVert)
        addItemDecoration(MarginDecoration(marginTop = 0, marginEnd = 0, marginBottom = margin, marginStart = 0))
    }
}