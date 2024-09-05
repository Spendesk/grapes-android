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
import androidx.compose.runtime.Immutable
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
    colors: GrapesOptionGroupItemColors = GrapesOptionGroupItemDefaults.colors(),
    border: GrapesOptionGroupItemBorder? = GrapesOptionGroupItemDefaults.border(),
    content: @Composable () -> Unit,
) {
    Card(
        elevation = elevation,
        shape = GrapesTheme.shapes.shape2,
        onClick = onClick,
        border = if (isSelected) {
            border?.selectedBorder
        } else {
            border?.unselectedBorder
        },
        colors = if (isSelected) {
            colors.selectedColors
        } else {
            colors.unselectedColors
        },
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

@Immutable
data class GrapesOptionGroupItemColors(
    val selectedColors: CardColors,
    val unselectedColors: CardColors,
)

@Immutable
data class GrapesOptionGroupItemBorder(
    val selectedBorder: BorderStroke?,
    val unselectedBorder: BorderStroke?,
)

internal object GrapesOptionGroupItemDefaults {

    @Composable
    fun colors(
        selectedContainerColor: Color = GrapesTheme.colors.primaryLightest,
        selectedContentColor: Color = GrapesTheme.colors.primaryNormal,
        unselectedContainerColor: Color = GrapesTheme.colors.structureSurface,
        unselectedContentColor: Color = GrapesTheme.colors.structureComplementary,
    ): GrapesOptionGroupItemColors = GrapesOptionGroupItemColors(
        selectedColors = CardDefaults.cardColors(
            containerColor = selectedContainerColor,
            contentColor = selectedContentColor,
        ),
        unselectedColors = CardDefaults.cardColors(
            containerColor = unselectedContainerColor,
            contentColor = unselectedContentColor,
        ),
    )

    @Composable
    fun border(
        selectedBorder: BorderStroke? = BorderStroke(2.dp, GrapesTheme.colors.primaryNormal),
        unselectedBorder: BorderStroke? = BorderStroke(1.dp, GrapesTheme.colors.neutralLighter),
    ): GrapesOptionGroupItemBorder = GrapesOptionGroupItemBorder(
        selectedBorder = selectedBorder,
        unselectedBorder = unselectedBorder,
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
                content = { icon() }
            )
            GrapesOptionGroupItem(
                isSelected = !isSelected,
                onClick = { isSelected = !isSelected },
                contentDescription = null,
                content = { icon() }
            )
        }
    }
}
