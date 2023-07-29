package com.example.rides.ui.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rides.data.VehicleModel
import com.example.rides.network.VehicleApi
import com.example.rides.network.VehicleApiService
import com.squareup.moshi.JsonAdapter
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {
    val vehiculeInformation = MutableLiveData<List<VehicleModel>>()

    init {
        getVehicles()
    }

    private fun getVehicles(){
        viewModelScope.launch {
            try {
                vehiculeInformation.value = VehicleApi.retrofitService.getVehicles()
            }catch (e: Exception){
                Log.d("RETROFIT", "Failure: ${e.message}")
            }
        }
    }
}