package com.spendesk.grapes.compose.icons

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.spendesk.grapes.compose.R
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author : RomainGF
 * @since : 03/01/2024
 **/
@Composable
fun GrapesHighlightIconPrimary(
    modifier: Modifier = Modifier,
    painter: Painter = painterResource(R.drawable.ic_block),
    contentDescription: String? = null,
    size: GrapesHighlightIconSize,
) {
    GrapesHighlightIcon(
        painter = painter,
        contentDescription = contentDescription,
        tint = GrapesTheme.colors.primaryNormal,
        containerColor = GrapesTheme.colors.primaryLightest,
        borderColor = GrapesTheme.colors.primaryLighter,
        size = size,
        modifier = modifier,
    )
}

@Composable
fun GrapesHighlightIconAlert(
    size: GrapesHighlightIconSize,
    modifier: Modifier = Modifier,
    painter: Painter = painterResource(R.drawable.ic_cross_filled),
    contentDescription: String? = null,
) {
    GrapesHighlightIcon(
        painter = painter,
        contentDescription = contentDescription,
        tint = GrapesTheme.colors.alertNormal,
        containerColor = GrapesTheme.colors.alertLightest,
        borderColor = GrapesTheme.colors.alertLighter,
        size = size,
        modifier = modifier,
    )
}

@Composable
fun GrapesHighlightIconWarning(
    size: GrapesHighlightIconSize,
    modifier: Modifier = Modifier,
    painter: Painter = painterResource(R.drawable.ic_warning),
    contentDescription: String? = null,
) {
    GrapesHighlightIcon(
        painter = painter,
        contentDescription = contentDescription,
        tint = GrapesTheme.colors.warningNormal,
        containerColor = GrapesTheme.colors.warningLightest,
        borderColor = GrapesTheme.colors.warningLighter,
        size = size,
        modifier = modifier,
    )
}

@Composable
fun GrapesHighlightIconSuccess(
    size: GrapesHighlightIconSize,
    modifier: Modifier = Modifier,
    painter: Painter = painterResource(R.drawable.ic_success),
    contentDescription: String? = null,
) {
    GrapesHighlightIcon(
        painter = painter,
        contentDescription = contentDescription,
        tint = GrapesTheme.colors.successNormal,
        containerColor = GrapesTheme.colors.successLightest,
        borderColor = GrapesTheme.colors.successLighter,
        size = size,
        modifier = modifier,
    )
}

@Composable
fun GrapesHighlightIconNeutral(
    size: GrapesHighlightIconSize,
    modifier: Modifier = Modifier,
    painter: Painter = painterResource(R.drawable.ic_block),
    contentDescription: String? = null,
) {
    GrapesHighlightIcon(
        painter = painter,
        contentDescription = contentDescription,
        tint = GrapesTheme.colors.neutralDark,
        containerColor = GrapesTheme.colors.neutralLightest,
        borderColor = GrapesTheme.colors.neutralLighter,
        size = size,
        modifier = modifier,
    )
}

@Composable
fun GrapesHighlightIconInfo(
    size: GrapesHighlightIconSize,
    modifier: Modifier = Modifier,
    painter: Painter = painterResource(R.drawable.ic_information),
    contentDescription: String? = null,
) {
    GrapesHighlightIcon(
        painter = painter,
        contentDescription = contentDescription,
        tint = GrapesTheme.colors.infoNormal,
        containerColor = GrapesTheme.colors.infoLightest,
        borderColor = GrapesTheme.colors.infoLighter,
        size = size,
        modifier = modifier,
    )
}

@Composable
private fun GrapesHighlightIcon(
    painter: Painter,
    contentDescription: String?,
    tint: Color,
    containerColor: Color,
    borderColor: Color,
    size: GrapesHighlightIconSize,
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(size.getContainerSize())
            .clip(GrapesTheme.shapes.shape4)
            .background(containerColor)
            .border(1.dp, borderColor, GrapesTheme.shapes.shape4)
    ) {
        Icon(
            painter = painter,
            contentDescription = contentDescription,
            tint = tint,
            modifier = Modifier.size(size.getIconSize()),
        )
    }
}

enum class GrapesHighlightIconSize {
    SMALL,
    MEDIUM,
    LARGE;

    @Composable
    fun getContainerSize(): Dp = when (this) {
        SMALL -> GrapesTheme.dimensions.sizing4
        MEDIUM -> GrapesTheme.dimensions.sizing5
        LARGE -> GrapesTheme.dimensions.sizing7
    }

    @Composable
    fun getIconSize(): Dp = when (this) {
        SMALL -> GrapesTheme.dimensions.sizing1
        MEDIUM -> GrapesTheme.dimensions.sizing2
        LARGE -> GrapesTheme.dimensions.sizing4
    }
}

@Preview
@Composable
private fun GrapesHighlightIconPreview(
    @PreviewParameter(SizeParameterProvider::class) size: GrapesHighlightIconSize,
) {
    GrapesTheme {
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            GrapesHighlightIconPrimary(
                size = size,
            )
            GrapesHighlightIconAlert(
                size = size,
            )
            GrapesHighlightIconInfo(
                size = size,
            )
            GrapesHighlightIconWarning(
                size = size,
            )
            GrapesHighlightIconSuccess(
                size = size,
            )
            GrapesHighlightIconNeutral(
                size = size,
            )
        }
    }
}

private class SizeParameterProvider : PreviewParameterProvider<GrapesHighlightIconSize> {
    override val values = GrapesHighlightIconSize.entries.asSequence()
}
