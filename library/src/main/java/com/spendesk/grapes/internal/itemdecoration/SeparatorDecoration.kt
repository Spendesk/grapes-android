package com.spendesk.grapes.internal.itemdecoration

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import androidx.annotation.FloatRange
import androidx.recyclerview.widget.RecyclerView

/**
 * Draws a line at the bottom of the item of a [RecyclerView].
 *
 * @author Vivien Mahe
 * @since 01/02/2019
 */
class SeparatorDecoration(
    val context: Context,
    val color: Int,
    @FloatRange(from = 0.0, fromInclusive = false) val heightDp: Float
) : RecyclerView.ItemDecoration() {

    private val paint = Paint()

    init {
        paint.color = color

        val thickness = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, heightDp, context.resources.displayMetrics)
        paint.strokeWidth = thickness
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val params = view.layoutParams as RecyclerView.LayoutParams

        // We want to retrieve the position in the list
        val position = params.viewAdapterPosition

        // And add a separator to any view but the last one
        if (position < state.itemCount) {
            outRect.set(0, 0, 0, paint.strokeWidth.toInt()) // left, top, right, bottom
        } else {
            outRect.setEmpty() // 0, 0, 0, 0
        }
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        // We set the stroke width before, so as to correctly draw the line we have to offset by width / 2
        val offset = (paint.strokeWidth / 2).toInt()

        // This will iterate over every visible view
        for (i in 0 until parent.childCount - 1) {
            // Get the view
            val view = parent.getChildAt(i)
            val params = view.layoutParams as RecyclerView.LayoutParams

            // Get the position
            val position = params.viewAdapterPosition

            // And finally draw the separator
            if (position < state.itemCount) {
                c.drawLine(view.left.toFloat(), (view.bottom + offset).toFloat(), view.right.toFloat(), (view.bottom + offset).toFloat(), paint)
            }
        }
    }
}