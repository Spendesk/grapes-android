package com.spendesk.grapes.compose.callout.molecules

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author jean-philippe
 * @since 17/10/2023, Tuesday
 **/
@Composable
fun ColumnScope.GrapesCalloutContentBottomCTA(
    primaryButton: (@Composable ColumnScope.() -> Unit)? = null,
    secondaryButton: (@Composable ColumnScope.() -> Unit)? = null,
    addExtraPaddingTop: Boolean = true,
) {
    require(primaryButton != null || secondaryButton != null) {
        "At least one button must be provided"
    }

    // Design require extra padding between the content and the buttons
    if (addExtraPaddingTop) {
        Spacer(modifier = Modifier.padding(top = GrapesTheme.dimensions.spacing2))
    }

    primaryButton?.invoke(this)
    secondaryButton?.invoke(this)
}
