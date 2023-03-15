package com.spendesk.grapes.compose.bucket

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author jean-philippe
 * @since 15/03/2023, Wednesday
 **/
@Composable
internal fun GrapesBucketCore(
    modifier: Modifier = Modifier,
    headline: @Composable () -> Unit,
    content: @Composable (() -> Unit)? = null
) {
    GrapesBucketBox(modifier = modifier) {
        Column(modifier = Modifier.padding(GrapesTheme.dimensions.paddingLarge)) {
            headline()

            if (content != null) {
                Spacer(Modifier.padding(bottom = GrapesTheme.dimensions.paddingLarge))
                content()
            }
        }
    }
}
