package com.developer.android.dev.technologia.androidapp.quickcabs.presentation.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.developer.android.dev.technologia.androidapp.quickcabs.ui.theme.colorWhite
import com.developer.android.dev.technologia.androidapp.quickcabs.ui.theme.spacing
import com.developer.android.dev.technologia.androidapp.quickcabs.utils.UberIconSize.LargeButton

@Composable
fun QuickCabBackButton(
    modifier: Modifier = Modifier,
    @DrawableRes iconId: Int,
    backgroundColor: Color = colorWhite,
    contentDescription: String = "",
    onClick: () -> Unit
) {
    IconButton(
        modifier = modifier
            .size(42.dp)
            .background(backgroundColor, CircleShape)
            .padding(MaterialTheme.spacing.small),
        onClick = onClick
    ) {
        Icon(
            painterResource(id = iconId),
            contentDescription = contentDescription,
            modifier = Modifier.size(LargeButton)
        )
    }
}