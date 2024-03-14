package com.spendesk.grapes.compose.badge

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.spendesk.grapes.compose.theme.GrapesTheme

@Composable
fun GrapesNeutralBadge(
    countValue: Int,
    modifier: Modifier = Modifier,
    maxCount: Int = GrapesBadgeDefaults.MAX_VALUE,
    moreIndicator: String = GrapesBadgeDefaults.MORE_INDICATOR,
) {
    GrapesBadge(
        countValue = countValue,
        colors = GrapesBadgeDefaults.neutralBadgeColors(),
        modifier = modifier,
        maxCount = maxCount,
        moreIndicator = moreIndicator,
    )
}

@Composable
fun GrapesPrimaryBadge(
    countValue: Int,
    modifier: Modifier = Modifier,
    maxCount: Int = GrapesBadgeDefaults.MAX_VALUE,
    moreIndicator: String = GrapesBadgeDefaults.MORE_INDICATOR,
) {
    GrapesBadge(
        countValue = countValue,
        colors = GrapesBadgeDefaults.primaryBadgeColors(),
        modifier = modifier,
        maxCount = maxCount,
        moreIndicator = moreIndicator,
    )
}

@Composable
fun GrapesAlertBadge(
    countValue: Int,
    modifier: Modifier = Modifier,
    maxCount: Int = GrapesBadgeDefaults.MAX_VALUE,
    moreIndicator: String = GrapesBadgeDefaults.MORE_INDICATOR,
) {
    GrapesBadge(
        countValue = countValue,
        colors = GrapesBadgeDefaults.alertBadgeColors(),
        modifier = modifier,
        maxCount = maxCount,
        moreIndicator = moreIndicator,
    )
}

@Composable
private fun GrapesBadge(
    countValue: Int,
    colors: GrapesBadgeColors,
    modifier: Modifier = Modifier,
    maxCount: Int = GrapesBadgeDefaults.MAX_VALUE,
    moreIndicator: String = GrapesBadgeDefaults.MORE_INDICATOR,
) {
    Box(
        modifier = modifier
            .widthIn(min = GrapesBadgeDefaults.MinWidth)
            .height(GrapesBadgeDefaults.Height)
            .background(
                color = colors.backgroundColor(),
                shape = GrapesBadgeDefaults.backgroundShape(),
            )
            .padding(horizontal = GrapesBadgeDefaults.HorizontalPadding)
    ) {
        Row(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.Center)
                .animateContentSize()
        ) {
            countValue.toString()
                .mapIndexed { index, c -> Digit(c, countValue, index) }
                .forEach { digit ->
                    AnimatedContent(
                        modifier = modifier,
                        targetState = digit,
                        transitionSpec = {
                            if (targetState > initialState) {
                                slideInVertically { -it } togetherWith slideOutVertically { it }
                            } else {
                                slideInVertically { it } togetherWith slideOutVertically { -it }
                            }
                        },
                        label = "Animated Badge Content"
                    ) { animatedDigit ->
                        val textValue = when {
                            animatedDigit.fullNumber > maxCount && animatedDigit.place == 0 -> "$moreIndicator$maxCount"
                            animatedDigit.fullNumber > maxCount && animatedDigit.place > 0 -> ""
                            else -> animatedDigit.digitChar.toString()
                        }
                        Text(
                            text = textValue,
                            style = GrapesBadgeDefaults.textTypography(colors.textColor()),
                        )
                    }
                }
        }
    }
}

internal data class Digit(val digitChar: Char, val fullNumber: Int, val place: Int) {

    override fun equals(other: Any?): Boolean {
        return when (other) {
            is Digit -> digitChar == other.digitChar
            else -> super.equals(other)
        }
    }
}

internal operator fun Digit.compareTo(other: Digit): Int {
    return fullNumber.compareTo(other.fullNumber)
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun GrapesBadgesPreview(@PreviewParameter(GrapesBadgeProvider::class) type: GrapesBadgeType) {
    GrapesTheme {
        var count1 by remember { mutableIntStateOf(12) }
        var count2 by remember { mutableIntStateOf(95) }

        Row(
            modifier = Modifier.padding(GrapesTheme.dimensions.spacing2),
            horizontalArrangement = Arrangement.spacedBy(GrapesTheme.dimensions.spacing2),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            when (type) {
                GrapesBadgeType.NEUTRAL -> {
                    GrapesNeutralBadge(countValue = count1)
                    GrapesNeutralBadge(countValue = count2)
                    GrapesNeutralBadge(countValue = 11, maxCount = 10)

                    Column {
                        Button(onClick = { count1 += 1 }) { Text("Increment first count") }
                        Button(onClick = { count1 -= 1 }) { Text("Decrement first count") }
                        Button(onClick = { count2 += 1 }) { Text("Increment second count") }
                        Button(onClick = { count2 -= 1 }) { Text("Decrement second count") }
                    }
                }

                GrapesBadgeType.PRIMARY -> {
                    GrapesPrimaryBadge(countValue = 20)
                    GrapesPrimaryBadge(countValue = 100)
                    GrapesPrimaryBadge(countValue = 11, maxCount = 10)
                }

                GrapesBadgeType.ALERT -> {
                    GrapesAlertBadge(countValue = 10)
                    GrapesAlertBadge(countValue = 100)
                    GrapesAlertBadge(countValue = 11, maxCount = 10)
                }
            }
        }
    }
}

private enum class GrapesBadgeType {
    NEUTRAL,
    PRIMARY,
    ALERT,
}

private class GrapesBadgeProvider : PreviewParameterProvider<GrapesBadgeType> {

    override val values: Sequence<GrapesBadgeType> = sequenceOf(
        GrapesBadgeType.NEUTRAL,
        GrapesBadgeType.PRIMARY,
        GrapesBadgeType.ALERT,
    )
}
