package com.spendesk.grapes.compose.calendar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
    modifier: Modifier = Modifier
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
        showModeToggle = false
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun GrapesCalendarPreview() {
    GrapesTheme {
        Column(modifier = Modifier.wrapContentSize(align = Alignment.Center)) {
            GrapesCalendar(date = Date())
        }
    }
}
