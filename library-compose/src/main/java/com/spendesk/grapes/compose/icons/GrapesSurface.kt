package com.spendesk.grapes.compose.icons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.spendesk.grapes.compose.model.GrapesConfigurationStatus
import com.spendesk.grapes.compose.theme.GrapesTheme

@Composable
fun GrapesSurface(
    configuration: GrapesConfigurationStatus,
    modifier: Modifier = Modifier,
    shape: Shape = CircleShape,
    hasBorder: Boolean = true,
    content: @Composable () -> Unit
) {
    val (backgroundColor: Color, borderColor: Color) = when (configuration) {
        GrapesConfigurationStatus.SUCCESS -> Pair(GrapesTheme.colors.mainSuccessLightest, GrapesTheme.colors.mainSuccessLighter)
        GrapesConfigurationStatus.INFORMATION -> Pair(GrapesTheme.colors.mainInfoLightest, GrapesTheme.colors.mainInfoLighter)
        GrapesConfigurationStatus.NEUTRAL -> Pair(GrapesTheme.colors.mainNeutralLighter, GrapesTheme.colors.mainNeutralNormal)
        GrapesConfigurationStatus.ALERT -> Pair(GrapesTheme.colors.mainAlertLightest, GrapesTheme.colors.mainAlertLighter)
        GrapesConfigurationStatus.WARNING -> Pair(GrapesTheme.colors.mainWarningLightest, GrapesTheme.colors.mainWarningLighter)
        GrapesConfigurationStatus.BLOCKED -> Pair(GrapesTheme.colors.mainPrimaryLightest, GrapesTheme.colors.mainPrimaryLighter)
    }

    val border = BorderStroke(1.dp, borderColor).takeIf { hasBorder }

    Surface(
        modifier = modifier,
        border = border,
        shape = shape,
        color = backgroundColor,
        content = content
    )
}

@Preview(group = "Surface")
@Composable
fun grapesSurfacePreview() {
    GrapesTheme {
        Column(verticalArrangement = Arrangement.spacedBy(GrapesTheme.dimensions.paddingSmall)) {
            GrapesSurface(modifier = Modifier.size(50.dp), configuration = GrapesConfigurationStatus.SUCCESS, content = {})
            GrapesSurface(modifier = Modifier.size(50.dp), configuration = GrapesConfigurationStatus.INFORMATION, content = {})
            GrapesSurface(modifier = Modifier.size(50.dp), configuration = GrapesConfigurationStatus.ALERT, content = {})
            GrapesSurface(modifier = Modifier.size(50.dp), configuration = GrapesConfigurationStatus.WARNING, content = {})
            GrapesSurface(modifier = Modifier.size(50.dp), configuration = GrapesConfigurationStatus.BLOCKED, content = {})
        }
    }
}
