package com.spendesk.grapes.inputs

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.res.ResourcesCompat
import com.spendesk.grapes.R
import com.spendesk.grapes.databinding.PasswordInputBinding

/**
 * @author Vivien Mahe
 * @since 04/04/2022
 */
class PasswordInput : TextInput {

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

    var onEndTextClicked: (() -> Unit)? = null

    private val binding = PasswordInputBinding.inflate(LayoutInflater.from(context), this, true)

    override fun getEditText(): AppCompatEditText = binding.editText

    private fun setupView(attributeSet: AttributeSet?) {
        attributeSet?.let {
            with(context.obtainStyledAttributes(it, R.styleable.PasswordInput)) {
                val focusable = getBoolean(R.styleable.PasswordInput_android_focusable, true)
                val hint = getString(R.styleable.PasswordInput_android_hint)
                val drawableStart = getResourceId(R.styleable.PasswordInput_android_drawableStart, ResourcesCompat.ID_NULL)
                val style = Style.fromPosition(getInt(R.styleable.PasswordInput_textInputStyle, Style.getDefault().position))
                val endText = getString(R.styleable.PasswordInput_passwordInputEndText)

                setStyle(style)
                configureEditText(focusable = focusable, hint = hint, drawableStart = drawableStart, style = style, endText = endText)
                recycle()
            }
        }
    }

    private fun configureEditText(focusable: Boolean, hint: String? = null, drawableStart: Int, style: Style, endText: String? = null) {
        super.configureEditText(binding.editText, focusable, hint, drawableStart, style)

        with(binding.endTextView) {
            text = endText
            setOnClickListener { onEndTextClicked?.invoke() }
        }
    }
}