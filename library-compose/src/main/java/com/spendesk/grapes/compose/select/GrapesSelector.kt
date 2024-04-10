package com.spendesk.grapes.compose.select

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spendesk.grapes.compose.badge.GrapesAlertBadge
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author RomainGF
 * @since 05/01/2024
 */
@Composable
fun GrapesSelector(
    label: String,
    modifier: Modifier = Modifier,
    badge: @Composable (() -> Unit)? = null,
    colors: GrapesSelectorColors = GrapesSelectorDefaults.defaultColors(),
    onClick: () -> Unit,
) {
    GrapesSelector(
        label = label,
        badge = badge,
        icon = {
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = null,
                tint = colors.contentColor,
            )
        },
        onClick = onClick,
        colors = colors,
        shape = GrapesTheme.shapes.shape4,
        contentPadding = PaddingValues(
            horizontal = GrapesTheme.dimensions.spacing3,
            vertical = GrapesTheme.dimensions.spacing2,
        ),
        modifier = modifier,
    )
}

@Composable
internal fun GrapesSelector(
    label: String,
    modifier: Modifier = Modifier,
    badge: @Composable (() -> Unit)? = null,
    shape: Shape = GrapesTheme.shapes.shape4,
    contentPadding: PaddingValues = PaddingValues(
        horizontal = GrapesTheme.dimensions.spacing3,
        vertical = GrapesTheme.dimensions.spacing2,
    ),
    colors: GrapesSelectorColors = GrapesSelectorDefaults.defaultColors(),
    icon: @Composable () -> Unit = {
        Icon(
            imageVector = Icons.Default.KeyboardArrowDown,
            contentDescription = null,
            tint = colors.contentColor,
        )
    },
    onClick: () -> Unit,
) {
    Surface(
        color = colors.backgroundColor,
        shape = shape,
        onClick = onClick,
        modifier = modifier,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(GrapesTheme.dimensions.spacing1),
            modifier = Modifier
                .border(1.dp, colors.borderColor, shape = shape)
                .padding(contentPadding)
        ) {
            Text(
                text = label,
                style = GrapesTheme.typography.titleM,
                color = colors.contentColor,
            )

            if (badge != null) {
                badge()
            }

            icon()
        }
    }
}

@Immutable
data class GrapesSelectorColors internal constructor(
    val backgroundColor: Color,
    val borderColor: Color,
    val contentColor: Color,
)

object GrapesSelectorDefaults {

    @Composable
    fun defaultColors(): GrapesSelectorColors = GrapesSelectorColors(
        backgroundColor = GrapesTheme.colors.structureSurface,
        borderColor = GrapesTheme.colors.primaryLighter,
        contentColor = GrapesTheme.colors.primaryNormal,
    )

    @Composable
    fun secondaryColors(): GrapesSelectorColors = GrapesSelectorColors(
        backgroundColor = GrapesTheme.colors.structureSurface,
        borderColor = GrapesTheme.colors.primaryLighter,
        contentColor = GrapesTheme.colors.structureComplementary,
    )

    @Composable
    fun primaryColors(): GrapesSelectorColors = GrapesSelectorColors(
        backgroundColor = GrapesTheme.colors.primaryNormal,
        borderColor = GrapesTheme.colors.primaryNormal,
        contentColor = GrapesTheme.colors.structureSurface,
    )
}

@Preview
@Composable
private fun GrapesSelectorPreview() {
    GrapesTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            GrapesSelector(
                modifier = Modifier,
                label = "Spendesk UK",
                badge = {
                    GrapesAlertBadge(countValue = 24)
                },
                onClick = {},
            )
            GrapesSelector(
                label = "Spendesk UK",
                colors = GrapesSelectorDefaults.defaultColors(),
                onClick = {},
            )
            GrapesSelector(
                label = "Spendesk UK",
                colors = GrapesSelectorDefaults.secondaryColors(),
                onClick = {},
            )
            GrapesSelector(
                label = "Spendesk UK",
                colors = GrapesSelectorDefaults.primaryColors(),
                onClick = {},
            )
        }
    }
}
