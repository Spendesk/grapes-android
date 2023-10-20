package com.spendesk.grapes.compose.tag

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.spendesk.grapes.compose.R
import com.spendesk.grapes.compose.tag.atoms.GrapesTagIcon
import com.spendesk.grapes.compose.theme.GrapesTheme
import com.spendesk.grapes.compose.theme.LocalGrapesShapes

/**
 * @author jean-philippe
 * @since 18/10/2023, Wednesday
 **/

@Composable
fun GrapesSuccessTag(
    label: String,
    showIcon: Boolean = true,
) {
    CompositionLocalProvider(
        LocalContentColor provides GrapesTheme.colors.mainSuccessNormal,
    ) {
        val icon: @Composable () -> Unit = @Composable {
            GrapesTagIcon(iconRes = R.drawable.ic_success, contentDescription = "Success tag icon")
        }

        val tagColors = SuccessGrapesTagColors()

        GrapesTag(
            label = label,
            icon = icon.takeIf { showIcon },
            backgroundColor = tagColors.containerColor().value,
            borderColor = tagColors.borderStrokeColor().value
        )
    }
}

@Composable
fun GrapesInfoTag(
    label: String,
    showIcon: Boolean = true,
) {
    CompositionLocalProvider(
        LocalContentColor provides GrapesTheme.colors.mainInfoNormal,
    ) {
        val icon: @Composable () -> Unit = @Composable {
            GrapesTagIcon(iconRes = R.drawable.ic_information, contentDescription = "Info tag icon")
        }

        val tagColors = InfoGrapesTagColors()

        GrapesTag(
            label = label,
            icon = icon.takeIf { showIcon },
            backgroundColor = tagColors.containerColor().value,
            borderColor = tagColors.borderStrokeColor().value
        )
    }
}

@Composable
fun GrapesWarningTag(
    label: String,
    showIcon: Boolean = true,
) {
    CompositionLocalProvider(
        LocalContentColor provides GrapesTheme.colors.mainWarningNormal,
    ) {
        val icon: @Composable () -> Unit = @Composable {
            GrapesTagIcon(iconRes = R.drawable.ic_warning, contentDescription = "Warning tag icon")
        }

        val tagColors = WarningGrapesTagColors()

        GrapesTag(
            label = label,
            icon = icon.takeIf { showIcon },
            backgroundColor = tagColors.containerColor().value,
            borderColor = tagColors.borderStrokeColor().value
        )
    }
}

@Composable
fun GrapesErrorTag(
    label: String,
    showIcon: Boolean = true,
) {
    CompositionLocalProvider(
        LocalContentColor provides GrapesTheme.colors.mainAlertNormal,
    ) {
        val icon: @Composable () -> Unit = @Composable {
            GrapesTagIcon(iconRes = R.drawable.ic_error, contentDescription = "Error tag icon")
        }

        val tagColors = ErrorGrapesTagColors()

        GrapesTag(
            label = label,
            icon = icon.takeIf { showIcon },
            backgroundColor = tagColors.containerColor().value,
            borderColor = tagColors.borderStrokeColor().value
        )
    }
}

@Composable
fun GrapesTag(
    label: String,
    backgroundColor: Color,
    borderColor: Color,
    modifier: Modifier = Modifier,
    icon: (@Composable () -> Unit)? = null,
) {
    Surface(
        modifier = modifier.wrapContentSize(),
        shape = LocalGrapesShapes.current.xSmall,
        color = backgroundColor,
        border = BorderStroke(GrapesTagDefaults.borderThickness, borderColor),
    ) {
        Row(
            modifier = Modifier
                .padding(PaddingValues(horizontal = 8.dp, vertical = 4.dp)),
            horizontalArrangement = Arrangement.spacedBy(GrapesTheme.dimensions.paddingXSmall),
        ) {
            if (icon != null) {
                Box(
                    modifier = Modifier
                        .size(GrapesTagDefaults.iconSize)
                        .align(Alignment.CenterVertically),
                    contentAlignment = Alignment.Center,
                ) {
                    icon()
                }
            }

            ProvideTextStyle(
                GrapesTheme.typography.titleS.copy(color = LocalContentColor.current),
            ) {
                Text(text = label)
            }
        }
    }
}

@Preview
@Composable
private fun Preview(
    @PreviewParameter(TagProvider::class) tag: Tags,
) {
    GrapesTheme {
        when (tag) {
            is Tags.Error -> GrapesErrorTag(label = tag.tag, showIcon = tag.showIcon)
            is Tags.Info -> GrapesInfoTag(label = tag.tag, showIcon = tag.showIcon)
            is Tags.Success -> GrapesSuccessTag(label = tag.tag, showIcon = tag.showIcon)
            is Tags.Warning -> GrapesWarningTag(label = tag.tag, showIcon = tag.showIcon)
        }
    }
}

internal class TagProvider : PreviewParameterProvider<Tags> {
    override val values: Sequence<Tags> = sequenceOf(
        Tags.Error("Label", true),
        Tags.Error("Label", false),

        Tags.Warning("Label", true),
        Tags.Warning("Label", false),

        Tags.Info("Label", true),
        Tags.Info("Label", false),

        Tags.Success("Label", true),
        Tags.Success("Label", false),
    )
}

internal sealed class Tags {
    abstract val tag: String
    abstract val showIcon: Boolean

    data class Error(override val tag: String, override val showIcon: Boolean) : Tags()
    data class Info(override val tag: String, override val showIcon: Boolean) : Tags()
    data class Warning(override val tag: String, override val showIcon: Boolean) : Tags()
    data class Success(override val tag: String, override val showIcon: Boolean) : Tags()
}
