package com.spendesk.grapes.compose.appbar

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.spendesk.grapes.compose.R
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author : RomainGF
 * @since : 31/07/2024
 **/
@Composable
fun GrapesTopAppBarIconButton(
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    IconButton(
        modifier = modifier,
        content = icon,
        onClick = onClick,
    )
}

@Composable
fun GrapesTopAppBarBackIcon(
    modifier: Modifier = Modifier,
) {
    Icon(
        modifier = modifier.size(24.dp),
        imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_back),
        contentDescription = stringResource(id = R.string.grapes_top_app_bar_back_icon_description),
        tint = GrapesTheme.colors.structureComplementary,
    )
}

@Composable
fun GrapesTopAppBarCloseIcon(
    modifier: Modifier = Modifier,
) {
    Icon(
        modifier = modifier.size(24.dp),
        imageVector = ImageVector.vectorResource(R.drawable.ic_close),
        contentDescription = stringResource(id = R.string.grapes_top_app_bar_close_icon_description),
        tint = GrapesTheme.colors.structureComplementary
    )
}

@Composable
fun GrapesTopAppBarMoreIcon(
    modifier: Modifier = Modifier,
) {
    Icon(
        modifier = modifier.size(24.dp),
        imageVector = ImageVector.vectorResource(R.drawable.ic_more_vertical),
        contentDescription = stringResource(id = R.string.grapes_top_app_bar_more_icon_description),
        tint = GrapesTheme.colors.structureComplementary
    )
}
