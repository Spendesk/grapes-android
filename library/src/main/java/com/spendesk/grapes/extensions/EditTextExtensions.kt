package com.spendesk.grapes.extensions

import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import com.spendesk.grapes.R

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
