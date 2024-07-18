package com.developer.android.dev.technologia.androidapp.quickcabs.presentation.screens.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.developer.android.dev.technologia.androidapp.quickcabs.ui.theme.colorGrayExtraLight
import com.developer.android.dev.technologia.androidapp.quickcabs.ui.theme.spacing
import com.developer.android.dev.technologia.androidapp.quickcabs.utils.clickableWithRipple
import com.developer.android.dev.technologia.androidapp.quickcabs.utils.limitWidth

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomCard(
    modifier: Modifier = Modifier,
    onClick:()->Unit
) {
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .size(140.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
    ){

        Row(
            modifier = modifier
                .height(80.dp)
                .limitWidth()
                .padding(start = 20.dp, end = 20.dp, top = 30.dp)
                .clip(RoundedCornerShape(50.dp))
                .background(color = colorGrayExtraLight)
                .clickableWithRipple {
                    onClick()
                },
            verticalAlignment = Alignment.CenterVertically
        ){
            Row(
                modifier = Modifier
                    .height(40.dp)
                    .weight(1.5f)
                    .padding(start = MaterialTheme.spacing.medium)
                    .background(color = Color.Transparent),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)
            ){
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "search Icon",
                    tint = Color.Black,
                    modifier = Modifier.size(36.dp)
                )
                androidx.compose.material.Text(
                    text = "Where to go?",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.alpha(ContentAlpha.medium)
                )
            }
        }
    }
}

