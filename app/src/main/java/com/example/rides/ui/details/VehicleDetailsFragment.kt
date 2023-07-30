package com.example.rides.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.rides.R
import com.example.rides.databinding.FragmentVehicleDetailsBinding
import com.example.rides.ui.shared.MainViewModel

class VehicleDetailsFragment : Fragment() {
    private var _binding: FragmentVehicleDetailsBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel by activityViewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVehicleDetailsBinding.inflate(inflater, container, false)
        binding.viewModel = sharedViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel.selectedVehicle.observe(viewLifecycleOwner){
            binding.vehicleMakeModel.text = it.makeModel
            binding.vehicleVin.text = getString(R.string.vin_vehicle_detail, it.vin)
            binding.vehicleColor.text = getString(R.string.color_vehicle_detail, it.color)
            binding.vehicleType.text = getString(R.string.type_vehicle_detail, it.carType)
        }
    }
}