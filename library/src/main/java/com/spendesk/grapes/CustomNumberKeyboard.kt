package com.spendesk.grapes

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.spendesk.grapes.databinding.ViewCustomNumberKeyboardBinding
import com.spendesk.grapes.extensions.invisible
import com.spendesk.grapes.extensions.visible
import java.text.DecimalFormatSymbols

/**
 * @author danyboucanova
 * @since 3/10/21
 */

class CustomNumberKeyboard : ConstraintLayout {

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

    var onTextChanged: ((String) -> Unit)? = null
    var onRequestedBiometricAuthentication: (() -> Unit)? = null

    data class Configuration(
        val style: Style = Style.getDefault(),
        val extraButton: ExtraButton = ExtraButton.getDefault()
    )

    enum class Style(val position: Int) {
        LIGHT(0),
        DARK(1);

        companion object {
            fun fromPosition(position: Int): Style {
                return try {
                    values().first { it.position == position }
                } catch (exception: NoSuchElementException) {
                    getDefault()
                }
            }

            fun getDefault() = LIGHT
        }
    }

    enum class ExtraButton(val position: Int) {
        NONE(0),
        FINGERPRINT(1),
        SEPARATOR(2);

        companion object {
            fun fromPosition(position: Int): ExtraButton {
                return try {
                    values().first { it.position == position }
                } catch (exception: NoSuchElementException) {
                    getDefault()
                }
            }

            fun getDefault() = NONE
        }
    }

    private val extraButtonKey: View
    private val deleteKey: ImageView
    private val numberKeys: MutableList<TextView> = mutableListOf()
    private val allKeys: MutableList<View> = mutableListOf()

    private val numberValue = StringBuilder()
    private val leftPartNumber = StringBuilder()
    private val rightPartNumber = StringBuilder()
    private var commaPressed: Boolean = false

    private var binding = ViewCustomNumberKeyboardBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        extraButtonKey = binding.extraButtonLayout
        deleteKey = binding.deleteImage
        numberKeys.addAll(
            listOf(
                binding.number0,
                binding.number1,
                binding.number2,
                binding.number3,
                binding.number4,
                binding.number5,
                binding.number6,
                binding.number7,
                binding.number8,
                binding.number9
            )
        )
        numberKeys.map { it as View }.toMutableList().apply { add(extraButtonKey); add(deleteKey) }

