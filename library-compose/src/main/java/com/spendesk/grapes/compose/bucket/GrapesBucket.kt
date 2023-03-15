package com.spendesk.grapes.compose.bucket

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author jean-philippe
 * @since 15/03/2023, Wednesday
 **/

@Composable
fun GrapesBucket(
    title: String,
    modifier: Modifier = Modifier,
    action: String? = null,
    actionColor: Color? = null,
    onActionClicked: (() -> Unit)? = null,
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
        headline = headLine,
        content = content
    )
}

@Composable
fun GrapesBucket(
    headline: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable (() -> Unit)? = null
) {
    GrapesBucketCore(
        modifier = modifier,
        headline = headline,
        content = content
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
        }
    }
}
