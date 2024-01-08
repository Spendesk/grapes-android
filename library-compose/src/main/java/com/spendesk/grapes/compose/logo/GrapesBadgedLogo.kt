package com.spendesk.grapes.compose.logo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.spendesk.grapes.compose.icons.GrapesHighlightIconSize
import com.spendesk.grapes.compose.icons.GrapesHighlightIconSuccess
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @param badge the icon to display as a badge. Maximum size is [GrapesTheme.dimensions.iconSize3]
 * @param badgeAlignment the alignment of the badge. Only TopEnd and BottomEnd are supported.
 * @param logo the logo to display, wrapped in a [GrapesLargeLogoContainer]
 * @param modifier the modifier to apply
 *
 * @author : RomainGF
 * @since : 03/01/2024
 **/
private val supportedAlignments = arrayOf(Alignment.TopEnd, Alignment.BottomEnd)

@Composable
fun GrapesBadgedLogo(
    modifier: Modifier = Modifier,
    badge: @Composable BoxScope.() -> Unit = {},
    badgeAlignment: Alignment = Alignment.TopEnd,
    logo: @Composable BoxScope.() -> Unit,
) {
    Box(modifier) {
        GrapesLargeLogoContainer {
            logo()
        }
        if (badgeAlignment in supportedAlignments) {
            val highlightIconOffset = GrapesTheme.dimensions.spacing2
            val yOffset = when (badgeAlignment) {
                Alignment.TopEnd -> -highlightIconOffset
                Alignment.BottomEnd -> highlightIconOffset
                else -> 0.dp
            }
            Box(
                modifier = Modifier
                    .align(badgeAlignment)
                    .size(GrapesTheme.dimensions.sizing4)
                    .offset(x = highlightIconOffset, y = yOffset),
            ) {
                badge()
            }
        }
    }
}

@Preview
@Composable
private fun GrapesBadgedLogoPreview(
    @PreviewParameter(AlignmentParameterProvider::class) alignment: Alignment,
) {
    GrapesTheme {
        GrapesBadgedLogo(
            badge = {
                GrapesHighlightIconSuccess(
                    size = GrapesHighlightIconSize.SMALL,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(GrapesTheme.shapes.shape4)
                        .background(GrapesTheme.colors.warningNormal)
                )
            },
            badgeAlignment = alignment,
            modifier = Modifier.padding(16.dp),
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(GrapesTheme.shapes.shape2)
                    .background(GrapesTheme.colors.neutralLight)
            )
        }
    }
}

private class AlignmentParameterProvider : PreviewParameterProvider<Alignment> {
    override val values = sequenceOf(Alignment.TopEnd, Alignment.BottomEnd)
}
