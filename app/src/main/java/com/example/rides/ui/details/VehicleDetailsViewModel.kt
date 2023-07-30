package com.example.rides.ui.details

import androidx.lifecycle.ViewModel

class VehicleDetailsViewModel : ViewModel(){
    var kilometrage: Int = 0

    fun estimateCarboneEmissions(kilometrage: Int) : Double{
        var unitPerKilometre = 1.0
        if(kilometrage > 5000) unitPerKilometre = 1.5

        return unitPerKilometre * kilometrage
    }
}