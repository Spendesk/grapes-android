package com.spendesk.grapes.list

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Wrapper of the {@link LinearLayoutManager} to avoid a known bug in Android: https://issuetracker.google.com/issues/37007605#c10
 *
 * @author Vivien Mahe
 * @since 2019-07-25
 */
open class WrapContentLinearLayoutManager(
    context: Context,
    orientation: Int,
    reverseLayout: Boolean
) : LinearLayoutManager(context, orientation, reverseLayout) {

    var canScroll: Boolean = true

    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
        try {
            super.onLayoutChildren(recycler, state)
        } catch (exception: IndexOutOfBoundsException) {
        }
    }

    override fun canScrollVertically(): Boolean = canScroll && super.canScrollVertically()
}