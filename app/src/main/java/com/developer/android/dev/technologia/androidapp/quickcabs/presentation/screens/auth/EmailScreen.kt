package com.developer.android.dev.technologia.androidapp.quickcabs.presentation.screens.auth

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.developer.android.dev.technologia.androidapp.quickcabs.R

@Composable
fun EmailScreen(
    navHostController: NavHostController
) {
    Scaffold(
        topBar = {
            EmailScreenTopBar(onBackButtonClick = { navHostController.navigateUp() })
        }
    ){paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            var Email by remember {
                mutableStateOf("")
            }
            var showError by remember { mutableStateOf(false) }

            Spacer(modifier = Modifier.height(10.dp))
            EmailTextField(
                value = Email,
                onValueChange = { Email = it }
            )
            Text(
                text = "We’ll send you your ride receipts",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.LightGray,
                modifier = Modifier.padding(start = 20.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {
                    if(Email.isBlank()){
                        showError = true
                    }else{
                        navHostController.navigate("UserNameScreen")
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .height(55.dp)
                    .border(BorderStroke(1.dp, Color.Gray), RoundedCornerShape(24.dp)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Blue
                ),
                shape = RoundedCornerShape(24.dp),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(
                    "Continue",
                    color = Color.White,
                    fontSize = 20.sp)

            }

            if (showError) {
                Text(
                    text = "Email is required",
                    color = Color.Red,
                    modifier = Modifier.padding(top = 8.dp, start = 16.dp)
                )
            }
        }
    }

}

@Composable
fun EmailTextField(
    value: String,
    onValueChange: (String) -> Unit,
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .border(BorderStroke(2.dp, Color.Blue), RoundedCornerShape(24.dp)),
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = {newNumber->
                    onValueChange(newNumber)
            },
            modifier = Modifier.weight(1f),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {  }),
            singleLine = true,
            label = { Text(text = "Email")},
            textStyle = TextStyle(fontSize = 18.sp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colorScheme.background,
                unfocusedBorderColor = MaterialTheme.colorScheme.surface
            ),
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailScreenTopBar(
    onBackButtonClick: () -> Unit
) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = onBackButtonClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back Navigation"
                )
            }
        },
        title = {
            Text(
                text = "Enter your email",
                style = MaterialTheme.typography.headlineSmall
            )
        }
    )
}