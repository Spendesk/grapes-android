package com.spendesk.grapes.compose.callout

import androidx.compose.material.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.spendesk.grapes.compose.R
import com.spendesk.grapes.compose.callout.atoms.GrapesCalloutContentBottomCTA
import com.spendesk.grapes.compose.callout.atoms.GrapesCalloutContentBottomSignature
import com.spendesk.grapes.compose.callout.molecules.GrapesCalloutContent
import com.spendesk.grapes.compose.icons.GrapesIcon
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author jean-philippe
 * @since 16/10/2023, Monday
 **/
@Composable
fun GrapesErrorCallout(
    title: String,
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    CompositionLocalProvider(
        LocalGrapesCalloutType provides GrapesCalloutType(CalloutType.ERROR),
        LocalContentColor provides GrapesTheme.colors.mainComplementary,
    ) {
        val calloutColors = ErrorGrapesCalloutColors()

        GrapesCoreCallout(
            leadingIcon = {
                GrapesIcon(
                    icon = R.drawable.ic_error,
                    contentDescription = "Error callout icon",
                    tint = calloutColors.titleColor().value,
                )
            },
            title = title,
            colors = calloutColors,
            modifier = modifier,
            content = { content() },
        )
    }
}

@Composable
fun GrapesWarningCallout(
    title: String,
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    CompositionLocalProvider(
        LocalGrapesCalloutType provides GrapesCalloutType(CalloutType.WARNING),
        LocalContentColor provides GrapesTheme.colors.mainComplementary,
    ) {
        val calloutColors = WarningGrapesCalloutColors()

        GrapesCoreCallout(
            leadingIcon = {
                GrapesIcon(
                    icon = R.drawable.ic_warning,
                    contentDescription = "Warning callout icon",
                    tint = calloutColors.titleColor().value,
                )
            },
            title = title,
            colors = calloutColors,
            modifier = modifier,
            content = { content() },
        )
    }
}

@Composable
fun GrapesInfoCallout(
    title: String,
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    CompositionLocalProvider(
        LocalGrapesCalloutType provides GrapesCalloutType(CalloutType.INFO),
        LocalContentColor provides GrapesTheme.colors.mainComplementary,
    ) {
        val calloutColors = InfoGrapesCalloutColors()

        GrapesCoreCallout(
            leadingIcon = {
                GrapesIcon(
                    icon = R.drawable.ic_information,
                    contentDescription = "Warning callout icon",
                    tint = calloutColors.titleColor().value,
                )
            },
            title = title,
            colors = calloutColors,
            modifier = modifier,
            content = { content() },
        )
    }
}

@Composable
fun GrapesSuccessCallout(
    title: String,
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    CompositionLocalProvider(
        LocalGrapesCalloutType provides GrapesCalloutType(CalloutType.SUCCESS),
        LocalContentColor provides GrapesTheme.colors.mainComplementary,
    ) {
        val calloutColors = SuccessGrapesCalloutColors()

        val leadingIcon = @Composable {
            GrapesIcon(
                icon = R.drawable.ic_success,
                contentDescription = "Success callout icon",
                tint = calloutColors.titleColor().value,
            )
        }

        GrapesCoreCallout(
            title = title,
            leadingIcon = leadingIcon,
            colors = calloutColors,
            modifier = modifier,
            content = { content() },
        )
    }
}

@Composable
fun GrapesNeutralCallout(
    title: String,
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    CompositionLocalProvider(
        LocalGrapesCalloutType provides GrapesCalloutType(CalloutType.NEUTRAL),
        LocalContentColor provides GrapesTheme.colors.mainComplementary,
    ) {
        val calloutColors = NeutralGrapesCalloutColors()

        GrapesCoreCallout(
            title = title,
            colors = calloutColors,
            modifier = modifier,
            content = { content() },
        )
    }
}

@Preview
@Composable
private fun CalloutPreview(
    @PreviewParameter(CalloutProvider::class) callout: Callouts,
) {
    GrapesTheme {
        when (callout) {
            is Callouts.Error -> GrapesErrorCallout(
                title = callout.title,
                content = { PreviewCalloutContent(callout) },
            )

            is Callouts.Warning -> GrapesWarningCallout(
                title = callout.title,
                content = { PreviewCalloutContent(callout) },
            )
            is Callouts.Info -> GrapesInfoCallout(
                title = callout.title,
                content = { PreviewCalloutContent(callout) },
            )
            is Callouts.Success -> GrapesSuccessCallout(
                title = callout.title,
                content = { PreviewCalloutContent(callout) },
            )
            is Callouts.Neutral -> GrapesNeutralCallout(
                title = callout.title,
                content = { PreviewCalloutContent(callout) },
            )
        }
    }
}

@Composable
private fun PreviewCalloutContent(callout: Callouts) {
    callout.description?.let {
        when (callout.contentType) {
            CalloutContentType.Simple -> GrapesCalloutContent(description = it)
            CalloutContentType.Action -> GrapesCalloutContent(description = it) {
                GrapesCalloutContentBottomCTA(
                    primaryButtonText = "Primary Action",
                    secondaryButtonText = "Secondary Action",
                    onPrimaryButtonClick = {},
                    onSecondaryButtonClick = {},
                )
            }
            CalloutContentType.Signature -> GrapesCalloutContent(description = it) {
                GrapesCalloutContentBottomSignature(fullName = "Signature")
            }
            null -> {
                //
            }
        }
    }
}
