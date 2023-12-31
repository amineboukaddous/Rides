package com.example.rides.ui

import com.example.rides.ui.search.VehicleSearchViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class VehicleSearchViewModelTest{
    private lateinit var viewModel: VehicleSearchViewModel

    @Before
    fun setup(){
        viewModel = VehicleSearchViewModel()
    }

    @Test
    fun `input within range should return true`(){
        val input = 50
        val isValid = viewModel.isSearchInputValid(input)
        assertEquals(true, isValid)
    }

    @Test
    fun `input above range should return false`(){
        val input = 101
        val isValid = viewModel.isSearchInputValid(input)
        assertEquals(false, isValid)
    }

    @Test
    fun `input below range should return false`(){
        val input = 0
        val isValid = viewModel.isSearchInputValid(input)
        assertEquals(false, isValid)
    }
}