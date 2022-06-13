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
import androidx.compose.ui.unit.dp
import com.spendesk.grapes.compose.model.GrapesConfigurationState
import com.spendesk.grapes.compose.theme.GrapesTheme

@Composable
fun GrapesSurface(
    modifier: Modifier = Modifier,
    shape: Shape = CircleShape,
    configuration: GrapesConfigurationState,
    content: @Composable () -> Unit
) {
    val (backgroundColor: Color, borderColor: Color) = when (configuration) {
        GrapesConfigurationState.SUCCESS -> Pair(GrapesTheme.colors.mainSuccessLightest, GrapesTheme.colors.mainSuccessLighter)
        GrapesConfigurationState.INFORMATION -> Pair(GrapesTheme.colors.mainInfoLightest, GrapesTheme.colors.mainInfoLighter)
        GrapesConfigurationState.NEUTRAL -> Pair(GrapesTheme.colors.mainNeutralLighter, GrapesTheme.colors.mainNeutralNormal)
        GrapesConfigurationState.ALERT -> Pair(GrapesTheme.colors.mainAlertLightest, GrapesTheme.colors.mainAlertLighter)
        GrapesConfigurationState.WARNING -> Pair(GrapesTheme.colors.mainWarningLightest, GrapesTheme.colors.mainWarningLighter)
        GrapesConfigurationState.BLOCKED -> Pair(GrapesTheme.colors.mainPrimaryLightest, GrapesTheme.colors.mainPrimaryLighter)
    }

    Surface(
        modifier = modifier,
        border = BorderStroke(1.dp, borderColor),
        shape = shape,
        color = backgroundColor
    ) {
        content()
    }
}

@Preview(group = "Surface")
@Composable
fun grapesSurfacePreview() {
    GrapesTheme {
        Column(verticalArrangement = Arrangement.spacedBy(GrapesTheme.dimensions.paddingSmall)) {
            GrapesSurface(modifier = Modifier.size(50.dp), configuration = GrapesConfigurationState.SUCCESS, content = {})
            GrapesSurface(modifier = Modifier.size(50.dp),configuration = GrapesConfigurationState.INFORMATION, content = {})
            GrapesSurface(modifier = Modifier.size(50.dp),configuration = GrapesConfigurationState.ALERT, content = {})
            GrapesSurface(modifier = Modifier.size(50.dp),configuration = GrapesConfigurationState.WARNING, content = {})
            GrapesSurface(modifier = Modifier.size(50.dp),configuration = GrapesConfigurationState.BLOCKED, content = {})
        }
    }
}