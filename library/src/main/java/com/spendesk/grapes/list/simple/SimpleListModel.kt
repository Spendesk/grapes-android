package com.spendesk.grapes.list.simple

import com.spendesk.grapes.component.SimpleEntryItemView
import com.spendesk.grapes.component.SimpleSectionItemView
import java.io.Serializable

/**
 * @author danyboucanova
 * @since 12/07/2021
 */
sealed class SimpleListModel(val viewType: SimpleListViewType): Serializable {
    data class Item(val id: String, val configuration: SimpleEntryItemView.Configuration) : SimpleListModel(SimpleListViewType.ITEM)
    data class Section(val id: String, val configuration: SimpleSectionItemView.Configuration) : SimpleListModel(SimpleListViewType.SECTION)
}
