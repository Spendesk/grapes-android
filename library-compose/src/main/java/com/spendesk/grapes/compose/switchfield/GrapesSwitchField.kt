package com.spendesk.grapes.compose.switchfield

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author : RomainGF
 * @since : 03/09/2024
 **/
@Composable
fun GrapesSwitchField(
    title: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = GrapesTheme.shapes.shape2,
    color: Color = GrapesTheme.colors.structureSurface,
    contentColor: Color = GrapesTheme.colors.neutralDarker,
    border: BorderStroke = BorderStroke(1.dp, GrapesTheme.colors.neutralLighter),
    shadowElevation: Dp = 1.dp,
) {
    Surface(
        color = color,
        contentColor = contentColor,
        shape = shape,
        border = border,
        shadowElevation = shadowElevation,
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(GrapesTheme.dimensions.spacing3),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = GrapesTheme.dimensions.spacing3)
        ) {
            Text(
                text = title,
                style = GrapesTheme.typography.bodyL,
                modifier = Modifier.padding(vertical = GrapesTheme.dimensions.spacing3)
            )
            Spacer(Modifier.weight(1f))
            GrapesSwitch(
                checked = checked,
                onCheckedChange = onCheckedChange,
                enabled = enabled,
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun PreviewGrapesSwitchField() {
    GrapesTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            GrapesSwitchField(
                title = "Switch field",
                checked = true,
                onCheckedChange = {},
            )
            GrapesSwitchField(
                title = "Switch field",
                checked = false,
                onCheckedChange = {},
            )
            GrapesSwitchField(
                title = "Switch field",
                checked = true,
                enabled = false,
                onCheckedChange = {},
            )
            GrapesSwitchField(
                title = "Switch field",
                checked = false,
                enabled = false,
                onCheckedChange = {},
            )
        }
    }
}
