package com.spendesk.grapes.selectors

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView
import com.spendesk.grapes.R
import com.spendesk.grapes.list.WrapContentLinearLayoutManager
import com.spendesk.spendesk.presentation.view.itemdecoration.MarginDecoration

/**
 * @author danyboucanova
 * @since 14/06/2021
 */
class PickerListView : RecyclerView {

    //region constructors

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    //endregion constructors

    init {
        layoutManager = WrapContentLinearLayoutManager(context, HORIZONTAL, false)

        val margin = context.resources.getDimensionPixelOffset(R.dimen.pickerListViewMargin)
        val itemDecoration = MarginDecoration(margin, 0, margin, margin)
        addItemDecoration(itemDecoration)
    }
}