        bindNumberKeyPad()
    }

    fun updateConfiguration(configuration: Configuration) {
        setStyleAndExtraButton(style = configuration.style, extraButton = configuration.extraButton)
    }

    override fun setEnabled(enabled: Boolean) {
        allKeys.map { view -> view.isEnabled = enabled }
        super.setEnabled(enabled)
    }

    /**
     * Sets the [Style] and the [ExtraButton] of this keyboard.
     */
    fun setStyleAndExtraButton(style: Style, extraButton: ExtraButton) {
        when (style) {
            Style.LIGHT -> {
                numberKeys.forEach { it.setTextAppearance(context, R.style.CustomNumberKeyboardTextLight) }
                deleteKey.setImageResource(R.drawable.ic_delete_return)

                when (extraButton) {
                    ExtraButton.NONE -> extraButtonKey.invisible()
                    ExtraButton.FINGERPRINT -> {
                        extraButtonKey.visible()
                        binding.extraButtonText.invisible()
                        binding.extraButtonImage.visible()
                        binding.extraButtonImage.setImageResource(R.drawable.ic_biometric_fingerprint)
                    }
                    ExtraButton.SEPARATOR -> {
                        extraButtonKey.visible()
                        binding.extraButtonImage.invisible()
                        binding.extraButtonText.visible()
                        binding.extraButtonText.setText(getSeparator().resId)
                        binding.extraButtonText.setTextAppearance(context, R.style.CustomNumberKeyboardTextLight)
                    }
                }
            }

            Style.DARK -> {
                numberKeys.forEach { it.setTextAppearance(context, R.style.CustomNumberKeyboardTextDark) }
                deleteKey.setImageResource(R.drawable.ic_delete_return_dark)

                when (extraButton) {
                    ExtraButton.NONE -> extraButtonKey.invisible()
                    ExtraButton.FINGERPRINT -> {
                        extraButtonKey.visible()
                        binding.extraButtonText.invisible()
                        binding.extraButtonImage.visible()
                        binding.extraButtonImage.setImageResource(R.drawable.ic_biometric_fingerprint_dark)
                    }
                    ExtraButton.SEPARATOR -> {
                        extraButtonKey.visible()
                        binding.extraButtonImage.invisible()
                        binding.extraButtonText.visible()
                        binding.extraButtonText.setText(getSeparator().resId)
                        binding.extraButtonText.setTextAppearance(context, R.style.CustomNumberKeyboardTextDark)
                    }
                }
            }
        }
    }

    private fun setupView(attributeSet: AttributeSet?) {
        attributeSet?.let {
            with(context.obtainStyledAttributes(it, R.styleable.CustomNumberKeyboard)) {
                val style = Style.fromPosition(getInt(R.styleable.CustomNumberKeyboard_keyboardStyle, Style.getDefault().position))
                val extraButton = ExtraButton.fromPosition(getInt(R.styleable.CustomNumberKeyboard_keyboardExtraButton, ExtraButton.getDefault().position))

                setStyleAndExtraButton(style = style, extraButton = extraButton)

                recycle()
            }
        }
    }

    private fun bindNumberKeyPad() {
        // Handle number bindings
        numberKeys.map { textView -> textView.setOnClickListener { onKeyNumberPressed(numberPressed = textView.text.toString()) } }

        // Handle special bindings
        binding.extraButtonImage.setOnClickListener { onRequestedBiometricAuthentication?.invoke() }
        binding.extraButtonText.setOnClickListener { commaPressed = true }

        deleteKey.setOnClickListener { onKeyDeletePressed() }
    }

    private fun onKeyNumberPressed(numberPressed: String) {
        when (commaPressed) {
            true -> rightPartNumber.append(numberPressed) // Comma has been clicked, which means we deal with the decimal part
            false -> leftPartNumber.append(numberPressed) // Comma has not been clicked yet, which means we only deal with the integer part
        }

        updateAmount()
    }

    private fun onKeyDeletePressed() {
        when (commaPressed) {
            true -> {
                // Comma has been clicked, which means we deal with the decimal part
                with(rightPartNumber.lastIndex) {
                    if (this > -1) rightPartNumber.deleteAt(this)
                    if (this == 0) commaPressed = false // The user deleted all the numbers in the right part, so we delete the comma
                }
            }

            false ->
                // Comma has not been clicked yet, which means we only deal with the integer part
                with(leftPartNumber.lastIndex) { if (this > -1) leftPartNumber.deleteAt(this) }
        }

        updateAmount()
    }

    /**
     * Emits a new value for the amount entered.
     */
    private fun updateAmount() {
        with(numberValue) {
            clear()
            append(leftPartNumber)
            if (rightPartNumber.isNotEmpty()) append(Separator.DOT.separator) // Always use the dot for formatting the amount so cast to Double is possible
            append(rightPartNumber)
        }

        onTextChanged?.invoke(numberValue.toString())
    }

    private enum class Separator(val separator: Char, val resId: Int) {
        COMMA(',', R.string.customNumberKeyboardComma),
        DOT('.', R.string.customNumberKeyboardDot);

        companion object {
            fun fromChar(char: Char): Separator {
                return try {
                    Separator.values().first { it.separator == char }
                } catch (exception: NoSuchElementException) {
                    getDefault()
                }
            }

            fun getDefault() = COMMA
        }
    }

    private fun getSeparator(): Separator {
        val separator: Char = DecimalFormatSymbols.getInstance().decimalSeparator
        return Separator.fromChar(char = separator)
    }
}