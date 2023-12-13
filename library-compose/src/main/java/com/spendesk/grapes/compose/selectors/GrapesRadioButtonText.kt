package com.spendesk.grapes.compose.selectors

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author : dany
 * @since : 23/02/2023, Thu
 **/

@Composable
fun GrapesRadioButtonText(
    text: String,
    onClick: (() -> Unit),
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    isEnabled: Boolean = true,
) =
    Row(
        modifier = modifier
            .clickable(
                enabled = isEnabled,
                onClickLabel = text,
                role = Role.RadioButton,
                onClick = onClick
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        GrapesRadioButton(
            isSelected = isSelected,
            isEnabled = isEnabled,
            onClick = null
        )
        Spacer(Modifier.padding(end = GrapesTheme.dimensions.spacing2))
        Text(
            text = text,
            color = GrapesTheme.colors.mainNeutralDarker,
            style = GrapesTheme.typography.bodyL
        )
    }


@Composable
@Preview(
    widthDp = 422,
    showBackground = true,
)
private fun GrapesRadioButtonTextPreview() {
    GrapesTheme {
        Column(modifier = Modifier.padding(16.dp)) {
            GrapesRadioButtonText(text = "Radio selected and enabled", isSelected = true, isEnabled = true, onClick = { Log.i("GrapesRadioButton", "action button click") })
            GrapesRadioButtonText(text = "Radio unselected and enabled", isSelected = false, isEnabled = true, onClick = { Log.i("GrapesRadioButton", "action button click") })
            GrapesRadioButtonText(text = "Radio selected and disabled", isSelected = true, isEnabled = false, onClick = { Log.i("GrapesRadioButton", "action button click") })
            GrapesRadioButtonText(text = "Radio unselected and disabled", isSelected = false, isEnabled = false, onClick = { Log.i("GrapesRadioButton", "action button click") })
        }
    }
}
