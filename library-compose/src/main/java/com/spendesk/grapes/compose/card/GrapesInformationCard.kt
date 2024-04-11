package com.spendesk.grapes.compose.card

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.spendesk.grapes.compose.listitem.GrapesDivider
import com.spendesk.grapes.compose.listitem.GrapesInlineInformationItem
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author RomainGF
 * @since 17/08/2023
 */
@Composable
fun GrapesInformationCard(
    title: String,
    modifier: Modifier = Modifier,
    colors: CardColors = GrapesInformationCardDefaults.colors,
    border: BorderStroke = GrapesInformationCardDefaults.border,
    contentVerticalArrangement: Arrangement.Vertical = GrapesInformationCardDefaults.contentVerticalArrangement,
    content: @Composable ColumnScope.() -> Unit = {},
) {
    Card(
        modifier = modifier,
        colors = colors,
        border = border,
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(GrapesTheme.dimensions.spacing3),
            modifier = Modifier.padding(vertical = GrapesTheme.dimensions.spacing3),
        ) {
            Text(
                text = title,
                style = GrapesTheme.typography.titleM,
                modifier = Modifier.padding(horizontal = GrapesTheme.dimensions.spacing3),
            )
            GrapesDivider()
            Column(
                verticalArrangement = contentVerticalArrangement,
                content = content,
                modifier = Modifier.padding(horizontal = GrapesTheme.dimensions.spacing3),
            )
        }
    }
}

object GrapesInformationCardDefaults {

    private val borderThickness = 0.5.dp

    val colors: CardColors
        @Composable get() = CardDefaults.elevatedCardColors(
            containerColor = GrapesTheme.colors.mainWhite,
        )

    val border: BorderStroke
        @Composable get() = BorderStroke(
            width = borderThickness,
            color = GrapesTheme.colors.neutralLight,
        )

    val contentVerticalArrangement: Arrangement.HorizontalOrVertical
        @Composable get() = Arrangement.spacedBy(GrapesTheme.dimensions.spacing3)
}

@Preview
@Composable
private fun PreviewDescription(
    @PreviewParameter(DescriptionParameterProvider::class) texts: Pair<String, String>,
) {
    GrapesTheme {
        Surface(
            color = GrapesTheme.colors.structureBackground,
        ) {
            GrapesInformationCard(
                title = texts.first,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            ) {
                Text(texts.second)
            }
        }
    }
}

private class DescriptionParameterProvider : PreviewParameterProvider<Pair<String, String>> {

    override val values = sequenceOf(
        "This is a simple example of a very very very long title" to "Lorem Ipsum is simply dummy text.",
        "Short title" to "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s.",
        "This is a simple example of a very very very long title" to "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s.",
    )
}

@Preview
@Composable
private fun PreviewDescriptionItems() {
    GrapesTheme {
        Surface(
            color = GrapesTheme.colors.structureBackground,
        ) {
            GrapesInformationCard(
                title = "Description",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            ) {
                GrapesInlineInformationItem(
                    title = "Subscription owner",
                    value = "Ben Hintz",
                )
                GrapesInlineInformationItem(
                    title = "Subscription owner",
                    value = "Ben Hintz",
                )
            }
        }
    }
}
