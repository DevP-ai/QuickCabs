package com.developer.android.dev.technologia.androidapp.quickcabs.presentation.screens.main

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.developer.android.dev.technologia.androidapp.quickcabs.presentation.screens.Home.HomeScreen
import com.developer.android.dev.technologia.androidapp.quickcabs.presentation.screens.Home.LocationSearchScreen
import com.developer.android.dev.technologia.androidapp.quickcabs.presentation.screens.auth.AuthScreen
import com.developer.android.dev.technologia.androidapp.quickcabs.presentation.screens.auth.EmailScreen
import com.developer.android.dev.technologia.androidapp.quickcabs.presentation.screens.auth.MailOptionScreen
import com.developer.android.dev.technologia.androidapp.quickcabs.presentation.screens.auth.OTPScreen
import com.developer.android.dev.technologia.androidapp.quickcabs.presentation.screens.auth.PhoneNumberScreen
import com.developer.android.dev.technologia.androidapp.quickcabs.presentation.screens.auth.UserNameScreen
import com.developer.android.dev.technologia.androidapp.quickcabs.presentation.screens.profile.ProfileScreen

@Composable
fun MainScreen(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = "HomeScreen") {
        composable("AuthScreen"){ AuthScreen(navHostController = navHostController)}
        composable("PhoneNumberScreen"){ PhoneNumberScreen(navHostController=navHostController)}
        composable("OTPScreen"){ OTPScreen(navHostController = navHostController)}
        composable("MailOptionScreen"){ MailOptionScreen(navHostController = navHostController)}
        composable("EmailScreen"){ EmailScreen(navHostController = navHostController) }
        composable("UserNameScreen"){ UserNameScreen(navHostController = navHostController)}
        composable("HomeScreen"){ HomeScreen(navHostController = navHostController)}
        composable("ProfileScreen"){ ProfileScreen(navHostController = navHostController)}
        composable("LocationSearchScreen"){ LocationSearchScreen(navHostController = navHostController)}
    }
}