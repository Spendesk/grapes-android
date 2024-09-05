package com.spendesk.grapes.compose.optiongroup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spendesk.grapes.compose.optiongroup.internal.GrapesOptionGroupItem
import com.spendesk.grapes.compose.optiongroup.internal.GrapesOptionGroupItemBorder
import com.spendesk.grapes.compose.optiongroup.internal.GrapesOptionGroupItemColors
import com.spendesk.grapes.compose.optiongroup.internal.GrapesOptionGroupItemDefaults
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author : RomainGF
 * @since : 30/08/2024
 **/
private val minWidth = 96.dp

@Composable
fun GrapesTextOptionGroupItem(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    border: GrapesOptionGroupItemBorder? = GrapesOptionGroupItemDefaults.border(),
    elevation: CardElevation = GrapesOptionGroupItemDefaults.elevation(),
    colors: GrapesOptionGroupItemColors = GrapesOptionGroupItemDefaults.colors(
        unselectedContentColor = GrapesTheme.colors.neutralDark,
    ),
) {
    GrapesOptionGroupItem(
        isSelected = isSelected,
        border = border,
        contentDescription = text,
        elevation = elevation,
        colors = colors,
        onClick = onClick,
        modifier = modifier.widthIn(min = minWidth)
    ) {
        Text(
            text = text,
            style = GrapesTheme.typography.titleL,
            maxLines = 1,
            modifier = Modifier.padding(
                vertical = GrapesTheme.dimensions.spacing3,
                horizontal = GrapesTheme.dimensions.spacing1,
            )
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun PreviewGrapesTextOptionGroupItem() {
    GrapesTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            GrapesTextOptionGroupItem(
                isSelected = false,
                text = "3-5cv",
                onClick = {},
            )
            GrapesTextOptionGroupItem(
                isSelected = true,
                text = "3-5cv",
                onClick = {},
            )
        }
    }
}
