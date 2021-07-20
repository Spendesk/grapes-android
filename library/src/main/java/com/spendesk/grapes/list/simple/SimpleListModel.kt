package com.spendesk.grapes.list.simple

import com.spendesk.grapes.extensions.empty
import com.spendesk.grapes.list.simple.item.ItemListItemView
import com.spendesk.grapes.list.simple.item.SectionListItemView


/**
 * @author danyboucanova
 * @since 12/07/2021
 */
sealed class SimpleListModel(val viewType: SimpleListViewType) {
    class Item(val id: String = String.empty(), val configuration: ItemListItemView.Configuration) : SimpleListModel(SimpleListViewType.ITEM)
    class Section(val id: String = String.empty(), val configuration: SectionListItemView.Configuration) : SimpleListModel(SimpleListViewType.SECTION)
}