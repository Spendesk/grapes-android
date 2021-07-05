package com.spendesk.grapes.extensions

import android.graphics.drawable.Drawable
import android.os.Build
import android.util.TypedValue
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat

/**
 * Extension functions for [TextView] class.
 *
 * @author danyboucanova
 * @since 11/09/2020
 */

/**
 * Sets a drawable on the left of this [TextView].
 */
fun TextView.setDrawableLeft(drawable: Drawable?) = this.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null)


/**
 * Sets a drawable on the left of this [TextView].
 */
fun TextView.setDrawableLeft(drawableResId: Int) = this.setCompoundDrawablesWithIntrinsicBounds(drawableResId, 0, 0, 0)


/**
 * Sets a drawable on the top of this [TextView].
 */
fun TextView.setDrawableTop(drawableResId: Int) = this.setCompoundDrawablesWithIntrinsicBounds(0, drawableResId, 0, 0)


/**
 * Sets a drawable on the right of this [TextView].
 */
fun TextView.setDrawableRight(drawableResId: Int) = this.setCompoundDrawablesWithIntrinsicBounds(0, 0, drawableResId, 0)


/**
 * Sets a drawable on the bottom of this [TextView].
 */
fun TextView.setDrawableBottom(drawableResId: Int) = this.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, drawableResId)

fun TextView.setDrawableTintList(colorStateListId: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        compoundDrawableTintList = ContextCompat.getColorStateList(context, colorStateListId)
    } else {
        DrawableCompat.setTintList(DrawableCompat.wrap(compoundDrawables[0]), ContextCompat.getColorStateList(context, colorStateListId))
    }

}

fun TextView.removeDrawables() = this.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, 0)

/**
 * Sets this textView to [View.VISIBLE] if the given [text] is not null. Sets this view to [View.GONE] otherwise.
 */
fun TextView.visibleWithTextOrGone(text: CharSequence?) =
    when (text.isNullOrEmpty()) {
        true -> gone()
        false -> {
            visible()
            this.text = text
        }
    }

fun TextView.addRippleEffect() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val outValue = TypedValue()
        context.theme.resolveAttribute(android.R.attr.selectableItemBackground, outValue, true)
        foreground = ContextCompat.getDrawable(context, outValue.resourceId)
    }
    isClickable = true
    isFocusable = true
}