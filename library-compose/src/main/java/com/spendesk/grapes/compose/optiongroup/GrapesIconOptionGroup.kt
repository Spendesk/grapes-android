package com.spendesk.grapes.compose.optiongroup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.spendesk.grapes.compose.R
import com.spendesk.grapes.compose.optiongroup.model.GrapesIconOptionGroupUiModel
import com.spendesk.grapes.compose.theme.GrapesTheme
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

/**
 * @author : RomainGF
 * @since : 30/08/2024
 **/
@Composable
fun GrapesIconOptionGroup(
    items: ImmutableList<GrapesIconOptionGroupUiModel>,
    onItemSelected: (GrapesIconOptionGroupUiModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(GrapesTheme.dimensions.spacing2),
        modifier = modifier,
    ) {
        items.forEach { item ->
            GrapesIconOptionGroupItem(
                isSelected = item.isSelected,
                imagePainter = painterResource(item.imageRes),
                onClick = { onItemSelected(item) },
                contentDescription = null,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Preview
@Composable
private fun PreviewGrapesIconOptionGroup() {
    GrapesTheme {
        GrapesIconOptionGroup(
            items = persistentListOf(
                GrapesIconOptionGroupUiModel(id = "", imageRes = R.drawable.ic_block, isSelected = true),
                GrapesIconOptionGroupUiModel(id = "", imageRes = R.drawable.ic_block, isSelected = false),
                GrapesIconOptionGroupUiModel(id = "", imageRes = R.drawable.ic_block, isSelected = false),
            ),
            onItemSelected = {},
            modifier = Modifier.fillMaxWidth()
        )
    }
}
