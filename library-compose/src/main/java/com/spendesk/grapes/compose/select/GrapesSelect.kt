package com.spendesk.grapes.compose.select

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author Kélian CLERC
 * @since 02/03/2023
 */
private const val RadiusFiftyPercent = 50

data class SelectEntry(
    val id: String,
    val label: String,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GrapesSelect(
    selectedEntry: SelectEntry?,
    entries: List<SelectEntry>,
    onItemSelected: (SelectEntry) -> Unit,
    placeHolder: String,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true
) {
    var isExpanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = { if (isEnabled) isExpanded = !isExpanded },
        modifier = modifier
    ) {
        Select(
            isExpanded = isExpanded,
            label = selectedEntry?.label ?: placeHolder,
            modifier = Modifier
                .menuAnchor()
                .animateContentSize(),
            isEnabled = isEnabled
        )

        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false },
            modifier = Modifier.background(GrapesTheme.colors.structureBackground)
        ) {
            entries.iterator().forEach {
                DropdownMenuItem(
                    text = { Text(it.label) },
                    onClick = {
                        onItemSelected(it)
                        isExpanded = false
                    }
                )
            }
        }
    }
}

@Composable
private fun Select(
    isExpanded: Boolean,
    label: String,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true
) {
    val radiusSize by animateIntAsState(
        targetValue = if (isExpanded) 0 else RadiusFiftyPercent,
        label = "Radius percentage animation",
    )

    val itemColor = if (isEnabled) {
        GrapesTheme.colors.structureSurface
    } else {
        GrapesTheme.colors.neutralLighter
    }

    val contentColor = if (isEnabled) {
        GrapesTheme.colors.structureComplementary
    } else {
        GrapesTheme.colors.neutralNormal
    }

    GrapesSelector(
        label = label,
        onClick = {},
        modifier = modifier,
        contentPadding = PaddingValues(
            horizontal = GrapesTheme.dimensions.spacing3,
            vertical = GrapesTheme.dimensions.paddingMedium,
        ),
        icon = {
            GrapesSelectIcon(
                expanded = isExpanded,
                tint = contentColor,
            )
        },
        colors = GrapesSelectorColors(
            backgroundColor = itemColor,
            contentColor = contentColor,
            borderColor = contentColor,
        ),
        shape = RoundedCornerShape(RadiusFiftyPercent, RadiusFiftyPercent, radiusSize, radiusSize),
    )
}

@Composable
private fun GrapesSelectIcon(expanded: Boolean, tint: Color) {
    val rotation by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f,
        label = "Rotation angle animation",
    )

    Icon(
        imageVector = Icons.Filled.ArrowDropDown,
        contentDescription = null,
        modifier = Modifier.rotate(rotation),
        tint = tint
    )
}

@Preview
@Composable
private fun GrapesSelectorPreview() {
    val values = List(4) {
        SelectEntry("$it", "Item $it")
    }
    var selectedValue by remember {
        mutableStateOf<SelectEntry?>(null)
    }

    GrapesTheme {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            GrapesSelect(selectedValue, values, onItemSelected = { selectedValue = it }, placeHolder = "Select Value")
            GrapesSelect(selectedValue, values, onItemSelected = { selectedValue = it }, placeHolder = "Select Value", isEnabled = false)
        }
    }
}
