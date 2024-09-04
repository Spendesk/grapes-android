package com.spendesk.grapes.compose.optiongroup.internal

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author : RomainGF
 * @since : 30/08/2024
 **/
@Composable
internal fun GrapesOptionGroupItem(
    isSelected: Boolean,
    onClick: () -> Unit,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    alignment: Alignment = Alignment.Center,
    elevation: CardElevation = GrapesOptionGroupItemDefaults.elevation(),
    colors: CardColors = if (isSelected) {
        GrapesOptionGroupItemDefaults.selectedColors()
    } else {
        GrapesOptionGroupItemDefaults.unselectedColors()
    },
    border: BorderStroke = if (isSelected) {
        GrapesOptionGroupItemDefaults.selectedBorder()
    } else {
        GrapesOptionGroupItemDefaults.unselectedBorder()
    },
    content: @Composable () -> Unit,
) {
    Card(
        colors = colors,
        border = border,
        elevation = elevation,
        shape = GrapesTheme.shapes.shape2,
        onClick = onClick,
        modifier = modifier
            .width(IntrinsicSize.Max)
            .height(IntrinsicSize.Max)
            .semantics(mergeDescendants = true) { this.contentDescription = contentDescription ?: "" }
    ) {
        Box(
            contentAlignment = alignment,
            modifier = Modifier.fillMaxSize(),
        ) {
            content()
        }
    }
}

internal object GrapesOptionGroupItemDefaults {

    @Composable
    fun selectedColors(
        containerColor: Color = GrapesTheme.colors.primaryLightest,
        contentColor: Color = GrapesTheme.colors.primaryNormal,
    ): CardColors = CardDefaults.cardColors(
        containerColor = containerColor,
        contentColor = contentColor,
    )

    @Composable
    fun selectedBorder(): BorderStroke = BorderStroke(
        width = 2.dp,
        color = GrapesTheme.colors.primaryNormal,
    )

    @Composable
    fun unselectedColors(
        containerColor: Color = GrapesTheme.colors.structureSurface,
        contentColor: Color = GrapesTheme.colors.structureComplementary,
    ): CardColors = CardDefaults.cardColors(
        containerColor = containerColor,
        contentColor = contentColor,
    )

    @Composable
    fun unselectedBorder(): BorderStroke = BorderStroke(
        width = 1.dp,
        color = GrapesTheme.colors.neutralLighter,
    )

    @Composable
    fun elevation(): CardElevation = CardDefaults.elevatedCardElevation()
}

@Composable
@Preview(showBackground = true)
private fun PreviewGrapesOptionGroupItem() {
    GrapesTheme {
        var isSelected by remember { mutableStateOf(false) }
        val icon = @Composable {
            Icon(
                imageVector = Icons.Default.DirectionsCar,
                contentDescription = null,
                modifier = Modifier.size(32.dp)
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            GrapesOptionGroupItem(
                isSelected = isSelected,
                onClick = { isSelected = !isSelected },
                contentDescription = null,
            ) {
                icon()
            }
            GrapesOptionGroupItem(
                isSelected = !isSelected,
                onClick = { isSelected = !isSelected },
                contentDescription = null,
            ) {
                icon()
            }
        }
    }
}
