package com.spendesk.grapes.list.content.summary

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.spendesk.grapes.list.content.summary.item.ApproverStatusItemView
import com.spendesk.grapes.list.content.summary.item.InlineKeyValueItemView

/**
 * @author danyboucanova
 * @since 1/14/21
 */
class SummaryBlockContentAdapter : RecyclerView.Adapter<SummaryBlockContentAdapter.SummaryBlockContentViewHolder>() {

    sealed class SummaryBlockContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        class ApproverStatus(val view: ApproverStatusItemView) : SummaryBlockContentViewHolder(view)
        class InlineKeyValue(val view: InlineKeyValueItemView) : SummaryBlockContentViewHolder(view)
    }

    var onItemSelected: ((itemViewPosition: Int, itemId: String) -> Unit)? = null

    private val listItems: MutableList<SummaryBlockContentModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SummaryBlockContentViewHolder =
        when (SummaryBlockContentViewType.values()[viewType]) {
            SummaryBlockContentViewType.APPROVER_STATUS -> SummaryBlockContentViewHolder.ApproverStatus(ApproverStatusItemView(parent.context))
            SummaryBlockContentViewType.INLINE_KEY_VALUE -> SummaryBlockContentViewHolder.InlineKeyValue(InlineKeyValueItemView(parent.context))
        }.apply {
            this.itemView.layoutParams = RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        }


    override fun onBindViewHolder(holder: SummaryBlockContentViewHolder, position: Int) =
        when (holder) {
            is SummaryBlockContentViewHolder.ApproverStatus -> {
                val data = (listItems[position] as SummaryBlockContentModel.ApproverStatus)

                holder.view.updateConfiguration(data.configuration)
                holder.view.setOnClickListener { onItemSelected?.invoke(position, data.id) }
            }
            is SummaryBlockContentViewHolder.InlineKeyValue -> {
                val data = (listItems[position] as SummaryBlockContentModel.InlineKeyValue)

                holder.view.updateConfiguration(data.configuration)
                holder.view.setOnClickListener { onItemSelected?.invoke(position, data.id) }
            }
        }

    override fun getItemCount(): Int = listItems.size

    override fun getItemViewType(position: Int): Int = getViewTypeAtPosition(position).ordinal

    fun updateList(items: List<SummaryBlockContentModel>) {
        listItems.clear()
        listItems.addAll(items)

        notifyDataSetChanged()
    }

    private fun getViewTypeAtPosition(position: Int): SummaryBlockContentViewType = listItems[position].viewType
}