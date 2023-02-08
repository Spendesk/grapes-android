package com.spendesk.grapes.compose.button

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.Text
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spendesk.grapes.compose.R
import com.spendesk.grapes.compose.button.GrapesButtonDefaults.CircularIndicatorBorderThickness
import com.spendesk.grapes.compose.button.GrapesButtonDefaults.iconSize
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author : danyboucanova
 * @since : 16/05/2022, Mon
 **/
@Composable
fun GrapesButton(
    modifier: Modifier = Modifier,
    buttonStyle: GrapesButtonStyle = GrapesButtonStyleDefaults.primary,
    state: GrapesButtonState = GrapesButtonState.Enabled,
    onClick: (() -> Unit) = {},
    leadingIcon: Painter? = null,
    content: @Composable RowScope.() -> Unit
) {
    val enabled = when (state) {
        GrapesButtonState.Enabled, GrapesButtonState.ShowCircularIndicator -> true
        GrapesButtonState.Disabled -> false
    }

    val showLoadingIndicator = state is GrapesButtonState.ShowCircularIndicator

    val interactionSource = if (showLoadingIndicator) {
        NoRippleInteractionSource()
    } else {
        remember { MutableInteractionSource() }
    }

    ProvideButtonRippleColor(
        rippleColor = buttonStyle.rippleColor
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
            interactionSource = interactionSource,
            content = {
                leadingIcon?.let {
                    Icon(
                        modifier = Modifier.size(iconSize),
                        painter = leadingIcon,
                        contentDescription = null,
                        tint = buttonStyle.colors.contentColor(enabled = enabled).value
                    )
                    Spacer(modifier = Modifier.size(GrapesButtonDefaults.IconSpacing))
                }

                if (showLoadingIndicator) {
                    val contentColor = buttonStyle.colors.contentColor(enabled = enabled)
                    CircularProgressIndicator(
                        modifier = Modifier.size(buttonStyle.iconSize),
                        color = contentColor.value,
                        strokeWidth = CircularIndicatorBorderThickness,
                    )
                } else {
                    ProvideTextStyle(value = buttonStyle.textStyle) {
                        content()
                    }
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
private fun ProvideButtonRippleColor(rippleColor: Color, content: @Composable () -> Unit) {
    CompositionLocalProvider(
        LocalRippleTheme provides GrapesButtonRippleTheme(rippleColor = rippleColor),
        content = content
    )
}

//region Previews

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
                state = GrapesButtonState.Disabled
            ) {
                Text(
                    text = "Button Text Disabled",
                )
            }

            GrapesButton(
                modifier = Modifier,
                buttonStyle = GrapesButtonStyleDefaults.text,
                state = GrapesButtonState.ShowCircularIndicator
            ) {
                Text(
                    text = "Should not be visible",
                )
            }
        }
    }
}

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
                buttonStyle = GrapesButtonStyleDefaults.primary
            ) {
                Text(
                    text = "Button Primary Enabled",
                )
            }

            GrapesButton(
                buttonStyle = GrapesButtonStyleDefaults.primary,
                state = GrapesButtonState.Disabled
            ) {
                Text(
                    text = "Button Primary Disabled",
                )
            }

            GrapesButton(
                buttonStyle = GrapesButtonStyleDefaults.primary,
                state = GrapesButtonState.ShowCircularIndicator
            ) {
                Text(
                    text = "This should not be visible",
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
                state = GrapesButtonState.Disabled
            ) {
                Text(
                    text = "Button Secondary Disabled",
                )
            }

            GrapesButton(
                modifier = Modifier,
                buttonStyle = GrapesButtonStyleDefaults.secondary,
                state = GrapesButtonState.ShowCircularIndicator
            ) {
                Text(
                    text = "This should not be visible",
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
                state = GrapesButtonState.Disabled
            ) {
                Text(
                    text = "Button Warning Disabled",
                )
            }

            GrapesButton(
                modifier = Modifier,
                buttonStyle = GrapesButtonStyleDefaults.warning,
                state = GrapesButtonState.ShowCircularIndicator
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
                state = GrapesButtonState.Disabled
            ) {
                Text(
                    text = "Button Alert Disabled",
                )
            }

            GrapesButton(
                modifier = Modifier,
                buttonStyle = GrapesButtonStyleDefaults.alert,
                state = GrapesButtonState.ShowCircularIndicator
            ) {
                Text(
                    text = "Should not be visible",
                )
            }
        }
    }
}


