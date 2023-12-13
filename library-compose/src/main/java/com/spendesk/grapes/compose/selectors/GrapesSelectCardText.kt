package com.spendesk.grapes.compose.selectors

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author : dany
 * @since : 24/10/2023, Tue
 **/
@Immutable
object GrapesSelectCardTextDefaultColors {
    val selectedIconColor: Color @Composable get() = GrapesTheme.colors.mainPrimaryDark
    val selectedTitleColor: Color @Composable get() = GrapesTheme.colors.mainPrimaryDark
    val selectedDescriptionColor: Color @Composable get() = GrapesTheme.colors.mainPrimaryDark
    val unselectedIconColor: Color @Composable get() = GrapesTheme.colors.mainComplementary
    val unselectedTitleColor: Color @Composable get() = GrapesTheme.colors.mainComplementary
    val unselectedDescriptionColor: Color @Composable get() = GrapesTheme.colors.mainNeutralDarker
}

@Composable
fun GrapesSelectCardText(
    @DrawableRes icon: Int,
    isSelected: Boolean,
    title: String,
    description: String,
    iconDescription: String? = null,
    modifier: Modifier = Modifier
) {
    val iconSize = GrapesTheme.dimensions.iconLarge

    GrapesSelectBlockContainer(
        modifier = modifier,
        isSelected = isSelected,
        content = {
            Row(
                modifier = Modifier.padding(horizontal = GrapesTheme.dimensions.spacing3),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(iconSize),
                    painter = painterResource(id = icon),
                    contentDescription = iconDescription,
                    tint = if (isSelected) GrapesSelectCardTextDefaultColors.selectedIconColor else GrapesSelectCardTextDefaultColors.unselectedIconColor
                )
                Spacer(Modifier.size(GrapesTheme.dimensions.spacing3))
                Column(
                    modifier = Modifier
                        .padding(
                            top = GrapesTheme.dimensions.spacing4,
                            bottom = GrapesTheme.dimensions.spacing4
                        ),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        color = if (isSelected) GrapesSelectCardTextDefaultColors.selectedTitleColor else GrapesSelectCardTextDefaultColors.unselectedTitleColor,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        style = GrapesTheme.typography.titleL,
                        text = title
                    )
                    Spacer(modifier = Modifier.padding(GrapesTheme.dimensions.spacing1))
                    Text(
                        color = if (isSelected) GrapesSelectCardTextDefaultColors.selectedDescriptionColor else GrapesSelectCardTextDefaultColors.unselectedDescriptionColor,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 2,
                        style = GrapesTheme.typography.bodyS,
                        text = description
                    )
                }
            }
        }
    )
}


@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun GrapesSelectCardTextPreview() {
    GrapesTheme {
        Column {
            GrapesSelectCardText(
                icon = R.drawable.ic_information,
                true,
                "Title",
                "description  description  description  description  description  description  description  description  description  "
            )
            GrapesSelectCardText(
                icon = R.drawable.ic_information,
                false,
                "Title",
                "description  description  description  description  description  description  description  description  description  "
            )

            Spacer(modifier = Modifier.padding(10.dp))

            var isSelected = remember { mutableIntStateOf(0) }

            LazyColumn(
                modifier = Modifier.padding(horizontal = GrapesTheme.dimensions.spacing3),
                verticalArrangement = Arrangement.spacedBy(GrapesTheme.dimensions.spacing3)
            ) {
                item {
                    GrapesSelectCardText(
                        modifier = Modifier.clickable { isSelected.intValue = 0 },
                        title = "Title",
                        description = "This is the right description",
                        icon = R.drawable.ic_add,
                        isSelected = isSelected.intValue == 0
                    )
                }
                item {
                    GrapesSelectCardText(
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
