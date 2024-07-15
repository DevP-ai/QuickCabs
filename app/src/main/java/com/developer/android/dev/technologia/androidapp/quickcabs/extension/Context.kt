package com.developer.android.dev.technologia.androidapp.quickcabs.extension

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.core.content.ContextCompat
import com.developer.android.dev.technologia.androidapp.quickcabs.utils.PermissionUtils

fun Context.hasLocationPermission() =
    PermissionUtils.locationPermissions.all {
        ContextCompat.checkSelfPermission(
            this,
            it
        ) == PackageManager.PERMISSION_GRANTED
    }

fun Context.hasAllPermission() =
    PermissionUtils.allPermissions.all {
        ContextCompat.checkSelfPermission(
            this,
            it
        ) == PackageManager.PERMISSION_GRANTED
    }

fun Context.openAppSetting() {
    Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        Uri.fromParts("package", packageName, null)
    ).also(::startActivity)
}