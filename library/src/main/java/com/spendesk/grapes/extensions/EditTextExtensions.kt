package com.spendesk.grapes.extensions

import android.text.Editable
import android.text.TextWatcher
import android.view.MotionEvent
import android.widget.EditText
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import com.spendesk.grapes.R
import java.util.*
import kotlin.concurrent.schedule

/**
 * @author danyboucanova
 * @since 17/06/2021
 */

fun AppCompatEditText.setupClearButtonWithAction() {
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

fun EditText.afterTextChangedWith(delay: Long, consumer: (String) -> Unit) =
    addTextChangedListener(object : TextWatcher {
        var timer = Timer()

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun afterTextChanged(editable: Editable?) {
            timer.cancel()
            timer = Timer().apply { schedule(delay) { editable?.let { consumer(it.toString()) } } }
        }
    })
