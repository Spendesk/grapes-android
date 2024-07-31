package com.spendesk.grapes.compose.appbar

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.spendesk.grapes.compose.theme.GrapesTheme

object GrapesTopAppBarDefaults {

    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    fun mediumTopAppBarColors(
        containerColor: Color = GrapesTheme.colors.structureBackground,
        scrolledContainerColor: Color = GrapesTheme.colors.structureBackground,
        navigationIconContentColor: Color = GrapesTheme.colors.structureComplementary,
        titleContentColor: Color = GrapesTheme.colors.structureComplementary,
        actionIconContentColor: Color = GrapesTheme.colors.structureComplementary,
    ) = TopAppBarDefaults.mediumTopAppBarColors(
        containerColor = containerColor,
        scrolledContainerColor = scrolledContainerColor,
        navigationIconContentColor = navigationIconContentColor,
        titleContentColor = titleContentColor,
        actionIconContentColor = actionIconContentColor,
    )
}
