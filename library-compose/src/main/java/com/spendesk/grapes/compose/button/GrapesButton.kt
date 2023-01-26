package com.spendesk.grapes.compose.button

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spendesk.grapes.compose.button.GrapesButtonDefaults.CircularIndicatorBorderThickness
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author : danyboucanova
 * @since : 16/05/2022, Mon
 **/
@Composable
fun GrapesButton(
    modifier: Modifier = Modifier,
    buttonStyle: GrapesButtonStyle = GrapesButtonStyleDefaults.primary,
    enabled: Boolean = true,
    showLoadingIndicator: Boolean = false,
    onClick: (() -> Unit) = {},
    content: @Composable RowScope.() -> Unit,
) {
    ProvideButtonRippleColor(
        rippleColor = buttonStyle.rippleColor,
        showLoadingIndicator = showLoadingIndicator
    ) {
        Button(
            modifier = modifier
                .widthIn(
                    min = buttonStyle.minSize.width.dp
                )
                .heightIn(
                    min = buttonStyle.minSize.height.dp
                ),
            shape = buttonStyle.shape,
            border = buttonStyle.borderStroke,
            colors = buttonStyle.colors,
            contentPadding = buttonStyle.contentPadding,
            elevation = null,
            enabled = enabled,
            onClick = onClick.takeUnless { showLoadingIndicator } ?: {},
            content = {
                if (showLoadingIndicator.not()) {
                    ProvideTextStyle(value = buttonStyle.textStyle) {
                        content()
                    }
                } else {
                    val contentColor = buttonStyle.colors.contentColor(enabled = enabled)
                    CircularProgressIndicator(
                        modifier = Modifier.size(buttonStyle.iconSize),
                        color = contentColor.value,
                        strokeWidth = CircularIndicatorBorderThickness,
                    )
                }
            }
        )
    }
}

/**
 * Default ripple theme use [LocalContentColor] as Ripple color but we require different
 * colors depending on button.
 * We need to provide [LocalRippleTheme] with specific color.
 */
