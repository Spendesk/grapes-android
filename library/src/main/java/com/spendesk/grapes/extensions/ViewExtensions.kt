package com.spendesk.grapes.extensions

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.RippleDrawable
import android.graphics.drawable.StateListDrawable
import android.os.Build
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.view.inputmethod.InputMethodManager
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible

/**
 * [View] related extensions.
 *
 * @author danyboucanova
 * @since 15/09/2020
 */

/**
 * Sets a [RippleDrawable] to the according [View].
 */
internal fun View.setRippleDrawable(
    @ColorRes colorId: Int,
    @ColorRes colorPressedId: Int,
    @ColorRes colorDisableId: Int,
    @DimenRes radiusId: Int,
    @DimenRes strokeId: Int = 0,
    @ColorRes strokeColorId: Int = 0
) {
    val backgroundColor = context.colorCompat(colorId)
    val colorPressed = context.colorCompat(colorPressedId)
    val colorDisable = context.colorCompat(colorDisableId)
    val strokeDimen = if (strokeId != 0) resources.getDimensionPixelOffset(strokeId) else 0
    val strokeColor = if (strokeColorId != 0) context.colorCompat(strokeColorId) else 0
    val radius = resources.getDimensionPixelSize(radiusId).toFloat()

    background = RippleDrawable(
        ColorStateList.valueOf(colorPressed),
        StateListDrawable().apply {
            addState(intArrayOf(-android.R.attr.state_enabled), GradientDrawable().create(colorDisable, radius, strokeDimen, strokeColor))
            addState(intArrayOf(), GradientDrawable().create(backgroundColor, radius, strokeDimen, strokeColor))
        },
        GradientDrawable().create(colorPressed, radius, strokeDimen, strokeColor)
    )
}

/**
 * Sets a [GradientDrawable] to the according [View].
 */
internal fun View.setDrawable(
    @ColorRes colorId: Int = 0,
    @DimenRes radiusId: Int = 0,
    @DimenRes strokeSizeId: Int = 0,
    @ColorRes strokeColorId: Int = 0,
    shape: Int = GradientDrawable.RECTANGLE
) {
    val backgroundColor = context.colorCompat(colorId)
    val strokeDimen = if (strokeSizeId != 0) resources.getDimensionPixelOffset(strokeSizeId) else 0
    val strokeColor = if (strokeColorId != 0) context.colorCompat(strokeColorId) else 0
    val radius = if (radiusId != 0) resources.getDimensionPixelSize(radiusId).toFloat() else 0F

    background = GradientDrawable().create(backgroundColor, radius, strokeDimen, strokeColor, shape)
}

/**
 * Sets this view to [View.VISIBLE] state.
 */
internal fun View.visible() {
    visibility = View.VISIBLE
}

/**
 * Sets this view to [View.INVISIBLE] state.
 */
internal fun View.invisible() {
    visibility = View.INVISIBLE
}

/**
 * Sets this view to [View.GONE] state.
 */
internal fun View.gone() {
    visibility = View.GONE
}

/**
 * Sets this view to [View.VISIBLE] if the given [show] is true. Sets this view to [View.GONE] otherwise.
 */
internal fun View.visibleOrGone(show: Boolean) {
    isVisible = show
}

/**
 * Sets this view to [View.VISIBLE] if the given [show] is true. Sets this view to [View.INVISIBLE] otherwise.
 */
internal fun View.visibleOrInvisible(show: Boolean) {
    if (show) visible() else invisible()
}

/**
 * Whether or not this view is visible.
 */
@Deprecated("Use attribute [androidx.core.view.isVisible] instead", ReplaceWith("isVisible", "androidx.core.view.isVisible"))
internal fun View.isVisible() = visibility == View.VISIBLE

/**
 * Shows the soft keyboard once this view gained focus.
 */
internal fun View.showSoftKeyboard() {
    if (requestFocus()) {
        val imm = this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }
}

/**
 * Hides the soft keyboard.
 */
internal fun View.hideKeyboard() {
    val imm = this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
}

/**
 * Forces the soft keyboard to hide.
 */
internal fun View.forceHideKeyboard() {
    val imm = this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

/**
 * Executes the given function after the view has been measured.
 */
internal inline fun View.afterMeasured(crossinline func: View.() -> Unit) {
    viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            if (measuredWidth > 0 && measuredHeight > 0) {
                viewTreeObserver.removeOnGlobalLayoutListener(this)
                func()
            }
        }
    })
}

internal fun View.setPaddingTop(paddingTop: Int) {
    setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom)
}

internal fun View.addRippleEffect() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val outValue = TypedValue()
        context.theme.resolveAttribute(android.R.attr.selectableItemBackground, outValue, true)
        foreground = ContextCompat.getDrawable(context, outValue.resourceId)
    }
    isClickable = true
    isFocusable = true
}

fun View.setMargins(left: Int? = null, top: Int? = null, right: Int? = null, bottom: Int? = null) {
    val lp = this.layoutParams as ViewGroup.MarginLayoutParams

    lp.setMargins(
        left ?: lp.leftMargin,
        top ?: lp.topMargin,
        right ?: lp.rightMargin,
        bottom ?: lp.bottomMargin
    )

    layoutParams = lp
}