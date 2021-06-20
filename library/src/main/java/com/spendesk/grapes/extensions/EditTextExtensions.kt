package com.spendesk.grapes.extensions

import android.graphics.drawable.Drawable
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.widget.doAfterTextChanged

/**
 * @author danyboucanova
 * @since 17/06/2021
 */

fun AppCompatEditText.setupClearButtonWithAction(drawable: Drawable?) {

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
