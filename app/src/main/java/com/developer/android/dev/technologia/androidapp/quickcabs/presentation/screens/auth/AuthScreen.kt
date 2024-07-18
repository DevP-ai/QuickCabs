package com.developer.android.dev.technologia.androidapp.quickcabs.presentation.screens.auth

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.developer.android.dev.technologia.androidapp.quickcabs.R
import androidx.compose.material3.Button
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import com.developer.android.dev.technologia.androidapp.quickcabs.ui.theme.colorGrayExtraLight
import com.developer.android.dev.technologia.androidapp.quickcabs.ui.theme.spacing
import com.developer.android.dev.technologia.androidapp.quickcabs.utils.clickableWithRipple
import com.developer.android.dev.technologia.androidapp.quickcabs.utils.limitWidth

@Composable
fun AuthScreen(
    navHostController: NavHostController
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text(
                text = "Enter You Number",
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(bottom = 40.dp)
                    .clickable {
                        navHostController.navigate("PhoneNumberScreen")
                    }
            )

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
            Text("OR", fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurface)

            Spacer(modifier = Modifier.height(16.dp))

            AuthButton(
                text = "Sign in with Google",
                icon = painterResource(id = R.drawable.google)
            )

            Spacer(modifier = Modifier.height(8.dp))

            AuthButton(
                text = "Sign in with Facebook",
                icon = painterResource(id = R.drawable.facebook)
            )

        }
        Text(
            text = "If you are creating a new account, Terms & Conditions and Privacy Policy will apply",
            fontSize = 12.sp,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp),
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
        )

    }


}

@Composable
fun AuthButton(text: String, icon: Painter) {

    Row(
        modifier = Modifier
            .height(80.dp)
            .limitWidth()
            .padding(start = 20.dp, end = 20.dp, top = 30.dp)
            .clip(RoundedCornerShape(50.dp))
            .background(color = colorGrayExtraLight)
            .clickableWithRipple {

            },
        verticalAlignment = Alignment.CenterVertically
    ){
        Row(
            modifier = Modifier
                .height(40.dp)
                .weight(1.5f)
                .padding(start = MaterialTheme.spacing.medium)
                .background(color = Color.Transparent),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)
        ){
            Image(
                painter = icon,
                contentDescription = "",
                modifier = Modifier
                    .size(24.dp)
            )
            androidx.compose.material.Text(
                text = text,
                color = Color.Black,
                fontSize = 20.sp,
                modifier = Modifier.alpha(ContentAlpha.medium)
            )
        }
    }
}