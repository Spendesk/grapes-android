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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
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
    GrapesAmountListItem(
        title = title,
        subtitle = subtitle,
        amount = AnnotatedString(amount),
        description = description,
        logo = logo,
        modifier = modifier,
        onClick = onClick,
        colors = colors,
    )
}

/**
 * Component to display an item with a logo, a title, a subtitle, an amount and a description.
 * The amount is an [AnnotatedString] to allow different styles for the amount.
 */
@Composable
fun GrapesAmountListItem(
    title: String,
    subtitle: String,
    amount: AnnotatedString,
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
        verticalAlignment = Alignment.CenterVertically,
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
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                )
                Text(
                    text = amount,
                    style = GrapesTheme.typography.titleL,
                    color = colors.amountColor,
                    maxLines = 1,
                )
            }
            Row {
                Text(
                    text = subtitle,
                    style = GrapesTheme.typography.bodyM,
                    color = colors.subtitleColor,
                    modifier = Modifier.weight(1f),
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
@Preview(showBackground = true, fontScale = 1.5f)
private fun PreviewGrapesAmountListItemSmallAmount() {
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
            logo = { PreviewLogo() },
        )
    }
}

@Composable
@Preview(showBackground = true)
@Preview(showBackground = true, fontScale = 1.5f)
private fun PreviewGrapesAmountListItemTextOverflow() {
    GrapesTheme {
        GrapesAmountListItem(
            title = "Some very long title that will overflow the space available",
            subtitle = "Some very long subtitle that will overflow the space available",
            amount = "2 000 000.00€",
            description = "Some very long description",
            colors = GrapesAmountListItemDefaults.colors(
                descriptionColor = GrapesTheme.colors.successNormal,
            ),
            onClick = {},
            logo = { PreviewLogo() },
        )
    }
}

@Composable
@Preview(showBackground = true)
@Preview(showBackground = true, fontScale = 1.5f)
private fun PreviewGrapesAmountListItemAnnotatedAmount() {
    GrapesTheme {
        GrapesAmountListItem(
            title = "Title",
            subtitle = "Subtitle",
            amount = buildAnnotatedString {
                withStyle(
                    GrapesTheme.typography.bodyL.toSpanStyle()
                        .copy(color = GrapesTheme.colors.neutralDark)
                ) {
                    append("100€ • ")
                }
                withStyle(GrapesTheme.typography.titleL.toSpanStyle()) {
                    append("100€")
                }
            },
            description = "description",
            colors = GrapesAmountListItemDefaults.colors(
                descriptionColor = GrapesTheme.colors.successNormal,
            ),
            onClick = {},
            logo = { PreviewLogo() },
        )
    }
}

@Composable
private fun PreviewLogo() {
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
}
