package com.developer.android.dev.technologia.androidapp.quickcabs.utils

import java.text.DecimalFormat

fun Float.toINRString():String{
    return try {
        val df = DecimalFormat("0.00")
        df.maximumFractionDigits = 2
        "₹" + df.format(this)
    }catch(ex:Exception){
        ""
    }
}