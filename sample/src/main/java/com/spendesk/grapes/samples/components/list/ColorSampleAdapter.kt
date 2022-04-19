package com.spendesk.grapes.samples.components.list

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.spendesk.grapes.samples.components.ColorSampleCardView

/**
 * @author danyboucanova
 * @since 10/03/2022
 */
class ColorSampleAdapter : RecyclerView.Adapter<ColorSampleAdapter.CoverViewHolder>() {

    private val itemsList: MutableList<ColorSampleBlockModel> = ArrayList()

    sealed class CoverViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        class Cover(val view: ColorSampleCardView) : CoverViewHolder(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoverViewHolder =
        when (ColorSampleViewType.values()[viewType]) {
            ColorSampleViewType.COLOR -> CoverViewHolder.Cover(ColorSampleCardView(parent.context))
        }.apply {
            itemView.layoutParams = RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        }

    override fun getItemCount(): Int = itemsList.size

    override fun onBindViewHolder(holder: CoverViewHolder, position: Int) =
        when (holder) {
            is CoverViewHolder.Cover -> holder.view.updateConfiguration((itemsList[position] as ColorSampleBlockModel.Color).configuration)
        }

    fun updateList(items: List<ColorSampleBlockModel>) {
        itemsList.clear()
        itemsList.addAll(items)

        notifyDataSetChanged()
    }
}