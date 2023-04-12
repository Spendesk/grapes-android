package com.spendesk.grapes

import android.content.Context
import android.text.InputFilter
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.EditText
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import com.spendesk.grapes.databinding.ViewRegistrationCodeBinding

/**
 * @author danyboucanova
 * @since 3/8/21
 */
class RegistrationCodeView : CardView {

    //region constructors
    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet, 0)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr)
    //endregion constructors

    private lateinit var onFirstTextChanged: ((String) -> Unit)
    private lateinit var onSecondTextChanged: ((String) -> Unit)
    private lateinit var onThirdTextChanged: ((String) -> Unit)
    private var activeListeners: Map<Int, TextWatcher> = mapOf()

    private var maxCodeItemLength: Int = 0

    data class Configuration(
        val maxCodeItemLength: Int,
        val firstTextWatcher: ((String) -> Unit),
        val secondTextWatcher: ((String) -> Unit),
        val thirdTextWatcher: ((String) -> Unit)
    )

    private val binding: ViewRegistrationCodeBinding = ViewRegistrationCodeBinding.inflate(LayoutInflater.from(context), this)

    init {
        setupView()
    }

    fun updateConfiguration(configuration: Configuration) {
        maxCodeItemLength = configuration.maxCodeItemLength

        onFirstTextChanged = configuration.firstTextWatcher
        onSecondTextChanged = configuration.secondTextWatcher
        onThirdTextChanged = configuration.thirdTextWatcher

        limitTextLength()
        bindView()
    }

    fun clearText() {
        listOf(binding.registrationCodeThirdText, binding.registrationCodeSecondText, binding.registrationCodeFirstText).forEach { it.text?.clear() }
    }

    private fun setupView() {
        setCardBackgroundColor(ContextCompat.getColor(context, R.color.registrationCodeViewBackground))
        radius = context.resources.getDimensionPixelOffset(R.dimen.registrationCodeViewCardRadius).toFloat()
        elevation = context.resources.getDimensionPixelOffset(R.dimen.registrationCodeViewCardElevation).toFloat()

        setOnClickListener {
            when {
                binding.registrationCodeFirstText.text?.length in 0 until maxCodeItemLength -> binding.registrationCodeFirstText.requestFocus()
                binding.registrationCodeSecondText.text?.length in 0 until maxCodeItemLength -> binding.registrationCodeSecondText.requestFocus()
                binding.registrationCodeThirdText.text?.length in 0 until maxCodeItemLength -> binding.registrationCodeThirdText.requestFocus()
            }
        }
    }

    private fun bindView() {
        handleItem(binding.registrationCodeFirstText, onFirstTextChanged) {
            when (it.length) {
                maxCodeItemLength -> binding.registrationCodeSecondText.requestFocus()
                else -> Unit // Nothing to do here
            }
        }

        handleItem(binding.registrationCodeSecondText, onSecondTextChanged) {
            when (it.length) {
                0 -> binding.registrationCodeFirstText.requestFocus()
                maxCodeItemLength -> binding.registrationCodeThirdText.requestFocus()
                else -> Unit // Nothing to do here
            }

        }
        handleItem(binding.registrationCodeThirdText, onThirdTextChanged) {
            when (it.length) {
                0 -> binding.registrationCodeSecondText.requestFocus()
                else -> Unit // Nothing to do here
            }
        }
    }

    private fun handleItem(registrationCodeItem: EditText, registrationCodeLambda: ((String) -> Unit)?, logicOnTextLength: ((String) -> Unit)) {
        removeExistingListener(registrationCodeItem)

        val textWatcher = registrationCodeItem
            .doAfterTextChanged { textEditable ->
                val itemText = textEditable.toString()

                registrationCodeLambda
                    ?.invoke(itemText)
                    .also { logicOnTextLength(itemText) }
            }

        activeListeners = activeListeners.plus(registrationCodeItem.id to textWatcher)
    }

    private fun removeExistingListener(registrationCodeItem: EditText) {
        activeListeners[registrationCodeItem.id]?.let { registrationCodeItem.removeTextChangedListener(it) }
    }

    private fun limitTextLength() {
        listOf(binding.registrationCodeFirstText, binding.registrationCodeSecondText, binding.registrationCodeThirdText).forEach {
            it.filters = arrayOf(InputFilter.LengthFilter(maxCodeItemLength))
        }
    }
}
