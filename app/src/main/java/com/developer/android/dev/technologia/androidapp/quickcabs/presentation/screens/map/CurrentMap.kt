package com.developer.android.dev.technologia.androidapp.quickcabs.presentation.screens.map

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun CurrentMap(
    modifier: Modifier = Modifier
) {
    var mapSize by remember { mutableStateOf(Size(0f, 0f)) }
    var mapCenter by remember { mutableStateOf(Offset(0f, 0f)) }
    var isMapLoaded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .onGloballyPositioned {
                val rect = it.boundsInRoot()
                mapSize = rect.size
                mapCenter = rect.center
            }
    ) {
        ShowMapLoading(!isMapLoaded)
        MainMap(onMapLoaded = {isMapLoaded = true})
    }
}

@Composable
private fun MainMap(
    onMapLoaded:()->Unit
){
    val mapUISettings = remember {
        MapUiSettings(
            mapToolbarEnabled = false,
            compassEnabled = true,
            zoomControlsEnabled = false
        )
    }

    val cameraPositionState = rememberCameraPositionState{
        position = CameraPosition.fromLatLngZoom(LatLng(19.8471388,75.9009769),13f)
    }

    val mapProperties = remember {
        MapProperties(
            mapType = MapType.NORMAL
        )
    }

    GoogleMap(
        modifier = Modifier
            .fillMaxSize(),
        uiSettings = mapUISettings,
        properties = mapProperties,
        cameraPositionState = cameraPositionState,
        onMapLoaded = onMapLoaded
    )
}

@Composable
private fun ShowMapLoading(
    visible: Boolean = false
) {
    AnimatedVisibility(
        modifier = Modifier
            .fillMaxSize(),
        visible = visible,
        enter = EnterTransition.None,
        exit = fadeOut()
    ){
        CircularProgressIndicator(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .wrapContentSize()
        )
    }
}