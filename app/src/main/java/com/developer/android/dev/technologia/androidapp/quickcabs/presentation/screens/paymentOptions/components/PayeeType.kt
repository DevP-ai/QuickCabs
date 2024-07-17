package com.developer.android.dev.technologia.androidapp.quickcabs.presentation.screens.paymentOptions.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.developer.android.dev.technologia.androidapp.quickcabs.ui.theme.spacing

@Composable
fun PayeeType(
    selectedItemTitle: String,
    onItemSelected: (itemTitle: String) -> Unit
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp)
    ) {
        item {
            UberSelectableChip(
                modifier = Modifier.padding(start = MaterialTheme.spacing.small),
                title = "Personal",
                isSelected = selectedItemTitle == "Personal",
                icon = Icons.Filled.Person,
                onClick = {
                    onItemSelected("Personal")
                }
            )
        }
        item {
            UberSelectableChip(
                modifier = Modifier.padding(start = MaterialTheme.spacing.small),
                title = "Business",
                isSelected = selectedItemTitle == "Business",
                icon = Icons.Filled.Work,
                onClick = {
                    onItemSelected("Business")
                },
                selectedBackgroundColor = MaterialTheme.colorScheme.secondary
            )
        }
    }
}