package com.spendesk.grapes.compose.button

import androidx.annotation.DrawableRes
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
import com.spendesk.grapes.compose.R
import com.spendesk.grapes.compose.button.atoms.GrapesButtonContentIcon
import com.spendesk.grapes.compose.button.atoms.GrapesButtonContentText
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author : danyboucanova
 * @since : 16/05/2022, Mon
 **/

@Composable
fun GrapesButtonIcon(
    text: String,
    @DrawableRes leadingIcon: Int,
    iconDescription: String,
    modifier: Modifier = Modifier,
    buttonStyle: GrapesButtonStyle = GrapesButtonStyleDefaults.primary,
    state: GrapesButtonState = GrapesButtonState.Enabled,
    onClick: (() -> Unit) = {},
) {
    GrapesButtonIcon(
        text = text,
        leadingIcon = {
            GrapesButtonContentIcon(
                icon = leadingIcon,
                iconDescription = iconDescription,
            )
        },
        modifier = modifier,
        buttonStyle = buttonStyle,
        state = state,
        onClick = onClick
    )
}

@Composable
internal fun GrapesButtonIcon(
    text: String,
    modifier: Modifier = Modifier,
    leadingIcon: (@Composable () -> Unit)? = null,
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
        leadingIcon = leadingIcon,
        fillMaxWidthContent = buttonStyle.isFillMaxWidthWithContent,
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
    name = "Primary with icon",
    group = "Button",
    widthDp = 372,
    showBackground = true,
)
@Composable
internal fun ButtonIconPrimaryPreview() {
    GrapesTheme {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            GrapesButtonIcon(
                text = "Button with Icon",
                leadingIcon = R.drawable.ic_success,
                iconDescription = "Description"
            )

            GrapesButtonIcon(
                text = "Button with Icon And a very long text to check if it's ok",
                leadingIcon = R.drawable.ic_success,
                iconDescription = "Description"
            )

            GrapesButtonIcon(
                text = "Text with Icon Disabled",
                state = GrapesButtonState.Disabled,
                leadingIcon = R.drawable.ic_success,
                iconDescription = "Description",
            )

            GrapesButtonIcon(
                text = "Should not be visible",
                state = GrapesButtonState.ShowCircularIndicator,
                leadingIcon = R.drawable.ic_success,
                iconDescription = "Description",
            )
        }
    }
}

@Preview(
    name = "Primary Small with icon",
    group = "Button",
    widthDp = 710,
    showBackground = true,
)
@Composable
internal fun ButtonIconPrimarySmallPreview() {
    GrapesTheme {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            GrapesButtonIcon(
                text = "Button with Icon",
                buttonStyle = GrapesButtonStyleDefaults.primarySmall,
                leadingIcon = R.drawable.ic_success,
                iconDescription = "Description"
            )

            GrapesButtonIcon(
                text = "Text with Icon Disabled",
                buttonStyle = GrapesButtonStyleDefaults.primarySmall,
                state = GrapesButtonState.Disabled,
                leadingIcon = R.drawable.ic_success,
                iconDescription = "Description",
            )

            GrapesButtonIcon(
                text = "Should not be visible",
                buttonStyle = GrapesButtonStyleDefaults.primarySmall,
                state = GrapesButtonState.ShowCircularIndicator,
                leadingIcon = R.drawable.ic_success,
                iconDescription = "Description",
            )
        }
    }
}

@Preview(
    name = "Secondary with icon",
    group = "Button",
    widthDp = 372,
    showBackground = true,
)
@Composable
internal fun ButtonIconSecondaryPreview() {
    GrapesTheme {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            GrapesButtonIcon(
                text = "Text with Icon",
                buttonStyle = GrapesButtonStyleDefaults.secondary,
                leadingIcon = R.drawable.ic_success,
                iconDescription = "Description"
            )

            GrapesButtonIcon(
                text = "Text with Icon Disabled",
                buttonStyle = GrapesButtonStyleDefaults.secondary,
                state = GrapesButtonState.Disabled,
                leadingIcon = R.drawable.ic_success,
                iconDescription = "Description",
            )

            GrapesButtonIcon(
                text = "Should not be visible",
                buttonStyle = GrapesButtonStyleDefaults.secondary,
                state = GrapesButtonState.ShowCircularIndicator,
                leadingIcon = R.drawable.ic_success,
                iconDescription = "Description",
            )
        }
    }
}

