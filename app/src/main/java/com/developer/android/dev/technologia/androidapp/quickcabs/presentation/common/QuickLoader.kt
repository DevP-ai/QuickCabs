package com.developer.android.dev.technologia.androidapp.quickcabs.presentation.common

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.developer.android.dev.technologia.androidapp.quickcabs.utils.systemTween

private val UberLoaderEasing = CubicBezierEasing(0.4f, 0.0f, 0.0f, 0.4f)


@Composable
fun QuickLoader(
    modifier: Modifier = Modifier,
    loaderMinSize: Dp = 8.dp,
    loaderMaxSize: Dp = 64.dp,
    loaderColor: Color = MaterialTheme.colorScheme.primary,
    loaderThickness: Dp = 2.dp,
    text: String? = null,
    textColor: Color = loaderColor,
    spaceBetweenTextAndLoader: Dp = 8.dp,
    animationDurationMillis: Int = 1000,
    animationEasing: Easing = UberLoaderEasing
) {
    val density = LocalDensity.current

    val screenWidthInDp = LocalConfiguration.current.screenWidthDp.dp
    val screenWidthInPx = remember(screenWidthInDp) { with(density) { screenWidthInDp.toPx() } }

    val loaderStartPositionPx = remember { 0f }
    val loaderEndPositionPx =
        remember(screenWidthInPx) { with(density) { screenWidthInPx - loaderMinSize.toPx() } }

    val infiniteTransition = rememberInfiniteTransition()

    val loaderCurrentPosition by infiniteTransition.animateFloat(
        initialValue = loaderStartPositionPx,
        targetValue = loaderEndPositionPx,
        animationSpec = infiniteRepeatable(
            animation = systemTween(
                durationMillis = animationDurationMillis,
                easing = animationEasing
            ),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )
    val loaderCurrentSizeInDp by infiniteTransition.animateFloat(
        initialValue = loaderMinSize.value,
        targetValue = loaderMaxSize.value,
        animationSpec = infiniteRepeatable(
            animation = systemTween(
                durationMillis = animationDurationMillis.div(2),
                easing = animationEasing
            ),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )
    val loaderCurrentSizeInPx by remember { derivedStateOf { with(density) { loaderCurrentSizeInDp.dp.toPx() } } }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center
    ) {
        Canvas(
            modifier = Modifier,
            onDraw = {
                drawLine(
                    color = loaderColor,
                    start = Offset(x = loaderCurrentPosition, y = 0f),
                    end = Offset(x = loaderCurrentPosition + loaderCurrentSizeInPx, y = 0f),
                    strokeWidth = loaderThickness.toPx()
                )
            }
        )
        text?.let {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth()
                    .padding(top = spaceBetweenTextAndLoader),
                text = text,
                color = textColor
            )
        }
    }
}

