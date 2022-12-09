package com.spendesk.grapes.compose.icons

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.spendesk.grapes.compose.model.GrapesConfigurationStatus
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author : danyboucanova
 * @since : 19/05/2022, Thu
 **/

enum class Size(val surfaceSize: Dp, val iconSize: Dp) {
    S(surfaceSize = 32.dp, iconSize = 16.dp),
    M(surfaceSize = 48.dp, iconSize = 24.dp),
    L(surfaceSize = 80.dp, iconSize = 32.dp);
}

@Composable
fun StatusInformationIcon(
    configuration: GrapesConfigurationStatus,
    size: Size,
    modifier: Modifier = Modifier,
    hasBorder: Boolean = true
) {
    GrapesSurface(
        modifier = modifier.size(size.surfaceSize),
        configuration = configuration,
        hasBorder = hasBorder
    ) {
        GrapesIcon(
            configuration = configuration,
            modifier = Modifier
                .wrapContentSize(align = Alignment.Center)
                .size(size.iconSize),
        )
    }
}

@Composable
fun StatusInformationIcon(
    @DrawableRes icon: Int,
    configuration: GrapesConfigurationStatus,
    size: Size,
    modifier: Modifier = Modifier,
    hasBorder: Boolean = true
) {
    GrapesSurface(
        modifier = modifier
            .size(size.surfaceSize),
        configuration = configuration,
        hasBorder = hasBorder
    ) {
        GrapesIcon(
            icon = icon,
            configuration = configuration,
            modifier = Modifier
                .wrapContentSize(align = Alignment.Center)
                .size(size.iconSize),
        )
    }
}

// region Previews

@Preview(group = "StatusInformationIcon")
@Preview(group = "S")
@Composable
fun StatusInformationIconS() {
    GrapesTheme {
        Row(horizontalArrangement = Arrangement.spacedBy(GrapesTheme.dimensions.paddingSmall)) {
            StatusInformationIcon(configuration = GrapesConfigurationStatus.SUCCESS, size = Size.S)
            StatusInformationIcon(configuration = GrapesConfigurationStatus.INFORMATION, size = Size.S)
            StatusInformationIcon(configuration = GrapesConfigurationStatus.ALERT, size = Size.S)
            StatusInformationIcon(configuration = GrapesConfigurationStatus.WARNING, size = Size.S)
            StatusInformationIcon(configuration = GrapesConfigurationStatus.BLOCKED, size = Size.S)
        }
    }
}

@Preview(group = "StatusInformationIcon")
@Preview(group = "M")
@Composable
fun StatusInformationIconM() {
    GrapesTheme {
        Row(horizontalArrangement = Arrangement.spacedBy(GrapesTheme.dimensions.paddingSmall)) {
            StatusInformationIcon(configuration = GrapesConfigurationStatus.SUCCESS, size = Size.M)
            StatusInformationIcon(configuration = GrapesConfigurationStatus.INFORMATION, size = Size.M)
            StatusInformationIcon(configuration = GrapesConfigurationStatus.ALERT, size = Size.M)
            StatusInformationIcon(configuration = GrapesConfigurationStatus.WARNING, size = Size.M)
            StatusInformationIcon(configuration = GrapesConfigurationStatus.BLOCKED, size = Size.M)
        }
    }
}

@Preview(group = "StatusInformationIcon")
@Preview(group = "L")
@Composable
fun StatusInformationIconL() {
    GrapesTheme {
        Column(verticalArrangement = Arrangement.spacedBy(GrapesTheme.dimensions.paddingSmall)) {
            StatusInformationIcon(configuration = GrapesConfigurationStatus.SUCCESS, size = Size.L)
            StatusInformationIcon(configuration = GrapesConfigurationStatus.INFORMATION, size = Size.L)
            StatusInformationIcon(configuration = GrapesConfigurationStatus.ALERT, size = Size.L)
            StatusInformationIcon(configuration = GrapesConfigurationStatus.WARNING, size = Size.L)
            StatusInformationIcon(configuration = GrapesConfigurationStatus.BLOCKED, size = Size.L)
        }
    }
}

// endregion Previews
