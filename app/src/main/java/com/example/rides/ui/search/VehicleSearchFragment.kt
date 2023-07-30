package com.example.rides.ui.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.rides.data.Vehicle
import com.example.rides.databinding.FragmentSearchBinding
import com.example.rides.ui.search.adapter.VehicleAdapter
import com.example.rides.ui.shared.MainViewModel

class VehicleSearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.vehicleRecyclerView.adapter = VehicleAdapter(listOf<Vehicle>())

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.vehicleInformation.observe(viewLifecycleOwner){ it ->
            if(it.isNotEmpty()){
                val vehicleAdapter:VehicleAdapter = VehicleAdapter(it)
                binding.vehicleRecyclerView.adapter = vehicleAdapter

                vehicleAdapter.listener = { vehicle ->
                    vehicle?.let { viewModel.updateSelectedVehicleItem(vehicle) }
                }
            }
        }

        viewModel.selectedVehicle.observe(viewLifecycleOwner){
            //TODO: use action to navigate to the vehicle details fragment
            Log.d("PDEBUG", it.vin)
        }

        binding.submitButton.setOnClickListener {
            val inputFieldValue:String = binding.inputField.text.toString()

            if(inputFieldValue.isNotEmpty()){
                val vehicleCount = Integer.parseInt(inputFieldValue)
                viewModel.loadVehicles(vehicleCount)
            }else{
                Log.d("View", "Bad input")
            }
        }

        binding.vehicleRecyclerView
    }
}