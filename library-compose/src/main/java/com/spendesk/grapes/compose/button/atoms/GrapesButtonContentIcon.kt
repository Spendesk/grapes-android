package com.spendesk.grapes.compose.button.atoms

import androidx.annotation.DrawableRes
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource

/**
 * @author jean-philippe
 * @since 17/02/2023, Friday
 **/
@Composable
internal fun GrapesButtonContentIcon(
    @DrawableRes icon: Int,
    iconDescription: String,
) {
    Icon(
        painter = painterResource(id = icon),
        contentDescription = iconDescription,
    )
}
