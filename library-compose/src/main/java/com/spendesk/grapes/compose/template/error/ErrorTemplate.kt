package com.spendesk.grapes.compose.template.error

/**
 * @author Kélian CLERC
 * @since 01/06/2023
 */
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.spendesk.grapes.compose.button.GrapesButton
import com.spendesk.grapes.compose.icons.Size
import com.spendesk.grapes.compose.icons.StatusInformationIcon
import com.spendesk.grapes.compose.model.GrapesConfigurationStatus
import com.spendesk.grapes.compose.theme.GrapesTheme
import kotlinx.coroutines.delay


@Composable
fun ErrorTemplate(
    title: String,
    description: String? = null,
    retryUiModel: ErrorRetryUiModel? = null,
    icon: @Composable () -> Unit = { StatusInformationIcon(configuration = GrapesConfigurationStatus.ALERT, size = Size.L) },
    configuration: ErrorTemplateDefaults.Configuration = ErrorTemplateDefaults.defaultConfiguration()
) {
    ErrorTemplate(
        title = {
            Text(text = title, style = GrapesTheme.typography.titleL)
        },
        icon = icon,
        description = description?.let {
            {
                Text(
                    text = it,
                    style = GrapesTheme.typography.bodyRegular,
                    textAlign = TextAlign.Center
                )
            }
        },
        retryButton = retryUiModel?.canRetry?.takeIf { it }?.let {
            {
                GrapesButton(
                    text = retryUiModel.message,
                    onClick = retryUiModel.onRetryClicked
                )
            }
        },
        configuration = configuration
    )
}

@Composable
fun ErrorTemplate(
    title: @Composable () -> Unit,
    icon: @Composable () -> Unit = { StatusInformationIcon(configuration = GrapesConfigurationStatus.ALERT, size = Size.L) },
    description: (@Composable () -> Unit)? = null,
    retryButton: (@Composable () -> Unit)? = null,
    configuration: ErrorTemplateDefaults.Configuration = ErrorTemplateDefaults.defaultConfiguration()
) {
    var isVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(configuration.startingDelayMs)
        isVisible = true
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(GrapesTheme.dimensions.spacing3),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AnimatedVisibility(visible = isVisible, enter = configuration.iconEnterAnimation) {
                icon()
            }

            Spacer(modifier = Modifier.height(GrapesTheme.dimensions.spacing4))

            AnimatedVisibility(visible = isVisible, enter = configuration.titleEnterAnimation) {
                title()
            }

            description?.let {
                Spacer(modifier = Modifier.height(GrapesTheme.dimensions.paddingMedium))
                AnimatedVisibility(visible = isVisible, enter = configuration.descriptionEnterAnimation) {
                    description()
                }
            }
        }

        if (retryButton != null) {
            AnimatedVisibility(visible = isVisible, enter = configuration.retryButtonEnterAnimation) {
                retryButton()
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Preview(showBackground = true)
@Composable
fun ErrorTemplatePreview() {
    var isError by remember { mutableStateOf(false) }
    GrapesTheme {
        Column(
            modifier = Modifier
                .background(GrapesTheme.colors.mainWhite)
                .padding(GrapesTheme.dimensions.spacing3),
            verticalArrangement = Arrangement.spacedBy(GrapesTheme.dimensions.spacing3)
        ) {
            AnimatedContent(targetState = isError, label = "Preview animation") { animatedIsError ->
                if (animatedIsError) {
                    ErrorTemplate(
                        title = "Phone number reset failed",
                        description = "An error occurred while resetting your phone number. Please try again later.",
                        retryUiModel = ErrorRetryUiModel(
                            canRetry = true,
                            message = "Retry",
                            onRetryClicked = { isError = false }
                        )
                    )
                } else {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        GrapesButton(text = "Perform some network call", onClick = { isError = true })
                    }
                }
            }

        }
    }
}
