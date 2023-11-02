package com.spendesk.grapes.compose.callout.molecules

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * @author jean-philippe
 * @since 17/10/2023, Tuesday
 **/
@Composable
fun ColumnScope.GrapesCalloutContentBottomCTA(
    primaryButton: (@Composable ColumnScope.() -> Unit)? = null,
    secondaryButton: (@Composable ColumnScope.() -> Unit)? = null,
) {
    require(primaryButton != null || secondaryButton != null) {
        "At least one button must be provided"
    }

    // Design require extra padding between the content and the buttons
    Spacer(modifier = Modifier.padding(top = 8.dp))

    primaryButton?.invoke(this)
    secondaryButton?.invoke(this)
}
