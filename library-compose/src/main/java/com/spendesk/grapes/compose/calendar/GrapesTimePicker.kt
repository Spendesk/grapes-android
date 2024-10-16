package com.spendesk.grapes.compose.calendar

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.spendesk.grapes.compose.theme.GrapesTheme
import java.util.Calendar
import java.util.Date

/**
 * @author : dany
 * @since : 07/03/2023, Tue
 **/

/**
 * Grapes time picker which lets the user selects a specific time by providing the hour, minutes and the am/pm format.
 *
 * @param initialHour Initial hour to be displayed in the picker
 * @param initialMinute Initial minute to be displayed in the picker
 * @param modifier The [Modifier] to be applied to this time picker
 * @param onTimeSelected Callback when an hour or minute is changed in the picker
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GrapesTimePicker(
    initialHour: Int,
    initialMinute: Int,
    modifier: Modifier = Modifier,
    onTimeSelected: ((Int, Int) -> Unit)? = null
) {
    val timerPickerState = rememberTimePickerState(
        initialHour = initialHour,
        initialMinute = initialMinute,
        is24Hour = false
    )

    TimeInput(
        modifier = modifier,
        state = timerPickerState,
        colors = TimePickerDefaults.colors(
            periodSelectorBorderColor = GrapesTheme.colors.primaryNormal,
            periodSelectorSelectedContainerColor = GrapesTheme.colors.primaryLightest,
            periodSelectorUnselectedContainerColor = GrapesTheme.colors.mainWhite,
            periodSelectorSelectedContentColor = GrapesTheme.colors.primaryNormal,
            periodSelectorUnselectedContentColor = GrapesTheme.colors.neutralDark,
            timeSelectorSelectedContainerColor = GrapesTheme.colors.mainWhite,
            timeSelectorUnselectedContainerColor = GrapesTheme.colors.mainWhite,
            timeSelectorSelectedContentColor = GrapesTheme.colors.neutralDark,
            timeSelectorUnselectedContentColor = GrapesTheme.colors.neutralDark
        )
    )

    LaunchedEffect(timerPickerState.hour, timerPickerState.minute) {
        if (timerPickerState.hour != initialHour || timerPickerState.minute != initialMinute) {
            onTimeSelected?.invoke(timerPickerState.hour, timerPickerState.minute)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun GrapesTimePickerPreview() {
    var itemHour by remember { mutableStateOf(Calendar.getInstance().apply { time = Date() }.get(Calendar.HOUR_OF_DAY)) }
    var itemMinutes by remember { mutableStateOf(Calendar.getInstance().apply { time = Date() }.get(Calendar.MINUTE)) }

    GrapesTheme {
        GrapesTimePicker(
            initialHour = itemHour,
            initialMinute = itemMinutes,
            onTimeSelected = { hour, minute ->
                itemHour = hour
                itemMinutes = minute
                println("Selected hour: $hour and minute: $minute")
            }
        )
    }
}
