package com.spendesk.grapes.compose.internal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * Use this composable to display a slot in a preview.
 *
 * @author : RomainGF
 * @since : 03/01/2024
 **/
@Composable
internal fun Slot(
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.background(GrapesTheme.colors.warningLightest),
    ) {
        Text(
            text = "To be replaced",
            color = GrapesTheme.colors.warningNormal,
            style = GrapesTheme.typography.titleM,
        )
    }
}
