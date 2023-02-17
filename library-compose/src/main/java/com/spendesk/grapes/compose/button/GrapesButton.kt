package com.spendesk.grapes.compose.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author : danyboucanova
 * @since : 16/05/2022, Mon
 **/
@Composable
fun GrapesButton(
    text: String,
    modifier: Modifier = Modifier,
    buttonStyle: GrapesButtonStyle = GrapesButtonStyleDefaults.primary,
    state: GrapesButtonState = GrapesButtonState.Enabled,
    onClick: (() -> Unit) = {},
) {
    val enabled = when (state) {
        GrapesButtonState.Enabled, GrapesButtonState.ShowCircularIndicator -> true
        GrapesButtonState.Disabled -> false
    }

    val showLoadingIndicator = state is GrapesButtonState.ShowCircularIndicator

    GrapesCoreButton(
        modifier = modifier,
        enabled = enabled,
        rippleColor = buttonStyle.rippleColor,
        colors = buttonStyle.colors,
        minSize = buttonStyle.minSize,
        iconSize = buttonStyle.iconSize,
        contentPaddingValues = buttonStyle.contentPadding,
        shape = buttonStyle.shape,
        borderStroke = buttonStyle.borderStroke,
        style = buttonStyle.textStyle,
        showLoadingIndicator = showLoadingIndicator,
        onClick = onClick
    ) {
        GrapesButtonContent(
            text = text
        )
    }
}

// TODO add leading Icon
@Composable
private fun GrapesButtonContent(
    text: String
) {
    GrapesButtonContentText(text)
}

@Composable
private fun GrapesButtonContentText(
    text: String,
) {
    Text(
        text = text,
        style = GrapesTheme.typography.titleM,
    )
}

//region Previews
@Preview(
    name = "Primary",
    group = "Default",
    widthDp = 372,
    showBackground = true,
)
@Composable
fun ButtonPrimaryPreview() {
    GrapesTheme {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            GrapesButton(
                text = "Button Primary Enabled",
                buttonStyle = GrapesButtonStyleDefaults.primary,
            )

            GrapesButton(
                text = "Button Primary Disabled",
                state = GrapesButtonState.Disabled,
                buttonStyle = GrapesButtonStyleDefaults.primary,
            )

            GrapesButton(
                text = "This should not be visible",
                buttonStyle = GrapesButtonStyleDefaults.primary,
                state = GrapesButtonState.ShowCircularIndicator
            )
        }
    }
}

@Preview(
    name = "Primary Small",
    group = "Small",
    widthDp = 700,
    showBackground = true,
)
@Composable
private fun ButtonPrimarySmallPreview() {
    GrapesTheme {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            GrapesButton(
                text = "Primary Small Enabled",
                buttonStyle = GrapesButtonStyleDefaults.primarySmall,
            )

            GrapesButton(
                text = "Primary Small Disabled",
                buttonStyle = GrapesButtonStyleDefaults.primarySmall,
                state = GrapesButtonState.Disabled
            )

            GrapesButton(
                text = "This should not be shown",
                buttonStyle = GrapesButtonStyleDefaults.primarySmall,
                state = GrapesButtonState.ShowCircularIndicator
            )
        }
    }
}

@Preview(
    name = "Secondary",
    group = "Default",
    widthDp = 372,
    showBackground = true,
)
@Composable
fun ButtonSecondaryPreview() {
    GrapesTheme {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            GrapesButton(
                text = "Button Secondary Enabled",
                buttonStyle = GrapesButtonStyleDefaults.secondary,
            )

            GrapesButton(
                text = "Button Secondary Disabled",
                buttonStyle = GrapesButtonStyleDefaults.secondary,
                state = GrapesButtonState.Disabled
            )

            GrapesButton(
                text = "This should not be visible",
                buttonStyle = GrapesButtonStyleDefaults.secondary,
                state = GrapesButtonState.ShowCircularIndicator
            )
        }
    }
}

@Preview(
    name = "Secondary Small",
    group = "Small",
    widthDp = 700,
    showBackground = true,
)
@Composable
fun ButtonSecondarySmallPreview() {
    GrapesTheme {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            GrapesButton(
                text = "Secondary Small Enabled",
                buttonStyle = GrapesButtonStyleDefaults.secondarySmall,
            )

            GrapesButton(
                text = "Secondary Small Disabled",
                buttonStyle = GrapesButtonStyleDefaults.secondarySmall,
                state = GrapesButtonState.Disabled
            )

            GrapesButton(
                text = "Should not be visible",
                buttonStyle = GrapesButtonStyleDefaults.secondarySmall,
                state = GrapesButtonState.ShowCircularIndicator
            )
        }
    }
}

