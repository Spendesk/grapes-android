package com.spendesk.grapes.inputs

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.widget.AppCompatEditText
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.spendesk.grapes.R
import com.spendesk.grapes.databinding.SearchInputBinding
import com.spendesk.grapes.extensions.setupClearButtonWithAction
import com.spendesk.grapes.extensions.visibleOrInvisible

/**
 * @author danyboucanova
 * @since 15/06/2021
 */
class SearchInput : CardView {

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

    private val binding = SearchInputBinding.inflate(LayoutInflater.from(context), this, true)

    fun setStyle(style: Style) {
        when (style) {
            Style.PRIMARY -> {
                radius = resources.getDimensionPixelOffset(R.dimen.searchInputPrimaryCardRadius).toFloat()
                elevation = resources.getDimensionPixelOffset(R.dimen.searchInputPrimaryCardElevation).toFloat()
            }
            Style.SECONDARY -> {
                radius = resources.getDimensionPixelOffset(R.dimen.searchInputSecondaryCardRadius).toFloat()
                elevation = resources.getDimensionPixelOffset(R.dimen.searchInputSecondaryCardElevation).toFloat()

                setBackgroundResource(R.drawable.shape_rect_solidbackground_stroke5neutrallight_radius8)
            }
        }
    }

    fun showProgressBar(visible: Boolean) = binding.progressBar.visibleOrInvisible(visible)

    fun getEditText(): AppCompatEditText = binding.editText

    private fun setupView(attributeSet: AttributeSet?) {
        attributeSet?.let {
            with(context.obtainStyledAttributes(it, R.styleable.SearchInput)) {
                val focusable = getBoolean(R.styleable.SearchInput_android_focusable, true)
                val hint = getResourceId(R.styleable.SearchInput_android_hint, ResourcesCompat.ID_NULL)
                val drawableStart = getResourceId(R.styleable.SearchInput_android_drawableStart, ResourcesCompat.ID_NULL)

                val shouldUseClearButton = getBoolean(R.styleable.SearchInput_searchInputClearButton, true)
                val style = Style.fromPosition(getInt(R.styleable.SearchInput_searchInputStyle, Style.getDefault().position))

                setStyle(style)
                configureEditText(focusable = focusable, hint = hint, drawableStart = drawableStart, style = style, shouldUseClearButton = shouldUseClearButton)
                recycle()
            }
        }
    }

    private fun configureEditText(focusable: Boolean, hint: Int, drawableStart: Int, style: Style, shouldUseClearButton: Boolean) {
        with(binding.editText) {
            // Set basic properties
            isFocusable = focusable
            if (hint != ResourcesCompat.ID_NULL) setHint(hint)

            if (drawableStart != ResourcesCompat.ID_NULL)
                setCompoundDrawablesRelativeWithIntrinsicBounds(
                    ContextCompat.getDrawable(context, drawableStart),
                    compoundDrawablesRelative[1],
                    compoundDrawablesRelative[1],
                    compoundDrawablesRelative[3]
                )

            // Add clear drawableEnd button
            if (shouldUseClearButton) {
                setupClearButtonWithAction()
            }

            // Add the according style tint for compound drawables except for clear icon
            val color = when (style) {
                Style.PRIMARY -> R.color.searchInputPrimaryTintColor
                Style.SECONDARY -> R.color.searchInputSecondaryTintColor
            }

            // The index 3 corresponds to the right compound drawable, the clear icon that we should not tint
            compoundDrawablesRelative.forEachIndexed { index, drawable ->
                if (drawable != null && index != 3) {
                    drawable.colorFilter = PorterDuffColorFilter(ContextCompat.getColor(context, color), PorterDuff.Mode.SRC_IN)
                }
            }

            // Handle click
            setOnClickListener { this@SearchInput.performClick() }
        }
    }
}