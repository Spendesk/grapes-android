package com.spendesk.grapes.compose.bucket

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author Kélian CLERC
 * @since 06/12/2022
 */

private val GrapesBucketBorderWidth = 0.2.dp

@Composable
fun GrapesBucketContainer(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .background(GrapesTheme.colors.mainWhite, GrapesTheme.shapes.medium)
            .border(GrapesBucketBorderWidth, GrapesTheme.colors.mainNeutralNormal, GrapesTheme.shapes.medium)
            .clip(GrapesTheme.shapes.medium)
    ) {
        content()
    }
}

@Preview
@Composable
private fun GrapesContainerPreview() {
    GrapesTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(GrapesTheme.colors.mainNeutralLighter)
                .verticalScroll(rememberScrollState()),
        ) {
            GrapesBucketContainer(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Bucket content",
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable { println("Clicked") }
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                )
            }
            GrapesBucketContainer(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                ) {
                    Text(text = "Line 1")
                    Text(text = "Line 2")
                }
            }
        }
    }
}
