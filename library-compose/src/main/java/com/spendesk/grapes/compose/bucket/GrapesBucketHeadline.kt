package com.spendesk.grapes.compose.bucket

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author : dany
 * @since : 06/03/2023, Mon
 **/
@Composable
fun GrapesBucketHeadline(
    title: String,
    modifier: Modifier = Modifier,
    action: String? = null,
    actionColor: Color? = null,
    onActionClicked: (() -> Unit)? = null
) {
    Row(modifier = modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier.weight(1f, fill = true),
            text = title,
            color = GrapesTheme.colors.structureComplementary,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            style = GrapesTheme.typography.titleM
        )
        if (action != null && actionColor != null) {
            Spacer(modifier = Modifier.size(GrapesTheme.dimensions.paddingMedium))
            Text(
                modifier = Modifier.clickable(onClick = onActionClicked ?: {}, onClickLabel = action, role = Role.Button),
                text = action,
                color = actionColor,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                style = GrapesTheme.typography.titleM
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun GrapesBucketHeadlinePreview() {
    GrapesTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(GrapesTheme.colors.neutralLightest)
                .verticalScroll(rememberScrollState()),
        ) {

            GrapesBucketHeadline(title = "Bucket de Rick Astley")
            Spacer(modifier = Modifier.size(GrapesTheme.dimensions.spacing3))

            GrapesBucketHeadline(title = "Bucket de Rick Astley a bit longer than expecteeeeeeeeeeeeeed")
            Spacer(modifier = Modifier.size(GrapesTheme.dimensions.spacing3))

            GrapesBucketHeadline(title = "Bucket de Rick Astley a bit longer than expecteeeeeeeeeeeeeeeeeeed", action = "Remove", actionColor = GrapesTheme.colors.alertNormal)
            Spacer(modifier = Modifier.size(GrapesTheme.dimensions.spacing3))

            GrapesBucketHeadline(title = "Bucket de Rick Astley", action = "Remove", actionColor = GrapesTheme.colors.alertNormal)
            Spacer(modifier = Modifier.size(GrapesTheme.dimensions.spacing3))

            GrapesBucketHeadline(title = "Bucket de Rick Astley", action = "Remooooooove with too many ooooooooooos", actionColor = GrapesTheme.colors.alertNormal)
            Spacer(modifier = Modifier.size(GrapesTheme.dimensions.spacing3))
        }
    }
}
