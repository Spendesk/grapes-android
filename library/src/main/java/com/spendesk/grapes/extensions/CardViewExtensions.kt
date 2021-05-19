package com.spendesk.grapes.extensions

import android.util.TypedValue
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat

/**
 * @author Vivien Mahe
 * @since 19/10/2020
 */

/**
 * Adds ripple effect when clicking on this [CardView].
 */
fun CardView.addRippleEffect() {
    val outValue = TypedValue()
    context.theme.resolveAttribute(android.R.attr.selectableItemBackground, outValue, true)
    foreground = ContextCompat.getDrawable(context, outValue.resourceId)
    isClickable = true
    isFocusable = true
}