package com.spendesk.grapes.compose.message

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author Kélian CLERC
 * @since 27/02/2023
 */
data class PasswordValidationEntry(
    val label: String,
    val isValid: Boolean
)

private val ValidationItemCircleSize = 6.dp

@Composable
fun PasswordValidation(
    modifier: Modifier = Modifier,
    validationEntries: List<PasswordValidationEntry>
) {
    Column(modifier = modifier.fillMaxWidth()) {
        validationEntries.forEach {
            PasswordValidationItem(label = it.label, isValid = it.isValid)
        }
    }
}

@Composable
private fun PasswordValidationItem(
    label: String,
    isValid: Boolean
) {

    // These colors may change according to the final design. In the future, consider extracting them to be more customizable.
    val itemColor: Color by animateColorAsState(
        if (isValid) GrapesTheme.colors.mainSuccessNormal else GrapesTheme.colors.mainAlertNormal
    )

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(GrapesTheme.dimensions.paddingSmall)
    ) {
        Spacer(
            modifier = Modifier
                .size(ValidationItemCircleSize)
                .background(itemColor, CircleShape)
        )
        Text(text = label, style = GrapesTheme.typography.bodyS, color = itemColor)
    }
}

@Preview
@Composable
private fun PasswordValidationPreview() {
    var items by remember {
        mutableStateOf(
            listOf(
                PasswordValidationEntry("Item 1", isValid = false),
                PasswordValidationEntry("Item 2", isValid = false),
                PasswordValidationEntry("Item 3", isValid = false),
                PasswordValidationEntry("Item 4", isValid = false),
            )
        )
    }

    GrapesTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(GrapesTheme.colors.mainBackground)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .background(GrapesTheme.colors.mainWhite)
                    .border(1.dp, GrapesTheme.colors.mainNeutralLight)
                    .padding(16.dp)
            ) {
                items.forEachIndexed { checkIndex, item ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = item.isValid,
                            onCheckedChange = { isChecked ->
                                items = items.mapIndexed { index, passwordValidationEntry ->
                                    if (checkIndex == index) {
                                        passwordValidationEntry.copy(isValid = isChecked)
                                    } else {
                                        passwordValidationEntry
                                    }
                                }
                            }
                        )
                        Text(text = "Change ${item.label} status", style = GrapesTheme.typography.bodyS)
                    }
                }
            }

            PasswordValidation(validationEntries = items, modifier = Modifier.padding(16.dp))
        }
    }
}
