package com.spendesk.grapes.compose.callout

import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author jean-philippe
 * @since 17/10/2023, Tuesday
 **/

@Immutable
object GrapesCalloutDefaults {
    val titleIconSize = 16.dp
    val borderThickness = 1.dp
    val signatureImageSize = 32.dp
}

@Composable
internal fun ErrorGrapesCalloutColors(
    containerColor: Color = GrapesTheme.colors.mainAlertLightest,
    titleColor: Color = GrapesTheme.colors.mainAlertNormal,
    contentColor: Color = LocalContentColor.current,
    borderStoreColor: Color = GrapesTheme.colors.mainAlertLighter,
) : GrapesCalloutColors = DefaultGrapesCalloutColors(
    containerColor = containerColor,
    titleColor = titleColor,
    contentColor = contentColor,
    borderStoreColor = borderStoreColor,
)

@Composable
internal fun WarningGrapesCalloutColors(
    containerColor: Color = GrapesTheme.colors.mainWarningLightest,
    titleColor: Color = GrapesTheme.colors.mainWarningNormal,
    contentColor: Color = LocalContentColor.current,
    borderStoreColor: Color = GrapesTheme.colors.mainWarningLighter,
) : GrapesCalloutColors = DefaultGrapesCalloutColors(
    containerColor = containerColor,
    titleColor = titleColor,
    contentColor = contentColor,
    borderStoreColor = borderStoreColor,
)

@Composable
internal fun InfoGrapesCalloutColors(
    containerColor: Color = GrapesTheme.colors.mainInfoLightest,
    titleColor: Color = GrapesTheme.colors.mainInfoNormal,
    contentColor: Color = LocalContentColor.current,
    borderStoreColor: Color = GrapesTheme.colors.mainInfoLighter,
) : GrapesCalloutColors = DefaultGrapesCalloutColors(
    containerColor = containerColor,
    titleColor = titleColor,
    contentColor = contentColor,
    borderStoreColor = borderStoreColor,
)

@Composable
internal fun SuccessGrapesCalloutColors(
    containerColor: Color = GrapesTheme.colors.mainSuccessLightest,
    titleColor: Color = GrapesTheme.colors.mainSuccessNormal,
    contentColor: Color = LocalContentColor.current,
    borderStoreColor: Color = GrapesTheme.colors.mainSuccessLighter,
) : GrapesCalloutColors = DefaultGrapesCalloutColors(
    containerColor = containerColor,
    titleColor = titleColor,
    contentColor = contentColor,
    borderStoreColor = borderStoreColor,
)

@Composable
internal fun NeutralGrapesCalloutColors(
    containerColor: Color = GrapesTheme.colors.mainNeutralLighter,
    titleColor: Color = GrapesTheme.colors.mainNeutralDarker,
    contentColor: Color = LocalContentColor.current,
    borderStoreColor: Color = GrapesTheme.colors.mainNeutralLighter,
) : GrapesCalloutColors = DefaultGrapesCalloutColors(
    containerColor = containerColor,
    titleColor = titleColor,
    contentColor = contentColor,
    borderStoreColor = borderStoreColor,
)

@Immutable
private class DefaultGrapesCalloutColors constructor(
    private val containerColor: Color,
    private val titleColor: Color,
    private val contentColor: Color,
    private val borderStoreColor: Color,
) : GrapesCalloutColors {
    @Composable
    override fun containerColor(): State<Color> {
        return rememberUpdatedState(containerColor)
    }

    @Composable
    override fun titleColor(): State<Color> {
        return rememberUpdatedState(titleColor)
    }

    @Composable
    override fun contentColor(): State<Color> {
        return rememberUpdatedState(contentColor)
    }

    @Composable
    override fun borderStrokeColor(): State<Color> {
        return rememberUpdatedState(borderStoreColor)
    }
}

@Stable
interface GrapesCalloutColors {
    @Composable
    fun containerColor(): State<Color>

    @Composable
    fun titleColor(): State<Color>

    @Composable
    fun contentColor(): State<Color>

    @Composable
    fun borderStrokeColor(): State<Color>
}

internal enum class CalloutType {
    ERROR,
    WARNING,
    INFO,
    SUCCESS,
    NEUTRAL,
}

internal data class GrapesCalloutType(val type: CalloutType)

internal val LocalGrapesCalloutType = compositionLocalOf { GrapesCalloutType(CalloutType.NEUTRAL) }
