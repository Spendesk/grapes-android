package com.spendesk.grapes.inputs

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.widget.doAfterTextChanged
import com.spendesk.grapes.R
import com.spendesk.grapes.extensions.empty

/**
 * @author danyboucanova
 * @since 15/06/2021
 */
class SearchInputMain : AppCompatEditText {

    //region constructors

    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr)

    //endregion constructors

    init {
        setupClearButtonWithAction()
        setSingleLine()
    }
}

fun AppCompatEditText.setupClearButtonWithAction() {

    doAfterTextChanged { editable ->
        val clearIcon = if (editable?.isNotEmpty() == true) R.drawable.ic_clear_round else 0
        setCompoundDrawablesWithIntrinsicBounds(0, 0, clearIcon, 0)
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