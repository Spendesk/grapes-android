package com.spendesk.grapes.inputs

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.res.ResourcesCompat
import com.spendesk.grapes.R
import com.spendesk.grapes.databinding.SearchInputBinding
import com.spendesk.grapes.extensions.setupClearButtonWithAction
import com.spendesk.grapes.extensions.visibleOrInvisible
import com.spendesk.grapes.inputs.definition.Input

/**
 * An implementation of [Input] which provides the look & feel of a search input, by adding a configurable drawableStart, a cross icon to clear the text typed in this input, and the ability to display a horizontal progress bar below the input.
 *
 * @author danyboucanova
 * @since 15/06/2021
 */
class SearchInput : Input {

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

    /**
     * Whether or not the horizontal [ProgressBar] displayed below the editText is shown.
     */
    fun showProgressBar(visible: Boolean) = binding.progressBar.visibleOrInvisible(visible)

    private fun setupView(attributeSet: AttributeSet?) {
        attributeSet?.let {
            with(context.obtainStyledAttributes(it, R.styleable.SearchInput)) {
                val focusable = getBoolean(R.styleable.SearchInput_android_focusable, true)
                val hint = getString(R.styleable.SearchInput_android_hint)
                val drawableStart = getResourceId(R.styleable.SearchInput_android_drawableStart, ResourcesCompat.ID_NULL)
                val style = Style.fromPosition(getInt(R.styleable.SearchInput_textInputStyle, Style.getDefault().position))
                val shouldUseClearButton = getBoolean(R.styleable.SearchInput_searchInputClearButton, true)
                recycle()

                setStyle(style)
                configureEditText(focusable = focusable, hint = hint, drawableStart = drawableStart, style = style) { editText ->
                    // Add clear drawableEnd button
                    if (shouldUseClearButton) {
                        editText.setupClearButtonWithAction()
                    }
                }
            }
        }
    }
}
