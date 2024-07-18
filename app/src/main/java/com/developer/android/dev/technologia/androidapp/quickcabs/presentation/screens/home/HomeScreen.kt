package com.developer.android.dev.technologia.androidapp.quickcabs.presentation.screens.home

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.navigation.NavHostController
import com.developer.android.dev.technologia.androidapp.quickcabs.R
import com.developer.android.dev.technologia.androidapp.quickcabs.component.LocationPermissionRequestDialog
import com.developer.android.dev.technologia.androidapp.quickcabs.core.LocationUtils
import com.developer.android.dev.technologia.androidapp.quickcabs.extension.hasAllPermission
import com.developer.android.dev.technologia.androidapp.quickcabs.extension.hasLocationPermission
import com.developer.android.dev.technologia.androidapp.quickcabs.extension.openAppSetting
import com.developer.android.dev.technologia.androidapp.quickcabs.presentation.screens.map.CurrentMap
import com.developer.android.dev.technologia.androidapp.quickcabs.utils.PermissionUtils

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    navHostController: NavHostController
) {
    val context = LocalContext.current

    PermissionRequest()

    Box(modifier = Modifier.fillMaxSize()) {
        CurrentMap()

        TopBar(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(24.dp),
            onClick = { navHostController.navigate("ProfileScreen") }
        )

        BottomCard(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            onClick = {navHostController.navigate("LocationSearchScreen")}
        )
    }



}


@Composable
fun PermissionRequest() {
    var showPermissionDeclinedRationale by rememberSaveable { mutableStateOf(false) }
    var showRationale by rememberSaveable { mutableStateOf(false) }

    val context = LocalContext.current

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { permissions ->
            val deniedPermissions = permissions.filter { !it.value }
            if (deniedPermissions.any { PermissionUtils.locationPermissions.contains(it.key) }) {
                showPermissionDeclinedRationale = true
            } else {
                LocationUtils.checkAndRequestLocationSetting(context as Activity)
            }
        }
    )

    if (showPermissionDeclinedRationale) {
        LocationPermissionRequestDialog(
            onDismissClick = {
                if (!context.hasLocationPermission()) {
                    (context as Activity).finish()
                } else {
                    showPermissionDeclinedRationale = false
                }
            },
            onOkClick = { context.openAppSetting() }
        )
    }

    if (showRationale) {
        LocationPermissionRequestDialog(
            onDismissClick = {(context as Activity).finish()},
            onOkClick = {
                showRationale = false
                permissionLauncher.launch(PermissionUtils.allPermissions)
            }
        )
    }

    LaunchedEffect(Unit) {
        when {
            context.hasAllPermission() -> {LocationUtils.checkAndRequestLocationSetting(context as Activity)}
            PermissionUtils.locationPermissions.any { ActivityCompat.shouldShowRequestPermissionRationale(context as Activity, it) } -> showRationale = true
            else -> permissionLauncher.launch(PermissionUtils.allPermissions)
        }
    }
}


@Composable
private fun TopBar(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
            .size(32.dp)
            .shadow(
                elevation = 4.dp,
                shape = MaterialTheme.shapes.medium,
                clip = true
            )
            .background(
                color = MaterialTheme.colorScheme.surface,
            )
            .padding(4.dp)
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_menu),
            contentDescription = "",
            tint = MaterialTheme.colorScheme.onSurface
        )
    }
}
