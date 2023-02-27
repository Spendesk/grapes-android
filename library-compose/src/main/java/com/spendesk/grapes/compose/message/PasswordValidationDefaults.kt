package com.spendesk.grapes.compose.message

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author Kélian CLERC
 * @since 27/02/2023
 */
@Immutable
object PasswordValidationDefaults {
    val ValidationItemCircleSize = 6.dp

    val SuccessColor: Color @Composable get() = GrapesTheme.colors.mainSuccessNormal
    val ErrorColor: Color @Composable get() = GrapesTheme.colors.mainAlertNormal
}
