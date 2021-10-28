package com.spendesk.grapes

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.spendesk.grapes.extensions.visibleOrInvisible
import kotlinx.android.synthetic.main.view_custom_number_keyboard.view.*

/**
 * @author danyboucanova
 * @since 3/10/21
 */

class CustomNumberKeyboard : ConstraintLayout {

    //region constructors
    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet, 0)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr)
    //endregion constructors

    var onTextChanged: ((String) -> Unit)? = null
    var onRequestedBiometricAuthentication: (() -> Unit)? = null

    data class Configuration(
        val showFingerprint: Boolean
    )

    private var number = String()

    init {
        View.inflate(context, R.layout.view_custom_number_keyboard, this)
    }

    fun updateConfiguration(configuration: Configuration) {
        customNumberKeyboardBiometricImage.visibleOrInvisible(configuration.showFingerprint)

        bindNumberKeyPad()
    }

    // TODO: Check why duplicateParentState disables the ripple effect on children, might be obvious but I did not had the bandwidth at the time.
    override fun setEnabled(enabled: Boolean) {
        listOf(
            customNumberKeyboard0,
            customNumberKeyboard1,
            customNumberKeyboard2,
            customNumberKeyboard3,
            customNumberKeyboard4,
            customNumberKeyboard5,
            customNumberKeyboard6,
            customNumberKeyboard7,
            customNumberKeyboard8,
            customNumberKeyboard9,
            customNumberKeyboardBiometricImage,
            customNumberKeyboardDeleteImage
        ).map { view ->
            view.isEnabled = enabled
        }
        super.setEnabled(enabled)
    }

    fun clearText() {
        number = ""
    }

    private fun bindNumberKeyPad() {
        // Handle number bindings
        listOf(
            customNumberKeyboard0,
            customNumberKeyboard1,
            customNumberKeyboard2,
            customNumberKeyboard3,
            customNumberKeyboard4,
            customNumberKeyboard5,
            customNumberKeyboard6,
            customNumberKeyboard7,
            customNumberKeyboard8,
            customNumberKeyboard9
        ).map { textView ->
            textView.setOnClickListener {
                if (isEnabled) {
                    number += textView.text
                    onTextChanged?.invoke(number)
                }
            }
        }

        // Handle special bindings
        customNumberKeyboardBiometricImage.setOnClickListener { if (isEnabled) onRequestedBiometricAuthentication?.invoke() }

        customNumberKeyboardDeleteImage.setOnClickListener {
            if (isEnabled) {
                if (number.isNotEmpty()) {
                    number = number.substring(0, number.length - 1)
                }

                onTextChanged?.invoke(number)
            }
        }
    }
}