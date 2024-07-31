package com.spendesk.grapes.compose.appbar

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author jean-philippe
 * @since 28/12/2022, Wed
 **/

// TODO: Create a parameter handling colors = GrapesTopAppBarDefaults.colors()
@Composable
fun GrapesTopAppBar(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {},
    elevationDp: Dp = GrapesTheme.dimensions.elevationNormal,
) {
    TopAppBar(
        modifier = modifier,
        title = title,
        backgroundColor = GrapesTheme.colors.structureSurface,
        contentColor = GrapesTheme.colors.structureComplementary,
        navigationIcon = navigationIcon,
        actions = actions,
        elevation = elevationDp,
    )
}

@Composable
fun GrapesTopAppBar(
    title: @Composable () -> Unit,
    windowInsets: WindowInsets,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {},
    elevationDp: Dp = GrapesTheme.dimensions.elevationNormal,
) {
    TopAppBar(
        modifier = modifier,
        title = title,
        backgroundColor = GrapesTheme.colors.structureSurface,
        contentColor = GrapesTheme.colors.structureComplementary,
        navigationIcon = navigationIcon,
        actions = actions,
        elevation = elevationDp,
        windowInsets = windowInsets,
    )
}

@Composable
fun GrapesTopAppBarTitle(
    title: String,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        text = title,
        color = GrapesTheme.colors.structureComplementary,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        style = GrapesTheme.typography.titleL,
    )
}

//region previews
@Preview
@Composable
fun GrapesSimpleTopAppBarPreview() {
    GrapesTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(GrapesTheme.colors.structureBackground)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            GrapesTopAppBar(
                modifier = Modifier.fillMaxWidth(),
                title = { GrapesTopAppBarTitle(title = "Top app bar title") },
            )

            GrapesTopAppBar(
                modifier = Modifier.fillMaxWidth(),
                title = { GrapesTopAppBarTitle(title = "Top app bar  with a very very very very very very very long title") },
            )

            GrapesTopAppBar(
                modifier = Modifier.fillMaxWidth(),
                title = { GrapesTopAppBarTitle(title = "Top app bar title") },
                navigationIcon = {
                    GrapesTopAppBarIconButton(
                        icon = { GrapesTopAppBarBackIcon() },
                        onClick = { Log.i("GrapesTopAppBar", "navigationIcon click") }
                    )
                },
            )

            GrapesTopAppBar(
                modifier = Modifier.fillMaxWidth(),
                title = {
                    GrapesTopAppBarTitle(
                        title = "Top app bar with no elevation",
                    )
                },
                navigationIcon = {
                    GrapesTopAppBarIconButton(
                        icon = { GrapesTopAppBarBackIcon() },
                        onClick = { Log.i("GrapesTopAppBar", "navigationIcon click") }
                    )
                },
                actions = {
                    GrapesTopAppBarIconButton(
                        icon = { GrapesTopAppBarMoreIcon() },
                        onClick = { Log.i("GrapesTopAppBar", "action button click") }
                    )
                },
                elevationDp = 0.dp
            )

            GrapesTopAppBar(
                modifier = Modifier.fillMaxWidth(),
                title = { GrapesTopAppBarTitle(title = "Top app bar title") },
                navigationIcon = {
                    GrapesTopAppBarIconButton(
                        icon = { GrapesTopAppBarCloseIcon() },
                        onClick = { Log.i("GrapesTopAppBar", "navigationIcon click") }
                    )
                },
            )

            GrapesTopAppBar(
                modifier = Modifier
                    .fillMaxWidth(),
                title = {
                    GrapesTopAppBarTitle(title = "Top app bar  with a very very very very very very very long title")
                },
                navigationIcon = {
                    GrapesTopAppBarIconButton(
                        icon = { GrapesTopAppBarBackIcon() },
                        onClick = { Log.i("GrapesTopAppBar", "navigationIcon click") }
                    )
                },
            )

            GrapesTopAppBar(
                modifier = Modifier.fillMaxWidth(),
                title = { GrapesTopAppBarTitle(title = "Top app bar title") },
                navigationIcon = {
                    GrapesTopAppBarIconButton(
                        icon = { GrapesTopAppBarBackIcon() },
                        onClick = { Log.i("GrapesTopAppBar", "navigationIcon click") }
                    )
                },
                actions = {
                    GrapesTopAppBarIconButton(
                        icon = { GrapesTopAppBarMoreIcon() },
                        onClick = { Log.i("GrapesTopAppBar", "action button click") }
                    )
                },
            )
        }
    }
}
//endregion previews
