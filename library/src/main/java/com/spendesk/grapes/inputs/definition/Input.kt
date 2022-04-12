package com.spendesk.grapes.inputs.definition

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.spendesk.grapes.R

/**
 * Definition of an Input field within Grapes Design System, which ensure the presence of an [AppCompatEditText] and its basic configuration.
 *
 * @author Vivien Mahe
 * @since 06/04/2022
 */
abstract class Input : CardView {

    // region constructors

    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr)

    // endregion constructors

    // region Observable properties

    var onFocusChanged: ((Boolean) -> Unit)? = null

    // endregion Observable properties

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

    /**
     * Subclasses must override this method and provide the [AppCompatEditText] present in the layout to build this [Input].
     */
    abstract fun getEditText(): AppCompatEditText

    /**
     * Returns the text value within the editText of this input.
     */
    fun getText() = getEditText().toString()

    /**
     * Sets the style of this input.
     */
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

    /**
     * Configures the input with basic properties. Subclasses can override this method to add a specific configuration of this input.
     *
     * @param focusable Whether or not this EditText can receive the focus.
     * @param hint Sets the text to be displayed when the text of the TextView is empty. Null means to use the normal empty text.
     * @param drawableStart Sets the Drawables (if not [ResourcesCompat.ID_NULL]) to appear to the start of the editText.
     * @param style The style to be applied to this editText.
     * @param extraConfiguration An instruction block that can be applied (if any) to this editText to allow some extra configuration.
     */
    open fun configureEditText(focusable: Boolean, hint: String? = null, drawableStart: Int, style: Style, extraConfiguration: ((editText: AppCompatEditText) -> Unit)? = null) {
        with(getEditText()) {
            // Set basic properties
            this.isFocusable = focusable
            this.hint = hint

            // Set the start drawable
            if (drawableStart != ResourcesCompat.ID_NULL)
                setCompoundDrawablesRelativeWithIntrinsicBounds(
                    ContextCompat.getDrawable(context, drawableStart),
                    compoundDrawablesRelative[1],
                    compoundDrawablesRelative[2],
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

            // Register listeners
            setOnClickListener { this@Input.performClick() } // TODO Do we really need this?
            setOnFocusChangeListener { _, hasFocus -> onFocusChanged?.invoke(hasFocus) }

            // Extra configuration of the EditText if needed
            extraConfiguration?.invoke(getEditText())
        }
    }
}
