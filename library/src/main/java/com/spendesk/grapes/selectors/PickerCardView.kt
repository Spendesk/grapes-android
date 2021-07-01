package com.spendesk.grapes.selectors

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.cardview.widget.CardView
import com.spendesk.grapes.R
import kotlinx.android.synthetic.main.selector_picker_card.view.*

/**
 * @author danyboucanova
 * @since 03/06/2021
 */
class PickerCardView : CardView {

    //region constructors

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    //endregion constructors

    class Configuration(
        val pickerModelList: List<PickerModel>
    )

    var onItemSelected: ((itemViewPosition: Int, itemId: String) -> Unit)? = null
    private val adapter = PickerAdapter()

    init {
        View.inflate(context, R.layout.selector_picker_card, this)

        selectorPickerCardViewList.adapter = adapter

        cardElevation = context.resources.getDimension(R.dimen.pickerCardViewElevation)
        radius = context.resources.getDimension(R.dimen.pickerCardViewRadius)

        onItemSelected = adapter.onItemSelected
    }

    fun updateConfiguration(configuration: Configuration) {
        adapter.updateList(configuration.pickerModelList)
    }
}
