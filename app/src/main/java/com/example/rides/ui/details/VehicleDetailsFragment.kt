package com.example.rides.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.rides.R
import com.example.rides.data.Vehicle
import com.example.rides.databinding.FragmentVehicleDetailsBinding
import com.example.rides.widget.VehicleDetailsBottomSheetFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VehicleDetailsFragment : Fragment() {
    private lateinit var vehicle: Vehicle

    private var _binding: FragmentVehicleDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<VehicleDetailsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            vehicle = it.getParcelable("vehicleArg")!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVehicleDetailsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.vehicleMakeModel.text = vehicle.makeModel
        binding.vehicleVin.text = getString(R.string.vin_vehicle_detail, vehicle.vin)
        binding.vehicleColor.text = getString(R.string.color_vehicle_detail, vehicle.color)
        binding.vehicleType.text = getString(R.string.type_vehicle_detail, vehicle.carType)
        viewModel.kilometrage = vehicle.kilometrage

        binding.carbonEmissions.setOnClickListener{
            val kilometrage: Int = viewModel.kilometrage
            val emissions: String = viewModel.estimateCarboneEmissions(kilometrage).toString()
            val emissionTextViewValue = getString(R.string.carbon_emission_unit, emissions)

            val modalBottomSheet = VehicleDetailsBottomSheetFragment(emissionTextViewValue)
            activity?.let { it1 -> modalBottomSheet.show(it1.supportFragmentManager, VehicleDetailsBottomSheetFragment.TAG) }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}