package com.spendesk.grapes.compose.button

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.RippleConfiguration
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author : kelian
 * @since : 23/09/2024
 **/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun grapesButtonRippleConfiguration(rippleColor: Color): RippleConfiguration =
    RippleConfiguration(
        color = rippleColor,
        rippleAlpha = GrapesButtonDefaults.buttonRippleAlpha(
            contentColor = LocalContentColor.current,
            lightTheme = GrapesTheme.colors.isLight
        )
    )
