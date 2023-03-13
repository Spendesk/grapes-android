package com.spendesk.grapes.compose.calendar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.TimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
    modifier: Modifier = Modifier
) {
    TimeInput(
        modifier = modifier,
        state = TimePickerState(selectedHour, selectedMinutes, is24Hour = false),
        colors = TimePickerDefaults.colors()
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun GrapesTimerPreview() {
    val date by remember { mutableStateOf(Date()) }
    val cal = Calendar.getInstance().apply { time = date }

    GrapesTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            GrapesTimer(selectedHour = cal.get(Calendar.HOUR_OF_DAY), selectedMinutes = cal.get(Calendar.MINUTE))
        }
    }
}
