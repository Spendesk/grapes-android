package com.spendesk.grapes.internal.itemdecoration

import android.graphics.Rect
import android.view.View
import androidx.annotation.IntRange
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * @author Vivien Mahe
 * @since 02/04/2019
 */
class MarginDecoration(
    @IntRange(from = 0) val marginTop: Int,
    @IntRange(from = 0) val marginEnd: Int,
    @IntRange(from = 0) val marginBottom: Int,
    @IntRange(from = 0) val marginStart: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        if (parent.layoutManager is LinearLayoutManager) {
            val orientation = (parent.layoutManager as LinearLayoutManager).orientation
            val isReversed = (parent.layoutManager as LinearLayoutManager).reverseLayout
            val params = view.layoutParams as RecyclerView.LayoutParams

            // We want to retrieve the position in the list
            val position = params.viewAdapterPosition

            outRect.top = marginTop
            outRect.right = marginEnd
            outRect.bottom = marginBottom
            outRect.left = marginStart

            // Only add margin to any view but the last one
            if (position < state.itemCount) {
                // Any view but the last one
                outRect.top = marginTop
                outRect.right = marginEnd
                outRect.bottom = marginBottom
                outRect.left = marginStart
            } else {
                // Last view
                if (orientation == RecyclerView.VERTICAL) {
                    outRect.right = marginEnd
                    outRect.left = marginStart

                    if (isReversed) {
                        outRect.bottom = marginBottom
                    } else {
                        outRect.top = marginTop
                    }
                } else {
                    outRect.top = marginTop
                    outRect.bottom = marginBottom

                    if (isReversed) {
                        outRect.right = marginEnd
                    } else {
                        outRect.left = marginStart
                    }
                }
            }
        }
    }
}