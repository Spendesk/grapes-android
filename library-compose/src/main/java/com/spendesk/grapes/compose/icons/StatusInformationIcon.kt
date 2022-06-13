package com.spendesk.grapes.compose.icons

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spendesk.grapes.compose.R
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
fun GrapesIcon(
    modifier: Modifier = Modifier,
    configuration: GrapesConfigurationStatus,
    size: Size
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


/**
 * The Grapes design system contains some main configuration typically used to describe a component state.
 *
 * This function tries to match the provided [configurationState] to its icon associated.
 *
 * @return matching icon for a [configurationState] given.
 */
fun iconFor(configurationState: GrapesConfigurationStatus): Int =
    when (configurationState) {
        GrapesConfigurationStatus.SUCCESS -> R.drawable.ic_success
        GrapesConfigurationStatus.INFORMATION -> R.drawable.ic_information
        GrapesConfigurationStatus.NEUTRAL -> R.drawable.ic_neutral
        GrapesConfigurationStatus.ALERT -> R.drawable.ic_error
        GrapesConfigurationStatus.WARNING -> R.drawable.ic_warning
        GrapesConfigurationStatus.BLOCKED -> R.drawable.ic_block
    }

// region Previews

@Preview(group = "Icon")
@Preview(group = "S")
@Composable
fun iconSizeS() {
    GrapesTheme {
        Row(horizontalArrangement = Arrangement.spacedBy(GrapesTheme.dimensions.paddingSmall)) {
            GrapesIcon(configuration = GrapesConfigurationStatus.SUCCESS, size = Size.S)
            GrapesIcon(configuration = GrapesConfigurationStatus.INFORMATION, size = Size.S)
            GrapesIcon(configuration = GrapesConfigurationStatus.ALERT, size = Size.S)
            GrapesIcon(configuration = GrapesConfigurationStatus.WARNING, size = Size.S)
            GrapesIcon(configuration = GrapesConfigurationStatus.BLOCKED, size = Size.S)
        }
    }
}

@Preview(group = "Icon")
@Preview(group = "M")
@Composable
fun iconSizeM() {
    GrapesTheme {
        Row(horizontalArrangement = Arrangement.spacedBy(GrapesTheme.dimensions.paddingSmall)) {
            GrapesIcon(configuration = GrapesConfigurationStatus.SUCCESS, size = Size.M)
            GrapesIcon(configuration = GrapesConfigurationStatus.INFORMATION, size = Size.M)
            GrapesIcon(configuration = GrapesConfigurationStatus.ALERT, size = Size.M)
            GrapesIcon(configuration = GrapesConfigurationStatus.WARNING, size = Size.M)
            GrapesIcon(configuration = GrapesConfigurationStatus.BLOCKED, size = Size.M)
        }
    }
}

@Preview(group = "Icon")
@Preview(group = "L")
@Composable
fun iconSizeL() {
    GrapesTheme {
        Column(verticalArrangement = Arrangement.spacedBy(GrapesTheme.dimensions.paddingSmall)) {
            GrapesIcon(configuration = GrapesConfigurationStatus.SUCCESS, size = Size.L)
            GrapesIcon(configuration = GrapesConfigurationStatus.INFORMATION, size = Size.L)
            GrapesIcon(configuration = GrapesConfigurationStatus.ALERT, size = Size.L)
            GrapesIcon(configuration = GrapesConfigurationStatus.WARNING, size = Size.L)
            GrapesIcon(configuration = GrapesConfigurationStatus.BLOCKED, size = Size.L)
        }
    }
}

// endregion Previews
