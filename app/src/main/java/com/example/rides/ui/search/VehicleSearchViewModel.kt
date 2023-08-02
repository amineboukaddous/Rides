package com.example.rides.ui.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rides.data.Vehicle
import com.example.rides.data.VehicleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VehicleSearchViewModel @Inject constructor(
    private val vehicleRepository: VehicleRepository
) : ViewModel() {

    private val _vehicleList = MutableLiveData<List<Vehicle>>()
    val vehicleList: LiveData<List<Vehicle>> = _vehicleList

    fun loadVehicles(desiredVehicleCount: Int) {
        viewModelScope.launch {
            try {
                val vehicles = vehicleRepository.fetchRandomVehicles(desiredVehicleCount)
                val sortedVehicles = vehicles.sortedBy { it.vin }

                _vehicleList.value = sortedVehicles
            } catch (e: Exception) {
                Log.d("RETROFIT", "Failure: ${e.message}")
            }
        }
    }

    fun isSearchInputValid(vehicleCount: Int): Boolean {
        if ((vehicleCount >= 1) and (vehicleCount <= 100)) return true
        return false
    }
}