package com.spendesk.grapes.compose.avatar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.spendesk.grapes.compose.R
import com.spendesk.grapes.compose.theme.GrapesTheme
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun GrapesEditAvatar(
    avatar: @Composable (Modifier) -> Unit,
    contentDescription: String,
    modifier: Modifier = Modifier,
    badgeOffset: DpOffset = EditAvatarDefaults.badgeOffset,
    badgeSize: DpSize = EditAvatarDefaults.badgeSize,
    badgeTint: Color = GrapesTheme.colors.mainComplementary,
    enabled: Boolean = true,
    onClick: () -> Unit = {},
) {
    EditAvatarLayout(
        badge = {
            Icon(
                painter = painterResource(R.drawable.ic_add),
                contentDescription = null,
                tint = badgeTint,
                modifier = Modifier
                    .size(badgeSize)
                    .clip(CircleShape)
                    .clickable(enabled = enabled, onClick = onClick),
            )
        },
        avatar = {
            avatar(
                Modifier
                    .clip(CircleShape)
                    .fillMaxSize()
                    .clickable(enabled = enabled, onClick = onClick)
            )
        },
        badgeOffset = badgeOffset,
        modifier = modifier
            .width(IntrinsicSize.Min)
            .height(IntrinsicSize.Min)
            .semantics(mergeDescendants = true) {
                this.contentDescription = contentDescription
            },
    )
}

private const val LAYOUT_ID_AVATAR = "avatar-anchor"
private const val LAYOUT_ID_AVATAR_EDIT = "avatar-edit"

@Composable
private fun EditAvatarLayout(
    badge: @Composable BoxScope.() -> Unit,
    avatar: @Composable BoxScope.() -> Unit,
    modifier: Modifier = Modifier,
    badgeOffset: DpOffset,
) {
    Layout(
        content = {
            Box(
                modifier = Modifier.layoutId(LAYOUT_ID_AVATAR),
                contentAlignment = Alignment.Center,
                content = avatar,
            )
            Box(
                modifier = Modifier.layoutId(LAYOUT_ID_AVATAR_EDIT),
                content = badge,
            )
        },
        modifier = modifier,
    ) { measurables, constraints ->
        val editPlaceable = measurables.first { it.layoutId == LAYOUT_ID_AVATAR_EDIT }.measure(
            constraints.copy(minWidth = 0, minHeight = 0)
        )
        val avatarPlaceable = measurables.first { it.layoutId == LAYOUT_ID_AVATAR }.measure(constraints)

        val avatarRadius = avatarPlaceable.width / 2f
        val editRadius = editPlaceable.width / 2f

        layout(avatarPlaceable.width, avatarPlaceable.height) {
            avatarPlaceable.placeRelative(0, 0)
            val angle = Math.toRadians(45.0)
            val badgeX = avatarRadius * cos(angle) - editRadius + avatarRadius
            val badgeY = avatarRadius * sin(angle) - editRadius + avatarRadius
            editPlaceable.placeRelative(
                x = badgeX.toInt() + badgeOffset.x.toPx().toInt(),
                y = badgeY.toInt() + badgeOffset.y.toPx().toInt(),
            )
        }
    }
}

@Immutable
object EditAvatarDefaults {
    val badgeOffset = DpOffset(8.dp, 2.dp)
    val badgeSize = DpSize(24.dp, 24.dp)
}

@Preview(showBackground = true, name = "Avatar", group = "Avatar")
@Composable
internal fun Preview() {
    GrapesTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(16.dp),
        ) {
            GrapesEditAvatar(
                avatar = { modifier ->
                    Box(
                        modifier = modifier
                            .background(Color.Blue)
                    )
                },
                badgeSize = DpSize(32.dp, 32.dp),
                contentDescription = "",
                modifier = Modifier.size(128.dp),
            )
            GrapesEditAvatar(
                avatar = { modifier ->
                    Box(
                        modifier = modifier
                            .background(Color.Blue)
                    )
                },
                contentDescription = "",
                modifier = Modifier.size(88.dp),
            )
        }
    }
}
