package com.spendesk.grapes.compose.listitem

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.spendesk.grapes.compose.R
import com.spendesk.grapes.compose.logo.GrapesStackLogo
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author : dany
 * @since : 30/01/2024, Tue
 **/

@Immutable
data class GrapesStackItemColors internal constructor(
    val titleColor: Color,
    val descriptionColor: Color,
    val backgroundColor: Color
)

object GrapesStackItemDefaults {

    @Composable
    fun colors(
        titleColor: Color = GrapesTheme.colors.neutralDarker,
        descriptionColor: Color = GrapesTheme.colors.neutralDarker,
        backgroundColor: Color = GrapesTheme.colors.structureBackground
    ): GrapesStackItemColors = GrapesStackItemColors(
        titleColor = titleColor,
        descriptionColor = descriptionColor,
        backgroundColor = backgroundColor
    )
}

@Composable
fun GrapesStackItem(
    title: String,
    description: String,
    itemsNumber: Int,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    colors: GrapesStackItemColors = GrapesStackItemDefaults.colors()
) {
    val clickableModifier = if (onClick != null) {
        Modifier.clickable(role = Role.Button, onClick = onClick)
    } else {
        Modifier
    }

    Row(
        horizontalArrangement = Arrangement.spacedBy(GrapesTheme.dimensions.spacing3),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clip(GrapesTheme.shapes.shape2)
            .then(clickableModifier)
            .background(colors.backgroundColor)
            .padding(
                vertical = GrapesTheme.dimensions.spacing2,
                horizontal = GrapesTheme.dimensions.spacing2,
            ),
    ) {
        GrapesStackLogo(numberOfStack = itemsNumber)
        Row(modifier = Modifier.weight(1f)) {
            Column {
                Text(
                    text = title,
                    style = GrapesTheme.typography.titleM,
                    color = colors.titleColor,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                )
                Text(
                    text = description,
                    style = GrapesTheme.typography.bodyM,
                    color = colors.descriptionColor,
                    maxLines = 1,
                )
            }
        }
        Row {
            Icon(
                painter = painterResource(R.drawable.ic_chevron_right),
                contentDescription = null,
                tint = GrapesTheme.colors.neutralLight,
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun GrapesStackItemPreview() {
    GrapesTheme {
        Column(modifier = Modifier.background(Color.White), verticalArrangement = Arrangement.spacedBy(GrapesTheme.dimensions.spacing1)) {
            Spacer(modifier = Modifier.size(GrapesTheme.dimensions.sizing2))
            GrapesStackItem(
                title = "Awaiting Reimbursement",
                description = "Total: 1000€",
                itemsNumber = 2,
                onClick = {}
            )
            Spacer(modifier = Modifier.size(GrapesTheme.dimensions.sizing2))
        }
    }
}
