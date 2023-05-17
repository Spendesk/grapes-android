package com.spendesk.grapes.compose.template.molecule

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author Kélian CLERC
 * @since 28/09/2022
 */
@Composable
internal fun InformativeComponent(
    middlePart: @Composable () -> Unit,
    bottomPart: @Composable () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    listOf(GrapesTheme.colors.mainPrimaryNormal, GrapesTheme.colors.mainPrimaryDark)
                )
            )
            .padding(GrapesTheme.dimensions.paddingLarge),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))
        middlePart()
        Spacer(modifier = Modifier.weight(1f))
        bottomPart()
    }
}
