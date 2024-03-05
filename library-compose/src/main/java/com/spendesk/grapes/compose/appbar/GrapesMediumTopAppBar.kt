package com.spendesk.grapes.compose.appbar

import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author : dany
 * @since : 01/03/2024, Fri
 **/

@Immutable
data class GrapesMediumTopAppBarColors internal constructor(
    val titleColor: Color,
    val iconColor: Color,
)

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun GrapesMediumTopAppBar(
    title: String,
    navigationIcon: @Composable (() -> Unit),
    modifier: Modifier = Modifier
) {
    val colors = TopAppBarDefaults.mediumTopAppBarColors(
        navigationIconContentColor = GrapesTheme.colors.structureComplementary,
        titleContentColor = GrapesTheme.colors.structureComplementary,
    )

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
        navigationIcon = navigationIcon
    )
}

// region color

object GrapesMediumTopAppBarDefaults {

    @Composable
    fun colors(
        titleColor: Color = GrapesTheme.colors.structureComplementary,
        iconColor: Color = GrapesTheme.colors.structureComplementary,
    ): GrapesMediumTopAppBarColors = GrapesMediumTopAppBarColors(
        titleColor = titleColor,
        iconColor = iconColor,
    )
}

// endregion color

@Composable
@Preview(showBackground = true)
private fun GrapesMediumTopAppBarPreview() {
    GrapesTheme {
        GrapesMediumTopAppBar(
            title = "Top app bar title",
            navigationIcon = { GrapesTopAppBarBackIcon() }
        )
    }
}
