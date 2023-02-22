package com.spendesk.grapes.compose.listitem

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author : dany
 * @since : 22/02/2023, Wed
 **/
object DividerDefaults {

    /** Default thickness of a divider. */
    val Thickness: Dp = 1.0.dp

    /** Default color of a divider. */
    val color: Color @Composable get() = GrapesTheme.colors.mainNeutralLighter
}
