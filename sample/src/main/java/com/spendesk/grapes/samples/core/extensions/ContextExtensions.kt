package com.spendesk.grapes.samples.core.extensions

import android.content.Context
import android.content.res.Configuration

/**
 * @author danyboucanova
 * @since 1/6/21
 */

internal fun Context.isDarkThemeOn(): Boolean {
    return resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
}