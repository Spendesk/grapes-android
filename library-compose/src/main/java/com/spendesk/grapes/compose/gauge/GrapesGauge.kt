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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
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

@Immutable
object GrapesGaugeDefaults {

    const val GaugeAddDelimiter = true
    val GaugeStrippedWidth = 5.dp
}

sealed class Gauge(open val progress: Float) {
    data class Solid(
        override val progress: Float,
        val color: Color,
        val addDelimiter: Boolean = GrapesGaugeDefaults.GaugeAddDelimiter
    ) : Gauge(progress)

    data class Stripped(
        override val progress: Float,
        val stripeColor: Color,
        val stripeColorSecondary: Color,
        val stripeWidth: Dp = GrapesGaugeDefaults.GaugeStrippedWidth,
        val addDelimiter: Boolean = GrapesGaugeDefaults.GaugeAddDelimiter
    ) : Gauge(progress)
}

/**
 * Displays a list of gauges.
 *
 * @param backgroundColor the background color of the gauge.
 * @param gauges the list of gauges to display. The gauges will automatically be sorted by progress in order to have the right visibility without any additional work.
 * @param modifier the modifier to apply to this layout.
 */
@Composable
fun GrapesGauge(
    backgroundColor: Color,
    gauges: List<Gauge>,
    modifier: Modifier = Modifier,
    clipShape: Shape = GrapesTheme.shapes.shape1,
) {
    GrapesGaugeContainer(
        modifier = modifier,
        backgroundColor = backgroundColor,
        clipShape = clipShape,
        content = {
            gauges
                .sortedByDescending { it.progress }
                .forEachIndexed { index, gauge ->
                    when (gauge) {
                        is Gauge.Solid -> {
                            GrapesGauge(
                                progress = gauge.progress,
                                color = gauge.color,
                                addDelimiter = gauge.addDelimiter
                            )
                        }

                        is Gauge.Stripped -> {
                            GrapesGaugeStripped(
                                progress = gauge.progress,
                                stripeColor = gauge.stripeColor,
                                stripeColorSecondary = gauge.stripeColorSecondary,
                                addDelimiter = gauge.addDelimiter
                            )
                        }
                    }
                }
        })
}

@Composable
private fun GrapesGaugeContainer(
    backgroundColor: Color,
    clipShape: Shape,
    modifier: Modifier = Modifier,
    content: @Composable (() -> Unit),
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
private fun GrapesGauge(
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
private fun GrapesGaugeStripped(
    progress: Float,
    stripeColor: Color,
    stripeColorSecondary: Color,
    modifier: Modifier = Modifier,
    addDelimiter: Boolean = true
) {
    Row {
        Box(
            modifier = modifier
                .background(createStripeGauge(stripeColor, stripeColorSecondary, 5.dp))
                .fillMaxHeight()
                .fillMaxWidth(progress)
        )
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
        Column(verticalArrangement = Arrangement.spacedBy(GrapesTheme.dimensions.spacing1)) {
            GrapesGauge(
                modifier = Modifier.padding(16.dp),
                backgroundColor = GrapesTheme.colors.mainNeutralLighter,
                gauges = listOf(
                    Gauge.Solid(progress = 0.2f, color = GrapesTheme.colors.mainPrimaryDark),
                    Gauge.Solid(progress = 0.5f, color = GrapesTheme.colors.mainWarningNormal),
                    Gauge.Solid(progress = 0.8f, color = GrapesTheme.colors.alertDark)
                )

            )
            GrapesGauge(
                modifier = Modifier.padding(16.dp),
                backgroundColor = GrapesTheme.colors.mainNeutralLighter,
                gauges = listOf(
                    Gauge.Solid(progress = 0.2f, color = GrapesTheme.colors.mainPrimaryDark)
                )
            )
            GrapesGauge(
                modifier = Modifier.padding(16.dp),
                backgroundColor = GrapesTheme.colors.mainNeutralLighter,
                gauges = listOf(
                    Gauge.Stripped(progress = 0.8f, stripeColor = GrapesTheme.colors.mainWarningLighter, stripeColorSecondary = GrapesTheme.colors.mainWarningNormal)
                )
            )
            GrapesGauge(
                modifier = Modifier.padding(16.dp),
                backgroundColor = GrapesTheme.colors.mainNeutralLighter,
                gauges = listOf(
                    Gauge.Solid(progress = 0.2f, color = GrapesTheme.colors.mainWarningNormal),
                    Gauge.Stripped(progress = 0.5f, stripeColor = GrapesTheme.colors.mainWarningLighter, stripeColorSecondary = GrapesTheme.colors.mainWarningNormal)
                )
            )
        }
    }
}
