package com.spendesk.grapes.samples.home.fragments.list

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * @author danyboucanova
 * @since 10/03/2022
 */
class ColorsListView(
    context: Context,
    attributeSet: AttributeSet?
) : RecyclerView(context, attributeSet) {

    init {
        layoutManager = LinearLayoutManager(context, VERTICAL, false)
    }
}