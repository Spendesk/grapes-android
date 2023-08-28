package com.spendesk.grapes.compose.listitem

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author : dany
 * @since : 22/02/2023, Wed
 **/
@Composable
fun GrapesDivider(
    modifier: Modifier = Modifier,
    color: Color = DividerDefaults.color,
    thickness: Dp = DividerDefaults.Thickness
) =
    Divider(modifier = modifier, color = color, thickness = thickness)


@Preview(
    widthDp = 372,
    showBackground = true,
    name = "Divider",
    group = "List item"
)
@Composable
internal fun GrapesDividerPreview() {
    GrapesTheme {
        LazyColumn {
            items(4) {
                Text(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    text = "item number $it"
                )

                GrapesDivider()
            }
        }
    }
}
