package com.spendesk.grapes.compose.actionmenu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spendesk.grapes.compose.R
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author : RomainGF
 * @since : 09/10/2023
 **/

private val iconMaxSize = 56.dp
private val sectionShape = RoundedCornerShape(16.dp)
private const val TITLE_MAX_LINES = 2
private const val DESCRIPTION_MAX_LINES = 3

@Composable
fun ActionMenuSection(
    title: String,
    description: String,
    illustration: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = GrapesTheme.colors.structureSurface,
        ),
        shape = sectionShape,
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(GrapesTheme.dimensions.paddingSmall),
            modifier = Modifier.padding(GrapesTheme.dimensions.paddingSmall),
        ) {
            ActionMenuSectionHeader(
                title = title,
                description = description,
                illustration = illustration,
                modifier = Modifier.padding(
                    top = GrapesTheme.dimensions.paddingSmall,
                    end = GrapesTheme.dimensions.paddingSmall,
                )
            )
            content()
        }
    }
}

@Composable
private fun ActionMenuSectionHeader(
    title: String,
    description: String,
    illustration: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(GrapesTheme.dimensions.paddingSmall),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
    ) {
        Box(
            content = { illustration() },
            modifier = Modifier.sizeIn(
                maxWidth = iconMaxSize,
                maxHeight = iconMaxSize,
            ),
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(GrapesTheme.dimensions.paddingXSmall),
        ) {
            Text(
                text = title,
                style = GrapesTheme.typography.titleM,
                color = GrapesTheme.colors.structureComplementary,
                maxLines = TITLE_MAX_LINES,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = description,
                style = GrapesTheme.typography.bodyS,
                color = GrapesTheme.colors.neutralDark,
                maxLines = DESCRIPTION_MAX_LINES,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Preview
@Preview(fontScale = 2f)
@Composable
private fun ActionMenuSectionPreview() {
    GrapesTheme {
        ActionMenuSection(
            title = "Make a purchase request",
            description = "Order a virtual card to directly use company money online",
            illustration = {
                Icon(painterResource(R.drawable.ic_neutral), null, Modifier.fillMaxSize())
            },
            modifier = Modifier.fillMaxWidth(),
        ) {
            ActionMenuItem(
                text = "Ask for a virtual card",
                icon = { Icon(painterResource(R.drawable.ic_neutral), null) },
                onClick = {},
            )
            ActionMenuItem(
                text = "Ask for a virtual card",
                icon = { Icon(painterResource(R.drawable.ic_neutral), null) },
                onClick = {},
            )
        }
    }
}
