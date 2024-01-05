package com.spendesk.grapes.compose.listitem

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spendesk.grapes.compose.icons.GrapesHighlightIconSize
import com.spendesk.grapes.compose.icons.GrapesHighlightIconSuccess
import com.spendesk.grapes.compose.logo.GrapesBadgedLogo
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * Component to display an item with a logo, a title, a subtitle, an amount and a description.
 *
 * @author : RomainGF
 * @since : 03/01/2024
 **/
@Composable
fun GrapesAmountListItem(
    title: String,
    subtitle: String,
    amount: String,
    description: String,
    logo: @Composable BoxScope.() -> Unit,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    colors: GrapesAmountListItemColors = GrapesAmountListItemDefaults.colors(),
) {
    val clickableModifier = if (onClick != null) {
        Modifier.clickable(role = Role.Button, onClick = onClick)
    } else {
        Modifier
    }

    Row(
        horizontalArrangement = Arrangement.spacedBy(GrapesTheme.dimensions.spacing3),
        modifier = modifier
            .clip(GrapesTheme.shapes.shape2)
            .then(clickableModifier)
            .padding(
                vertical = GrapesTheme.dimensions.spacing3,
                horizontal = GrapesTheme.dimensions.spacing2,
            ),
    ) {
        Box(
            content = logo,
            modifier = Modifier.sizeIn(
                maxWidth = GrapesTheme.dimensions.sizing6,
                maxHeight = GrapesTheme.dimensions.sizing6,
            ),
        )
        Column(
            modifier = Modifier.weight(1f),
        ) {
            Row {
                Text(
                    text = title,
                    style = GrapesTheme.typography.titleL,
                    color = colors.titleColor,
                    modifier = Modifier.weight(1f),
                )
                Text(
                    text = amount,
                    style = GrapesTheme.typography.titleL,
                    color = colors.amountColor,
                )
            }
            Row {
                Text(
                    text = subtitle,
                    style = GrapesTheme.typography.bodyM,
                    color = colors.subtitleColor,
                    modifier = Modifier.weight(1f),
                )
                Text(
                    text = description,
                    style = GrapesTheme.typography.bodyM,
                    color = colors.descriptionColor,
                )
            }
        }
    }
}

@Immutable
data class GrapesAmountListItemColors internal constructor(
    val titleColor: Color,
    val subtitleColor: Color,
    val amountColor: Color,
    val descriptionColor: Color,
)

object GrapesAmountListItemDefaults {

    @Composable
    fun colors(
        titleColor: Color = GrapesTheme.colors.structureComplementary,
        subtitleColor: Color = GrapesTheme.colors.neutralDark,
        amountColor: Color = GrapesTheme.colors.structureComplementary,
        descriptionColor: Color = GrapesTheme.colors.neutralDark,
    ): GrapesAmountListItemColors = GrapesAmountListItemColors(
        titleColor = titleColor,
        subtitleColor = subtitleColor,
        amountColor = amountColor,
        descriptionColor = descriptionColor,
    )
}

@Composable
@Preview(showBackground = true)
private fun GrapesAmountListItemPreview() {
    GrapesTheme {
        GrapesAmountListItem(
            title = "Title",
            subtitle = "Subtitle",
            amount = "200€",
            description = "description",
            colors = GrapesAmountListItemDefaults.colors(
                descriptionColor = GrapesTheme.colors.successNormal,
            ),
            onClick = {},
            logo = {
                GrapesBadgedLogo(
                    badge = {
                        GrapesHighlightIconSuccess(
                            size = GrapesHighlightIconSize.SMALL,
                        )
                    },
                ) {
                    Box(
                        modifier = Modifier
                            .size(64.dp)
                            .background(Color.Red)
                    )
                }
            },
        )
    }
}
