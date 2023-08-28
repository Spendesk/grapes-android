package com.spendesk.grapes.compose.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spendesk.grapes.compose.button.atoms.GrapesButtonContentText
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
        borderStroke = if (enabled) buttonStyle.borderStroke else null,
        style = buttonStyle.textStyle,
        showLoadingIndicator = showLoadingIndicator,
        onClick = onClick,
        content = {
            GrapesButtonContentText(
                text = text,
                textStyle = buttonStyle.textStyle
            )
        }
    )
}

//region Previews
@Preview(
    name = "Primary",
    group = "Button",
    widthDp = 372,
    showBackground = true,
)
@Composable
internal fun ButtonPrimaryPreview() {
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
    group = "Button",
    widthDp = 810,
    showBackground = true,
)
@Composable
internal fun ButtonPrimarySmallPreview() {
    GrapesTheme {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
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
    group = "Button",
    widthDp = 372,
    showBackground = true,
)
@Composable
internal fun ButtonSecondaryPreview() {
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
    group = "Button",
    widthDp = 850,
    showBackground = true,
)
@Composable
internal fun ButtonSecondarySmallPreview() {
    GrapesTheme {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
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
    group = "Button",
    widthDp = 372,
    showBackground = true,
    backgroundColor = 0xFF421896 // Primary Dark
)
@Composable
internal fun ButtonTextPreview() {
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
    group = "Button",
    widthDp = 850,
    showBackground = true,
    backgroundColor = 0xFF421896 // Primary Dark
)
@Composable
internal fun ButtonTextSmallPreview() {
    GrapesTheme {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
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
    group = "Button",
    widthDp = 372,
    showBackground = true,
)
@Composable
internal fun ButtonWarningPreview() {
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
    group = "Button",
    widthDp = 372,
    showBackground = true,
)
@Composable
internal fun ButtonAlertPreview() {
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
    group = "Button",
    widthDp = 850,
    showBackground = true,
)
@Composable
internal fun ButtonLinkPrimaryPreview() {
    GrapesTheme {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
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
    group = "Button",
    widthDp = 1030,
    showBackground = true,
)
@Composable
internal fun ButtonLinkSecondaryPreview() {
    GrapesTheme {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
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

@Preview(
    name = "Alert Outlined",
    group = "Button",
    widthDp = 372,
    showBackground = true,
)
@Composable
internal fun ButtonAlertOutlinedPreview() {
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
                buttonStyle = GrapesButtonStyleDefaults.alertOutlined,
            )

            GrapesButton(
                text = "Button Alert Disabled",
                buttonStyle = GrapesButtonStyleDefaults.alertOutlined,
                state = GrapesButtonState.Disabled
            )

            GrapesButton(
                text = "Should not be visible",
                buttonStyle = GrapesButtonStyleDefaults.alertOutlined,
                state = GrapesButtonState.ShowCircularIndicator
            )
        }
    }
}

//endregion Previews
