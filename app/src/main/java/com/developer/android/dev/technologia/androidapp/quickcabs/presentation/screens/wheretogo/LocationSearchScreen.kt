package com.developer.android.dev.technologia.androidapp.quickcabs.presentation.screens.wheretogo

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Shop
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.navigation.NavHostController
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.movableContentOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.developer.android.dev.technologia.androidapp.quickcabs.R
import com.developer.android.dev.technologia.androidapp.quickcabs.presentation.common.QuickCabButton
import com.developer.android.dev.technologia.androidapp.quickcabs.presentation.common.QuickCabGoogleMap
import com.developer.android.dev.technologia.androidapp.quickcabs.presentation.screens.wheretogo.components.ListTile
import com.developer.android.dev.technologia.androidapp.quickcabs.presentation.screens.wheretogo.components.UberTextField
import com.developer.android.dev.technologia.androidapp.quickcabs.service.RecentSearchesDataService
import com.developer.android.dev.technologia.androidapp.quickcabs.ui.theme.Typography
import com.developer.android.dev.technologia.androidapp.quickcabs.ui.theme.colorGrayLighter
import com.developer.android.dev.technologia.androidapp.quickcabs.ui.theme.spacing
import com.developer.android.dev.technologia.androidapp.quickcabs.utils.clickableWithRipple
import com.developer.android.dev.technologia.androidapp.quickcabs.utils.defaultCameraPosition
import com.developer.android.dev.technologia.androidapp.quickcabs.utils.rememberBottomSheetProgress
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.launch



