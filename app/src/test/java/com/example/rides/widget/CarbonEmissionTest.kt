package com.example.rides.widget

import com.example.rides.ui.details.VehicleDetailsViewModel
import com.example.rides.ui.shared.MainViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CarbonEmissionTest{
    private lateinit var viewModel: VehicleDetailsViewModel

    @Before
    fun setup(){
        viewModel = VehicleDetailsViewModel()
    }

    @Test
    fun `input within 5000 range`(){
        val input = 5000
        val isValid = (viewModel.estimateCarboneEmissions(input) == 5000.0)

        assertEquals(true, isValid)
    }

    @Test
    fun `input above 5000`(){
        val input = 5500
        val isValid = (viewModel.estimateCarboneEmissions(input) == 8250.0)
        assertEquals(true, isValid)
    }
}