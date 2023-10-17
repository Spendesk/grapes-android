package com.spendesk.grapes.compose.callout.atoms

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.spendesk.grapes.compose.button.GrapesButton
import com.spendesk.grapes.compose.button.GrapesButtonStyleDefaults
import com.spendesk.grapes.compose.callout.CalloutType
import com.spendesk.grapes.compose.callout.LocalGrapesCalloutType

/**
 * @author jean-philippe
 * @since 17/10/2023, Tuesday
 **/
@Composable
fun GrapesCalloutContentBottomCTA(
    primaryButtonText: String,
    secondaryButtonText: String,
    onPrimaryButtonClick: () -> Unit,
    onSecondaryButtonClick: () -> Unit,
) {
    val primaryButtonStyle = when (LocalGrapesCalloutType.current.type) {
        CalloutType.ERROR -> GrapesButtonStyleDefaults.alert
        CalloutType.WARNING -> GrapesButtonStyleDefaults.warning
        CalloutType.INFO,
        CalloutType.SUCCESS,
        CalloutType.NEUTRAL -> GrapesButtonStyleDefaults.primary
    }

    GrapesButton(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        text = primaryButtonText,
        buttonStyle = primaryButtonStyle,
        onClick = onPrimaryButtonClick
    )

    GrapesButton(
        modifier = Modifier.fillMaxWidth(),
        text = secondaryButtonText,
        buttonStyle = GrapesButtonStyleDefaults.secondary,
        onClick = onSecondaryButtonClick
    )
}
