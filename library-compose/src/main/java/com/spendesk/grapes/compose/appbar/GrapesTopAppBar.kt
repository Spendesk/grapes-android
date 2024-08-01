package com.spendesk.grapes.compose.appbar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author RomainGF
 * @since 31/07/2024
 **/
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun GrapesTopAppBar(
    title: String,
    modifier: Modifier = Modifier,
    actions: @Composable RowScope.() -> Unit = {},
    navigationIcon: @Composable () -> Unit = {},
    colors: TopAppBarColors = GrapesTopAppBarDefaults.topAppBarColors(),
    scrollBehavior: TopAppBarScrollBehavior? = null,
    windowInsets: WindowInsets = TopAppBarDefaults.windowInsets,
    elevation: Dp = 1.dp,
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                style = GrapesTheme.typography.titleXl,
            )
        },
        actions = actions,
        colors = colors,
        navigationIcon = navigationIcon,
        scrollBehavior = scrollBehavior,
        windowInsets = windowInsets,
        modifier = modifier
            .shadow(elevation)
    )
}

@Preview
@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun GrapesTopAppBarPreview() {
    GrapesTheme {
        GrapesTopAppBar(
            title = "Top app bar title",
            navigationIcon = {
                GrapesTopAppBarIconButton(
                    icon = { GrapesTopAppBarBackIcon() },
                )
            },
            actions = {
                GrapesTopAppBarIconButton(
                    icon = { GrapesTopAppBarMoreIcon() },
                )
            },
        )
    }
}
