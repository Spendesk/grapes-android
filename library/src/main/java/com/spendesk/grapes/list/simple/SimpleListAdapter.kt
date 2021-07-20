package com.spendesk.grapes.list.simple

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.spendesk.grapes.list.simple.item.ItemListItemView
import com.spendesk.grapes.list.simple.item.SectionListItemView

/**
 * @author danyboucanova
 * @since 12/07/2021
 */
class SimpleListAdapter : RecyclerView.Adapter<SimpleListAdapter.SimpleListAdapterViewHolder>() {

    sealed class SimpleListAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        class Item(val view: ItemListItemView) : SimpleListAdapterViewHolder(view)
        class Section(val view: SectionListItemView) : SimpleListAdapterViewHolder(view)
    }

    var onItemSelected: ((itemViewPosition: Int, itemId: String) -> Unit)? = null

    private val listItems: MutableList<SimpleListModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleListAdapterViewHolder =
        when (SimpleListViewType.values()[viewType]) {
            SimpleListViewType.ITEM -> SimpleListAdapterViewHolder.Item(ItemListItemView(parent.context))
            SimpleListViewType.SECTION -> SimpleListAdapterViewHolder.Section(SectionListItemView(parent.context))
        }.apply {
            this.itemView.layoutParams = RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        }


    override fun onBindViewHolder(holder: SimpleListAdapterViewHolder, position: Int) =
        when (holder) {
            is SimpleListAdapterViewHolder.Item -> {
                val data = (listItems[position] as SimpleListModel.Item)

                holder.view.updateConfiguration(data.configuration)
                holder.view.setOnClickListener { onItemSelected?.invoke(position, data.id) }
            }
            is SimpleListAdapterViewHolder.Section -> {
                val data = (listItems[position] as SimpleListModel.Section)

                holder.view.updateConfiguration(data.configuration)
                holder.view.setOnClickListener { onItemSelected?.invoke(position, data.id) }
            }
        }

    override fun getItemCount(): Int = listItems.size

    override fun getItemViewType(position: Int): Int = getViewTypeAtPosition(position).ordinal

    fun updateList(items: List<SimpleListModel>) {
        listItems.clear()
        listItems.addAll(items)

        notifyDataSetChanged()
    }

    private fun getViewTypeAtPosition(position: Int): SimpleListViewType = listItems[position].viewType
}