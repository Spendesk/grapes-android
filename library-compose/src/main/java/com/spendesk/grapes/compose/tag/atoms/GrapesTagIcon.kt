package com.spendesk.grapes.compose.tag.atoms

import androidx.annotation.DrawableRes
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import com.spendesk.grapes.compose.icons.GrapesIcon

/**
 * @author jean-philippe
 * @since 18/10/2023, Wednesday
 **/

@Composable
fun GrapesTagIcon(
    @DrawableRes iconRes: Int,
    contentDescription: String,
) {
    GrapesIcon(
        icon = iconRes,
        contentDescription = contentDescription,
        tint = LocalContentColor.current,
    )
}
