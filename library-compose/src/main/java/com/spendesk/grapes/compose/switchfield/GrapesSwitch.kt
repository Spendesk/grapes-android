package com.spendesk.grapes.compose.switchfield

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author : RomainGF
 * @since : 03/09/2024
 **/
@Composable
fun GrapesSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    Switch(
        checked = checked,
        onCheckedChange = onCheckedChange,
        enabled = enabled,
        colors = GrapesSwitchDefaults.colors(),
        modifier = modifier,
    )
}

object GrapesSwitchDefaults {

    @Composable
    fun colors() = SwitchDefaults.colors(
        checkedThumbColor = GrapesTheme.colors.structureSurface,
        checkedTrackColor = GrapesTheme.colors.primaryNormal,
        uncheckedThumbColor = GrapesTheme.colors.structureSurface,
        uncheckedTrackColor = GrapesTheme.colors.neutralLight,
        uncheckedBorderColor = GrapesTheme.colors.neutralLight,
        disabledCheckedTrackColor = GrapesTheme.colors.neutralLightest,
        disabledCheckedBorderColor = GrapesTheme.colors.neutralLight,
        disabledCheckedThumbColor = GrapesTheme.colors.neutralLight,
        disabledUncheckedTrackColor = GrapesTheme.colors.neutralLightest,
        disabledUncheckedThumbColor = GrapesTheme.colors.neutralLight,
        disabledUncheckedBorderColor = GrapesTheme.colors.neutralLight,
    )
}

@Preview
@Composable
private fun PreviewGrapesSwitch() {
    GrapesTheme {
        Row(
            horizontalArrangement = Arrangement.spacedBy(GrapesTheme.dimensions.spacing2),
        ) {
            GrapesSwitch(checked = true, onCheckedChange = {})
            GrapesSwitch(checked = false, onCheckedChange = {})
            GrapesSwitch(checked = true, onCheckedChange = {}, enabled = false)
            GrapesSwitch(checked = false, onCheckedChange = {}, enabled = false)
        }
    }
}
