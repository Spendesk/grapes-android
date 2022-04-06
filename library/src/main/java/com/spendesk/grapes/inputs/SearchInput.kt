package com.spendesk.grapes.inputs

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.res.ResourcesCompat
import com.spendesk.grapes.R
import com.spendesk.grapes.databinding.SearchInputBinding
import com.spendesk.grapes.extensions.setupClearButtonWithAction
import com.spendesk.grapes.extensions.visibleOrInvisible

/**
 * @author danyboucanova
 * @since 15/06/2021
 */
class SearchInput : TextInput {

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

    private val binding = SearchInputBinding.inflate(LayoutInflater.from(context), this, true)

    override fun getEditText(): AppCompatEditText = binding.editText

    fun showProgressBar(visible: Boolean) = binding.progressBar.visibleOrInvisible(visible)

    private fun setupView(attributeSet: AttributeSet?) {
        attributeSet?.let {
            with(context.obtainStyledAttributes(it, R.styleable.SearchInput)) {
                val focusable = getBoolean(R.styleable.SearchInput_android_focusable, true)
                val hint = getString(R.styleable.SearchInput_android_hint)
                val drawableStart = getResourceId(R.styleable.SearchInput_android_drawableStart, ResourcesCompat.ID_NULL)
                val style = Style.fromPosition(getInt(R.styleable.SearchInput_textInputStyle, Style.getDefault().position))
                val shouldUseClearButton = getBoolean(R.styleable.SearchInput_searchInputClearButton, true)

                setStyle(style)
                configureEditText(focusable = focusable, hint = hint, drawableStart = drawableStart, style = style, shouldUseClearButton = shouldUseClearButton)
                recycle()
            }
        }
    }

    private fun configureEditText(focusable: Boolean, hint: String? = null, drawableStart: Int, style: Style, shouldUseClearButton: Boolean) {
        super.configureEditText(binding.editText, focusable, hint, drawableStart, style)

        // Add clear drawableEnd button
        if (shouldUseClearButton) {
            binding.editText.setupClearButtonWithAction()
        }
    }
}
