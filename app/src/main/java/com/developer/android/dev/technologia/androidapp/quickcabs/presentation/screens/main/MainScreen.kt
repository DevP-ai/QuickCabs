package com.developer.android.dev.technologia.androidapp.quickcabs.presentation.screens.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.developer.android.dev.technologia.androidapp.quickcabs.presentation.screens.confirmPickUpScreen.ConfirmPickupScreen
import com.developer.android.dev.technologia.androidapp.quickcabs.presentation.screens.paymentOptions.PaymentOptionsScreen
import com.developer.android.dev.technologia.androidapp.quickcabs.presentation.screens.Screens
import com.developer.android.dev.technologia.androidapp.quickcabs.presentation.screens.home.HomeScreen
import com.developer.android.dev.technologia.androidapp.quickcabs.presentation.screens.wheretogo.LocationSearchScreen
import com.developer.android.dev.technologia.androidapp.quickcabs.presentation.screens.auth.AuthScreen
import com.developer.android.dev.technologia.androidapp.quickcabs.presentation.screens.auth.EmailScreen
import com.developer.android.dev.technologia.androidapp.quickcabs.presentation.screens.auth.MailOptionScreen
import com.developer.android.dev.technologia.androidapp.quickcabs.presentation.screens.auth.OTPScreen
import com.developer.android.dev.technologia.androidapp.quickcabs.presentation.screens.auth.PhoneNumberScreen
import com.developer.android.dev.technologia.androidapp.quickcabs.presentation.screens.auth.UserNameScreen
import com.developer.android.dev.technologia.androidapp.quickcabs.presentation.screens.mapwithcab.MapWithCab
import com.developer.android.dev.technologia.androidapp.quickcabs.presentation.screens.profile.ProfileScreen

@Composable
fun MainScreen(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = "AuthScreen") {
        composable("AuthScreen"){ AuthScreen(navHostController = navHostController)}
        composable("PhoneNumberScreen"){ PhoneNumberScreen(navHostController=navHostController)}
        composable("OTPScreen"){ OTPScreen(navHostController = navHostController)}
        composable("MailOptionScreen"){ MailOptionScreen(navHostController = navHostController)}
        composable("EmailScreen"){ EmailScreen(navHostController = navHostController) }
        composable("UserNameScreen"){ UserNameScreen(navHostController = navHostController)}
        composable("HomeScreen"){ HomeScreen(navHostController = navHostController)}
        composable("ProfileScreen"){ ProfileScreen(navHostController = navHostController)}
        composable("LocationSearchScreen"){ LocationSearchScreen(navHostController = navHostController) }
        composable("MapWithCab"){ MapWithCab(navHostController = navHostController) }
        composable("PaymentOptionsScreen"){ PaymentOptionsScreen(navigationController = navHostController) }
        composable("ConfirmPickupScreen"){ ConfirmPickupScreen(navHostController = navHostController) }
    }
}