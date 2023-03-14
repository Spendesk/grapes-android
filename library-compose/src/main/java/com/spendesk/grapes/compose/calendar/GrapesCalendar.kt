package com.spendesk.grapes.compose.calendar

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.spendesk.grapes.compose.theme.GrapesTheme
import java.util.*
import java.util.Calendar.YEAR

/**
 * @author : dany
 * @since : 07/03/2023, Tue
 **/
@Composable
@ExperimentalMaterial3Api
fun GrapesCalendar(
    date: Date,
    modifier: Modifier = Modifier,
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
        )
    )

    if (onDateSelected != null && selectedDate.selectedDateMillis != null) {
        onDateSelected.invoke(Date(selectedDate.selectedDateMillis!!))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun GrapesCalendarPreview() {
    GrapesTheme {
        GrapesCalendar(
            date = Date(),
            onDateSelected = { date -> println("Selected date: $date") }
        )
    }
}