@Composable
private fun ProvideButtonRippleColor(rippleColor: Color, showLoadingIndicator: Boolean, content: @Composable () -> Unit) {
    val rippleTheme = if (showLoadingIndicator.not()) {
        GrapesButtonRippleTheme(rippleColor = rippleColor)
    } else {
        GrapesButtonClearRippleTheme()
    }

    CompositionLocalProvider(
        LocalRippleTheme provides rippleTheme,
        content = content
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
                modifier = Modifier,
                buttonStyle = GrapesButtonStyleDefaults.primary,
            ) {
                Text(
                    text = "Button Primary Enabled",
                )
            }

            GrapesButton(
                modifier = Modifier,
                buttonStyle = GrapesButtonStyleDefaults.primary,
                enabled = false
            ) {
                Text(
                    text = "Button Primary Disabled",
                )
            }

            GrapesButton(
                modifier = Modifier,
                buttonStyle = GrapesButtonStyleDefaults.primary,
                showLoadingIndicator = true,
            ) {
                Text(
                    text = "This should not be visible",
                )
            }
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
fun ButtonPrimarySmallPreview() {
    GrapesTheme {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            GrapesButton(
                modifier = Modifier,
                buttonStyle = GrapesButtonStyleDefaults.primarySmall,
            ) {
                Text(
                    text = "Primary Small Enabled",
                )
            }

            GrapesButton(
                modifier = Modifier,
                buttonStyle = GrapesButtonStyleDefaults.primarySmall,
                enabled = false
            ) {
                Text(
                    text = "Primary Small Disabled",
                )
            }

            GrapesButton(
                modifier = Modifier,
                buttonStyle = GrapesButtonStyleDefaults.primarySmall,
                showLoadingIndicator = true,
            ) {
                Text(
                    text = "This should not be shown",
                )
            }
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
                modifier = Modifier,
                buttonStyle = GrapesButtonStyleDefaults.secondary,
            ) {
                Text(
                    text = "Button Secondary Enabled",
                )
            }

            GrapesButton(
                modifier = Modifier,
                buttonStyle = GrapesButtonStyleDefaults.secondary,
                enabled = false
            ) {
                Text(
                    text = "Button Secondary Disabled",
                )
            }

            GrapesButton(
                modifier = Modifier,
                buttonStyle = GrapesButtonStyleDefaults.secondary,
                showLoadingIndicator = true,
            ) {
                Text(
                    text = "This should not be visible",
                )
            }
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
                modifier = Modifier,
                buttonStyle = GrapesButtonStyleDefaults.secondarySmall,
            ) {
                Text(
                    text = "Secondary Small Enabled",
                )
            }

            GrapesButton(
                modifier = Modifier,
                buttonStyle = GrapesButtonStyleDefaults.secondarySmall,
                enabled = false
            ) {
                Text(
                    text = "Secondary Small Disabled",
                )
            }

            GrapesButton(
                modifier = Modifier,
                buttonStyle = GrapesButtonStyleDefaults.secondarySmall,
                showLoadingIndicator = true,
            ) {
                Text(
                    text = "Should not be visible",
                )
            }
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
                modifier = Modifier,
                buttonStyle = GrapesButtonStyleDefaults.text,
            ) {
                Text(
                    text = "Button Text Enabled",
                )
            }

            GrapesButton(
                modifier = Modifier,
                buttonStyle = GrapesButtonStyleDefaults.text,
                enabled = false
            ) {
                Text(
                    text = "Button Text Disabled",
                )
            }

            GrapesButton(
                modifier = Modifier,
                buttonStyle = GrapesButtonStyleDefaults.text,
                showLoadingIndicator = true,
            ) {
                Text(
                    text = "Should not be visible",
                )
            }
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
                modifier = Modifier,
                buttonStyle = GrapesButtonStyleDefaults.textSmall,
            ) {
                Text(
                    text = "Secondary Text Enabled",
                )
            }

            GrapesButton(
                modifier = Modifier,
                buttonStyle = GrapesButtonStyleDefaults.textSmall,
                enabled = false
            ) {
                Text(
                    text = "Secondary Text Disabled",
                )
            }

            GrapesButton(
                modifier = Modifier,
                buttonStyle = GrapesButtonStyleDefaults.textSmall,
                showLoadingIndicator = true,
            ) {
                Text(
                    text = "Should not be visible",
                )
            }
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
                modifier = Modifier,
                buttonStyle = GrapesButtonStyleDefaults.warning,
            ) {
                Text(
                    text = "Button Warning Enabled",
                )
            }

            GrapesButton(
                modifier = Modifier,
                buttonStyle = GrapesButtonStyleDefaults.warning,
                enabled = false
            ) {
                Text(
                    text = "Button Warning Disabled",
                )
            }

            GrapesButton(
                modifier = Modifier,
                buttonStyle = GrapesButtonStyleDefaults.warning,
                showLoadingIndicator = true
            ) {
                Text(
                    text = "Should not be visible",
                )
            }
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
                modifier = Modifier,
                buttonStyle = GrapesButtonStyleDefaults.alert,
            ) {
                Text(
                    text = "Button Alert Enabled",
                )
            }

            GrapesButton(
                modifier = Modifier,
                buttonStyle = GrapesButtonStyleDefaults.alert,
                enabled = false
            ) {
                Text(
                    text = "Button Alert Disabled",
                )
            }

            GrapesButton(
                modifier = Modifier,
                buttonStyle = GrapesButtonStyleDefaults.alert,
                showLoadingIndicator = true
            ) {
                Text(
                    text = "Should not be visible",
                )
            }
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
                modifier = Modifier,
                buttonStyle = GrapesButtonStyleDefaults.linkPrimary,
            ) {
                Text(
                    text = "Link Primary Enabled",
                )
            }

            GrapesButton(
                modifier = Modifier,
                buttonStyle = GrapesButtonStyleDefaults.linkPrimary,
                enabled = false
            ) {
                Text(
                    text = "Link Primary Disabled",
                )
            }

            GrapesButton(
                modifier = Modifier,
                buttonStyle = GrapesButtonStyleDefaults.linkPrimary,
                showLoadingIndicator = true,
            ) {
                Text(
                    text = "Should not be visible",
                )
            }
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
                modifier = Modifier,
                buttonStyle = GrapesButtonStyleDefaults.linkSecondary,
            ) {
                Text(
                    text = "Link Secondary Enabled",
                )
            }

            GrapesButton(
                modifier = Modifier,
                buttonStyle = GrapesButtonStyleDefaults.linkSecondary,
                enabled = false
            ) {
                Text(
                    text = "Link Secondary Disabled",
                )
            }

            GrapesButton(
                modifier = Modifier,
                buttonStyle = GrapesButtonStyleDefaults.linkSecondary,
                showLoadingIndicator = true,
            ) {
                Text(
                    text = "Should not be visible",
                )
            }
        }
    }
}
//endregion Previews
