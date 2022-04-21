package com.spendesk.grapes.samples.home.fragments.list

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * @author danyboucanova
 * @since 10/03/2022
 */
class ColorsListView : RecyclerView {

    //region constructors

    constructor(context: Context) : super(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr)

    //endregion constructors

    init {
        layoutManager = LinearLayoutManager(context, VERTICAL, false)
    }
}