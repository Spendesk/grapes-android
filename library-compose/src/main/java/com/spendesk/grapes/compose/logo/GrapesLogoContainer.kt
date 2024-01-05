package com.spendesk.grapes.compose.logo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author : RomainGF
 * @since : 03/01/2024
 **/

/**
 * Container for a logo with a maximum size of [GrapesTheme.dimensions.sizing5],
 * clipped in a [GrapesTheme.shapes.shape1]
 */
@Composable
fun GrapesMediumLogoContainer(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit = {},
) {
    Box(
        contentAlignment = Alignment.Center,
        content = content,
        modifier = modifier
            .clip(GrapesTheme.shapes.shape1)
            .size(GrapesTheme.dimensions.sizing5),
    )
}

/**
 * Container for a logo with a maximum size of [GrapesTheme.dimensions.sizing6],
 * clipped in a [GrapesTheme.shapes.shape2]
 */
@Composable
fun GrapesLargeLogoContainer(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit = {},
) {
    Box(
        contentAlignment = Alignment.Center,
        content = content,
        modifier = modifier
            .clip(GrapesTheme.shapes.shape2)
            .size(GrapesTheme.dimensions.sizing6),
    )
}

@Preview
@Composable
private fun GrapesMediumLogoContainerPreview() {
    GrapesTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            GrapesMediumLogoContainer {
                Box(
                    modifier = Modifier
                        .size(200.dp)
                        .background(Color.Red)
                )
            }
            GrapesLargeLogoContainer {
                Box(
                    modifier = Modifier
                        .size(200.dp)
                        .background(Color.Red)
                )
            }
        }
    }
}