@Preview(
    name = "Text",
    group = "Default",
    widthDp = 372,
    showBackground = true,
    backgroundColor = 0xFF421896 // Primary Dark
)
@Composable
fun ButtonTextPreview() {
    GrapesTheme {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            GrapesButton(
                text = "Button Text Enabled",
                buttonStyle = GrapesButtonStyleDefaults.text,
            )

            GrapesButton(
                text = "Button Text Disabled",
                buttonStyle = GrapesButtonStyleDefaults.text,
                state = GrapesButtonState.Disabled
            )

            GrapesButton(
                text = "Should not be visible",
                buttonStyle = GrapesButtonStyleDefaults.text,
                state = GrapesButtonState.ShowCircularIndicator
            )
        }
    }
}

@Preview(
    name = "Text Small",
    group = "Small",
    widthDp = 700,
    showBackground = true,
    backgroundColor = 0xFF421896 // Primary Dark
)
@Composable
fun ButtonTextSmallPreview() {
    GrapesTheme {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            GrapesButton(
                text = "Secondary Text Enabled",
                buttonStyle = GrapesButtonStyleDefaults.textSmall,
            )

            GrapesButton(
                text = "Secondary Text Disabled",
                buttonStyle = GrapesButtonStyleDefaults.textSmall,
                state = GrapesButtonState.Disabled
            )

            GrapesButton(
                text = "Secondary Text Disabled",
                buttonStyle = GrapesButtonStyleDefaults.textSmall,
                state = GrapesButtonState.ShowCircularIndicator
            )
        }
    }
}

@Preview(
    name = "Warning",
    group = "Default",
    widthDp = 372,
    showBackground = true,
)
@Composable
fun ButtonWarningPreview() {
    GrapesTheme {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            GrapesButton(
                text = "Button Warning Enabled",
                buttonStyle = GrapesButtonStyleDefaults.warning,
            )

            GrapesButton(
                text = "Button Warning Disabled",
                buttonStyle = GrapesButtonStyleDefaults.warning,
                state = GrapesButtonState.Disabled
            )

            GrapesButton(
                text = "Should not be visible",
                buttonStyle = GrapesButtonStyleDefaults.warning,
                state = GrapesButtonState.ShowCircularIndicator
            )
        }
    }
}

@Preview(
    name = "Alert",
    group = "Default",
    widthDp = 372,
    showBackground = true,
)
@Composable
fun ButtonAlertPreview() {
    GrapesTheme {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            GrapesButton(
                text = "Button Alert Enabled",
                buttonStyle = GrapesButtonStyleDefaults.alert,
            )

            GrapesButton(
                text = "Button Alert Disabled",
                buttonStyle = GrapesButtonStyleDefaults.alert,
                state = GrapesButtonState.Disabled
            )

            GrapesButton(
                text = "Should not be visible",
                buttonStyle = GrapesButtonStyleDefaults.alert,
                state = GrapesButtonState.ShowCircularIndicator
            )
        }
    }
}

@Preview(
    name = "Link Primary",
    group = "Small",
    widthDp = 700,
    showBackground = true,
)
@Composable
fun ButtonLinkPrimaryPreview() {
    GrapesTheme {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            GrapesButton(
                text = "Link Primary Enabled",
                buttonStyle = GrapesButtonStyleDefaults.linkPrimary,
            )

            GrapesButton(
                text = "Link Primary Disabled",
                buttonStyle = GrapesButtonStyleDefaults.linkPrimary,
                state = GrapesButtonState.Disabled
            )

            GrapesButton(
                text = "Should not be visible",
                buttonStyle = GrapesButtonStyleDefaults.linkPrimary,
                state = GrapesButtonState.ShowCircularIndicator
            )
        }
    }
}

@Preview(
    name = "Link Secondary",
    group = "Small",
    widthDp = 730,
    showBackground = true,
)
@Composable
fun ButtonLinkSecondaryPreview() {
    GrapesTheme {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            GrapesButton(
                text = "Link Secondary Enabled",
                buttonStyle = GrapesButtonStyleDefaults.linkSecondary,
            )

            GrapesButton(
                text = "Link Secondary Disabled",
                buttonStyle = GrapesButtonStyleDefaults.linkSecondary,
                state = GrapesButtonState.Disabled
            )

            GrapesButton(
                text = "Should not be visible",
                buttonStyle = GrapesButtonStyleDefaults.linkSecondary,
                state = GrapesButtonState.ShowCircularIndicator
            )
        }
    }
}
//endregion Previews
