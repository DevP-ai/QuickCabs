package com.developer.android.dev.technologia.androidapp.quickcabs.presentation.screens.wheretogo.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.developer.android.dev.technologia.androidapp.quickcabs.ui.theme.Typography
import com.developer.android.dev.technologia.androidapp.quickcabs.ui.theme.bodyMedium_gray
import com.developer.android.dev.technologia.androidapp.quickcabs.ui.theme.spacing
import com.developer.android.dev.technologia.androidapp.quickcabs.utils.clickableWithRipple


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListTile(
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    title: String? = null,
    subtitle: String? = null,
    contentDesc: String? = null,
    backgroundColor: Color = Color.Gray,
    maxlines: Int = 1,
    onClick: () -> Unit = {},
) {
    ListItem(
        modifier = modifier.clickableWithRipple { onClick() },
        leadingContent = {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(backgroundColor)
                ,
                contentAlignment = Alignment.Center
            ) {
                icon?.let { nnIcon ->
                    Icon(
                        imageVector = nnIcon,
                        contentDescription = contentDesc,
                        tint = Color.White,
                        modifier = Modifier.padding(MaterialTheme.spacing.extraSmall)
                    )
                }
            }
        },
        headlineContent = {
            title?.let { nnTitle ->
                Text(
                    text = nnTitle,
                    style = Typography.headlineMedium,
                    maxLines = maxlines,
                    overflow = TextOverflow.Ellipsis
                )
            }
        },
        supportingContent = {
            subtitle?.let { nnSubtitle ->
                Text(
                    text = nnSubtitle,
                    style = Typography.bodyMedium_gray,
                    maxLines = maxlines,
                    overflow = TextOverflow.Ellipsis
                )
            }
        },
        shadowElevation = ListItemDefaults.Elevation
    )
    HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
}