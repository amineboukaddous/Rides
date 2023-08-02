package com.example.rides.injection

import com.example.rides.data.VehicleDataSource
import com.example.rides.data.VehicleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    fun provideVehicleRepository(vehicleDataSource: VehicleDataSource): VehicleRepository {
        return VehicleRepository(vehicleDataSource)
    }
}
