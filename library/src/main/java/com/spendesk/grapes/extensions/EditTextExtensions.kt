package com.spendesk.grapes.extensions

import android.text.Editable
import android.text.TextWatcher
import android.view.MotionEvent
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import com.spendesk.grapes.R
import java.util.*
import kotlin.concurrent.schedule

/**
 * @author danyboucanova
 * @since 17/06/2021
 */

internal fun EditText.setupClearButtonWithAction() {
    val drawable = ContextCompat.getDrawable(context, R.drawable.ic_clear_round)

    doAfterTextChanged { editable ->
        val clearIcon = if (editable?.isNotEmpty() == true) drawable else null
        setCompoundDrawablesRelativeWithIntrinsicBounds(compoundDrawablesRelative[0], compoundDrawablesRelative[1], clearIcon, compoundDrawablesRelative[3])
    }

    setOnTouchListener OnTouchListener@{ _, event ->
        if (event.action == MotionEvent.ACTION_UP && event.rawX >= (right - compoundPaddingRight)) {
            setText(String.empty())
            performClick()
            return@OnTouchListener true
        }
        return@OnTouchListener false
    }
}

internal fun EditText.afterTextChangedWith(delay: Long, consumer: (String) -> Unit) =
    addTextChangedListener(object : TextWatcher {
        var timer = Timer()

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun afterTextChanged(editable: Editable?) {
            timer.cancel()
            timer = Timer().apply {
                schedule(delay) {
                    try {
                        if (editable.isNullOrEmpty().not()) consumer(editable.toString())
                    } catch (exception: IndexOutOfBoundsException) {
                        // A crash randomly happens here causing an IndexOutOfBoundsException when toString() tries to get the characters from the EditText
                        // https://console.firebase.google.com/u/0/project/spendesk-mobile/crashlytics/app/android:com.spendesk.spendesk/issues/366c950f3b688d79a5de9004b6c5e947?time=last-ninety-days&types=crash&versions=2.23.4%20(22302888);2.23.3%20(22302887)&sessionEventKey=6297C55100E60001732296088542CD65_1682809803838467563
                        // There is nothing much we can do here to get the text from the EditText if this method fails, so we just catch it to avoid a crash.
                    }
                }
            }
        }
    })


fun EditText.setTextAndPositionCursorEnd(text: CharSequence) {
    setText(text)
    setSelection(text.length)
}