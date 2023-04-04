package com.spendesk.grapes.compose.calendar

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
            periodSelectorBorderColor = GrapesTheme.colors.mainPrimaryNormal,
            periodSelectorSelectedContainerColor = GrapesTheme.colors.mainPrimaryLightest,
            periodSelectorUnselectedContainerColor = GrapesTheme.colors.mainWhite,
            periodSelectorSelectedContentColor = GrapesTheme.colors.mainPrimaryNormal,
            periodSelectorUnselectedContentColor = GrapesTheme.colors.mainNeutralDarker,
            timeSelectorSelectedContainerColor = GrapesTheme.colors.mainWhite,
            timeSelectorUnselectedContainerColor = GrapesTheme.colors.mainWhite,
            timeSelectorSelectedContentColor = GrapesTheme.colors.mainNeutralDarker,
            timeSelectorUnselectedContentColor = GrapesTheme.colors.mainNeutralDarker
        )
    )

    LaunchedEffect(timerPickerState.hour, timerPickerState.minute) {
        if (timerPickerState.hour != initialHour && timerPickerState.minute != initialMinute) {
            onTimeSelected?.invoke(timerPickerState.hour, timerPickerState.minute)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun GrapesTimePickerPreview() {
    val cal = Calendar.getInstance().apply { time = Date() }

    GrapesTheme {
        GrapesTimePicker(
            initialHour = cal.get(Calendar.HOUR_OF_DAY),
            initialMinute = cal.get(Calendar.MINUTE),
            onTimeSelected = { hour, minute -> println("Selected hour: $hour and minute: $minute") }
        )
    }
}
