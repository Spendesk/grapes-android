package com.spendesk.grapes.compose.icons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spendesk.grapes.compose.R
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author : danyboucanova
 * @since : 19/05/2022, Thu
 **/

enum class Configuration {
    SUCCESS,
    INFORMATION,
    ALERT,
    WARNING,
    BLOCKED
}

enum class Size {
    S,
    M,
    L
}


@Composable
fun Icon(
    modifier: Modifier = Modifier,
    configuration: Configuration,
    size: Size
) {
    val (backgroundColor: Color, borderColor: Color, icon: Int) = when (configuration) {
        Configuration.SUCCESS -> Triple(GrapesTheme.colors.mainSuccessLightest, GrapesTheme.colors.mainSuccessLighter, R.drawable.ic_success)
        Configuration.INFORMATION -> Triple(GrapesTheme.colors.mainInfoLightest, GrapesTheme.colors.mainInfoLighter, R.drawable.ic_information)
        Configuration.ALERT -> Triple(GrapesTheme.colors.mainAlertLightest, GrapesTheme.colors.mainAlertLighter, R.drawable.ic_error)
        Configuration.WARNING -> Triple(GrapesTheme.colors.mainWarningLightest, GrapesTheme.colors.mainWarningLighter, R.drawable.ic_warning)
        Configuration.BLOCKED -> Triple(GrapesTheme.colors.mainPrimaryLightest, GrapesTheme.colors.mainPrimaryLighter, R.drawable.ic_block)
    }

    Surface(
        modifier = modifier
            .size(
                when (size) {
                    Size.S -> 32.dp
                    Size.M -> 48.dp
                    Size.L -> 80.dp
                }
            ),
        border = BorderStroke(1.dp, borderColor),
        shape = CircleShape,
        color = backgroundColor
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
            painter = painterResource(id = icon),
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
            Icon(configuration = Configuration.SUCCESS, size = Size.S)
            Icon(configuration = Configuration.INFORMATION, size = Size.S)
            Icon(configuration = Configuration.ALERT, size = Size.S)
            Icon(configuration = Configuration.WARNING, size = Size.S)
            Icon(configuration = Configuration.BLOCKED, size = Size.S)
        }
    }
}

@Preview(group = "Icon")
@Preview(group = "M")
@Composable
fun iconSizeM() {
    GrapesTheme {
        Row(horizontalArrangement = Arrangement.spacedBy(GrapesTheme.dimensions.paddingSmall)) {
            Icon(configuration = Configuration.SUCCESS, size = Size.M)
            Icon(configuration = Configuration.INFORMATION, size = Size.M)
            Icon(configuration = Configuration.ALERT, size = Size.M)
            Icon(configuration = Configuration.WARNING, size = Size.M)
            Icon(configuration = Configuration.BLOCKED, size = Size.M)
        }
    }
}

@Preview(group = "Icon")
@Preview(group = "L")
@Composable
fun iconSizeL() {
    GrapesTheme {
        Column(verticalArrangement = Arrangement.spacedBy(GrapesTheme.dimensions.paddingSmall)) {
            Icon(configuration = Configuration.SUCCESS, size = Size.L)
            Icon(configuration = Configuration.INFORMATION, size = Size.L)
            Icon(configuration = Configuration.ALERT, size = Size.L)
            Icon(configuration = Configuration.WARNING, size = Size.L)
            Icon(configuration = Configuration.BLOCKED, size = Size.L)
        }
    }
}

// endregion Previews
