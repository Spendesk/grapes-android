package com.spendesk.grapes.compose.optiongroup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spendesk.grapes.compose.optiongroup.model.GrapesTextOptionGroupUiModel
import com.spendesk.grapes.compose.theme.GrapesTheme
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList

/**
 * @author : RomainGF
 * @since : 30/08/2024
 **/
@Composable
fun GrapesTextOptionGroup(
    items: ImmutableList<GrapesTextOptionGroupUiModel>,
    onItemSelected: (GrapesTextOptionGroupUiModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    val shape = GrapesTheme.shapes.shape2
    Box(
        modifier = modifier
            .width(IntrinsicSize.Max)
            .height(IntrinsicSize.Max)
            .shadow(2.dp, shape)
            .background(GrapesTheme.colors.structureSurface)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
        ) {
            items.forEachIndexed { index, item ->
                if (item.isSelected) {
                    GrapesTextOptionGroupItem(
                        text = item.text,
                        isSelected = true,
                        onClick = { /* item already selected, do nothing */ },
                        modifier = Modifier
                            .weight(1f)
                    )
                } else {
                    TextButton(
                        onClick = { onItemSelected(item) },
                        shape = GrapesTheme.shapes.shape2,
                        modifier = modifier
                            .fillMaxHeight()
                            .weight(1f)
                    ) {
                        Text(
                            text = item.text,
                            color = GrapesTheme.colors.neutralDark,
                            style = GrapesTheme.typography.bodyM,
                        )
                    }
                    if (index < items.lastIndex && !items[index + 1].isSelected) {
                        Box(
                            modifier = Modifier
                                .width(1.dp)
                                .height(16.dp)
                                .background(GrapesTheme.colors.neutralLighter)
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun PreviewGrapesTextOptionGroup() {
    GrapesTheme {
        var items by remember {
            mutableStateOf(
                persistentListOf(
                    GrapesTextOptionGroupUiModel("", "< 50cc", false),
                    GrapesTextOptionGroupUiModel("", "1-2cv", false),
                    GrapesTextOptionGroupUiModel("", "3-5cv", true),
                    GrapesTextOptionGroupUiModel("", "> 5cv", false)
                )
            )
        }
        Box(modifier = Modifier.padding(16.dp)) {
            GrapesTextOptionGroup(
                items = items,
                onItemSelected = {
                    items = items.map { item ->
                        item.copy(isSelected = item == it)
                    }.toPersistentList()
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
