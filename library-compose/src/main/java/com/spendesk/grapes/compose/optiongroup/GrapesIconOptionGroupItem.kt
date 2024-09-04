package com.spendesk.grapes.compose.optiongroup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spendesk.grapes.compose.R
import com.spendesk.grapes.compose.optiongroup.internal.GrapesOptionGroupItem
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author : RomainGF
 * @since : 30/08/2024
 **/
private val minHeight = 96.dp
private val minWidth = 96.dp

@Composable
fun GrapesIconOptionGroupItem(
    isSelected: Boolean,
    imagePainter: Painter,
    onClick: () -> Unit,
    contentDescription: String?,
    modifier: Modifier = Modifier,
) {
    GrapesOptionGroupItem(
        isSelected = isSelected,
        onClick = onClick,
        contentDescription = contentDescription,
        modifier = modifier.sizeIn(minWidth = minWidth, minHeight = minHeight)
    ) {
        Icon(
            painter = imagePainter,
            contentDescription = null,
            modifier = Modifier
                .size(GrapesTheme.dimensions.sizing5)
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun PreviewGrapesIconOptionGroupItem() {
    GrapesTheme {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            GrapesIconOptionGroupItem(
                isSelected = false,
                imagePainter = painterResource(R.drawable.ic_block),
                onClick = {},
                contentDescription = null,
            )
            GrapesIconOptionGroupItem(
                isSelected = true,
                imagePainter = painterResource(R.drawable.ic_block),
                onClick = {},
                contentDescription = null,
            )
        }
    }
}
