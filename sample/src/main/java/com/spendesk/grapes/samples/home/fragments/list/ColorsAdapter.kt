package com.spendesk.grapes.samples.home.fragments.list

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.spendesk.grapes.samples.R
import com.spendesk.grapes.samples.components.SectionTitleTextView
import com.spendesk.grapes.samples.components.list.ColorSampleListItemView

/**
 * @author danyboucanova
 * @since 10/03/2022
 */
class ColorsAdapter : RecyclerView.Adapter<ColorsAdapter.ColorsViewHolder>() {

    sealed class ColorsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        class Section(val view: SectionTitleTextView) : ColorsViewHolder(view)
        class ColorList(val view: ColorSampleListItemView) : ColorsViewHolder(view)
    }

    private val listItems: MutableList<ColorsBlockModel> = ArrayList()

    fun updateList(items: List<ColorsBlockModel>) {
        listItems.clear()
        listItems.addAll(items)

        notifyDataSetChanged()
    }

    // region override

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorsViewHolder {
        val context = parent.context

        return when (ColorsBlockViewType.values()[viewType]) {
            ColorsBlockViewType.SECTION -> ColorsViewHolder.Section(SectionTitleTextView(context))
            ColorsBlockViewType.COLOR_LIST -> ColorsViewHolder.ColorList(ColorSampleListItemView(context))
        }.apply {
            val layoutParams = RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

            when (this) {
                is ColorsViewHolder.Section -> {
                    val marginHorz = parent.context.resources.getDimensionPixelSize(R.dimen.homeSectionTitleMarginHorz)
                    val marginVert = parent.context.resources.getDimensionPixelSize(R.dimen.homeSectionTitleMarginVert)

                    layoutParams.setMargins(marginHorz, marginVert, marginHorz, marginVert)
                }
                is ColorsViewHolder.ColorList -> Unit // Nothing to do here
            }
            itemView.layoutParams = layoutParams
        }
    }

    override fun onBindViewHolder(holder: ColorsViewHolder, position: Int) {
        when (holder) {
            is ColorsViewHolder.Section -> holder.view.text = (listItems[position] as ColorsBlockModel.Section).text
            is ColorsViewHolder.ColorList -> holder.view.updateConfiguration(ColorSampleListItemView.Configuration((listItems[position] as ColorsBlockModel.Color).configuration.items))
        }
    }

    override fun getItemViewType(position: Int): Int = getViewTypeAtPosition(position).ordinal

    override fun getItemCount(): Int = listItems.size

    //endregion override

    private fun getViewTypeAtPosition(position: Int): ColorsBlockViewType = listItems[position].viewType
}