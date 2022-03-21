package com.spendesk.grapes.samples.components

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.spendesk.grapes.samples.components.list.CoverViewType

/**
 * @author danyboucanova
 * @since 10/03/2022
 */
class CoverAdapter : RecyclerView.Adapter<CoverAdapter.CoverViewHolder>() {

    private val itemsList: MutableList<CoverBlockModel> = ArrayList()

    sealed class CoverViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        class Cover(val view: CoverCardView) : CoverViewHolder(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoverViewHolder =
        when (CoverViewType.values()[viewType]) {
            CoverViewType.COVER -> CoverViewHolder.Cover(CoverCardView(parent.context))
        }.apply {
            itemView.layoutParams = RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        }

    override fun getItemCount(): Int = itemsList.size

    override fun onBindViewHolder(holder: CoverViewHolder, position: Int) =
        when (holder) {
            is CoverViewHolder.Cover -> holder.view.updateConfiguration((itemsList[position] as CoverBlockModel.Cover).configuration)
        }

    fun updateList(items: List<CoverBlockModel>) {
        itemsList.clear()
        itemsList.addAll(items)

        notifyDataSetChanged()
    }
}