package com.spendesk.grapes.compose.logo

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author : dany
 * @since : 30/01/2024, Tue
 **/

private const val GrapesStackSurfaceMaxLines = 1

@Composable
fun GrapesStackLogo(
    numberOfStack: Int,
    modifier: Modifier = Modifier,
) {
    GrapesLargeLogoContainer(modifier = modifier) {
        GrapesStackSurface(modifier = modifier.align(if (numberOfStack > 1) Alignment.TopStart else Alignment.Center), text = numberOfStack.toString())

        if (numberOfStack > 1) {
            val highlightIconOffset = GrapesTheme.dimensions.spacing1

            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .size(GrapesTheme.dimensions.grapesStackSurface)
                    .offset(x = highlightIconOffset, y = highlightIconOffset),
            ) {
                GrapesStackSurface(text = numberOfStack.toString())
            }
        }
    }
}

@Composable
fun GrapesStackSurface(
    modifier: Modifier = Modifier,
    text: String? = null
) {
    GrapesLargeLogoContainer(
        modifier = modifier
            .size(GrapesTheme.dimensions.grapesStackSurface)
            .border(1.dp, color = GrapesTheme.colors.neutralLighter, shape = GrapesTheme.shapes.shape2)
            .clip(GrapesTheme.shapes.shape2)
            .background(GrapesTheme.colors.neutralLightest)
    ) {
        text?.let {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = text,
                style = GrapesTheme.typography.titleM,
                color = GrapesTheme.colors.neutralDark,
                textAlign = TextAlign.Center,
                maxLines = GrapesStackSurfaceMaxLines,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Preview
@Composable
private fun GrapesStackLogoPreview() {
    GrapesTheme {
        Column(verticalArrangement = Arrangement.spacedBy(GrapesTheme.dimensions.spacing1)) {
            GrapesStackSurface(text = "2")
            GrapesStackSurface(text = "24")
            GrapesStackSurface(text = "24343")
            Spacer(modifier = Modifier.size(GrapesTheme.dimensions.spacing3))
            GrapesStackLogo(numberOfStack = 5)
            GrapesStackLogo(numberOfStack = 1)
        }
    }
}
