package com.spendesk.grapes.compose.appbar

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.spendesk.grapes.compose.R
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
    elevationDp: Dp = GrapesTheme.dimensions.elevationNormal
) {
    TopAppBar(
        modifier = modifier,
        title = title,
        contentColor = GrapesTheme.colors.mainBlack,
        backgroundColor = GrapesTheme.colors.mainWhite,
        navigationIcon = navigationIcon,
        actions = actions,
        elevation = elevationDp
    )
}

@Composable
fun GrapesTopAppBarTitle(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = title,
        color = GrapesTheme.colors.mainComplementary,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        style = GrapesTheme.typography.titleL,
    )
}

@Composable
fun GrapesTopAppBarTitleWithSubtitle(
    title: String,
    subTitle: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        GrapesTopAppBarTitle(
            title = title,
        )

        Text(
            text = subTitle,
            lineHeight = GrapesTheme.typography.bodyS.fontSize,
            style = GrapesTheme.typography.bodyS,
        )
    }
}

@Composable
fun GrapesTopAppBarIconButton(
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
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
        tint = GrapesTheme.colors.mainComplementary
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
        tint = GrapesTheme.colors.mainComplementary
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
        tint = GrapesTheme.colors.mainComplementary
    )
}

//region previews
@Preview("GrapesTopAppBars", group = "TopAppBar")
@Composable
internal fun GrapesSimpleTopAppBarPreview() {
    GrapesTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(GrapesTheme.colors.mainNeutralLighter)
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
                title = {
                    GrapesTopAppBarTitleWithSubtitle(
                        title = "Top app bar title",
                        subTitle = "Top app bar subtitle"
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
                title = {
                    GrapesTopAppBarTitleWithSubtitle(
                        title = "Top app bar title",
                        subTitle = "Top app bar subtitle"
                    )
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

            GrapesTopAppBar(
                modifier = Modifier.fillMaxWidth(),
                title = {
                    GrapesTopAppBarTitleWithSubtitle(
                        title = "Top app bar title",
                        subTitle = "Top app bar subtitle"
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
            )
        }
    }
}
//endregion previews
