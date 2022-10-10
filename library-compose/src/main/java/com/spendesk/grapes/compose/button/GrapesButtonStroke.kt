package com.spendesk.grapes.compose.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author Kélian CLERC
 * @since 28/09/2022
 */

@Composable
fun primaryButtonStroke(): BorderStroke? = null

@Composable
fun secondaryButtonStroke(): BorderStroke = BorderStroke(
    1.dp,
    GrapesTheme.colors.mainNeutralNormal
)

@Composable
fun alertButtonStroke(): BorderStroke? = null

@Composable
fun warningButtonStroke(): BorderStroke? = null

@Composable
fun textButtonStroke(): BorderStroke? = null
@Composable
fun secondaryTextButtonStroke(): BorderStroke? = null
