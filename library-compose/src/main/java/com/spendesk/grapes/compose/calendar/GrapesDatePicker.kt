package com.spendesk.grapes.compose.calendar

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.spendesk.grapes.compose.extensions.resetDateToMidnight
import com.spendesk.grapes.compose.extensions.resetDateToTomorrowMidnight
import com.spendesk.grapes.compose.theme.GrapesTheme
import java.util.Calendar
import java.util.Date
import java.util.TimeZone

/**
 * @author : dany
 * @since : 07/03/2023, Tue
 **/

/**
 * Grapes date picker which lets the user select a date via a calendar UI.
 *
 * @param modifier The [Modifier] to be applied to this date picker
 * @param date The pre-selected date in the calendar. Defaults to now if not provided
 * @param minDate The minimum selectable date in the calendar (previous dates will be disabled) if provided. Defaults to infinite in the past
 * @param maxDate The maximum selectable date in the calendar (further dates will be disabled) if provided. Defaults to infinite in the future
 * @param onDateSelected Callback when a date is selected in the picker
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GrapesDatePicker(
    modifier: Modifier = Modifier,
    date: Date? = null,
    minDate: Date? = null,
    maxDate: Date? = null,
    onDateSelected: ((Date) -> Unit)? = null
) {
    val selectedDate = rememberDatePickerState(
        initialSelectedDateMillis = date?.time,
        initialDisplayedMonthMillis = date?.time,
        yearRange = DatePickerDefaults.YearRange,
        initialDisplayMode = DisplayMode.Picker,
        selectableDates = object : SelectableDates {
            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC")).apply { timeInMillis = utcTimeMillis }

                val isAfterMinDate = minDate?.let { calendar.time.after(it.resetDateToMidnight()) } ?: true
                val isBeforeMaxDate = maxDate?.let { calendar.time.before(it.resetDateToTomorrowMidnight()) } ?: true

                return isAfterMinDate && isBeforeMaxDate
            }
        }
    )

    DatePicker(
        state = selectedDate,
        modifier = modifier,
        showModeToggle = false,
        title = null,
        headline = null,
        colors = DatePickerDefaults.colors(
            containerColor = GrapesTheme.colors.structureBackground,
            titleContentColor = GrapesTheme.colors.neutralDarker,
            headlineContentColor = GrapesTheme.colors.neutralDarker,
            weekdayContentColor = GrapesTheme.colors.neutralDarker,
            subheadContentColor = GrapesTheme.colors.neutralDarker,
            yearContentColor = GrapesTheme.colors.neutralDarker,
            currentYearContentColor = GrapesTheme.colors.neutralDarker,
            selectedYearContentColor = Color.White,
            selectedYearContainerColor = GrapesTheme.colors.primaryNormal,
            dayContentColor = GrapesTheme.colors.neutralDarker,
            selectedDayContentColor = Color.White,
            selectedDayContainerColor = GrapesTheme.colors.primaryNormal,
            todayContentColor = GrapesTheme.colors.primaryNormal,
            todayDateBorderColor = GrapesTheme.colors.primaryNormal
        ),
    )

    LaunchedEffect(selectedDate.selectedDateMillis) {
        selectedDate.selectedDateMillis?.let {
            if (Date(it).resetDateToMidnight() != date?.resetDateToMidnight()) {
                onDateSelected?.invoke(Date(it))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun GrapesDatePickerWithMinDateAndMaxDatePreview() {
    val minDate = Calendar.getInstance().apply { add(Calendar.DAY_OF_WEEK, -1) }.time
    val maxDate = Calendar.getInstance().apply { add(Calendar.DAY_OF_WEEK, 2) }.time

    GrapesTheme {
        GrapesDatePicker(
            minDate = minDate,
            maxDate = maxDate,
            onDateSelected = { date -> println("Selected date: $date") }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun GrapesDatePickerWithMinDatePreview() {
    val minDate = Calendar.getInstance().apply { add(Calendar.DAY_OF_WEEK, -1) }.time

    GrapesTheme {
        GrapesDatePicker(
            minDate = minDate,
            onDateSelected = { date -> println("Selected date: $date") }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun GrapesDatePickerWithMaxDatePreview() {
    val maxDate = Calendar.getInstance().apply { add(Calendar.DAY_OF_WEEK, 2) }.time

    GrapesTheme {
        GrapesDatePicker(
            maxDate = maxDate,
            onDateSelected = { date -> println("Selected date: $date") }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun GrapesDatePickerPreview() {
    GrapesTheme {
        GrapesDatePicker(
            onDateSelected = { date -> println("Selected date: $date") }
        )
    }
}
