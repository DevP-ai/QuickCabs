package com.developer.android.dev.technologia.androidapp.quickcabs.presentation.screens.confirmPickUpScreen

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import com.developer.android.dev.technologia.androidapp.quickcabs.R
import com.developer.android.dev.technologia.androidapp.quickcabs.presentation.common.MapInfoWindowText
import com.developer.android.dev.technologia.androidapp.quickcabs.presentation.common.QuickCabBackButton
import com.developer.android.dev.technologia.androidapp.quickcabs.presentation.common.QuickCabButton
import com.developer.android.dev.technologia.androidapp.quickcabs.presentation.common.QuickCabGoogleMap
import com.developer.android.dev.technologia.androidapp.quickcabs.presentation.common.QuickLoader
import com.developer.android.dev.technologia.androidapp.quickcabs.presentation.common.UberDivider
import com.developer.android.dev.technologia.androidapp.quickcabs.presentation.common.bottomSheet.QuickCabBottomSheetScaffold
import com.developer.android.dev.technologia.androidapp.quickcabs.presentation.screens.finalisingDriver.FinalisingDriverScreen
import com.developer.android.dev.technologia.androidapp.quickcabs.presentation.screens.rideConfirm.RideConfirmedScreen
import com.developer.android.dev.technologia.androidapp.quickcabs.ui.theme.colorGrayExtraLight
import com.developer.android.dev.technologia.androidapp.quickcabs.ui.theme.colorLocationUI
import com.developer.android.dev.technologia.androidapp.quickcabs.ui.theme.colorUberGrayBg
import com.developer.android.dev.technologia.androidapp.quickcabs.ui.theme.colorWhite
import com.developer.android.dev.technologia.androidapp.quickcabs.ui.theme.spacing
import com.developer.android.dev.technologia.androidapp.quickcabs.utils.bitmapDescriptorFromVector
import com.developer.android.dev.technologia.androidapp.quickcabs.utils.clickableWithRipple
import com.developer.android.dev.technologia.androidapp.quickcabs.utils.defaultCameraPosition
import com.developer.android.dev.technologia.androidapp.quickcabs.utils.pathLatLongsFirst
import com.developer.android.dev.technologia.androidapp.quickcabs.utils.rememberDeviceHeight
import com.developer.android.dev.technologia.androidapp.quickcabs.utils.rememberIsMobileDevice
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.JointType
import com.google.android.gms.maps.model.RoundCap
import com.google.maps.android.compose.MarkerInfoWindowContent
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("UnusedContentLambdaTargetStateParameter")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ConfirmPickupScreen(
    navHostController: NavHostController
) {
    val scope = rememberCoroutineScope()
    val dynamicWidth = Modifier.fillMaxWidth()

    val currentDen = LocalDensity.current
    val navBottom = WindowInsets.Companion.navigationBars.getBottom(currentDen)
    val dynamicPadding by remember {
        mutableStateOf(navBottom)
    }

    var isLocationConfirmed by rememberSaveable {
        mutableStateOf(false)
    }
    var locationChangeAnimation by rememberSaveable {
        mutableStateOf(false)
    }
    var bottomSheetCase by rememberSaveable {
        mutableStateOf(1)
    }

    /*Bottom sheet related properties*/
    val isDeviceMobileType = true

    var scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(
            BottomSheetValue.Collapsed
        )
    )

    val sheetToggle: () -> Unit = {
        scope.launch {
            if (scaffoldState.bottomSheetState.isCollapsed) {
                scaffoldState.bottomSheetState.expand()
            } else {
                scaffoldState.bottomSheetState.collapse()
            }
        }
    }

    val screenHeight = rememberDeviceHeight()
    // define dynamic height so we can show atlease 2 list item of cabs in different screen sizes
    var sheetPeekHeight by rememberSaveable {
        mutableStateOf(screenHeight.div(if (isDeviceMobileType) 3f else 2.8f))
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
    ) {
        Box(
            modifier = Modifier
                .then(if (isDeviceMobileType) Modifier.fillMaxWidth() else Modifier)
                .zIndex(2f),
            contentAlignment = Alignment.BottomCenter
        ) {
            QuickCabBottomSheetScaffold(
                modifier =
                dynamicWidth
                    .fillMaxHeight(),
                scaffoldState = scaffoldState,
                sheetShape = RectangleShape,
                sheetBackgroundColor = MaterialTheme.colorScheme.onPrimary,
                sheetPeekHeight = sheetPeekHeight.dp,
                sheetContent = {
                    AnimatedContent(targetState = ""){
                        if (locationChangeAnimation){
                            QuickLoader(
                                modifier = dynamicWidth
                                    .height(MaterialTheme.spacing.small)
                                    .background(color = MaterialTheme.colorScheme.onPrimary),
                                text = "Processing your request...",
                                loaderColor = MaterialTheme.colorScheme.primary,
                                loaderThickness = MaterialTheme.spacing.extraSmall
                            )
                            LaunchedEffect(locationChangeAnimation) {
                                delay(2000)
                                locationChangeAnimation = false
                            }
                        }
                    }
                    when (bottomSheetCase) {
                        2 -> {
                            // case to show finalising bottom sheet with animation
                            FinalisingDriverScreen(
                                onAnimationFinished = {
                                    val deviceHeight = screenHeight.div(1.25f)
                                    sheetPeekHeight = deviceHeight
                                    bottomSheetCase = 3
                                }
                            ) {
                               navHostController.navigateUp()
                            }
                        }
                        3 -> {
                            // case for ride confirm screen
                            RideConfirmedScreen(
                                scaffoldState.bottomSheetState.isExpanded,
                                { navHostController.navigate("HomeScreen") }
                            )
                        }
                        else -> {
                            // normal bottom sheet content for choose pickup spot
                            Column(
                                modifier = Modifier
                                    .padding(top = MaterialTheme.spacing.medium)
                                    .background(colorWhite),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    modifier = Modifier.padding(bottom = MaterialTheme.spacing.medium),
                                    text = "Choose you pickup spot",
                                    style = MaterialTheme.typography.titleLarge
                                )
                                UberDivider()
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.padding(
                                        horizontal = MaterialTheme.spacing.medium,
                                        vertical = MaterialTheme.spacing.medium
                                    )
                                ) {
                                    Text(
                                        modifier = Modifier
                                            .padding(vertical = MaterialTheme.spacing.medium)
                                            .weight(1f),
                                        text = "Near Home",
                                        style = MaterialTheme.typography.titleLarge
                                    )
                                    Text(
                                        modifier = Modifier
                                            .wrapContentHeight()
                                            .wrapContentWidth()
                                            .background(
                                                colorUberGrayBg,
                                                RoundedCornerShape(MaterialTheme.spacing.extraLarge)
                                            )
                                            .padding(
                                                horizontal = MaterialTheme.spacing.large,
                                                vertical = MaterialTheme.spacing.small
                                            )
                                            .clickableWithRipple {

                                            },

                                        text = "Search",
                                        style = MaterialTheme.typography.titleLarge.copy(textAlign = TextAlign.Center)
                                    )
                                }
                                QuickCabButton(
                                    text = "Confirm Pickup",
                                    modifier = Modifier.padding(MaterialTheme.spacing.medium),
                                    enabled = !locationChangeAnimation
                                ) {
                                    // onChooseConfirmLocationClick()
                                    isLocationConfirmed = true
                                }
                                Spacer(modifier = Modifier.padding(22.dp))
                            }
                        }
                    }

                },
                bodyContent = {
                    if (isDeviceMobileType) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .fillMaxSize(1f)
                        ) {
                            Box(
                                contentAlignment = Alignment.TopStart,
                                modifier = Modifier
                                    .fillMaxSize(1f)
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                ) {
                                    ConfirmPickupGoogleMap(
                                        modifier = Modifier.weight(1f),
                                        bottomSheetCase = bottomSheetCase
                                    ) {
                                        locationChangeAnimation = true
                                    }
                                }
                            }
                            GoogleMapCurrentLocationUI(Modifier.padding(bottom = MaterialTheme.spacing.minWidth))
                        }
                    } else {
                        Spacer(modifier = Modifier.padding(1.dp))
                    }
                }

            )
        }

        if (!isDeviceMobileType) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize(1f),
            ) {
                ConfirmPickupGoogleMap(
                    bottomSheetCase = bottomSheetCase
                ) {
                    locationChangeAnimation = true
                }
                if (bottomSheetCase == 1) {
                    GoogleMapCurrentLocationUI(Modifier.padding(start = MaterialTheme.spacing.minWidth))
                }
            }
        }
        if (isLocationConfirmed) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .zIndex(2f),
                contentAlignment = Alignment.Center
            ) {
                QuickLoader(
                    modifier = Modifier
                        .defaultMinSize(minWidth = 200.dp)
                        .fillMaxSize()
                        .background(color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)),
                    text = "Processing your request...",
                    loaderColor = MaterialTheme.colorScheme.surface
                )
            }
            scaffoldState = rememberBottomSheetScaffoldState(
                bottomSheetState = rememberBottomSheetState(
                    BottomSheetValue.Collapsed
                )
            )
            val deviceHeight = screenHeight.div(1.35f)
            LaunchedEffect(isLocationConfirmed) {
                delay(5000)
                sheetPeekHeight = deviceHeight
                bottomSheetCase = 2
                isLocationConfirmed = false
                scaffoldState.bottomSheetState.collapse()
            }
        }
        QuickCabBackButton(
            modifier = Modifier
                .padding(
                    vertical = MaterialTheme.spacing.extraLarge,
                    horizontal = MaterialTheme.spacing.medium
                )
                .zIndex(2f),
            iconId = R.drawable.baseline_arrow_back_24,
            backgroundColor = if (rememberIsMobileDevice()) {
                MaterialTheme.colorScheme.onPrimary
            } else {
                colorGrayExtraLight
            }
        ) {
            when (bottomSheetCase) {
                3 -> {navHostController.navigate("HomeScreen")}
                else -> navHostController.navigateUp()
            }
        }
    }
    BackHandler {
        when (bottomSheetCase) {
            3 -> ""
            else -> navHostController.navigateUp()
        }
    }




}

