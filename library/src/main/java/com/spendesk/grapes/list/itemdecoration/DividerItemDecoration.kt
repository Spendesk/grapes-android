package com.spendesk.grapes.list.itemdecoration

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView

/**
 * A custom [DividerItemDecoration] used in order to remove the last item drawable item separator.
 *
 * @author danyboucanova
 * @since 19/07/2021
 */
class DividerItemDecoration(
    context: Context,
    orientation: Int
) : DividerItemDecoration(context, orientation) {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        if (parent.getChildAdapterPosition(view) == state.itemCount - 1)
            outRect.setEmpty()
        else
            super.getItemOffsets(outRect, view, parent, state)
    }
}
