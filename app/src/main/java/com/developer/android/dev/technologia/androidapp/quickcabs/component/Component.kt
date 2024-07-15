package com.developer.android.dev.technologia.androidapp.quickcabs.component

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

@Composable
fun LocationPermissionRequestDialog(
    modifier: Modifier = Modifier,
    onDismissClick: () -> Unit,
    onOkClick: () -> Unit,
) {

    AlertDialog(
        onDismissRequest = onDismissClick,
        title = {
            androidx.compose.material.Text(
                text = "Permission Required",
                style = MaterialTheme.typography.titleLarge
            )
        },
        text = {
            androidx.compose.material.Text(
                text = "LocationPoint permission is needed to record you running status.",
                style = MaterialTheme.typography.bodyMedium
            )
        },
        modifier = modifier,
        confirmButton = {
            Button(
                onClick = onOkClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    contentColor = MaterialTheme.colorScheme.onTertiaryContainer
                )
            ) {
                androidx.compose.material.Text(
                    text = "Grant",
                    style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                )
            }
        },
        dismissButton = {
            Button(
                onClick = onDismissClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            ) {
                androidx.compose.material.Text(
                    text = "Deny",
                    style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.SemiBold),
                )
            }
        },
    )

}