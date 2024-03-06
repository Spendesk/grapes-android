package com.spendesk.grapes.compose.badge

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
        val textValue = when {
            countValue > maxCount -> "$maxCount$moreIndicator"
            else -> countValue.toString()
        }

        Text(
            modifier = Modifier.align(Alignment.Center),
            style = GrapesBadgeDefaults.textTypography(colors.textColor()),
            text = textValue,
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun GrapesBadgesPreview(@PreviewParameter(GrapesBadgeProvider::class) type: GrapesBadgeType) {
    GrapesTheme {
        Row(
            modifier = Modifier.padding(GrapesTheme.dimensions.spacing2),
            horizontalArrangement = Arrangement.spacedBy(GrapesTheme.dimensions.spacing2),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            when (type) {
                GrapesBadgeType.NEUTRAL -> {
                    GrapesNeutralBadge(countValue = 1)
                    GrapesNeutralBadge(countValue = 100)
                    GrapesNeutralBadge(countValue = 11, maxCount = 10)
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
