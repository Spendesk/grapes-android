package com.spendesk.grapes.extensions

import android.content.Context
import androidx.core.content.ContextCompat

/**
 * Extension functions for [ContextCompat] class.
 * @author danyboucanova
 * @since 14/09/2020
 */

/**
 * @see <a href="https://developer.android.com/reference/androidx/core/content/ContextCompat#getColor(android.content.Context,%20int)">getColor(Context, int)</a>
 */
internal fun Context.colorCompat(id: Int) = ContextCompat.getColor(this, id)

/**
 * @see <a href="https://developer.android.com/reference/androidx/core/content/ContextCompat#getColorStateList(android.content.Context,%20int)">getColor(Context, int)</a>
 */
internal fun Context.colorStateListCompat(id: Int) = ContextCompat.getColorStateList(this, id)
