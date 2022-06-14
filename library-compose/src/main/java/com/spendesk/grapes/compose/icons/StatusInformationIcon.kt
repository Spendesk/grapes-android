package com.spendesk.grapes.compose.icons

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spendesk.grapes.compose.model.GrapesConfigurationStatus
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author : danyboucanova
 * @since : 19/05/2022, Thu
 **/

enum class Size {
    S,
    M,
    L
}

@Composable
fun StatusInformationIcon(
    configuration: GrapesConfigurationStatus,
    size: Size,
    modifier: Modifier = Modifier
) =
    GrapesSurface(
        modifier = modifier
            .size(
                when (size) {
                    Size.S -> 32.dp
                    Size.M -> 48.dp
                    Size.L -> 80.dp
                }
            ),
        configuration = configuration
    ) {
        GrapesIcon(
            configuration = configuration,
            modifier = Modifier
                .wrapContentSize(align = Alignment.Center)
                .size(
                    when (size) {
                        Size.S -> 16.dp
                        Size.M -> 24.dp
                        Size.L -> 32.dp
                    }
                ),
        )
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
