package com.spendesk.grapes.compose.message

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.spendesk.grapes.compose.R
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author Kélian CLERC
 * @since 27/02/2023
 */
@Immutable
object PasswordValidationDefaults {
    val ValidationItemSize = 6.dp
    val ValidationSuccessItemSize = 10.dp
    val ValidationBoxSize = 16.dp

    val SuccessColor: Color @Composable get() = GrapesTheme.colors.mainSuccessNormal
    val ErrorColor: Color @Composable get() = GrapesTheme.colors.mainAlertNormal

    @DrawableRes val ValidIcon: Int = R.drawable.ic_valid_tick
    @DrawableRes val InvalidIcon: Int = R.drawable.ic_neutral_tick
}
