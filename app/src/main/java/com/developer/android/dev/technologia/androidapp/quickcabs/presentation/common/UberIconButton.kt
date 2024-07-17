package com.developer.android.dev.technologia.androidapp.quickcabs.presentation.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import com.developer.android.dev.technologia.androidapp.quickcabs.ui.theme.colorUberGrayBg
import com.developer.android.dev.technologia.androidapp.quickcabs.utils.UberIconSize.MediumIcon
import com.developer.android.dev.technologia.androidapp.quickcabs.utils.clickableWithRipple

/**
 * An Uber-styled icon button that helps its users initiate actions.
 * @param modifier the [Modifier] to be applied to this button
 * @param iconId paint resource id of icon
 * @param backgroundColor icon button background color
 * @param contentDescription icon content Description
 * @param onClick called when this button is clicked
 * */
@Composable
fun UberIconButton(
    modifier: Modifier = Modifier,
    @DrawableRes iconId: Int,
    backgroundColor: Color = colorUberGrayBg,
    contentDescription: String = "",
    onClick: () -> Unit
) {
    Box(modifier = modifier
        .background(backgroundColor)
        .clickableWithRipple {
            onClick()
        }) {
        Icon(
            painterResource(id = iconId),
            modifier = modifier
                .size(MediumIcon)
                .clip(RectangleShape),
            contentDescription = contentDescription
        )
    }
}
