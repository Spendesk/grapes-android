package com.spendesk.grapes.compose.icons

import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.spendesk.grapes.compose.extensions.GrapesIcons
import com.spendesk.grapes.compose.model.GrapesConfigurationStatus
import com.spendesk.grapes.compose.theme.GrapesTheme
import com.spendesk.grapes.compose.theme.extensions.contentColorFor

/**
 * @author : danyboucanova
 * @since : 13/06/2022, Thu
 **/
@Composable
fun GrapesIcon(
    configuration: GrapesConfigurationStatus,
    modifier: Modifier = Modifier,
    contentDescription: String? = null
) =
    Icon(
        modifier = modifier,
        imageVector = ImageVector.vectorResource(GrapesIcons.iconFor(configuration)),
        contentDescription = contentDescription,
        tint = GrapesTheme.colors.contentColorFor(configuration)
    )