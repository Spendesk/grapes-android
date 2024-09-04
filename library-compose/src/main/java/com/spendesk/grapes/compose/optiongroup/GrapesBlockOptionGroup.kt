package com.spendesk.grapes.compose.optiongroup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.spendesk.grapes.compose.R
import com.spendesk.grapes.compose.optiongroup.model.GrapesBlockOptionGroupUiModel
import com.spendesk.grapes.compose.theme.GrapesTheme
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

/**
 * @author : RomainGF
 * @since : 30/08/2024
 **/
@Composable
fun GrapesBlockOptionGroup(
    items: ImmutableList<GrapesBlockOptionGroupUiModel>,
    onItemSelected: (GrapesBlockOptionGroupUiModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(GrapesTheme.dimensions.spacing2),
        modifier = modifier.width(IntrinsicSize.Max),
    ) {
        items.forEach { item ->
            GrapesBlockOptionGroupItem(
                model = item,
                onClick = { onItemSelected(item) },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview
@Composable
private fun PreviewGrapesBlockOptionGroupItem() {
    GrapesTheme {
        GrapesBlockOptionGroup(
            items = persistentListOf(
                GrapesBlockOptionGroupUiModel(
                    id = "",
                    imageRes = R.drawable.ic_block,
                    title = "A short title",
                    description = "Some description here to fill the space",
                    isSelected = false,
                ),
                GrapesBlockOptionGroupUiModel(
                    id = "",
                    imageRes = R.drawable.ic_block,
                    title = "A short title",
                    description = "Some description here to fill the space",
                    isSelected = true,
                ),
            ),
            onItemSelected = {},
            modifier = Modifier.fillMaxWidth()
        )
    }
}
