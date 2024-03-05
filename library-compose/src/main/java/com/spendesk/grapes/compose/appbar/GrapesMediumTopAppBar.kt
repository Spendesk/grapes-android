package com.spendesk.grapes.compose.appbar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author : dany
 * @since : 01/03/2024, Fri
 **/
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun GrapesMediumTopAppBar(
    title: String,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    colors: TopAppBarColors = GrapesTopAppBarDefaults.colors(),
    scrollBehavior: TopAppBarScrollBehavior? = null,
) {
    MediumTopAppBar(
        modifier = modifier,
        title = {
            Text(
                modifier = modifier,
                text = title,
                color = GrapesTheme.colors.structureComplementary,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = GrapesTheme.typography.titleXl,
            )
        },
        colors = colors,
        actions = actions,
        navigationIcon = navigationIcon,
        scrollBehavior = scrollBehavior,
    )
}

@Composable
@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
private fun GrapesMediumTopAppBarPreview() {
    GrapesTheme {
        GrapesMediumTopAppBar(
            title = "Top app bar title",
            navigationIcon = { GrapesTopAppBarBackIcon() }
        )
    }
}
