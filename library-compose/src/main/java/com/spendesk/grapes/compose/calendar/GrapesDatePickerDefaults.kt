package com.spendesk.grapes.compose.calendar

import androidx.compose.material3.DatePickerColors
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import com.spendesk.grapes.compose.extensions.resetDateToMidnight
import com.spendesk.grapes.compose.extensions.resetDateToTomorrowMidnight
import com.spendesk.grapes.compose.theme.GrapesTheme
import java.util.Calendar
import java.util.Date
import java.util.TimeZone

@Immutable
object GrapesDatePickerDefaults {

    val YearRange: IntRange = IntRange(1900, 2100)

    @ExperimentalMaterial3Api
    @Composable
    fun colors(
        containerColor: Color = GrapesTheme.colors.structureBackground,
        titleContentColor: Color = GrapesTheme.colors.neutralDarker,
        headlineContentColor: Color = GrapesTheme.colors.neutralDarker,
        weekdayContentColor: Color = GrapesTheme.colors.neutralDarker,
        subheadContentColor: Color = GrapesTheme.colors.neutralDarker,
        yearContentColor: Color = GrapesTheme.colors.neutralDarker,
        currentYearContentColor: Color = GrapesTheme.colors.neutralDarker,
        selectedYearContentColor: Color = Color.White,
        selectedYearContainerColor: Color = GrapesTheme.colors.primaryNormal,
        dayContentColor: Color = GrapesTheme.colors.neutralDarker,
        selectedDayContentColor: Color = Color.White,
        selectedDayContainerColor: Color = GrapesTheme.colors.primaryNormal,
        todayContentColor: Color = GrapesTheme.colors.primaryNormal,
        todayDateBorderColor: Color = GrapesTheme.colors.primaryNormal,
    ): DatePickerColors = DatePickerDefaults.colors(
        containerColor = containerColor,
        titleContentColor = titleContentColor,
        headlineContentColor = headlineContentColor,
        weekdayContentColor = weekdayContentColor,
        subheadContentColor = subheadContentColor,
        yearContentColor = yearContentColor,
        currentYearContentColor = currentYearContentColor,
        selectedYearContentColor = selectedYearContentColor,
        selectedYearContainerColor = selectedYearContainerColor,
        dayContentColor = dayContentColor,
        selectedDayContentColor = selectedDayContentColor,
        selectedDayContainerColor = selectedDayContainerColor,
        todayContentColor = todayContentColor,
        todayDateBorderColor = todayDateBorderColor,
    )

    @OptIn(ExperimentalMaterial3Api::class)
    fun selectableDatesEdges(minDate: Date? = null, maxDate: Date? = null): SelectableDates {
        return if (minDate != null || maxDate != null) {
            object : SelectableDates {
                override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                    val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC")).apply { timeInMillis = utcTimeMillis }

                    val isAfterMinDate = minDate?.let { calendar.time.after(it.resetDateToMidnight()) } ?: true
                    val isBeforeMaxDate = maxDate?.let { calendar.time.before(it.resetDateToTomorrowMidnight()) } ?: true

                    return isAfterMinDate && isBeforeMaxDate
                }
            }
        } else {
            object : SelectableDates {}
        }
    }
}
