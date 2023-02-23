package com.spendesk.grapes.compose.selectors

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author : dany
 * @since : 22/02/2023, Wed
 **/
@Composable
fun GrapesRadioButton(
    modifier: Modifier,
    isSelected: Boolean = false,
    isEnabled: Boolean = true,
    onClick: (() -> Unit)
) =
    RadioButton(
        modifier = modifier,
        selected = isSelected,
        enabled = isEnabled,
        colors = RadioButtonDefaults.colors(
            selectedColor = GrapesRadioButtonDefaultColors.selectedColor,
            unselectedColor = GrapesRadioButtonDefaultColors.unselectedColor,
            disabledSelectedColor = GrapesRadioButtonDefaultColors.disabledSelectedColor,
            disabledUnselectedColor = GrapesRadioButtonDefaultColors.disabledUnselectedColor
        ),
        onClick = onClick
    )


@Composable
@Preview(
    widthDp = 422,
    showBackground = true,
)
private fun GrapesRadioPreview() {
    GrapesTheme {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                GrapesRadioButton(modifier = Modifier, isSelected = true, isEnabled = true, onClick = { Log.i("GrapesRadioButton", "action button click") })
                Text("Radio selected and enabled")
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                GrapesRadioButton(modifier = Modifier, isSelected = false, isEnabled = true, onClick = { Log.i("GrapesRadioButton", "action button click") })
                Text("Radio unselected and enabled")
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                GrapesRadioButton(modifier = Modifier, isSelected = true, isEnabled = false, onClick = { Log.i("GrapesRadioButton", "action button click") })
                Text("Radio selected and disabled")
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                GrapesRadioButton(modifier = Modifier, isSelected = false, isEnabled = false, onClick = { Log.i("GrapesRadioButton", "action button click") })
                Text("Radio unselected and disabled")
            }
        }
    }
}
