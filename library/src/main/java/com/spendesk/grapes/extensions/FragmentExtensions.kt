package com.spendesk.grapes.extensions

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

/**
 * @author Vivien Mahe
 * @since 05/05/2022
 */

/**
 * Ensures the parent activity is attached to perform the given action [consumer].
 */
internal fun Fragment.withActivityAttached(consumer: FragmentActivity.() -> Unit) {
    if (isAdded && activity != null) {
        consumer(requireActivity())
    }
}
