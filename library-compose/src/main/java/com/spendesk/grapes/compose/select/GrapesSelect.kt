package com.spendesk.grapes.compose.select

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author Kélian CLERC
 * @since 02/03/2023
 */
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
            modifier = Modifier.background(GrapesTheme.colors.mainBackground)
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Select(
    isExpanded: Boolean,
    label: String,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true
) {
    val radiusSize by animateIntAsState(targetValue = if (isExpanded) 0 else 50, label = "Radius percentage animation")

    val itemColor = if (isEnabled) GrapesTheme.colors.mainWhite else GrapesTheme.colors.mainNeutralLighter
    val contentColor = if (isEnabled) GrapesTheme.colors.mainComplementary else GrapesTheme.colors.mainNeutralDark

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(GrapesTheme.dimensions.paddingLarge),
        modifier = modifier
            .background(itemColor, shape = RoundedCornerShape(50, 50, radiusSize, radiusSize))
            .border(1.dp, GrapesTheme.colors.mainNeutralLight, shape = RoundedCornerShape(50, 50, radiusSize, radiusSize))
            .padding(horizontal = GrapesTheme.dimensions.paddingLarge, vertical = GrapesTheme.dimensions.paddingMedium)
    ) {
        Text(text = label, style = GrapesTheme.typography.titleS, color = contentColor)

        GrapesSelectIcon(expanded = isExpanded, tint = contentColor)
    }
}

@Composable
private fun GrapesSelectIcon(expanded: Boolean, tint: Color) {
    val rotation by animateFloatAsState(targetValue = if (expanded) 180f else 0f, label = "Rotation angle animation")

    Icon(
        Icons.Filled.ArrowDropDown,
        null,
        Modifier.rotate(rotation),
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
