package com.spendesk.grapes.compose.icons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spendesk.grapes.compose.extensions.GrapesDefault
import com.spendesk.grapes.compose.model.GrapesConfigurationState
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
fun Icon(
    modifier: Modifier = Modifier,
    configuration: GrapesConfigurationState,
    size: Size
) {
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
        Image(
            modifier = modifier
                .wrapContentSize(align = Alignment.Center)
                .size(
                    when (size) {
                        Size.S -> 16.dp
                        Size.M -> 24.dp
                        Size.L -> 32.dp
                    }
                ),
            painter = painterResource(id = GrapesDefault.iconFor(configuration)),
            contentDescription = null
        )
    }
}

// region Previews

@Preview(group = "Icon")
@Preview(group = "S")
@Composable
fun iconSizeS() {
    GrapesTheme {
        Row(horizontalArrangement = Arrangement.spacedBy(GrapesTheme.dimensions.paddingSmall)) {
            Icon(configuration = GrapesConfigurationState.SUCCESS, size = Size.S)
            Icon(configuration = GrapesConfigurationState.INFORMATION, size = Size.S)
            Icon(configuration = GrapesConfigurationState.ALERT, size = Size.S)
            Icon(configuration = GrapesConfigurationState.WARNING, size = Size.S)
            Icon(configuration = GrapesConfigurationState.BLOCKED, size = Size.S)
        }
    }
}

@Preview(group = "Icon")
@Preview(group = "M")
@Composable
fun iconSizeM() {
    GrapesTheme {
        Row(horizontalArrangement = Arrangement.spacedBy(GrapesTheme.dimensions.paddingSmall)) {
            Icon(configuration = GrapesConfigurationState.SUCCESS, size = Size.M)
            Icon(configuration = GrapesConfigurationState.INFORMATION, size = Size.M)
            Icon(configuration = GrapesConfigurationState.ALERT, size = Size.M)
            Icon(configuration = GrapesConfigurationState.WARNING, size = Size.M)
            Icon(configuration = GrapesConfigurationState.BLOCKED, size = Size.M)
        }
    }
}

@Preview(group = "Icon")
@Preview(group = "L")
@Composable
fun iconSizeL() {
    GrapesTheme {
        Column(verticalArrangement = Arrangement.spacedBy(GrapesTheme.dimensions.paddingSmall)) {
            Icon(configuration = GrapesConfigurationState.SUCCESS, size = Size.L)
            Icon(configuration = GrapesConfigurationState.INFORMATION, size = Size.L)
            Icon(configuration = GrapesConfigurationState.ALERT, size = Size.L)
            Icon(configuration = GrapesConfigurationState.WARNING, size = Size.L)
            Icon(configuration = GrapesConfigurationState.BLOCKED, size = Size.L)
        }
    }
}

// endregion Previews
