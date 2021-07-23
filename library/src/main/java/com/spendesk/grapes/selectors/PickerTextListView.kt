package com.spendesk.grapes.selectors

import android.content.Context
import android.graphics.drawable.InsetDrawable
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView
import com.spendesk.grapes.R
import com.spendesk.grapes.list.WrapContentLinearLayoutManager
import com.spendesk.grapes.list.itemdecoration.DividerItemDecoration

/**
 * @author danyboucanova
 * @since 19/07/2021
 */
class PickerTextListView : RecyclerView {

    //region constructors

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    //endregion constructors

    init {
        layoutManager = WrapContentLinearLayoutManager(context, HORIZONTAL, false)
        overScrollMode = OVER_SCROLL_IF_CONTENT_SCROLLS

        setItemDecoration()
    }

    private fun setItemDecoration() {
        val separatorDecoration = DividerItemDecoration(context, HORIZONTAL)

        // Change the drawable of the divider item decoration and its height.
        with(context.obtainStyledAttributes(intArrayOf(android.R.attr.listDivider))) {
            getDrawable(0)?.let {
                val inset = resources.getDimensionPixelSize(R.dimen.pickerTextListViewItemDecorationMarginVert)
                separatorDecoration.setDrawable(InsetDrawable(it, 0, inset, 0, inset))
            }
            recycle()
        }

        addItemDecoration(separatorDecoration)
    }
}