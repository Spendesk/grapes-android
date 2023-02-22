package com.spendesk.grapes.compose.listitem

import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

/**
 * @author : dany
 * @since : 22/02/2023, Wed
 **/
@Composable
fun GrapesDivider(
    modifier: Modifier = Modifier,
    color: Color = DividerDefaults.color,
    thickness: Dp = DividerDefaults.Thickness
) =
    Divider(modifier = modifier, color = color, thickness = thickness)
