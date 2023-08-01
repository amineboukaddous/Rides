package com.example.rides.data.network

import com.example.rides.data.Vehicle
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject

private const val BASE_URL = "https://random-data-api.com/api/vehicle/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface VehicleApiService {
    @GET("random_vehicle")
    suspend fun getVehicles(@Query("size") size: Int): List<Vehicle>
}

class VehicleApi @Inject constructor(){
    val retrofitService: VehicleApiService by lazy { retrofit.create(VehicleApiService::class.java) }
}
