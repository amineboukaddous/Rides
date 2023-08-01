package com.example.rides.data


import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize


@Parcelize
data class Vehicle(
    val id:Int,
    val uid: String,
    val vin: String,
    @Json(name = "make_and_model") val makeModel: String,
    val color: String,
    val transmission: String,
    @Json(name = "drive_type") val driveType: String,
    @Json(name = "fuel_type") val fuelType: String,
    @Json(name = "car_type") val carType: String,
    @Json(name = "car_options") val carOptions: List<String>,
    val specs: List<String>,
    val doors: Int,
    val mileage: Int,
    val kilometrage: Int,
    @Json(name = "license_plate") val licensePlate: String
) : Parcelable