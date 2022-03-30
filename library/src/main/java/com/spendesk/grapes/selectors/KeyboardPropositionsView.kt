package com.spendesk.grapes.selectors

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.StyleRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.use
import com.spendesk.grapes.R
import com.spendesk.grapes.databinding.SelectorKeyboardPropositionsViewBinding
import kotlin.math.abs

class KeyboardPropositionsView : ConstraintLayout {

    companion object {
        private const val DEFAULT_MAX_ITEMS_PER_LINE = 5
    }

    //region constructors

    constructor(context: Context) : super(context) {
        setupView(null)
    }

    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet) {
        setupView(attributeSet)
    }

    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr) {
        setupView(attributeSet)
    }

    //endregion constructors

    data class Item(
        val id: String,
        val text: CharSequence
    )

    fun interface OnItemClickedListener {
        fun onItemClicked(item: Item)
    }

    data class Configuration(
        val items: List<Item>,
        val maxItemsPerLine: Int = DEFAULT_MAX_ITEMS_PER_LINE,
        val listener: OnItemClickedListener? = null,
    )

    private val propositionsViewId = mutableListOf<Int>()
    private val binding: SelectorKeyboardPropositionsViewBinding = SelectorKeyboardPropositionsViewBinding.inflate(LayoutInflater.from(context), this)

    @StyleRes
    private var itemStyle: Int = R.style.KeyboardPropositionsViewText
    private var listener: OnItemClickedListener? = null

    fun updateData(configuration: Configuration) {
        this.listener = configuration.listener

        val itemsDelta = configuration.items.size - propositionsViewId.size
        if (itemsDelta > 0) {
            addItemViews(itemsDelta)
        } else if (itemsDelta < 0) {
            removeItemViews(abs(itemsDelta))
        }

        updateItemViewsContent(configuration.items)

        with(binding.flow) {
            setMaxElementsWrap(configuration.maxItemsPerLine)
            referencedIds = propositionsViewId.toIntArray()
        }
    }

    private fun addItemViews(amountOfItemViewsToAdd: Int) {
        for (i in 1..amountOfItemViewsToAdd) {
            val textView = TextView(context, null, 0, itemStyle)
            with(textView) {
                layoutParams = ViewGroup.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT)
                id = generateViewId()
            }

            addView(textView)

            propositionsViewId.add(textView.id)
        }
    }

    private fun removeItemViews(amountOfItemViewsToRemove: Int) {
        for (i in propositionsViewId.size - 1 downTo propositionsViewId.size - amountOfItemViewsToRemove) {
            val textViewId = propositionsViewId[i]
            val textView = findViewById<TextView>(textViewId)

            binding.flow.removeView(textView)
            removeView(textView)

            propositionsViewId.remove(textViewId)
        }
    }

    private fun updateItemViewsContent(items: List<Item>) {
        if (propositionsViewId.size != items.size) {
            throw IllegalStateException("${items.size} items to add in KeyboardPropositionsView and only ${propositionsViewId.size} views available. Internal error.")
        }

        propositionsViewId.forEachIndexed { index, textViewId ->
            val item = items[index]
            val textView = findViewById<TextView>(textViewId)

            textView.text = item.text
            textView.setOnClickListener { listener?.onItemClicked(item) }
        }
    }

    private fun setupView(attributeSet: AttributeSet?) {
        if (attributeSet == null) return

        context.obtainStyledAttributes(attributeSet, R.styleable.KeyboardPropositionsView).use { styleAttributes ->
            itemStyle = styleAttributes.getResourceId(R.styleable.KeyboardPropositionsView_itemStyle, R.style.KeyboardPropositionsViewText)
        }
    }
}
