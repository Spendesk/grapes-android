package com.spendesk.grapes.inputs

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.EditText
import androidx.core.content.res.ResourcesCompat
import com.spendesk.grapes.R
import com.spendesk.grapes.databinding.TextInputBinding
import com.spendesk.grapes.inputs.definition.Input

/**
 * A basic implementation [Input] which only displays an [EditText].
 *
 * @author Vivien Mahe
 * @since 04/04/2022
 */
class TextInput : Input {

    // region constructors

    constructor(context: Context) : super(context) {
        setupView(null)
    }

    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet) {
        setupView(attributeSet)
    }

    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr) {
        setupView(attributeSet)
    }

    // endregion constructors

    private val binding = TextInputBinding.inflate(LayoutInflater.from(context), this, true)

    override fun getEditText(): EditText = binding.textInputEditText

    private fun setupView(attributeSet: AttributeSet?) {
        attributeSet?.let {
            with(context.obtainStyledAttributes(it, R.styleable.TextInput)) {
                val focusable = getBoolean(R.styleable.TextInput_android_focusable, true)
                val hint = getString(R.styleable.TextInput_android_hint)
                val drawableStart = getResourceId(R.styleable.TextInput_android_drawableStart, ResourcesCompat.ID_NULL)
                val style = Style.fromPosition(getInt(R.styleable.TextInput_textInputStyle, Style.getDefault().position))
                recycle()

                setStyle(style)
                configureEditText(focusable = focusable, hint = hint, drawableStart = drawableStart, style = style)
            }
        }
    }
}
