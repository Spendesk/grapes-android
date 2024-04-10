package com.spendesk.grapes.compose.tag

import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author jean-philippe
 * @since 18/10/2023, Wednesday
 **/

@Immutable
object GrapesTagDefaults {

    val iconSize = 12.dp

    val borderThickness = 1.dp
}

@Composable
internal fun ErrorGrapesTagColors(
    containerColor: Color = GrapesTheme.colors.alertLightest,
    contentColor: Color = LocalContentColor.current,
    borderStoreColor: Color = GrapesTheme.colors.alertLighter,
): GrapesTagColors = DefaultGrapesTagColors(
    containerColor = containerColor,
    contentColor = contentColor,
    borderStoreColor = borderStoreColor,
)

@Composable
internal fun WarningGrapesTagColors(
    containerColor: Color = GrapesTheme.colors.mainWarningLightest,
    contentColor: Color = LocalContentColor.current,
    borderStoreColor: Color = GrapesTheme.colors.mainWarningLighter,
): GrapesTagColors = DefaultGrapesTagColors(
    containerColor = containerColor,
    contentColor = contentColor,
    borderStoreColor = borderStoreColor,
)

@Composable
internal fun InfoGrapesTagColors(
    containerColor: Color = GrapesTheme.colors.mainInfoLightest,
    contentColor: Color = LocalContentColor.current,
    borderStoreColor: Color = GrapesTheme.colors.mainInfoLighter,
): GrapesTagColors = DefaultGrapesTagColors(
    containerColor = containerColor,
    contentColor = contentColor,
    borderStoreColor = borderStoreColor,
)

@Composable
internal fun SuccessGrapesTagColors(
    containerColor: Color = GrapesTheme.colors.mainSuccessLightest,
    contentColor: Color = LocalContentColor.current,
    borderStoreColor: Color = GrapesTheme.colors.mainSuccessLighter,
): GrapesTagColors = DefaultGrapesTagColors(
    containerColor = containerColor,
    contentColor = contentColor,
    borderStoreColor = borderStoreColor,
)

private class DefaultGrapesTagColors constructor(
    private val containerColor: Color,
    private val contentColor: Color,
    private val borderStoreColor: Color,
) : GrapesTagColors {

    @Composable
    override fun containerColor(): State<Color> {
        return rememberUpdatedState(containerColor)
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
interface GrapesTagColors {

    @Composable
    fun containerColor(): State<Color>

    @Composable
    fun contentColor(): State<Color>

    @Composable
    fun borderStrokeColor(): State<Color>
}
