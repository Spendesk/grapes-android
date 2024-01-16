package com.spendesk.grapes.compose.textblock.actions

/**
 * @author Kélian CLERC
 * @since 15/01/2024
 */
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import com.spendesk.grapes.compose.theme.GrapesTheme

@Composable
fun PrimaryTextBlockAction(
    label: String,
    onActionClicked: () -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true
) {
    val actionColor by animateColorAsState(
        targetValue = if (isEnabled) GrapesTheme.colors.primaryNormal else GrapesTheme.colors.neutralLighter,
        label = "actionColor"
    )
    ColoredTextBlockAction(
        label = label,
        color = actionColor,
        onActionClicked = onActionClicked,
        modifier = modifier,
        isEnabled = isEnabled
    )
}

@Composable
fun NeutralDarkTextBlockAction(
    label: String,
    onActionClicked: () -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true
) {
    val actionColor by animateColorAsState(
        targetValue = if (isEnabled) GrapesTheme.colors.neutralDark else GrapesTheme.colors.neutralLighter,
        label = "actionColor"
    )
    ColoredTextBlockAction(
        label = label,
        color = actionColor,
        onActionClicked = onActionClicked,
        modifier = modifier,
        isEnabled = isEnabled
    )
}

@Composable
private fun ColoredTextBlockAction(
    label: String,
    color: Color,
    onActionClicked: () -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
) {
    Text(
        text = label,
        style = GrapesTheme.typography.titleM,
        color = color,
        modifier = modifier
            .clickable(enabled = isEnabled, onClick = onActionClicked, role = Role.Button)
            .padding(GrapesTheme.dimensions.spacing1)
    )
}

@Preview
@Composable
fun PrimaryTextBlockActionPreview() {
    GrapesTheme {
        Column(
            modifier = Modifier.padding(GrapesTheme.dimensions.paddingLarge),
            verticalArrangement = Arrangement.spacedBy(GrapesTheme.dimensions.paddingLarge)
        ) {
            PrimaryTextBlockAction("Test", onActionClicked = {})
            PrimaryTextBlockAction("Test disabled", isEnabled = false, onActionClicked = {})
            NeutralDarkTextBlockAction("Test", onActionClicked = {})
            NeutralDarkTextBlockAction("Test disabled", isEnabled = false, onActionClicked = {})
        }
    }
}
