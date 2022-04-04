package com.spendesk.grapes.inputs

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.EditText
import androidx.appcompat.widget.AppCompatEditText
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.spendesk.grapes.R
import com.spendesk.grapes.databinding.TextInputBinding

/**
 * @author Vivien Mahe
 * @since 04/04/2022
 */
open class TextInput : CardView {

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

    private val binding = TextInputBinding.inflate(LayoutInflater.from(context), this, true)

    fun setStyle(style: Style) {
        when (style) {
            Style.PRIMARY -> {
                radius = resources.getDimensionPixelOffset(R.dimen.textInputPrimaryCardRadius).toFloat()
                elevation = resources.getDimensionPixelOffset(R.dimen.textInputPrimaryCardElevation).toFloat()
            }
            Style.SECONDARY -> {
                radius = resources.getDimensionPixelOffset(R.dimen.textInputSecondaryCardRadius).toFloat()
                elevation = resources.getDimensionPixelOffset(R.dimen.textInputSecondaryCardElevation).toFloat()

                setBackgroundResource(R.drawable.shape_rect_solidbackground_stroke5neutrallight_radius8)
            }
        }
    }

    open fun getEditText(): AppCompatEditText = binding.editText

    protected open fun configureEditText(editText: EditText, focusable: Boolean, hint: Int, drawableStart: Int, style: Style) {
        with(editText) {
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

            // Add the according style tint for compound drawables except for clear icon
            val color = when (style) {
                Style.PRIMARY -> R.color.textInputPrimaryTintColor
                Style.SECONDARY -> R.color.textInputSecondaryTintColor
            }

            // The index 3 corresponds to the right compound drawable, the clear icon that we should not tint
            compoundDrawablesRelative.forEachIndexed { index, drawable ->
                if (drawable != null && index != 3) {
                    drawable.colorFilter = PorterDuffColorFilter(ContextCompat.getColor(context, color), PorterDuff.Mode.SRC_IN)
                }
            }

            // Handle click
            setOnClickListener { this@TextInput.performClick() }
        }
    }

    private fun setupView(attributeSet: AttributeSet?) {
        attributeSet?.let {
            with(context.obtainStyledAttributes(it, R.styleable.TextInput)) {
                val focusable = getBoolean(R.styleable.TextInput_android_focusable, true)
                val hint = getResourceId(R.styleable.TextInput_android_hint, ResourcesCompat.ID_NULL)
                val drawableStart = getResourceId(R.styleable.TextInput_android_drawableStart, ResourcesCompat.ID_NULL)
                val style = Style.fromPosition(getInt(R.styleable.TextInput_textInputStyle, Style.getDefault().position))

                setStyle(style)
                configureEditText(editText = binding.editText, focusable = focusable, hint = hint, drawableStart = drawableStart, style = style)
                recycle()
            }
        }
    }
}
