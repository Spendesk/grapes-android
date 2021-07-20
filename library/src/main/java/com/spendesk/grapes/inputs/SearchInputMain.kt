package com.spendesk.grapes.inputs

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.util.AttributeSet
import android.util.TypedValue
import androidx.appcompat.widget.AppCompatEditText
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.setPadding
import com.spendesk.grapes.R
import com.spendesk.grapes.extensions.setupClearButtonWithAction

/**
 * @author danyboucanova
 * @since 15/06/2021
 */
class SearchInputMain : CardView {

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

    enum class Style(val position: Int) {
        PRIMARY(0),
        SECONDARY(1);

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

    private lateinit var editText: AppCompatEditText
    private var style = Style.getDefault()

    fun setStyle(style: Style) {
        when (style) {
            Style.PRIMARY -> {
                radius = resources.getDimensionPixelOffset(R.dimen.searchInputMainPrimaryCardRadius).toFloat()
                elevation = resources.getDimensionPixelOffset(R.dimen.searchInputMainPrimaryCardElevation).toFloat()
            }
            Style.SECONDARY -> {
                radius = resources.getDimensionPixelOffset(R.dimen.searchInputMainSecondaryCardRadius).toFloat()
                elevation = resources.getDimensionPixelOffset(R.dimen.searchInputMainSecondaryCardElevation).toFloat()

                setBackgroundResource(R.drawable.shape_rect_solidbackground_stroke5neutrallight_radius8)
            }
        }
    }

    private fun setupView(attributeSet: AttributeSet?) {
        attributeSet?.let {
            with(context.obtainStyledAttributes(it, R.styleable.SearchInputMain)) {
                style = Style.fromPosition(getInt(R.styleable.SearchInputMain_searchInputMainStyle, Style.getDefault().position))

                setStyle(style)
                recycle()
            }
        }

        configureEditText(attributeSet)
    }

    private fun configureEditText(attributeSet: AttributeSet?) {
        editText = AppCompatEditText(context, attributeSet)

        with(editText) {
            setSingleLine()
            setTextSize(TypedValue.COMPLEX_UNIT_PX, resources.getDimensionPixelOffset(R.dimen.searchInputMainTextSize).toFloat())
            setTextColor(ContextCompat.getColor(context, R.color.searchInputMainPrimaryTextColor))
            setPadding(resources.getDimensionPixelOffset(R.dimen.searchInputMainPrimaryPadding))
            background = ContextCompat.getDrawable(context, android.R.color.transparent) // remove underline bar

            // Compound drawables
            compoundDrawablePadding = resources.getDimensionPixelOffset(R.dimen.searchInputMainPrimaryDrawableCompoundPadding)
            setupClearButtonWithAction()

            // Add the according style tint for compound drawables except for clear icon
            val color = when (style) {
                Style.PRIMARY -> R.color.searchInputMainPrimaryTintColor
                Style.SECONDARY -> R.color.searchInputMainSecondaryTintColor
            }

            // The index 3 corresponds to the right compound drawable, the clear icon that we should not tint
            compoundDrawablesRelative.forEachIndexed { index, drawable ->
                if (drawable != null && index != 3) {
                    drawable.colorFilter = PorterDuffColorFilter(ContextCompat.getColor(context, color), PorterDuff.Mode.SRC_IN)
                }
            }
        }.also { addView(editText) }
    }
}