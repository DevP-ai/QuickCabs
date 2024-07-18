package com.developer.android.dev.technologia.androidapp.quickcabs.presentation.screens.profile

import androidx.annotation.FloatRange
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Uber Design divider.
 * @param modifier the Modifier to be applied to this divider line
 * */
@Composable
fun ProfileDivider(
    modifier: Modifier = Modifier,
    thickness: Dp = 3.dp,
    @FloatRange(from = 0.0, to = 1.0) alpha: Float = 0.15f,
    color: Color = Color.Black,
    shape: Shape = RectangleShape
) {
    Divider(
        modifier = modifier
            .alpha(alpha)
            .clip(shape),
        thickness = thickness,
        color = color
    )
}