package com.spendesk.grapes.extensions

import android.graphics.drawable.GradientDrawable

/**
 * @author danyboucanova
 * @since 15/09/2020
 */

/**
 * Easily creates [GradientDrawable] and returns it.
 */
internal fun GradientDrawable.create(
    color: Int,
    radius: Float = 0f,
    stroke: Int = 0,
    strokeColor: Int = 0,
    shape: Int = GradientDrawable.RECTANGLE
): GradientDrawable =
    GradientDrawable().apply {
        setShape(shape)
        setColor(color)
        cornerRadius = radius
        if (stroke != 0 && strokeColor != 0) {
            setStroke(stroke, strokeColor)
        }
    }