@Preview(
    name = "Secondary Small with icon",
    group = "Button",
    widthDp = 710,
    showBackground = true,
)
@Composable
internal fun ButtonIconSecondarySmallPreview() {
    GrapesTheme {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {

            GrapesButtonIcon(
                text = "Text with Icon",
                buttonStyle = GrapesButtonStyleDefaults.secondarySmall,
                leadingIcon = R.drawable.ic_success,
                iconDescription = "Description"
            )

            GrapesButtonIcon(
                text = "Text with Icon Disabled",
                buttonStyle = GrapesButtonStyleDefaults.secondarySmall,
                state = GrapesButtonState.Disabled,
                leadingIcon = R.drawable.ic_success,
                iconDescription = "Description",
            )

            GrapesButtonIcon(
                text = "Should not be visible",
                buttonStyle = GrapesButtonStyleDefaults.secondarySmall,
                state = GrapesButtonState.ShowCircularIndicator,
                leadingIcon = R.drawable.ic_success,
                iconDescription = "Description",
            )
        }
    }
}

@Preview(
    name = "Text with icon",
    group = "Button",
    widthDp = 372,
    showBackground = true,
    backgroundColor = 0xFF421896 // Primary Dark
)
@Composable
internal fun ButtonIconTextPreview() {
    GrapesTheme {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            GrapesButtonIcon(
                text = "Text with Icon",
                buttonStyle = GrapesButtonStyleDefaults.text,
                leadingIcon = R.drawable.ic_success,
                iconDescription = "Description"
            )

            GrapesButtonIcon(
                text = "Text with Icon Disabled",
                buttonStyle = GrapesButtonStyleDefaults.text,
                state = GrapesButtonState.Disabled,
                leadingIcon = R.drawable.ic_success,
                iconDescription = "Description",
            )

            GrapesButtonIcon(
                text = "Should not be visible",
                buttonStyle = GrapesButtonStyleDefaults.text,
                state = GrapesButtonState.ShowCircularIndicator,
                leadingIcon = R.drawable.ic_success,
                iconDescription = "Description",
            )
        }
    }
}

@Preview(
    name = "Text Small with icon",
    group = "Button",
    widthDp = 710,
    showBackground = true,
    backgroundColor = 0xFF421896 // Primary Dark
)
@Composable
internal fun ButtonIconTextSmallPreview() {
    GrapesTheme {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            GrapesButtonIcon(
                text = "Text with Icon",
                buttonStyle = GrapesButtonStyleDefaults.textSmall,
                leadingIcon = R.drawable.ic_success,
                iconDescription = "Description"
            )

            GrapesButtonIcon(
                text = "Text with Icon Disabled",
                buttonStyle = GrapesButtonStyleDefaults.textSmall,
                state = GrapesButtonState.Disabled,
                leadingIcon = R.drawable.ic_success,
                iconDescription = "Description",
            )

            GrapesButtonIcon(
                text = "Should not be visible",
                buttonStyle = GrapesButtonStyleDefaults.textSmall,
                state = GrapesButtonState.ShowCircularIndicator,
                leadingIcon = R.drawable.ic_success,
                iconDescription = "Description",
            )
        }
    }
}

