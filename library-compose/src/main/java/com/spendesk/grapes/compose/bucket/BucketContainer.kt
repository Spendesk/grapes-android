package com.spendesk.grapes.compose.bucket

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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

@Composable
fun GrapesBucket(
    title: String,
    modifier: Modifier = Modifier,
    action: String? = null,
    actionColor: Color? = null,
    onActionClicked: (() -> Unit)? = null,
    content: @Composable (() -> Unit)? = null
) {
    GrapesBucketCore(
        modifier = modifier,
        headline = { GrapesBucketHeadline(title = title, action = action, actionColor = actionColor, onActionClicked = onActionClicked) },
        content = content
    )
}

// region internal

@Composable
internal fun GrapesBucketCore(
    modifier: Modifier = Modifier,
    headline: @Composable () -> Unit,
    content: @Composable (() -> Unit)? = null
) {
    GrapesBucketContainer(modifier = modifier) {
        Column(modifier = Modifier.padding(GrapesTheme.dimensions.paddingLarge)) {
            headline()

            if (content != null) {
                Spacer(Modifier.padding(bottom = GrapesTheme.dimensions.paddingLarge))
                content()
            }
        }
    }
}

// endregion internal

@Preview
@Composable
fun GrapesBucketPreview() {
    GrapesTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(GrapesTheme.colors.mainNeutralLighter)
                .verticalScroll(rememberScrollState()),
        ) {

            GrapesBucket(
                modifier = Modifier.padding(12.dp),
                title = "Rick's bucket",
                action = "Delete",
                actionColor = GrapesTheme.colors.mainAlertNormal,
                onActionClicked = { println("Clicked") },
                content = {
                    Column {
                        Text(text = "Line 1")
                        Text(text = "Line 2")
                    }
                }
            )

            GrapesBucket(
                modifier = Modifier.padding(12.dp),
                title = "Grapes Bucket Container Title"
            )

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
