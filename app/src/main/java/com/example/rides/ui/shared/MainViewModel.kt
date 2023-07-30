package com.example.rides.ui.shared

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rides.data.Vehicle
import com.example.rides.network.VehicleApi
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private var _selectedVehicle = MutableLiveData<Vehicle>()
    val selectedVehicle: LiveData<Vehicle> = _selectedVehicle

    private val _vehicleInformation = MutableLiveData<List<Vehicle>>()
    val vehicleInformation:LiveData<List<Vehicle>> = _vehicleInformation

    fun loadVehicles(desiredVehicleCount: Int){
        viewModelScope.launch {
            try {
                val vehicles = VehicleApi.retrofitService.getVehicles(desiredVehicleCount)
                val sortedVehicles = vehicles.sortedBy { it.vin }

                _vehicleInformation.value = sortedVehicles
            }catch (e: Exception){
                Log.d("RETROFIT", "Failure: ${e.message}")
            }
        }
    }

    fun updateSelectedVehicleItem(vehicle: Vehicle){
        _selectedVehicle.value = vehicle
    }

    fun getVehicleCount(): Int? = _vehicleInformation.value?.size

    fun isSearchInputValid(vehicleCount: Int) : Boolean{
        if((vehicleCount >= 1) and (vehicleCount <= 100)) return true
        return false
    }
}