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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.developer.android.dev.technologia.androidapp.quickcabs.R

@Composable
fun MailOptionScreen(
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
                text = "Create an account",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(bottom = 40.dp)
                    .clickable {
                        navHostController.navigate("PhoneNumberScreen")
                    }
            )
            Text(
                text = "save time by linking your social account.\nwe will never share any personal data.",
                fontSize = 20.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(5.dp))

            AuthButton(
                text = "Sign in with Google",
                icon = painterResource(id = R.drawable.google)
            )
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
            Text("OR", fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurface)



            Spacer(modifier = Modifier.height(15.dp))

            emailButton(
                text = "Continue with email",
                onClick = { navHostController.navigate("EmailScreen") }
            )

        }

    }
}


@Composable
fun emailButton(text: String,onClick:()->Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
            .border(BorderStroke(1.dp, Color.Gray), RoundedCornerShape(24.dp)),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.LightGray
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
            Text(
                text,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        }
    }
}