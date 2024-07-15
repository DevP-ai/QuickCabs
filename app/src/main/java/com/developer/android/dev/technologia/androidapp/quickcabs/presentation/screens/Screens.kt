package com.developer.android.dev.technologia.androidapp.quickcabs.presentation.screens

enum class Screens(private val route:String) {
    MapScreen("MapScreen"),
    PaymentOptionsScreen("paymentOptionsScreen"),
    SchedulePickupScreen("schedulePickupScreen"),
    AddPaymentMethodScreen("addPaymentMethodScreen"),
    ConfirmPickUpLocation("ConfirmPickUpLocation"),
    WhereToScreen("whereToScreen");

    operator fun invoke() = route
}