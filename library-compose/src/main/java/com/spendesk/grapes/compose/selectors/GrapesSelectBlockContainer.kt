package com.spendesk.grapes.compose.selectors

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author : dany
 * @since : 02/11/2023, Thu
 **/

object GrapesSelectBlockContainerDefaultColors {
    val selectedBorderColor: Color @Composable get() = GrapesTheme.colors.mainPrimaryDark
    val selectedBackgroundColor: Color @Composable get() = GrapesTheme.colors.mainPrimaryLightest
}

@Composable
fun GrapesSelectBlockContainer(
    isSelected: Boolean,
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    val borderModifier = if (isSelected) {
        Modifier.border(
            width = GrapesTheme.dimensions.borderLarge,
            color = GrapesSelectBlockContainerDefaultColors.selectedBorderColor,
            shape = GrapesTheme.shapes.shape2
        )
    } else {
        Modifier
    }
    val backgroundModifier = if (isSelected) {
        Modifier.background(
            color = GrapesSelectBlockContainerDefaultColors.selectedBackgroundColor,
            GrapesTheme.shapes.shape2
        )
    } else {
        Modifier
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .then(borderModifier)
            .then(backgroundModifier)
            .clip(GrapesTheme.shapes.shape2)
    ) {
        content()
    }
}
