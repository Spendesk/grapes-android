package com.spendesk.grapes.selectors

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.widget.TextViewCompat
import com.spendesk.grapes.R
import com.spendesk.grapes.databinding.SelectorViewBinding
import com.spendesk.grapes.extensions.*

/**
 * @author Vivien Mahe
 * @since 26/01/2022
 */
class SelectorView : CardView {

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

    enum class Style(val position: Int) {
        PRIMARY(0), // white and primary text
        SECONDARY(1), // white and complementary text
        ACTIVE_PRIMARY(2), // primary and white text
        ACTIVE_PRIMARY_DARK(3); // primary dark and white text

        companion object {
            fun fromPosition(position: Int): Style {
                return try {
                    values().first { it.position == position }
                } catch (exception: NoSuchElementException) {
                    getDefault()
                }
            }

            fun getDefault() = PRIMARY
        }
    }

    data class Configuration(
        val style: Style,
        val text: CharSequence,
        val notificationText: CharSequence?,
        val shouldShowDrawableEnd: Boolean = false
    )

    private var binding = SelectorViewBinding.inflate(LayoutInflater.from(context), this)

    init {
        addRippleEffect()
    }

    fun updateConfiguration(configuration: Configuration) {
        updateStyle(configuration.style)
        setText(text = configuration.text)
        setBadgeText(notificationText = configuration.notificationText)
        showDrawableEnd(shouldShowDrawableEnd = configuration.shouldShowDrawableEnd)
    }

    fun updateStyle(style: Style) {
        when (style) {
            Style.PRIMARY -> {
                val textColor = ContextCompat.getColor(context, R.color.selectorViewPrimaryText)

                binding.text.setTextColor(textColor)
                TextViewCompat.setCompoundDrawableTintList(binding.text, ColorStateList.valueOf(textColor))
                getContainer().setDrawable(
                    colorId = R.color.selectorViewBackgroundPrimary,
                    strokeSizeId = R.dimen.selectorViewBackgroundStroke,
                    strokeColorId = R.color.selectorViewBackgroundPrimaryStroke,
                    radiusId = R.dimen.selectorViewBackgroundRadius
                )
            }
            Style.SECONDARY -> {
                val textColor = ContextCompat.getColor(context, R.color.selectorViewSecondaryText)

                binding.text.setTextColor(textColor)
                TextViewCompat.setCompoundDrawableTintList(binding.text, ColorStateList.valueOf(textColor))
                getContainer().setDrawable(
                    colorId = R.color.selectorViewBackgroundSecondary,
                    strokeSizeId = R.dimen.selectorViewBackgroundStroke,
                    strokeColorId = R.color.selectorViewBackgroundSecondaryStroke,
                    radiusId = R.dimen.selectorViewBackgroundRadius
                )
            }
            Style.ACTIVE_PRIMARY -> {
                val textColor = ContextCompat.getColor(context, R.color.selectorViewActivePrimaryText)

                binding.text.setTextColor(textColor)
                TextViewCompat.setCompoundDrawableTintList(binding.text, ColorStateList.valueOf(textColor))
                getContainer().setDrawable(
                    colorId = R.color.selectorViewBackgroundActivePrimary,
                    radiusId = R.dimen.selectorViewBackgroundRadius
                )
            }
            Style.ACTIVE_PRIMARY_DARK -> {
                val textColor = ContextCompat.getColor(context, R.color.selectorViewActivePrimaryDarkText)

                binding.text.setTextColor(textColor)
                TextViewCompat.setCompoundDrawableTintList(binding.text, ColorStateList.valueOf(textColor))
                getContainer().setDrawable(
                    colorId = R.color.selectorViewBackgroundActivePrimaryDark,
                    radiusId = R.dimen.selectorViewBackgroundRadius
                )
            }
        }
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
                val style = Style.fromPosition(getInt(R.styleable.SelectorView_selectorStyle, Style.getDefault().position))

                text?.let { setText(text = text) }
                showDrawableEnd(shouldShowDrawableEnd = shouldShowDrawableEnd)
                setBadgeText(notificationText = badgeText)
                updateStyle(style)

                recycle()
            }
        }
    }

    private fun getContainer(): CardView = binding.root as CardView
}