package com.spendesk.grapes.samples.components

import android.content.Context
import android.util.AttributeSet
import com.spendesk.grapes.samples.home.fragments.list.ColorsBlockModel

/**
 * @author danyboucanova
 * @since 10/03/2022
 */
class CoverListItemView : CoverListView {

    //region constructors
    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr)
    //endregion constructors

    private val adapter = CoverAdapter()

    class Configuration(
        val items: List<CoverBlockModel>
    )

    init {
        setAdapter(adapter)
    }

    fun updateConfiguration(configuration: Configuration) {
        adapter.updateList(configuration.items)
    }
}