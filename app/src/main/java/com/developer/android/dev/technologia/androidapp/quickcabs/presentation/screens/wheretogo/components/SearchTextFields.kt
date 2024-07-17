package com.developer.android.dev.technologia.androidapp.quickcabs.presentation.screens.wheretogo.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.developer.android.dev.technologia.androidapp.quickcabs.ui.theme.ClearIconTint
import com.developer.android.dev.technologia.androidapp.quickcabs.ui.theme.ContentTextStyle
import com.developer.android.dev.technologia.androidapp.quickcabs.ui.theme.PlaceholderTextStyle
import com.developer.android.dev.technologia.androidapp.quickcabs.ui.theme.SelectedBgColor
import com.developer.android.dev.technologia.androidapp.quickcabs.ui.theme.UnselectedBgColor
import com.developer.android.dev.technologia.androidapp.quickcabs.ui.theme.spacing
import com.developer.android.dev.technologia.androidapp.quickcabs.utils.clickableWithRipple


private val TfMinHeight = 48.dp

@Composable
fun UberTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    contentPadding: PaddingValues = PaddingValues(
        horizontal = MaterialTheme.spacing.medium,
        vertical = MaterialTheme.spacing.extraSmall
    ),
    placeholder: String,
    onFocus: () -> Unit
) {
    var isTfFocused by rememberSaveable { mutableStateOf(false) }
    val bgColor by animateColorAsState(targetValue = if (isTfFocused) SelectedBgColor else UnselectedBgColor)

    Box(
        modifier = modifier
            .background(bgColor)
            .defaultMinSize(minHeight = TfMinHeight),
        contentAlignment = Alignment.CenterStart
    ) {
        AnimatedVisibility(
            visible = value.isEmpty(),
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(contentPadding),
                text = placeholder,
                style = PlaceholderTextStyle,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BasicTextField(
                modifier = Modifier
                    .weight(5f)
                    .padding(contentPadding)
                    .onFocusChanged {
                        isTfFocused = it.isFocused || it.hasFocus
                        if (isTfFocused) { onFocus() }
                    },
                value = value,
                onValueChange = onValueChange,
                textStyle = ContentTextStyle
            )
            AnimatedVisibility(
                modifier = Modifier
                    .weight(1f)
                    .clip(CircleShape),
                visible = value.isNotBlank(),
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Icon(
                    modifier = Modifier.clickableWithRipple {
                        onValueChange("")
                    },
                    imageVector = Icons.Default.Close,
                    contentDescription = null,
                    tint = ClearIconTint
                )
            }
        }
    }
}