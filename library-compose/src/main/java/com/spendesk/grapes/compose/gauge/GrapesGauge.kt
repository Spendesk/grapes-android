package com.spendesk.grapes.compose.gauge

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author : dany
 * @since : 26/10/2023, Thu
 **/
@Composable
fun GrapesGauges(
    backgroundColor: Color,
    content: @Composable (() -> Unit),
    modifier: Modifier = Modifier,
    clipShape: Shape = RoundedCornerShape(GrapesTheme.dimensions.borderRadiusNormal),
) {
    Box(
        modifier = modifier
            .clip(clipShape)
            .background(backgroundColor)
            .fillMaxWidth()
            .height(GrapesTheme.dimensions.gaugeHeight)
    ) {
        content()
    }
}

@Composable
fun GrapesGauge(
    progress: Float,
    color: Color,
    modifier: Modifier = Modifier,
    addDelimiter: Boolean = true
) {
    Row {
        Box(
            modifier = modifier
                .background(color)
                .fillMaxHeight()
                .fillMaxWidth(progress)
        )
        if (addDelimiter) {
            GrapesGaugeDelimiter()
        }
    }
}

@Composable
fun GrapesGaugeStripped(
    progress: Float,
    stripeColor: Color,
    stripeColorSecondary: Color,
    backgroundColor: Color,
    modifier: Modifier = Modifier,
    clipShape: Shape = RoundedCornerShape(GrapesTheme.dimensions.borderRadiusSmall),
    addDelimiter: Boolean = true
) {
    Row {
        Box(
            modifier = modifier
                .clip(clipShape)
                .background(backgroundColor)
        ) {
            Box(
                modifier = Modifier
                    .clip(clipShape)
                    .background(createStripeGauge(stripeColor, stripeColorSecondary, 5.dp))
                    .fillMaxHeight()
                    .fillMaxWidth(progress)
            )
        }
        if (addDelimiter) {
            GrapesGaugeDelimiter()
        }
    }
}

@Composable
private fun GrapesGaugeDelimiter() {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .width(GrapesTheme.dimensions.gaugeDelimiterWidth)
            .background(GrapesTheme.colors.mainWhite)
    )
}

@Composable
private fun createStripeGauge(
    stripeColor: Color,
    stripeBg: Color,
    stripeWidth: Dp
): Brush {
    val stripeWidthPx = with(LocalDensity.current) { stripeWidth.toPx() }
    val brushSizePx = 2 * stripeWidthPx
    val stripeStart = stripeWidthPx / brushSizePx

    return Brush.linearGradient(
        stripeStart to stripeBg,
        stripeStart to stripeColor,
        start = Offset(0f, 0f),
        end = Offset(brushSizePx, brushSizePx),
        tileMode = TileMode.Repeated
    )
}

@Composable
@Preview(showBackground = true)
private fun GaugePreview() {
    GrapesTheme {
        Column(verticalArrangement = Arrangement.spacedBy(GrapesTheme.dimensions.paddingXSmall)) {
            GrapesGauges(
                modifier = Modifier.padding(16.dp),
                backgroundColor = GrapesTheme.colors.mainNeutralLighter,
                content = {
                    GrapesGauge(
                        progress = 0.2f,
                        color = GrapesTheme.colors.mainPrimaryDark,
                        addDelimiter = true
                    )
                }
            )

            GrapesGauges(
                modifier = Modifier.padding(16.dp),
                backgroundColor = GrapesTheme.colors.mainNeutralLighter,
                content = {
                    GrapesGaugeStripped(
                        progress = 0.8f,
                        stripeColor = GrapesTheme.colors.mainWarningLighter,
                        stripeColorSecondary = GrapesTheme.colors.mainWarningNormal,
                        backgroundColor = GrapesTheme.colors.mainNeutralDark
                    )
                }
            )

            GrapesGauges(
                modifier = Modifier.padding(16.dp),
                backgroundColor = GrapesTheme.colors.mainNeutralLighter,
                content = {
                    Box {
                        GrapesGaugeStripped(
                            progress = 0.5f,
                            stripeColor = GrapesTheme.colors.mainWarningLighter,
                            stripeColorSecondary = GrapesTheme.colors.mainWarningNormal,
                            backgroundColor = GrapesTheme.colors.mainNeutralDark
                        )
                        GrapesGauge(
                            progress = 0.2f,
                            color = GrapesTheme.colors.mainWarningNormal,
                            addDelimiter = true
                        )
                    }
                }
            )
        }
    }
}
