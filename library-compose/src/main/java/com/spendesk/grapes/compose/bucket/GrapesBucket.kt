package com.spendesk.grapes.compose.bucket

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author jean-philippe
 * @since 15/03/2023, Wednesday
 **/

@Immutable
object GrapesBucketDefaults {
    private val BorderWidth = 0.2.dp
    private val BucketPadding = 16.dp

    @Composable
    fun DefaultGrapesBucketDecorationBox(
        modifier: Modifier = Modifier,
        contentPaddingValues: PaddingValues = bucketPadding(),
        innerContent: @Composable () -> Unit
    ) {
        Box(
            modifier = modifier
                .background(GrapesTheme.colors.mainWhite, GrapesTheme.shapes.medium)
                .border(BorderWidth, GrapesTheme.colors.mainNeutralNormal, GrapesTheme.shapes.medium)
                .clip(GrapesTheme.shapes.medium)
                .padding(contentPaddingValues)
        ) {
            innerContent()
        }
    }

    fun bucketPadding(
        start: Dp = BucketPadding,
        top: Dp = BucketPadding,
        end: Dp = BucketPadding,
        bottom: Dp = BucketPadding
    ): PaddingValues = PaddingValues(start, top, end, bottom)
}

@Composable
fun GrapesBucket(
    title: String,
    modifier: Modifier = Modifier,
    action: String? = null,
    actionColor: Color? = null,
    onActionClicked: (() -> Unit)? = null,
    decorationBox: @Composable (innerContent: @Composable () -> Unit) -> Unit =
        @Composable { innerContent ->
            GrapesBucketDefaults.DefaultGrapesBucketDecorationBox(innerContent = innerContent)
        },
    content: @Composable (() -> Unit)? = null
) {
    val headLine = @Composable {
        GrapesBucketHeadline(
            title = title,
            action = action,
            actionColor = actionColor,
            onActionClicked = onActionClicked
        )
    }

    GrapesBucket(
        modifier = modifier,
        decorationBox = decorationBox,
        headline = headLine,
        content = content
    )
}

@Composable
fun GrapesBucket(
    modifier: Modifier = Modifier,
    decorationBox: @Composable (innerContent: @Composable () -> Unit) -> Unit =
        @Composable { innerContent ->
            GrapesBucketDefaults.DefaultGrapesBucketDecorationBox(innerContent = innerContent)
        },
    headline: @Composable (() -> Unit)? = null,
    content: @Composable (() -> Unit)? = null,
) {
    GrapesBucketCore(
        modifier = modifier,
        headline = headline,
        content = content,
        decorationBox = decorationBox,
    )
}

@Preview
@Composable
private fun GrapesBucketPreview() {
    GrapesTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(GrapesTheme.colors.mainNeutralLighter)
                .padding(12.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            GrapesBucket(
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
                title = "Grapes Bucket Container Title"
            )

            val headline = @Composable {
                Text(text = "This is a headline composable")
            }

            val content = @Composable {
                Text(text = "This is a content composable")
            }

            GrapesBucket(
                headline = headline,
                content = content
            )

            val customDecorationBox: @Composable (innerContent: @Composable () -> Unit) -> Unit = @Composable { innerContent ->
                Box(
                    modifier = Modifier
                        .background(Color.Yellow, GrapesTheme.shapes.medium)
                        .clip(GrapesTheme.shapes.medium)
                        .padding(4.dp)
                ) {
                    innerContent()
                }
            }

            GrapesBucket(
                headline = headline,
                content = content,
                decorationBox = customDecorationBox,
            )
        }
    }
}
