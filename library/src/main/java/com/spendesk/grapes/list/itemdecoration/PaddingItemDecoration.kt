package com.spendesk.grapes.list.itemdecoration

import android.graphics.Rect
import android.view.View
import androidx.annotation.IntRange
import androidx.recyclerview.widget.RecyclerView

/**
 * @author danyboucanova
 * @since 19/07/2021
 */
class PaddingItemDecoration(
    @IntRange(from = 0) private val paddingStart: Int,
    @IntRange(from = 0) private val paddingEnd: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        when (parent.getChildAdapterPosition(view)) {
            0 -> outRect.left += paddingStart
            state.itemCount - 1 -> outRect.right += paddingEnd
        }
    }
}