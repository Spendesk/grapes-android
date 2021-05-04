package com.spendesk.grapes.extensions

import android.app.Activity
import android.view.Gravity
import android.widget.TextView
import android.widget.Toast
import com.spendesk.grapes.R

/**
 * Extension functions for [Activity].
 *
 * @author Vivien Mahe
 * @since 08/02/2021
 */

fun Activity.shortToaster(message: CharSequence) = createToaster(message = message, duration = Toast.LENGTH_SHORT)

fun Activity.longToaster(message: CharSequence) = createToaster(message = message, duration = Toast.LENGTH_LONG)

private fun Activity.createToaster(message: CharSequence, duration: Int) =
    layoutInflater
        .inflate(R.layout.toaster, findViewById(R.id.toasterRootView))
        .let { layout ->
            layout.findViewById<TextView>(R.id.toasterText).text = message

            with(Toast(applicationContext)) {
                setGravity(Gravity.FILL_HORIZONTAL or Gravity.BOTTOM, 0, 0)
                this.duration = duration
                view = layout
                show()
            }
        }
