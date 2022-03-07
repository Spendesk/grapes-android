package com.spendesk.grapes.list.content.summary.item

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.spendesk.grapes.R
import com.spendesk.grapes.databinding.ItemInlineKeyValueBinding

/**
 * @author danyboucanova
 * @since 1/14/21
 */
class InlineKeyValueItemView : ConstraintLayout {

    //region constructors

    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet, 0)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr)

    //endregion constructors

    data class Configuration(
        val key: CharSequence,
        val value: CharSequence
    )

    private val binding: ItemInlineKeyValueBinding = ItemInlineKeyValueBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        View.inflate(context, R.layout.item_inline_key_value, this)
    }

    fun updateConfiguration(configuration: Configuration) {
        with(binding) {
            inlineBlockViewKeyText.text = configuration.key
            inlineBlockViewValueText.text = configuration.value
        }
    }
}