package com.developer.android.dev.technologia.androidapp.quickcabs

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.developer.android.dev.technologia.androidapp.quickcabs.component.LocationPermissionRequestDialog
import com.developer.android.dev.technologia.androidapp.quickcabs.core.LocationUtils
import com.developer.android.dev.technologia.androidapp.quickcabs.extension.hasAllPermission
import com.developer.android.dev.technologia.androidapp.quickcabs.extension.hasLocationPermission
import com.developer.android.dev.technologia.androidapp.quickcabs.extension.openAppSetting
import com.developer.android.dev.technologia.androidapp.quickcabs.presentation.screens.main.MainScreen
import com.developer.android.dev.technologia.androidapp.quickcabs.ui.theme.QuickCabsTheme
import com.developer.android.dev.technologia.androidapp.quickcabs.utils.PermissionUtils

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuickCabsTheme {
                PermissionRequest()
               Surface(
                   modifier = Modifier.fillMaxSize(),
                   color = MaterialTheme.colorScheme.surface
               ) {
                   MainScreen(navHostController = rememberNavController())
               }
            }
        }
    }

    @Composable
    private fun PermissionRequest() {
        var showPermissionDeclinedRationale by rememberSaveable { mutableStateOf(false) }

        var showRationale by rememberSaveable { mutableStateOf(false) }

        val permissionLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestMultiplePermissions(),
            onResult = {
                it.forEach{(permission,isGranted)->
                    if(!isGranted && PermissionUtils.locationPermissions.contains(permission)){
                        showPermissionDeclinedRationale = false
                    }
                }
            }
        )

        if(showPermissionDeclinedRationale){
            LocationPermissionRequestDialog(
                onDismissClick = {
                    if(!hasLocationPermission())
                        finish()
                    else showPermissionDeclinedRationale = false
                },
                onOkClick = {openAppSetting()}
            )
        }

        if (showRationale)
            LocationPermissionRequestDialog(
                onDismissClick = ::finish,
                onOkClick = {
                    showRationale = false
                    permissionLauncher.launch(PermissionUtils.allPermissions)
                }
            )

        LaunchedEffect(key1 = Unit) {
            when {
                hasAllPermission() -> return@LaunchedEffect
                PermissionUtils.locationPermissions.any { shouldShowRequestPermissionRationale(it) } -> showRationale =
                    true

                else -> permissionLauncher.launch(PermissionUtils.allPermissions)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == LocationUtils.LOCATION_ENABLE_REQUEST_CODE && resultCode != Activity.RESULT_OK) {
            Toast.makeText(
                this,
                "Please enable GPS to get proper running statistics.",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}
