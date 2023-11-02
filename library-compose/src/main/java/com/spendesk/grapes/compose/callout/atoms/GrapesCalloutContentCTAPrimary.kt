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
fun GrapesCalloutContentCTAPrimary(
    buttonText: String,
    onButtonClick: () -> Unit,
) {
    val primaryButtonStyle = when (LocalGrapesCalloutType.current.type) {
        CalloutType.ERROR -> GrapesButtonStyleDefaults.alert
        CalloutType.WARNING -> GrapesButtonStyleDefaults.warning
        CalloutType.INFO,
        CalloutType.SUCCESS,
        CalloutType.NEUTRAL -> GrapesButtonStyleDefaults.primary
    }

    GrapesButton(
        modifier = Modifier.fillMaxWidth(),
        text = buttonText,
        buttonStyle = primaryButtonStyle,
        onClick = onButtonClick,
    )
}
