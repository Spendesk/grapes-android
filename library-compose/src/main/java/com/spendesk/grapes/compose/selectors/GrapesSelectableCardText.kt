package com.spendesk.grapes.compose.selectors

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spendesk.grapes.compose.R
import com.spendesk.grapes.compose.bucket.GrapesBucketContainer
import com.spendesk.grapes.compose.selectors.GrapesSelectableCardTextDefaultColors.selectedBorderColor
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author : dany
 * @since : 24/10/2023, Tue
 **/
@Immutable
object GrapesSelectableCardTextDefaultColors {

    val selectedBackgroundColor: Color @Composable get() = GrapesTheme.colors.mainPrimaryLightest
    val selectedBorderColor: Color @Composable get() = GrapesTheme.colors.mainPrimaryDark
    val selectedIconColor: Color @Composable get() = GrapesTheme.colors.mainPrimaryDark
    val selectedTitleColor: Color @Composable get() = GrapesTheme.colors.mainPrimaryDark
    val selectedDescriptionColor: Color @Composable get() = GrapesTheme.colors.mainPrimaryDark
    val unselectedIconColor: Color @Composable get() = GrapesTheme.colors.mainComplementary
    val unselectedTitleColor: Color @Composable get() = GrapesTheme.colors.mainComplementary
    val unselectedDescriptionColor: Color @Composable get() = GrapesTheme.colors.mainNeutralDarker
}

@Composable
fun GrapesSelectableCardText(
    @DrawableRes icon: Int,
    isSelected: Boolean,
    title: String,
    description: String,
    iconDescription: String? = null,
    modifier: Modifier = Modifier
) {
    val iconSize = GrapesTheme.dimensions.iconLarge

    GrapesBucketContainer(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .let { m ->
                if (isSelected) {
                    m.border(GrapesTheme.dimensions.borderLarge, selectedBorderColor, GrapesTheme.shapes.small)
                } else {
                    m
                }
            }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().let { m -> if (isSelected) m.background(color = GrapesSelectableCardTextDefaultColors.selectedBackgroundColor) else m },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .padding(start = GrapesTheme.dimensions.paddingLarge, end = GrapesTheme.dimensions.paddingLarge)
                    .size(iconSize),
                painter = painterResource(id = icon),
                contentDescription = iconDescription,
                tint = if (isSelected) GrapesSelectableCardTextDefaultColors.selectedIconColor else GrapesSelectableCardTextDefaultColors.unselectedIconColor
            )
            Column(
                modifier = Modifier
                    .padding(top = GrapesTheme.dimensions.paddingSmall, bottom = GrapesTheme.dimensions.paddingSmall, end = GrapesTheme.dimensions.paddingLarge),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    color = if (isSelected) GrapesSelectableCardTextDefaultColors.selectedTitleColor else GrapesSelectableCardTextDefaultColors.unselectedTitleColor,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    style = GrapesTheme.typography.titleM,
                    text = title
                )
                Spacer(modifier = Modifier.padding(GrapesTheme.dimensions.paddingXSmall))
                Text(
                    color = if (isSelected) GrapesSelectableCardTextDefaultColors.selectedDescriptionColor else GrapesSelectableCardTextDefaultColors.unselectedDescriptionColor,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2,
                    style = GrapesTheme.typography.bodyS,
                    text = description
                )
            }
        }
    }
}


@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun GrapesSelectableCardTextPreview() {
    GrapesTheme {
        Column {
            GrapesSelectableCardText(
                icon = R.drawable.ic_information,
                true,
                "Title",
                "description  description  description  description  description  description  description  description  description  "
            )
            GrapesSelectableCardText(
                icon = R.drawable.ic_information,
                false,
                "Title",
                "description  description  description  description  description  description  description  description  description  "
            )

            Spacer(modifier = Modifier.padding(10.dp))

            var isSelected = remember { mutableIntStateOf(0) }

            LazyColumn(
                modifier = Modifier.padding(horizontal = GrapesTheme.dimensions.paddingLarge),
                verticalArrangement = Arrangement.spacedBy(GrapesTheme.dimensions.paddingLarge)
            ) {
                item {
                    GrapesSelectableCardText(
                        modifier = Modifier.clickable { isSelected.intValue = 0 },
                        title = "Title",
                        description = "This is the right description",
                        icon = R.drawable.ic_add,
                        isSelected = isSelected.intValue == 0
                    )
                }
                item {
                    GrapesSelectableCardText(
                        modifier = Modifier.clickable { isSelected.intValue = 1 },
                        title = "Title 2",
                        description = "Second description",
                        icon = R.drawable.ic_add,
                        isSelected = isSelected.intValue == 1
                    )
                }
            }
        }
    }
}
