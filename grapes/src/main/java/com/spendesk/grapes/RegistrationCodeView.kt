package com.spendesk.grapes

import android.content.Context
import android.text.InputFilter
import android.util.AttributeSet
import android.view.View
import android.widget.EditText
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import kotlinx.android.synthetic.main.view_registration_code.view.*

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

    private var maxCodeItemLength: Int = 0

    class Configuration(
        val maxCodeItemLength: Int,
        val firstTextWatcher: ((String) -> Unit),
        val secondTextWatcher: ((String) -> Unit),
        val thirdTextWatcher: ((String) -> Unit)
    )

    init {
        View.inflate(context, R.layout.view_registration_code, this)

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

    fun clearText() =
        listOf(registrationCodeThirdText, registrationCodeSecondText, registrationCodeFirstText).map { it.text?.clear() }

    private fun setupView() {
        setCardBackgroundColor(ContextCompat.getColor(context, R.color.registrationCodeViewBackground))
        radius = context.resources.getDimensionPixelOffset(R.dimen.registrationCodeViewCardRadius).toFloat()
        elevation = context.resources.getDimensionPixelOffset(R.dimen.registrationCodeViewCardElevation).toFloat()

        setOnClickListener {
            when {
                registrationCodeFirstText.text?.length in 0 until maxCodeItemLength -> registrationCodeFirstText.requestFocus()
                registrationCodeSecondText.text?.length in 0 until maxCodeItemLength -> registrationCodeSecondText.requestFocus()
                registrationCodeThirdText.text?.length in 0 until maxCodeItemLength -> registrationCodeThirdText.requestFocus()
            }
        }
    }

    private fun bindView() {
        handleItem(registrationCodeFirstText, onFirstTextChanged) {
            when (it.length) {
                maxCodeItemLength -> registrationCodeSecondText.requestFocus()
                else -> Unit // Nothing to do here
            }
        }

        handleItem(registrationCodeSecondText, onSecondTextChanged) {
            when (it.length) {
                0 -> registrationCodeFirstText.requestFocus()
                maxCodeItemLength -> registrationCodeThirdText.requestFocus()
                else -> Unit // Nothing to do here
            }

        }
        handleItem(registrationCodeThirdText, onThirdTextChanged) {
            when (it.length) {
                0 -> registrationCodeSecondText.requestFocus()
                else -> Unit // Nothing to do here
            }
        }
    }

    private fun handleItem(registrationCodeItem: EditText, registrationCodeLambda: ((String) -> Unit)?, logicOnTextLength: ((String) -> Unit)) =
        registrationCodeItem
            .doAfterTextChanged { textEditable ->
                val itemText = textEditable.toString()

                registrationCodeLambda
                    ?.invoke(itemText)
                    .also { logicOnTextLength(itemText) }
            }

    private fun limitTextLength() {
        listOf(registrationCodeFirstText, registrationCodeSecondText, registrationCodeThirdText).map {
            it.filters = arrayOf(InputFilter.LengthFilter(maxCodeItemLength))
        }
    }
}
