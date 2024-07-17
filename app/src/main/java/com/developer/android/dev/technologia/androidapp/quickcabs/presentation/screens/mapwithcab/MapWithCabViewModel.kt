package com.developer.android.dev.technologia.androidapp.quickcabs.presentation.screens.mapwithcab

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.developer.android.dev.technologia.androidapp.quickcabs.R
import com.developer.android.dev.technologia.androidapp.quickcabs.model.CabInfo

class MapWithCabViewModel: ViewModel() {
    var cabList = mutableStateListOf(
        CabInfo(
            cabInfo = "QuikCab Bike",
            cabIcon =
            R.drawable.qc_bike,
            cabPrice = 120.80f,
            carTime = "11:19 AM"
        ),
        CabInfo(
            cabInfo = "QuikCab Pool",
            cabIcon =
            R.drawable.qc_carpool,
            cabPrice = 80.80f,
            isChecked = true,
            cabPriceAlter = 100.20f,
            carTime = "3:09 PM"
        ),
        CabInfo(
            cabInfo = "QuikCab Car",
            cabIcon =
            R.drawable.qc_ride,
            cabPrice = 120.80f,
            cabPriceAlter = 200.20f,
            carTime = "1:19 PM"
        ),
        CabInfo(
            cabInfo = "QuikCab Bike Scooter",
            cabIcon =
            R.drawable.qc_bike_scooter,
            cabPrice = 120.80f,
            carTime = "11:19 AM"
        ),
        CabInfo(
            cabInfo = "QuikCab Pool",
            cabIcon =
            R.drawable.qc_carpool,
            cabPrice = 80.80f,
            cabPriceAlter = 100.20f,
            carTime = "3:09 PM"
        ),
        CabInfo(
            cabInfo = "QuikCab Car",
            cabIcon =
            R.drawable.qc_ride,
            cabPrice = 80.80f,
            cabPriceAlter = 110.20f,
            carTime = "1:19 PM"
        )
    )
    var selectedCab: CabInfo? = null

    fun selectItem(selectedCabIndex:Int){
        cabList.forEach {
            it.isChecked = false
        }
        cabList[selectedCabIndex] = cabList[selectedCabIndex].copy(isChecked = true)
        selectedCab = cabList[selectedCabIndex]
    }

    fun selectedItem(): CabInfo {
        return selectedCab?:cabList.first()
    }
}