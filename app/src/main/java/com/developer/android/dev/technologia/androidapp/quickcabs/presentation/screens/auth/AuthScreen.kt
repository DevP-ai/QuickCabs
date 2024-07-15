package com.developer.android.dev.technologia.androidapp.quickcabs.presentation.screens.auth

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.material.Divider
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
    Button(
        onClick = { },
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
            .border(BorderStroke(1.dp, Color.Gray), RoundedCornerShape(24.dp)),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(24.dp),
        contentPadding = PaddingValues(0.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.padding(
                start = 16.dp, end = 16.dp
            )
        ) {
            Image(
                painter = icon,
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text,
                color = Color.Black
            )
        }
    }
}