package com.spendesk.grapes.compose.progress

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author Kélian CLERC
 * @since 02/11/2022
 */

@Composable
fun StepProgressIndicator(
    maxStepCount: Int,
    step: Int,
    modifier: Modifier = Modifier
) {
    var currentStepProgress by remember { mutableStateOf(0f) }

    val progressAnimation = animateFloatAsState(
        targetValue = currentStepProgress,
        animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)
    )

    LaunchedEffect(step) {
        currentStepProgress = step / maxStepCount.toFloat()
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(8.dp)
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .background(GrapesTheme.colors.mainNeutralNormal)
        )
        Spacer(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(progressAnimation.value)
                .background(GrapesTheme.colors.mainPrimaryNormal)
        )
        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
            repeat(maxStepCount - 1) {
                Spacer(
                    modifier = Modifier
                        .width(2.dp)
                        .fillMaxHeight()
                        .background(GrapesTheme.colors.mainWhite)
                )
            }
        }
    }

}

@Preview
@Composable
fun StepProgressIndicatorPreview() {
    val maxStepCount = 4
    var currentStep by remember {
        mutableStateOf(1)
    }

    GrapesTheme {
        Column(modifier = Modifier.padding(GrapesTheme.dimensions.paddingNormal), verticalArrangement = Arrangement.spacedBy(GrapesTheme.dimensions.paddingMedium)) {
            Button(onClick = { currentStep = (currentStep + 1) % (maxStepCount + 1) }) {
                Text(text = "Increment step")
            }
            StepProgressIndicator(maxStepCount = maxStepCount, step = currentStep)
            StepProgressIndicator(
                maxStepCount = maxStepCount, step = currentStep, modifier = Modifier
                    .width(100.dp)
                    .height(32.dp)
            )
        }
    }
}
