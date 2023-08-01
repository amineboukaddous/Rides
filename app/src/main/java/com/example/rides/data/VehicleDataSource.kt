package com.example.rides.data

import com.example.rides.network.VehicleApi
import javax.inject.Inject

class VehicleDataSource @Inject constructor(
    private val vehicleApi: VehicleApi
) {
    suspend fun fetchRandomVehicles(desiredVehicleCount: Int): List<Vehicle> {
        return vehicleApi.retrofitService.getVehicles(desiredVehicleCount)
    }
}