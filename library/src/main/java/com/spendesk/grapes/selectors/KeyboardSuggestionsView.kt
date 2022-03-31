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
import com.spendesk.grapes.databinding.SelectorKeyboardSuggestionsViewBinding
import kotlin.math.abs

class KeyboardSuggestionsView : ConstraintLayout {

    companion object {
        private const val DEFAULT_MAX_ITEMS = 5
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

    data class Configuration(
        val items: List<Item>,
        val maxItems: Int = DEFAULT_MAX_ITEMS
    )

    private val suggestionViewIds = mutableListOf<Int>()
    private val binding: SelectorKeyboardSuggestionsViewBinding = SelectorKeyboardSuggestionsViewBinding.inflate(LayoutInflater.from(context), this)

    @StyleRes
    private var itemStyle: Int = R.style.KeyboardSuggestionsViewText
    var onItemClicked: ((item: Item) -> Unit)? = null

    fun updateConfiguration(configuration: Configuration) {
        val itemsToShow = configuration.items.take(configuration.maxItems)

        val itemsDelta = itemsToShow.size - suggestionViewIds.size
        if (itemsDelta > 0) {
            addItemViews(itemsDelta)
        } else if (itemsDelta < 0) {
            removeItemViews(abs(itemsDelta))
        }

        // Update each suggestion's text and click listener
        suggestionViewIds.forEachIndexed { index, textViewId ->
            val item = itemsToShow[index]
            val textView = findViewById<TextView>(textViewId)

            updateItemViewContent(item = item, textView = textView)
        }

        with(binding.flow) {
            referencedIds = suggestionViewIds.toIntArray()
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

            suggestionViewIds.add(textView.id)
        }
    }

    private fun removeItemViews(amountOfItemViewsToRemove: Int) {
        for (i in suggestionViewIds.size - 1 downTo suggestionViewIds.size - amountOfItemViewsToRemove) {
            val textViewId = suggestionViewIds[i]
            val textView = findViewById<TextView>(textViewId)

            binding.flow.removeView(textView)
            removeView(textView)

            suggestionViewIds.remove(textViewId)
        }
    }

    private fun updateItemViewContent(item: Item, textView: TextView) {
        textView.text = item.text
        textView.setOnClickListener { onItemClicked?.invoke(item) }
    }

    private fun setupView(attributeSet: AttributeSet?) {
        if (attributeSet == null) return

        context.obtainStyledAttributes(attributeSet, R.styleable.KeyboardSuggestionsView).use { styleAttributes ->
            itemStyle = styleAttributes.getResourceId(R.styleable.KeyboardSuggestionsView_itemStyle, R.style.KeyboardSuggestionsViewText)
        }
    }
}
