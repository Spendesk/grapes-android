package com.spendesk.grapes.samples.home.fragments.list

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.spendesk.grapes.samples.R
import com.spendesk.grapes.samples.components.SectionTitleTextView
import com.spendesk.grapes.samples.components.list.CoverListItemView

/**
 * @author danyboucanova
 * @since 10/03/2022
 */
class ColorsAdapter : RecyclerView.Adapter<ColorsAdapter.CoverViewHolder>() {

    sealed class CoverViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        class Section(val view: SectionTitleTextView) : CoverViewHolder(view)
        class CoverList(val view: CoverListItemView) : CoverViewHolder(view)
    }

    private val listItems: MutableList<ColorsBlockModel> = ArrayList()

    fun updateList(items: List<ColorsBlockModel>) {
        listItems.clear()
        listItems.addAll(items)

        notifyDataSetChanged()
    }

    // region override

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoverViewHolder {
        val context = parent.context

        return when (ColorsBlockViewType.values()[viewType]) {
            ColorsBlockViewType.SECTION -> CoverViewHolder.Section(SectionTitleTextView(context))
            ColorsBlockViewType.COVER_LIST -> CoverViewHolder.CoverList(CoverListItemView(context))
        }.apply {
            val layoutParams = RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

            when (this) {
                is CoverViewHolder.Section -> {
                    val marginHorz = parent.context.resources.getDimensionPixelSize(R.dimen.homeSectionTitleMarginHorz)
                    val marginVert = parent.context.resources.getDimensionPixelSize(R.dimen.homeSectionTitleMarginVert)

                    layoutParams.setMargins(marginHorz, marginVert, marginHorz, marginVert)
                }
                is CoverViewHolder.CoverList -> Unit // Nothing to do here
            }
            itemView.layoutParams = layoutParams
        }
    }

    override fun onBindViewHolder(holder: CoverViewHolder, position: Int) {
        when (holder) {
            is CoverViewHolder.Section -> holder.view.text = (listItems[position] as ColorsBlockModel.Section).text
            is CoverViewHolder.CoverList -> holder.view.updateConfiguration(CoverListItemView.Configuration((listItems[position] as ColorsBlockModel.Cover).configuration.items))
        }
    }

    override fun getItemViewType(position: Int): Int = getViewTypeAtPosition(position).ordinal

    override fun getItemCount(): Int = listItems.size

    //endregion override

    private fun getViewTypeAtPosition(position: Int): ColorsBlockViewType = listItems[position].viewType
}