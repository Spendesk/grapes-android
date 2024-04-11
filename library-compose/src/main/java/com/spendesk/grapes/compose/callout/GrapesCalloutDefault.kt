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
    containerColor: Color = GrapesTheme.colors.alertLightest,
    titleColor: Color = GrapesTheme.colors.alertNormal,
    contentColor: Color = LocalContentColor.current,
    borderStoreColor: Color = GrapesTheme.colors.alertLighter,
): GrapesCalloutColors = DefaultGrapesCalloutColors(
    containerColor = containerColor,
    titleColor = titleColor,
    contentColor = contentColor,
    borderStoreColor = borderStoreColor,
)

@Composable
internal fun WarningGrapesCalloutColors(
    containerColor: Color = GrapesTheme.colors.warningLightest,
    titleColor: Color = GrapesTheme.colors.warningNormal,
    contentColor: Color = LocalContentColor.current,
    borderStoreColor: Color = GrapesTheme.colors.warningLighter,
): GrapesCalloutColors = DefaultGrapesCalloutColors(
    containerColor = containerColor,
    titleColor = titleColor,
    contentColor = contentColor,
    borderStoreColor = borderStoreColor,
)

@Composable
internal fun InfoGrapesCalloutColors(
    containerColor: Color = GrapesTheme.colors.infoLightest,
    titleColor: Color = GrapesTheme.colors.infoNormal,
    contentColor: Color = LocalContentColor.current,
    borderStoreColor: Color = GrapesTheme.colors.infoLighter,
): GrapesCalloutColors = DefaultGrapesCalloutColors(
    containerColor = containerColor,
    titleColor = titleColor,
    contentColor = contentColor,
    borderStoreColor = borderStoreColor,
)

@Composable
internal fun SuccessGrapesCalloutColors(
    containerColor: Color = GrapesTheme.colors.successLightest,
    titleColor: Color = GrapesTheme.colors.successNormal,
    contentColor: Color = LocalContentColor.current,
    borderStoreColor: Color = GrapesTheme.colors.successLighter,
): GrapesCalloutColors = DefaultGrapesCalloutColors(
    containerColor = containerColor,
    titleColor = titleColor,
    contentColor = contentColor,
    borderStoreColor = borderStoreColor,
)

@Composable
internal fun NeutralGrapesCalloutColors(
    titleColor: Color = GrapesTheme.colors.neutralDark,
    containerColor: Color = GrapesTheme.colors.neutralLightest,
    contentColor: Color = LocalContentColor.current,
    borderStoreColor: Color = GrapesTheme.colors.neutralLightest,
): GrapesCalloutColors = DefaultGrapesCalloutColors(
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
