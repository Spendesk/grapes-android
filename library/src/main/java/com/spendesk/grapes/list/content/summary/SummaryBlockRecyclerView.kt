package com.spendesk.grapes.list.content.summary

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.spendesk.grapes.R
import com.spendesk.grapes.internal.itemdecoration.MarginDecoration
import com.spendesk.grapes.list.WrapContentLinearLayoutManager

/**
 * @author Vivien Mahe
 * @since 07/01/2021
 */
class SummaryBlockRecyclerView : RecyclerView {

    //region constructors
    constructor(context: Context) : super(context) {
        setupView(null)
    }

    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet) {
        setupView(attributeSet)
    }

    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr) {
        setupView(attributeSet)
    }
    //endregion constructors

    init {
        layoutManager = WrapContentLinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    private fun setupView(attributeSet: AttributeSet?) {
        attributeSet?.let {
            with(context.obtainStyledAttributes(it, R.styleable.SummaryBlockRecyclerView)) {
                val itemDecorationMarginTop = getDimensionPixelOffset(R.styleable.SummaryBlockRecyclerView_itemDecorationMarginTop, 0)
                val itemDecorationMarginBottom = getDimensionPixelOffset(R.styleable.SummaryBlockRecyclerView_itemDecorationMarginBottom, 0)
                val itemDecorationMarginStart = getDimensionPixelOffset(R.styleable.SummaryBlockRecyclerView_itemDecorationMarginStart, 0)
                val itemDecorationMarginEnd = getDimensionPixelOffset(R.styleable.SummaryBlockRecyclerView_itemDecorationMarginEnd, 0)

                recycle()

                addItemDecoration(
                    MarginDecoration(marginTop = itemDecorationMarginTop, marginEnd = itemDecorationMarginEnd, marginBottom = itemDecorationMarginBottom, marginStart = itemDecorationMarginStart)
                )
            }
        }
    }
}