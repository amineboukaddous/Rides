package com.example.rides.data

class VehicleRepository(private val vehicleDataSource: VehicleDataSource) {
    suspend fun fetchRandomVehicles(desiredVehicleCount: Int): List<Vehicle> {
        return vehicleDataSource.fetchRandomVehicles(desiredVehicleCount)
    }
}