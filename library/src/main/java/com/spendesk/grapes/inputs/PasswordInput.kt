package com.spendesk.grapes.inputs

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.EditText
import androidx.core.content.res.ResourcesCompat
import com.spendesk.grapes.R
import com.spendesk.grapes.databinding.PasswordInputBinding
import com.spendesk.grapes.extensions.visibleWithTextOrGone
import com.spendesk.grapes.inputs.definition.Input

/**
 * An implementation of [Input] which configures this input as a password field and the possibility to add a clickable text at the end of this input.
 *
 * @author Vivien Mahe
 * @since 04/04/2022
 */
class PasswordInput : Input {

    // region Constructors

    constructor(context: Context) : super(context) {
        setupView(null)
    }

    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet) {
        setupView(attributeSet)
    }

    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr) {
        setupView(attributeSet)
    }

    // endregion Constructors

    // region Observable properties

    var onEndTextClicked: (() -> Unit)? = null

    // endregion Observable properties

    private val binding = PasswordInputBinding.inflate(LayoutInflater.from(context), this, true)

    override fun getEditText(): EditText = binding.editText

    private fun setupView(attributeSet: AttributeSet?) {
        attributeSet?.let {
            with(context.obtainStyledAttributes(it, R.styleable.PasswordInput)) {
                val focusable = getBoolean(R.styleable.PasswordInput_android_focusable, true)
                val hint = getString(R.styleable.PasswordInput_android_hint)
                val drawableStart = getResourceId(R.styleable.PasswordInput_android_drawableStart, ResourcesCompat.ID_NULL)
                val style = Style.fromPosition(getInt(R.styleable.PasswordInput_textInputStyle, Style.getDefault().position))
                val endText = getString(R.styleable.PasswordInput_passwordInputEndText)
                recycle()

                setStyle(style)
                configureEditText(focusable = focusable, hint = hint, drawableStart = drawableStart, style = style) {
                    // Configure the text displayed at the end of the editText, if any
                    with(binding.endTextView) {
                        visibleWithTextOrGone(endText)
                        setOnClickListener { onEndTextClicked?.invoke() }
                    }
                }
            }
        }
    }
}
