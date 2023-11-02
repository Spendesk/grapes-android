package com.spendesk.grapes.compose.callout.atoms

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.spendesk.grapes.compose.button.GrapesButton
import com.spendesk.grapes.compose.button.GrapesButtonStyleDefaults
import com.spendesk.grapes.compose.callout.CalloutType
import com.spendesk.grapes.compose.callout.LocalGrapesCalloutType

/**
 * @author jean-philippe
 * @since 30/10/2023, Monday
 **/

@Composable
fun GrapesCalloutContentCTASecondary(
    buttonText: String,
    onButtonClick: () -> Unit,
) {
    GrapesButton(
        modifier = Modifier.fillMaxWidth(),
        text = buttonText,
        buttonStyle = GrapesButtonStyleDefaults.secondary,
        onClick = onButtonClick,
    )
}
