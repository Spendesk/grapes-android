package com.spendesk.grapes.compose.calendar

import androidx.compose.material3.DatePickerColors
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.DialogProperties
import com.spendesk.grapes.compose.theme.GrapesTheme
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GrapesDatePickerDialog(
    initialDisplayedDate: Date,
    onDateSelected: (selectedDate: Date) -> Unit,
    modifier: Modifier = Modifier,
    colors: DatePickerColors = GrapesDatePickerDefaults.colors(),
    shape: Shape = GrapesTheme.shapes.shape2,
    dismissOnBack: Boolean = true,
    dismissOnClickOutside: Boolean = true,
    yearRange: IntRange? = null,
    dateEdges: SelectableDates? = null,
    confirmButton: @Composable () -> Unit = { },
    dismissButton: (@Composable () -> Unit)? = null,
    onDismissRequest: (() -> Unit)? = null,
) {
    DatePickerDialog(
        modifier = modifier,
        onDismissRequest = { onDismissRequest?.invoke() },
        confirmButton = confirmButton,
        dismissButton = dismissButton,
        colors = colors,
        shape = shape,
        properties = DialogProperties(
            dismissOnBackPress = dismissOnBack,
            dismissOnClickOutside = dismissOnClickOutside,
        ),
        content = {
            GrapesDatePicker(
                yearRange = yearRange ?: GrapesDatePickerDefaults.YearRange,
                dateEdges = dateEdges ?: GrapesDatePickerDefaults.selectableDatesEdges(),
                initialDisplayedDate = initialDisplayedDate,
                onDateSelected = { date -> onDateSelected(date) }
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = false)
@Composable
fun PreviewGrapesDatePickerDialog() {
    GrapesTheme {
        GrapesDatePickerDialog(
            initialDisplayedDate = Date(),
            onDateSelected = { }
        )
    }
}
