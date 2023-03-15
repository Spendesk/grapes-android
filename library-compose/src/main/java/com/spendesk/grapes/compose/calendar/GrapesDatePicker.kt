package com.spendesk.grapes.compose.calendar

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.spendesk.grapes.compose.extensions.resetDateToMidnight
import com.spendesk.grapes.compose.extensions.resetDateToTomorrowMidnight
import com.spendesk.grapes.compose.theme.GrapesTheme
import java.util.Calendar
import java.util.Calendar.YEAR
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
    date: Date = Date(),
    minDate: Date? = null,
    maxDate: Date? = null,
    onDateSelected: ((Date) -> Unit)? = null
) {
    val cal = Calendar.getInstance().apply { time = date }
    val selectedDate =
        rememberDatePickerState(
            initialSelectedDateMillis = date.time,
            initialDisplayedMonthMillis = null,
            yearRange = IntRange(cal.get(YEAR) - 3, cal.get(YEAR)),
            initialDisplayMode = DisplayMode.Picker
        )

    DatePicker(
        state = selectedDate,
        modifier = modifier,
        showModeToggle = false,
        title = null,
        colors = DatePickerDefaults.colors(
            containerColor = GrapesTheme.colors.mainBackground,
            titleContentColor = GrapesTheme.colors.mainNeutralDarkest,
            headlineContentColor = GrapesTheme.colors.mainNeutralDarkest,
            weekdayContentColor = GrapesTheme.colors.mainNeutralDarkest,
            subheadContentColor = GrapesTheme.colors.mainNeutralDarkest,
            yearContentColor = GrapesTheme.colors.mainNeutralDarkest,
            currentYearContentColor = GrapesTheme.colors.mainNeutralDarkest,
            selectedYearContentColor = GrapesTheme.colors.mainWhite,
            selectedYearContainerColor = GrapesTheme.colors.mainPrimaryNormal,
            dayContentColor = GrapesTheme.colors.mainNeutralDarkest,
            selectedDayContentColor = GrapesTheme.colors.mainWhite,
            selectedDayContainerColor = GrapesTheme.colors.mainPrimaryNormal,
            todayContentColor = GrapesTheme.colors.mainPrimaryNormal,
            todayDateBorderColor = GrapesTheme.colors.mainPrimaryNormal
        ),
        dateValidator = { utcDateInMills ->
            val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC")).apply { timeInMillis = utcDateInMills }

            val isAfterMinDate = minDate?.let { calendar.time.after(it.resetDateToMidnight()) } ?: true
            val isBeforeMaxDate = maxDate?.let { calendar.time.before(it.resetDateToTomorrowMidnight()) } ?: true

            isAfterMinDate && isBeforeMaxDate
        }
    )

    LaunchedEffect(selectedDate.selectedDateMillis) {
        selectedDate.selectedDateMillis?.let {
            onDateSelected?.invoke(Date(it))
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
