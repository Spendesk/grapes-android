package com.spendesk.grapes.compose.appbar

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author : dany
 * @since : 01/03/2024, Fri
 **/

private const val CollapsedThreshold = 0.75f
private val collapsedShadowElevation = 1.dp
private val expandedShadowElevation = 0.dp

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun GrapesMediumTopAppBar(
    title: String,
    modifier: Modifier = Modifier,
    actions: @Composable RowScope.() -> Unit = {},
    navigationIcon: @Composable () -> Unit = {},
    colors: TopAppBarColors = GrapesTopAppBarDefaults.mediumTopAppBarColors(),
    scrollBehavior: TopAppBarScrollBehavior? = null,
    windowInsets: WindowInsets = TopAppBarDefaults.windowInsets,
) {
    val topBarState = scrollBehavior?.state
    val collapsed = topBarState != null && topBarState.collapsedFraction > CollapsedThreshold
    val elevation by animateDpAsState(
        targetValue = if (collapsed) collapsedShadowElevation else expandedShadowElevation,
        label = "TopAppBar elevation"
    )

    MaterialTheme(
        typography = Typography(
            headlineSmall = GrapesTheme.typography.titleXl,
            titleLarge = GrapesTheme.typography.titleXl,
        ),
    ) {
        MediumTopAppBar(
            title = {
                Text(
                    text = title,
                    color = GrapesTheme.colors.structureComplementary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = GrapesTheme.typography.titleXl,
                )
            },
            colors = colors,
            actions = actions,
            navigationIcon = navigationIcon,
            scrollBehavior = scrollBehavior,
            windowInsets = windowInsets,
            modifier = modifier
                .shadow(elevation = elevation)
        )
    }
}

@Composable
@Preview
@OptIn(ExperimentalMaterial3Api::class)
private fun GrapesMediumTopAppBarPreview() {
    GrapesTheme {
        GrapesMediumTopAppBar(
            title = "Top app bar title",
            navigationIcon = {
                GrapesTopAppBarIconButton(
                    icon = { GrapesTopAppBarBackIcon() },
                    onClick = {}
                )
            },
        )
    }
}

@Composable
@Preview
@OptIn(ExperimentalMaterial3Api::class)
private fun GrapesMediumTopAppBarPreviewLongTitle() {
    GrapesTheme {
        GrapesMediumTopAppBar(
            title = "Top app bar with a very long title that should be truncated",
            navigationIcon = {
                GrapesTopAppBarIconButton(
                    icon = { GrapesTopAppBarCloseIcon() },
                    onClick = {}
                )
            },
        )
    }
}
