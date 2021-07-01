package com.spendesk.grapes.selectors

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlin.properties.Delegates

/**
 * @author danyboucanova
 * @since 08/06/2021
 */
class PickerAdapter : RecyclerView.Adapter<PickerAdapter.PickerViewHolder>() {

    sealed class PickerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        class Label(val view: PickerLabelTextView) : PickerViewHolder(view)
        class Block(val view: PickerBlockIconCardView) : PickerViewHolder(view)
    }

    var onItemSelected: ((itemViewPosition: Int, itemId: String) -> Unit)? = null

    private val listItems: MutableList<PickerModel> = ArrayList()
    private var selectedPosition by Delegates.observable(RecyclerView.NO_POSITION) { _, oldPos, newPos ->
        if (newPos in listItems.indices) {
            notifyItemChanged(oldPos)
            notifyItemChanged(newPos)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PickerViewHolder =
        when (PickerViewType.values()[viewType]) {
            PickerViewType.LABEL -> PickerViewHolder.Label(PickerLabelTextView(parent.context))
            PickerViewType.BLOCK -> PickerViewHolder.Block(PickerBlockIconCardView(parent.context))
        }.apply {
            this.itemView.layoutParams = when (this) {
                is PickerViewHolder.Label -> RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
                is PickerViewHolder.Block -> RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            }
        }

    override fun onBindViewHolder(holder: PickerViewHolder, position: Int) =
        when (holder) {
            is PickerViewHolder.Label -> {
                val data = listItems[position] as PickerModel.Label

                holder.view.updateConfiguration(data.configuration.copy(isSelected = position == selectedPosition))
                handleOnClickListener(holder.itemView, position, data.id)
            }
            is PickerViewHolder.Block -> {
                val data = listItems[position] as PickerModel.Block

                holder.view.updateConfiguration(data.configuration.copy(isSelected = position == selectedPosition))
                handleOnClickListener(holder.itemView, position, data.id)
            }
        }

    override fun getItemCount(): Int = listItems.size

    override fun getItemViewType(position: Int): Int = getViewTypeAtPosition(position).ordinal

    fun updateList(items: List<PickerModel>) {
        listItems.clear()
        listItems.addAll(items)

        selectedPosition = items.indexOfFirst {
            when (it) {
                is PickerModel.Label -> it.configuration.isSelected
                is PickerModel.Block -> it.configuration.isSelected
            }
        }

        notifyDataSetChanged()
    }

    private fun getViewTypeAtPosition(position: Int): PickerViewType =
        listItems[position].viewType

    private fun handleOnClickListener(itemView: View, itemViewPosition: Int, itemId: String) {
        itemView.setOnClickListener {
            selectedPosition = itemViewPosition
            onItemSelected?.invoke(selectedPosition, itemId)
        }
    }
}