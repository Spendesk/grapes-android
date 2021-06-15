package com.spendesk.grapes.selectors

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.spendesk.grapes.selectors.PickerCardGridView.Constants.GRIDVIEW_SPAN_COUNT

/**
 * @author danyboucanova
 * @since 07/06/2021
 */
class PickerCardGridView(context: Context, attributeSet: AttributeSet?) : RecyclerView(context, attributeSet) {

    private object Constants {
        const val GRIDVIEW_SPAN_COUNT = 4
    }

    init {
        layoutManager = GridLayoutManager(context, GRIDVIEW_SPAN_COUNT)
    }
}
