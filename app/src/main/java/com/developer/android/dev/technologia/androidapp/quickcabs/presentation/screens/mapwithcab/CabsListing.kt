package com.developer.android.dev.technologia.androidapp.quickcabs.presentation.screens.mapwithcab

import android.opengl.Visibility
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.traceEventEnd
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.developer.android.dev.technologia.androidapp.quickcabs.model.CabInfo
import com.developer.android.dev.technologia.androidapp.quickcabs.ui.theme.colorGrayExtraLight
import com.developer.android.dev.technologia.androidapp.quickcabs.ui.theme.colorGrayLight
import com.developer.android.dev.technologia.androidapp.quickcabs.ui.theme.colorGrayLighter
import com.developer.android.dev.technologia.androidapp.quickcabs.ui.theme.colorWhite
import com.developer.android.dev.technologia.androidapp.quickcabs.ui.theme.spacing
import com.developer.android.dev.technologia.androidapp.quickcabs.utils.UberIconSize.MediumImage
import com.developer.android.dev.technologia.androidapp.quickcabs.utils.UberIconSize.SmallImage
import com.developer.android.dev.technologia.androidapp.quickcabs.utils.clickableWithRipple
import com.developer.android.dev.technologia.androidapp.quickcabs.utils.toINRString

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CabsListing(
    cabMapWithCabViewModel: MapWithCabViewModel,
    isVisibleDivider: Boolean = true,
    currentFraction: Float = 1f,
    onItemChecked: (CabInfo) -> Unit,
    onItemSelected: (CabInfo) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(color = colorWhite)
            .fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.graphicsLayer(alpha = if (isVisibleDivider) currentFraction else 1f - currentFraction)
        ) {
            Spacer(modifier = Modifier.padding(MaterialTheme.spacing.extraSmall))
            val dividerColor = colorGrayExtraLight
            Divider(
                thickness = 4.dp,
                color = dividerColor,
                modifier = Modifier
                    .background(
                        dividerColor,
                        RoundedCornerShape(12.dp)
                    )
                    .fillMaxWidth(0.2f)
                    .padding(1.dp)
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Text(
                "Choose a ride, or swipe up for more",
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(MaterialTheme.spacing.extraSmall)
            )
            Spacer(modifier = Modifier.padding(MaterialTheme.spacing.extraSmall))
        }

        LazyColumn {
            stickyHeader {
                AnimatedVisibility(
                    visible = !isVisibleDivider,
                    enter = slideInVertically(),
                    exit = slideOutVertically()
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.statusBarsPadding())
                        Text(
                            "Popular",
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(MaterialTheme.spacing.medium)
                                .graphicsLayer(alpha = currentFraction)
                        )
                    }
                }
            }
            itemsIndexed(cabMapWithCabViewModel.cabList) { index, cabs ->
                CabListItem(cabs) {
                    if(!isVisibleDivider){
                        cabMapWithCabViewModel.selectItem(index)
                        onItemSelected(it)
                        onItemChecked(it)
                    }else{
                        if(it.isChecked){
                            onItemSelected(it)
                        }else{
                            onItemChecked(it)
                            cabMapWithCabViewModel.selectItem(index)
                        }
                    }
                }

            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun CabListItem(cabInfo: CabInfo, onItemSelected: (CabInfo) -> Unit) {
    val scaleX by animateFloatAsState(targetValue = if (cabInfo.isChecked) 1.1f else 1f)
    val scaleY by animateFloatAsState(targetValue = if (cabInfo.isChecked) 1.1f else 1f)

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .background(if (cabInfo.isChecked) colorGrayLight else colorWhite)
            .clickableWithRipple {
                onItemSelected(cabInfo)
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = MaterialTheme.spacing.extraSmall)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = cabInfo.cabIcon),
                contentDescription = cabInfo.cabInfo,
                modifier = Modifier
                    .requiredSize(SmallImage)
                    .graphicsLayer(
                        scaleX = scaleX,
                        scaleY = scaleY
                    )
            )

            Column(modifier = Modifier.padding(start = MaterialTheme.spacing.small)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        cabInfo.cabInfo,
                        style = MaterialTheme.typography.titleLarge,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .padding(MaterialTheme.spacing.extraSmall)
                            .weight(1f)
                    )
                    AnimatedContent(
                        targetState = cabInfo.cabPrice.toINRString(),
                        transitionSpec = {
                            slideInVertically { height -> height } + fadeIn() with
                                    slideOutVertically { height -> -height } + fadeOut() using (
                                    SizeTransform(clip = false)
                                    )
                        }, label = ""
                    ) {
                        Text(
                            it,
                            style = MaterialTheme.typography.titleLarge,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.padding(MaterialTheme.spacing.extraSmall)
                        )
                    }

                }

                Row(verticalAlignment = Alignment.CenterVertically){
                    CabsListItemText(
                        text = cabInfo.carTime,
                        textStyle = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                            .padding(MaterialTheme.spacing.extraSmall)
                            .weight(1f),
                        )

                    cabInfo.cabPriceAlter?.let {
                        CabsListItemText(
                            text = it.toINRString(),
                            textStyle = MaterialTheme.typography.bodyMedium.copy(
                                textAlign = TextAlign.End,
                                textDecoration = TextDecoration.LineThrough
                            ),
                            modifier = Modifier.padding(MaterialTheme.spacing.extraSmall)
                        )
                    }
                }
            }

        }
    }

}



@Composable
fun CabsListItemText(
    text: String,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = LocalTextStyle.current,
    maxLine: Int = 2,
    overflow: TextOverflow = TextOverflow.Ellipsis
) {
    Text(
        text,
        style = textStyle,
        maxLines = maxLine,
        overflow = overflow,
        modifier = modifier
    )
}


@Composable
fun QuickCabsListItemDetails(cabInfo: CabInfo) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(horizontal = 12.dp, vertical = MaterialTheme.spacing.extraSmall)
            .fillMaxWidth()

    ) {
        Image(
            painter = painterResource(id = cabInfo.cabIcon),
            contentDescription = cabInfo.cabInfo,
            modifier = Modifier
                .requiredSize(MediumImage)
        )
        Column() {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    cabInfo.cabInfo,
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(4.dp)
                        .weight(1f)
                )
                Text(
                    cabInfo.cabPrice.toINRString(),
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(4.dp)
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    cabInfo.carTime,
                    style = MaterialTheme.typography.titleSmall,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(4.dp)
                        .weight(1f)
                )
                cabInfo.cabPriceAlter?.let {
                    Text(
                        it.toINRString(),
                        style = MaterialTheme.typography.labelMedium.copy(
                            textAlign = TextAlign.End,
                            textDecoration = TextDecoration.LineThrough
                        ),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(4.dp)
                    )
                }
            }
        }
    }
}
