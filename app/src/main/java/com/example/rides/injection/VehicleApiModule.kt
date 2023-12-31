package com.example.rides.injection

import com.example.rides.data.network.VehicleApi
import com.example.rides.data.network.VehicleApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object VehicleApiModule {
    @Provides
    fun provideVehicleApi(): VehicleApiService {
        return VehicleApi().retrofitService
    }
}