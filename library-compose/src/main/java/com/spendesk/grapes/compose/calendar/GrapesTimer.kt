package com.spendesk.grapes.compose.calendar

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.spendesk.grapes.compose.theme.GrapesTheme
import java.util.*

/**
 * @author : dany
 * @since : 07/03/2023, Tue
 **/
@ExperimentalMaterial3Api
@Composable
fun GrapesTimer(
    selectedHour: Int,
    selectedMinutes: Int,
    modifier: Modifier = Modifier,
    onTimeSelected: ((Int, Int) -> Unit)? = null
) {
    val timerPickerState = rememberTimePickerState(
        initialHour = selectedHour,
        initialMinute = selectedMinutes,
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

    onTimeSelected?.invoke(timerPickerState.hour, timerPickerState.minute)
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun GrapesTimerPreview() {
    val cal = Calendar.getInstance().apply { time = Date() }

    GrapesTheme {
        GrapesTimer(
            selectedHour = cal.get(Calendar.HOUR_OF_DAY),
            selectedMinutes = cal.get(Calendar.MINUTE),
            onTimeSelected = { hour, minute -> println("Selected hour: $hour and minute: $minute") }
        )
    }
}
