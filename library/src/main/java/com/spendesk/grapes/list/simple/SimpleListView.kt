package com.spendesk.grapes.list.simple

import android.content.Context
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.spendesk.grapes.R
import com.spendesk.grapes.list.WrapContentLinearLayoutManager
import com.spendesk.grapes.internal.itemdecoration.SeparatorDecoration

/**
 * @author danyboucanova
 * @since 12/07/2021
 */
class SimpleListView : RecyclerView {

    //region constructors
    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr)
    //endregion constructors

    init {
        layoutManager = WrapContentLinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        val itemDecoration = SeparatorDecoration(context, ContextCompat.getColor(context, R.color.simpleListListItemSeparator), 1f)
        addItemDecoration(itemDecoration)
    }
}