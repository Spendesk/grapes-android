package com.spendesk.grapes.compose.calendar

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerColors
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.spendesk.grapes.compose.extensions.resetDateToMidnight
import com.spendesk.grapes.compose.theme.GrapesTheme
import java.util.Calendar
import java.util.Date

/**
 * @author : dany
 * @since : 07/03/2023, Tue
 **/

/**
 * Grapes date picker which lets the user select a date via a calendar UI.
 *
 * @param modifier The [Modifier] to be applied to this date picker
 * @param initialDisplayedDate The pre-selected date in the calendar. Defaults to now if not provided
 * @param yearRange The range of years to be displayed in the year picker. Defaults to 1900-2100
 * @param dateEdges The minimum and maximum selectable dates in the calendar. Defaults to infinite in the past and future
 * @param colors The colors to be used for the date picker
 * @param title The title to be displayed at the top of the date picker
 * @param headline The headline to be displayed below the title of the date picker
 * @param onDateSelected Callback when a date is selected in the picker
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GrapesDatePicker(
    modifier: Modifier = Modifier,
    initialDisplayedDate: Date? = null,
    yearRange: IntRange = GrapesDatePickerDefaults.YearRange,
    dateEdges: SelectableDates = GrapesDatePickerDefaults.selectableDatesEdges(),
    colors: DatePickerColors = GrapesDatePickerDefaults.colors(),
    title: (@Composable () -> Unit)? = null,
    headline: (@Composable () -> Unit)? = null,
    onDateSelected: ((Date) -> Unit)? = null,
) {
    val selectedDate = rememberDatePickerState(
        initialSelectedDateMillis = initialDisplayedDate?.time,
        initialDisplayedMonthMillis = initialDisplayedDate?.time,
        yearRange = yearRange,
        initialDisplayMode = DisplayMode.Picker,
        selectableDates = dateEdges,
    )

    DatePicker(
        state = selectedDate,
        modifier = modifier,
        showModeToggle = false,
        title = title,
        headline = headline,
        colors = colors,
    )

    LaunchedEffect(selectedDate.selectedDateMillis) {
        selectedDate.selectedDateMillis?.let {
            if (Date(it).resetDateToMidnight() != initialDisplayedDate?.resetDateToMidnight()) {
                onDateSelected?.invoke(Date(it))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
private data class GrapesDatePickerData(
    val title: String? = null,
    val headline: String? = null,
    val initialDisplayedDate: Date? = null,
    val yearRange: IntRange? = null,
    val dateEdges: SelectableDates? = null,
    val onDateSelected: ((Date) -> Unit)? = null,
)

@OptIn(ExperimentalMaterial3Api::class)
private class GrapesDatePickerProvider : PreviewParameterProvider<GrapesDatePickerData> {

    private val calendar = Calendar.getInstance()

    override val values: Sequence<GrapesDatePickerData> = sequenceOf(
        GrapesDatePickerData(
            title = "Select a date",
            headline = "Choose a date to proceed",
        ),

        GrapesDatePickerData(
            initialDisplayedDate = calendar.time, // Today
            dateEdges = GrapesDatePickerDefaults.selectableDatesEdges(
                minDate = calendar.apply { add(Calendar.DAY_OF_WEEK, -1) }.time,
                maxDate = calendar.apply { add(Calendar.DAY_OF_WEEK, 2) }.time
            ),
        ),

        GrapesDatePickerData(
            dateEdges = GrapesDatePickerDefaults.selectableDatesEdges(
                minDate = calendar.apply { add(Calendar.DAY_OF_WEEK, -1) }.time,
                maxDate = null
            ),
        ),

        GrapesDatePickerData(
            dateEdges = GrapesDatePickerDefaults.selectableDatesEdges(
                minDate = null,
                maxDate = calendar.apply { add(Calendar.DAY_OF_WEEK, 1) }.time
            ),
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true)
private fun GrapesDatePickerPreview(
    @PreviewParameter(GrapesDatePickerProvider::class) data: GrapesDatePickerData,
) {
    GrapesTheme {
        GrapesDatePicker(
            title = data.title?.let {
                @Composable {
                    Text(
                        modifier = Modifier.padding(GrapesTheme.dimensions.spacing3),
                        text = it,
                        style = GrapesTheme.typography.titleXl
                    )
                }
            },
            headline = data.headline?.let {
                @Composable {
                    Text(
                        modifier = Modifier.padding(GrapesTheme.dimensions.spacing3),
                        text = it,
                        style = GrapesTheme.typography.titleM
                    )
                }
            },
            initialDisplayedDate = data.initialDisplayedDate,
            dateEdges = data.dateEdges ?: GrapesDatePickerDefaults.selectableDatesEdges(),
            onDateSelected = { date -> println("Selected date: $date") }
        )
    }
}
