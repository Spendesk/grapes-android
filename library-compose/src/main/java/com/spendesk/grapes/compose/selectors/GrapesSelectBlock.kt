package com.spendesk.grapes.compose.selectors

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spendesk.grapes.compose.R
import com.spendesk.grapes.compose.bucket.GrapesBucketContainer
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author : dany
 * @since : 24/10/2023, Tue
 **/
@Immutable
object GrapesSelectBlockDefaultColors {

    val selectedBorderColor: Color @Composable get() = GrapesTheme.colors.mainPrimaryDark
    val selectedIconColor: Color @Composable get() = GrapesTheme.colors.mainPrimaryDark
    val unselectedIconColor: Color @Composable get() = GrapesTheme.colors.mainComplementary
}

@Composable
fun GrapesSelectBlock(
    @DrawableRes icon: Int,
    isSelected: Boolean,
    iconDescription: String? = null,
    modifier: Modifier = Modifier
) {
    val iconSize = 32.dp

    GrapesBucketContainer(
        modifier = modifier
            .size(104.dp)
            .fillMaxSize()
            .let { m ->
                if (isSelected) {
                    m.border(2.dp, GrapesSelectBlockDefaultColors.selectedBorderColor, GrapesTheme.shapes.small)
                } else {
                    m
                }
            }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .let { m -> if (isSelected) m.background(color = GrapesSelectableCardTextDefaultColors.selectedBackgroundColor) else m },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp)
                    .size(iconSize)
                    .fillMaxSize(),
                painter = painterResource(id = icon),
                contentDescription = iconDescription,
                tint = if (isSelected) GrapesSelectBlockDefaultColors.selectedIconColor else GrapesSelectBlockDefaultColors.unselectedIconColor
            )
        }
    }
}


@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun GrapesSelectBlockPreview() {
    GrapesTheme {
        Row {
            GrapesSelectBlock(
                icon = R.drawable.ic_information,
                isSelected = true
            )
            Spacer(modifier = Modifier.padding(10.dp))
            GrapesSelectBlock(
                icon = R.drawable.ic_information,
                isSelected = false
            )
        }
    }
}