@Composable
fun ConfirmPickupGoogleMap(
    modifier: Modifier = Modifier,
    bottomSheetCase: Int,
    showMyCab: Boolean = bottomSheetCase == 3,
    onMovementCallback: () -> Unit,
) {
    val infiniteTransition = rememberInfiniteTransition()
    val animateColor by infiniteTransition.animateColor(
        initialValue = Color(0xFFF4F4F4),
        targetValue = Color(0xFF000000),
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 2000,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )
    val cameraPositionState = rememberCameraPositionState {
        position = defaultCameraPosition
    }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(bottomSheetCase) {
        if (bottomSheetCase == 3) {
            coroutineScope.launch {
                cameraPositionState.animate(
                    CameraUpdateFactory.newCameraPosition(
                        CameraPosition.fromLatLngZoom(pathLatLongsFirst[2], 12f)
                    )
                )
            }
        }
    }

    QuickCabGoogleMap(
        modifier = modifier,
        cameraPositionState = cameraPositionState,
        cameraPositionDefault = CameraPosition.fromLatLngZoom(pathLatLongsFirst.first(), 25f),
        mapMovingCallback = onMovementCallback,
    ) {
        if (showMyCab) {
            MarkerInfoWindowContent(
                MarkerState(pathLatLongsFirst.first()),
                icon = bitmapDescriptorFromVector(LocalContext.current, R.mipmap.ub__marker_vehicle_fallback)
            ) {
                 MapInfoWindowText("My start Location", R.drawable.baseline_navigate_next_24)
            }
            MarkerInfoWindowContent(
                MarkerState(pathLatLongsFirst.last()),
                icon = bitmapDescriptorFromVector(LocalContext.current, R.drawable.ic_location_start)
            ) {
                MapInfoWindowText("My end Location", R.drawable.baseline_navigate_next_24)
            }
            Polyline(
                points = pathLatLongsFirst,
                color = animateColor,
                clickable = true,
                startCap = RoundCap(),
                endCap = RoundCap(),
                jointType = JointType.ROUND
            )
        }
    }
}

@Composable
fun GoogleMapCurrentLocationUI(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .wrapContentHeight()
                .wrapContentWidth()
                .background(
                    colorLocationUI,
                    RoundedCornerShape(MaterialTheme.spacing.extraLarge)
                )
                .padding(
                    horizontal = MaterialTheme.spacing.large,
                    vertical = MaterialTheme.spacing.small
                ),
            text = "Pick-up here",
            color = colorWhite,
            style = MaterialTheme.typography.bodyMedium.copy(textAlign = TextAlign.Center)
        )
        Divider(
            color = colorLocationUI,
            modifier = Modifier
                .width(2.dp)
                .height(MaterialTheme.spacing.large)
        )
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(26.dp)
                .background(colorLocationUI, CircleShape)
        ) {
            Box(
                Modifier
                    .size(MaterialTheme.spacing.small)
                    .background(colorWhite, RectangleShape)
                    .zIndex(2f)
            )
        }
    }
}