@Preview(
    name = "Primary Small",
    group = "Small",
    widthDp = 372,
    showBackground = true,
)
@Composable
fun ButtonPrimarySmallPreview() {
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
                buttonStyle = GrapesButtonStyleDefaults.primarySmall,
            ) {
                Text(
                    text = "Primary Small Enabled",
                )
            }

            GrapesButton(
                modifier = Modifier,
                buttonStyle = GrapesButtonStyleDefaults.primarySmall,
                state = GrapesButtonState.Disabled
            ) {
                Text(
                    text = "Primary Small Disabled",
                )
            }

            GrapesButton(
                modifier = Modifier,
                buttonStyle = GrapesButtonStyleDefaults.primarySmall,
                state = GrapesButtonState.ShowCircularIndicator
            ) {
                Text(
                    text = "This should not be shown",
                )
            }
        }
    }
}

@Preview(
    name = "Secondary Small",
    group = "Small",
    widthDp = 372,
    showBackground = true,
)
@Composable
fun ButtonSecondarySmallPreview() {
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
                buttonStyle = GrapesButtonStyleDefaults.secondarySmall,
            ) {
                Text(
                    text = "Secondary Small Enabled",
                )
            }

            GrapesButton(
                modifier = Modifier,
                buttonStyle = GrapesButtonStyleDefaults.secondarySmall,
                state = GrapesButtonState.Disabled
            ) {
                Text(
                    text = "Secondary Small Disabled",
                )
            }

            GrapesButton(
                modifier = Modifier,
                buttonStyle = GrapesButtonStyleDefaults.secondarySmall,
                state = GrapesButtonState.ShowCircularIndicator
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
    widthDp = 372,
    showBackground = true,
    backgroundColor = 0xFF421896 // Primary Dark
)
@Composable
fun ButtonTextSmallPreview() {
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
                buttonStyle = GrapesButtonStyleDefaults.textSmall,
            ) {
                Text(
                    text = "Secondary Text Enabled",
                )
            }

            GrapesButton(
                modifier = Modifier,
                buttonStyle = GrapesButtonStyleDefaults.textSmall,
                state = GrapesButtonState.Disabled
            ) {
                Text(
                    text = "Secondary Text Disabled",
                )
            }

            GrapesButton(
                modifier = Modifier,
                buttonStyle = GrapesButtonStyleDefaults.textSmall,
                state = GrapesButtonState.ShowCircularIndicator
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
    widthDp = 372,
    showBackground = true,
)
@Composable
fun ButtonLinkPrimaryPreview() {
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
                buttonStyle = GrapesButtonStyleDefaults.linkPrimary,
            ) {
                Text(
                    text = "Link Primary Enabled",
                )
            }

            GrapesButton(
                modifier = Modifier,
                buttonStyle = GrapesButtonStyleDefaults.linkPrimary,
                state = GrapesButtonState.Disabled
            ) {
                Text(
                    text = "Link Primary Disabled",
                )
            }

            GrapesButton(
                modifier = Modifier,
                buttonStyle = GrapesButtonStyleDefaults.linkPrimary,
                state = GrapesButtonState.ShowCircularIndicator
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
    widthDp = 372,
    showBackground = true,
)
@Composable
fun ButtonLinkSecondaryPreview() {
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
                buttonStyle = GrapesButtonStyleDefaults.linkSecondary,
                leadingIcon = painterResource(id = R.drawable.ic_error)
            ) {
                Text(
                    text = "Icon Link Secondary Enabled",
                )
            }

            GrapesButton(
                modifier = Modifier,
                buttonStyle = GrapesButtonStyleDefaults.linkSecondary,
                state = GrapesButtonState.Disabled,
                leadingIcon = painterResource(id = R.drawable.ic_error)
            ) {
                Text(
                    text = "Icon Link Secondary Disabled",
                )
            }

            GrapesButton(
                modifier = Modifier,
                buttonStyle = GrapesButtonStyleDefaults.linkSecondary,
                state = GrapesButtonState.ShowCircularIndicator
            ) {
                Text(
                    text = "Should not be visible",
                )
            }
        }
    }
}
//endregion Previews
