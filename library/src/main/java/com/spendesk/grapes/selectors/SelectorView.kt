package com.spendesk.grapes.selectors

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.spendesk.grapes.R
import com.spendesk.grapes.databinding.SelectorViewBinding
import com.spendesk.grapes.extensions.removeDrawables
import com.spendesk.grapes.extensions.setDrawableRight
import com.spendesk.grapes.extensions.visibleWithTextOrGone

/**
 * @author Vivien Mahe
 * @since 26/01/2022
 */
class SelectorView : ConstraintLayout {

    // region constructor

    constructor(context: Context) : super(context) {
        setupView(null)
    }

    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet) {
        setupView(attributeSet)
    }

    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr) {
        setupView(attributeSet)
    }

    // endregion constructor

    data class Configuration(
        val text: CharSequence,
        val notificationText: CharSequence?,
        val shouldShowDrawableEnd: Boolean = false,
        val isSelected: Boolean = false
    )

    private var binding = SelectorViewBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        isClickable = true
    }

    override fun setSelected(selected: Boolean) {
        super.setSelected(selected)

        binding.text.isSelected = selected
    }

    fun updateConfiguration(configuration: Configuration) {
        setText(text = configuration.text)
        setBadgeText(notificationText = configuration.notificationText)
        showDrawableEnd(shouldShowDrawableEnd = configuration.shouldShowDrawableEnd)
        isSelected = configuration.isSelected
    }

    fun setText(text: CharSequence) {
        binding.text.text = text
    }

    fun setBadgeText(notificationText: CharSequence?) {
        binding.badgeView.visibleWithTextOrGone(notificationText)
    }

    fun showDrawableEnd(shouldShowDrawableEnd: Boolean) {
        when (shouldShowDrawableEnd) {
            true -> {
                binding.text.setDrawableRight(R.drawable.ic_dropdown_bottom_purple)
                binding.text.compoundDrawablePadding = resources.getDimensionPixelOffset(R.dimen.selectorViewTextDrawablePadding)
            }

            false -> binding.text.removeDrawables()
        }
    }

    private fun setupView(attributeSet: AttributeSet?) {
        attributeSet?.let {
            with(context.obtainStyledAttributes(it, R.styleable.SelectorView)) {
                val text = getString(R.styleable.SelectorView_android_text)
                val shouldShowDrawableEnd = getBoolean(R.styleable.SelectorView_showDrawableEnd, false)
                val badgeText = getString(R.styleable.SelectorView_badgeText)
                val isSelected = getBoolean(R.styleable.SelectorView_isSelected, false)

                text?.let { setText(text = text) }
                showDrawableEnd(shouldShowDrawableEnd = shouldShowDrawableEnd)
                setBadgeText(notificationText = badgeText)
                setSelected(selected = isSelected)

                recycle()
            }
        }
    }
}