@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
@Composable
fun LocationSearchScreen(
    navHostController: NavHostController
) {
    val coroutineScope = rememberCoroutineScope()

    var isPickupLocationFocused by rememberSaveable { mutableStateOf(false) }
    var isWhereToTfFocused by rememberSaveable { mutableStateOf(false) }

    var pickupLocationIfText by rememberSaveable { mutableStateOf("") }
    var whereToTfText by rememberSaveable { mutableStateOf("") }

    val pickupLocationIfFocusedRequester = remember { FocusRequester() }
    val whereToTfRequester = remember { FocusRequester() }


    val state = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(
            initialValue = BottomSheetValue.Expanded
        )
    )

    val keyboardManager = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    val swipeProgress = state.rememberBottomSheetProgress()

    val isMapPinVisible by remember {
        derivedStateOf {
            !state.bottomSheetState.isExpanded
        }
    }

    var filteredList = remember {
        derivedStateOf {
            RecentSearchesDataService.recentSearchesList.filter { searchItem ->
                when {
                    isPickupLocationFocused -> {
                        searchItem.location.contains(pickupLocationIfText, true)
                    }

                    isWhereToTfFocused -> {
                        searchItem.location.contains(whereToTfText, true)
                    }

                    else -> false
                }
            }
        }
    }

    val cameraPositionState = rememberCameraPositionState {
        position = defaultCameraPosition
    }

    val QuickCabGoogleMap: @Composable (modifier: Modifier) -> Unit = remember {
        movableContentOf { modifier: Modifier ->
            QuickCabGoogleMap(
                modifier = modifier,
                cameraPositionState = cameraPositionState,
                nonMapContent = {
                    QuickCabButton(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(MaterialTheme.spacing.medium)
                            .navigationBarsPadding()
                            .imePadding(),
                        text = "Done"
                    ){
                        when {
                            pickupLocationIfText.isBlank() -> {
                                pickupLocationIfFocusedRequester.requestFocus()
                            }

                            whereToTfText.isBlank() -> {
                                whereToTfRequester.requestFocus()
                            }

                            else -> {
                                navHostController.navigate("MapWithCab")
                            }
                        }
                    }
                    if (isMapPinVisible) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_location_pin),
                            contentDescription = "",
                            modifier = Modifier
                                .align(Alignment.Center)
                                .imePadding()
                        )
                    }
                }
            )
        }
    }


    LaunchedEffect(cameraPositionState.isMoving) {
        if (cameraPositionState.isMoving) {
            filteredList = derivedStateOf { emptyList() }
        }else{
            when{
                isPickupLocationFocused ->{
                    pickupLocationIfText = RecentSearchesDataService
                        .recentSearchesList
                        .random()
                        .location
                }

                isWhereToTfFocused ->{
                    whereToTfText = RecentSearchesDataService
                        .recentSearchesList
                        .random()
                        .location
                }
            }
        }
    }

    LaunchedEffect(Unit) { pickupLocationIfFocusedRequester.requestFocus() }

    LaunchedEffect(state.bottomSheetState.isExpanded) {
        if (state.bottomSheetState.isExpanded) {
            when {
                isPickupLocationFocused -> {
                    pickupLocationIfFocusedRequester.requestFocus()
                }

                isWhereToTfFocused -> {
                    whereToTfRequester.requestFocus()
                }
            }
        } else {
            keyboardManager?.hide()
            focusManager.clearFocus()
        }
    }

    Row(modifier = Modifier.fillMaxWidth()) {
        Scaffold(
            modifier = Modifier.fillMaxWidth(),
            topBar = {
                Surface(
                    modifier = Modifier.shadow(elevation = MaterialTheme.spacing.small)
                ) {
                    Column {
                        Box(
                            modifier = Modifier.statusBarsPadding(),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            //BackButton
                            IconButton(onClick = { navHostController.navigateUp() })
                            {
                                Icon(
                                    imageVector = Icons.Filled.ArrowBack,
                                    contentDescription = "Back to previous screen"
                                )
                            }
                            // SwitchRider

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentWidth()
                                    .clickableWithRipple {}
                            ) {
                                // RiderIconWithGradient
                                val gradientRadial =
                                    Brush.radialGradient(
                                        listOf(
                                            Color.White,
                                            Color.White,
                                            Color.LightGray
                                        )
                                    )
                                androidx.compose.material3.Icon(
                                    imageVector = Icons.Filled.Person,
                                    contentDescription = "Rider Icon",
                                    tint = Color.Gray,
                                    modifier = Modifier
                                        .clip(CircleShape)
                                        .background(gradientRadial)
                                )
                                Text(
                                    text = "Switch Rider",
                                    style = Typography.bodyMedium,
                                    modifier = Modifier.padding(horizontal = 12.dp)
                                )
                                androidx.compose.material3.Icon(
                                    Icons.Filled.KeyboardArrowDown,
                                    contentDescription = "Switch rider dropdown button"
                                )
                            }

                        }

                        // SearchFieldCard
                        Row(modifier = Modifier.background(MaterialTheme.colorScheme.onPrimary)) {
                            // TimeLineWidget
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .padding(horizontal = MaterialTheme.spacing.medium)
                                    .padding(top = 20.dp)
                            ) {
                                // circle
                                Box(
                                    modifier = Modifier
                                        .size(MaterialTheme.spacing.small)
                                        .clip(CircleShape)
                                        .background(if (isPickupLocationFocused) Color.Black else Color.LightGray)
                                )

                                // vertical line
                                UberVerticalDivider()

                                // square
                                Box(
                                    modifier = Modifier
                                        .size(MaterialTheme.spacing.small)
                                        .background(if (isWhereToTfFocused) Color.Black else Color.LightGray)
                                )
                            }

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                            ){
                                // SearchFields
                                Column {
                                    UberTextField(
                                        modifier = Modifier
                                            .padding(horizontal = MaterialTheme.spacing.small)
                                            .onFocusChanged {
                                                if ((it.hasFocus || it.isFocused) && state.bottomSheetState.isCollapsed) {
                                                    coroutineScope.launch {
                                                        state.bottomSheetState.expand()
                                                    }
                                                }
                                            }
                                            .focusRequester(pickupLocationIfFocusedRequester),
                                        value = pickupLocationIfText,
                                        onValueChange = {newPickupLocationText->
                                            pickupLocationIfText = newPickupLocationText
                                        },
                                        placeholder = "Enter pickup location",
                                        onFocus = {
                                            isPickupLocationFocused = true
                                            isWhereToTfFocused = false
                                        }
                                    )
                                    Spacer(modifier = Modifier.padding(top = MaterialTheme.spacing.small))

                                    UberTextField(
                                        modifier = Modifier
                                            .padding(horizontal = MaterialTheme.spacing.small)
                                            .onFocusChanged {
                                                if ((it.hasFocus || it.isFocused) && state.bottomSheetState.isCollapsed) {
                                                    coroutineScope.launch {
                                                        state.bottomSheetState.expand()
                                                    }
                                                }
                                            }
                                            .focusRequester(whereToTfRequester),
                                        placeholder = "Where to?",
                                        value = whereToTfText,
                                        onValueChange = { newWhereToText ->
                                            whereToTfText = newWhereToText
                                        },
                                        onFocus = {
                                            isPickupLocationFocused = false
                                            isWhereToTfFocused = true
                                        }
                                    )
                                    Spacer(modifier = Modifier.padding(bottom = MaterialTheme.spacing.medium))

                                }
                            }
                            // AddDestinationButton
                            androidx.compose.material3.IconButton(
                                onClick = {},
                                modifier = Modifier
                                    .padding(
                                        horizontal = 12.dp,
                                        vertical = MaterialTheme.spacing.large
                                    )
                                    .size(36.dp)
                                    .clip(CircleShape)
                                    .background(colorGrayLighter)
                            ) {
                                androidx.compose.material3.Icon(
                                    Icons.Outlined.Add,
                                    contentDescription = "Navigate to previous screen button"
                                )
                            }

                        }
                    }
                }
            }
        ){ padding->
            androidx.compose.material.BottomSheetScaffold(
                scaffoldState = state,
                modifier = Modifier
                    .padding(padding),
                sheetContent = {
                    LazyColumn(
                        modifier = Modifier
                            .padding(horizontal = MaterialTheme.spacing.extraLarge.times(1 - swipeProgress))
                            .clip(
                                RoundedCornerShape(
                                    topStart = MaterialTheme.spacing.small,
                                    topEnd = MaterialTheme.spacing.small
                                )
                            )
                            .fillMaxHeight()
                            .background(Color.White)
                            .imePadding(),
                        userScrollEnabled = true
                    ) {
                        item {
                            LazyRow(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                item {
                                    ListTile(
                                        modifier = Modifier.width(200.dp),
                                        icon = Icons.Filled.Home,
                                        title = "Home",
                                        subtitle = "24,IITNR Naya Raipur",
                                        backgroundColor = MaterialTheme.colorScheme.secondary
                                    )
                                    UberVerticalDivider(
                                        height = 48.dp
                                    )
                                }

                                item {
                                    ListTile(
                                        modifier = Modifier.width(200.dp),
                                        icon = Icons.Filled.Shop,
                                        title = "Work",
                                        subtitle = "Abc Ltd.",
                                        backgroundColor = MaterialTheme.colorScheme.secondary
                                    )
                                    UberVerticalDivider(
                                        height = 48.dp
                                    )
                                }

                                item {
                                    ListTile(
                                        modifier = Modifier.width(200.dp),
                                        icon = Icons.Filled.Star,
                                        title = "Saved Places"
                                    )
                                    UberVerticalDivider(
                                        height = 48.dp
                                    )
                                }
                            }
                        }
                        item {
                            Divider(
                                thickness = 3.dp,
                                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                            )
                        }

                        items(filteredList.value){searchItem->
                            ListTile(
                                icon = Icons.Filled.Schedule,
                                contentDesc = "",
                                title = searchItem.location,
                                subtitle = searchItem.locationDesc,
                                onClick = {
                                    when{
                                        isPickupLocationFocused ->{
                                            pickupLocationIfText = searchItem.location
                                            focusManager.moveFocus(FocusDirection.Down)
                                        }
                                        isWhereToTfFocused -> {
                                            whereToTfText = searchItem.location
                                            navHostController.navigate("MapWithCab")
                                        }
                                    }
                                }
                            )
                        }

                        item {
                            ListTile(
                                icon = Icons.Default.LocationOn,
                                contentDesc = null,
                                title = "Set location on map"
                            ){
                                coroutineScope.launch {
                                    state.bottomSheetState.collapse()
                                }
                            }
                        }

                    }
                },
                sheetPeekHeight = if(!isMapPinVisible) 100.dp else 0.dp,
                sheetElevation = 0.dp,
                sheetBackgroundColor = Color.Transparent,
                sheetGesturesEnabled = true
            ) {
                QuickCabGoogleMap(Modifier.fillMaxWidth())
            }
        }
    }
}

@Composable
fun UberVerticalDivider(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(vertical = 2.dp),
    width: Dp = 1.dp,
    height: Dp = 45.dp,
    color: Color = Color.LightGray,
) {
    Box(
        modifier = modifier
            .padding(paddingValues)
            .width(width)
            .height(height)
            .background(color)
    )
}
