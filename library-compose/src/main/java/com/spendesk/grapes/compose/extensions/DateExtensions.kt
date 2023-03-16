package com.spendesk.grapes.compose.extensions

import java.util.Calendar
import java.util.Date

/**
 * TODO This is copied from "common" module in spendesk-android repo and will be removed once this "common" module will become an independent repo.
 *
 * @author Vivien Mahe
 * @since 14/03/2023
 */

fun Date.resetDateToMidnight(): Date =
    Calendar.getInstance()
        .apply {
            time = this@resetDateToMidnight

            this.set(Calendar.HOUR_OF_DAY, 0)
            this.set(Calendar.MINUTE, 0)
            this.set(Calendar.SECOND, 0)
            this.set(Calendar.MILLISECOND, 0)
        }
        .time

fun Date.resetDateToTomorrowMidnight(): Date =
    Calendar.getInstance()
        .apply {
            time = this@resetDateToTomorrowMidnight

            this.add(Calendar.DAY_OF_YEAR, 1)
            this.set(Calendar.HOUR_OF_DAY, 0)
            this.set(Calendar.MINUTE, 0)
            this.set(Calendar.SECOND, 0)
            this.set(Calendar.MILLISECOND, 0)
        }
        .time