@Preview(
    name = "Warning with icon",
    group = "Button",
    widthDp = 372,
    showBackground = true,
)
@Composable
internal fun ButtonIconWarningPreview() {
    GrapesTheme {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            GrapesButtonIcon(
                text = "Text with Icon",
                buttonStyle = GrapesButtonStyleDefaults.warning,
                leadingIcon = R.drawable.ic_success,
                iconDescription = "Description"
            )

            GrapesButtonIcon(
                text = "Text with Icon Disabled",
                buttonStyle = GrapesButtonStyleDefaults.warning,
                state = GrapesButtonState.Disabled,
                leadingIcon = R.drawable.ic_success,
                iconDescription = "Description",
            )

            GrapesButtonIcon(
                text = "Should not be visible",
                buttonStyle = GrapesButtonStyleDefaults.warning,
                state = GrapesButtonState.ShowCircularIndicator,
                leadingIcon = R.drawable.ic_success,
                iconDescription = "Description",
            )
        }
    }
}

@Preview(
    name = "Alert with icon",
    group = "Button",
    widthDp = 372,
    showBackground = true,
)
@Composable
internal fun ButtonIconAlertPreview() {
    GrapesTheme {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            GrapesButtonIcon(
                text = "Text with Icon",
                buttonStyle = GrapesButtonStyleDefaults.alert,
                leadingIcon = R.drawable.ic_success,
                iconDescription = "Description"
            )

            GrapesButtonIcon(
                text = "Text with Icon Disabled",
                buttonStyle = GrapesButtonStyleDefaults.alert,
                state = GrapesButtonState.Disabled,
                leadingIcon = R.drawable.ic_success,
                iconDescription = "Description",
            )

            GrapesButtonIcon(
                text = "Should not be visible",
                buttonStyle = GrapesButtonStyleDefaults.alert,
                state = GrapesButtonState.ShowCircularIndicator,
                leadingIcon = R.drawable.ic_success,
                iconDescription = "Description",
            )
        }
    }
}

@Preview(
    name = "Link Primary with icon",
    group = "Button",
    widthDp = 710,
    showBackground = true,
)
@Composable
internal fun ButtonIconLinkPrimaryPreview() {
    GrapesTheme {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            GrapesButtonIcon(
                text = "Text with Icon",
                buttonStyle = GrapesButtonStyleDefaults.linkPrimary,
                leadingIcon = R.drawable.ic_success,
                iconDescription = "Description"
            )

            GrapesButtonIcon(
                text = "Text with Icon Disabled",
                buttonStyle = GrapesButtonStyleDefaults.linkPrimary,
                state = GrapesButtonState.Disabled,
                leadingIcon = R.drawable.ic_success,
                iconDescription = "Description",
            )

            GrapesButtonIcon(
                text = "Should not be visible",
                buttonStyle = GrapesButtonStyleDefaults.linkPrimary,
                state = GrapesButtonState.ShowCircularIndicator,
                leadingIcon = R.drawable.ic_success,
                iconDescription = "Description",
            )
        }
    }
}

@Preview(
    name = "Link Secondary with icon",
    group = "Button",
    widthDp = 710,
    showBackground = true,
)
@Composable
internal fun ButtonIconLinkSecondaryPreview() {
    GrapesTheme {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            GrapesButtonIcon(
                text = "Button with Icon",
                buttonStyle = GrapesButtonStyleDefaults.linkSecondary,
                leadingIcon = R.drawable.ic_success,
                iconDescription = "Description"
            )

            GrapesButtonIcon(
                text = "Button with Icon Disabled",
                buttonStyle = GrapesButtonStyleDefaults.linkSecondary,
                state = GrapesButtonState.Disabled,
                leadingIcon = R.drawable.ic_success,
                iconDescription = "Description",
            )

            GrapesButtonIcon(
                text = "Should not be visible",
                buttonStyle = GrapesButtonStyleDefaults.linkSecondary,
                state = GrapesButtonState.ShowCircularIndicator,
                leadingIcon = R.drawable.ic_success,
                iconDescription = "Description",
            )
        }
    }
}

//endregion Previews
