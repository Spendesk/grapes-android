package com.spendesk.grapes.compose.textblock.actions

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import com.spendesk.grapes.compose.R
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author Kélian CLERC
 * @since 15/01/2024
 */
@Composable
fun GrapesRetryTextBlockAction(retryLabel: String, onRetryClicked: () -> Unit, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.clickable(onClickLabel = retryLabel, onClick = onRetryClicked, enabled = true, role = Role.Button),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(GrapesTheme.dimensions.spacing2)
    ) {
        Text(text = retryLabel, style = GrapesTheme.typography.bodyM, color = GrapesTheme.colors.warningNormal)
        Icon(painter = painterResource(R.drawable.ic_warning), contentDescription = null, tint = GrapesTheme.colors.warningNormal)
    }
}

@Preview
@Composable
fun TextBlockActionPreview() {
    GrapesTheme {
        Column(
            modifier = Modifier.padding(GrapesTheme.dimensions.paddingLarge),
            verticalArrangement = Arrangement.spacedBy(GrapesTheme.dimensions.paddingLarge)
        ) {
            GrapesRetryTextBlockAction("Tap to retry", onRetryClicked = {})
        }
    }
}
