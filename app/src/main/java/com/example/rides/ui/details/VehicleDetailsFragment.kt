package com.example.rides.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.rides.R
import com.example.rides.data.Vehicle
import com.example.rides.databinding.FragmentVehicleDetailsBinding
import com.example.rides.widget.VehicleDetailsBottomSheetFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class VehicleDetailsFragment @Inject constructor(

) : Fragment() {
    private var _binding: FragmentVehicleDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<VehicleDetailsViewModel>()
    private val args: VehicleDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVehicleDetailsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.vehicle = args.vehicle
        viewModel.kilometrage = args.vehicle.kilometrage

        binding.carbonEmissions.setOnClickListener {
            val emissions: String =
                viewModel.estimateCarboneEmissions(viewModel.kilometrage).toString()
            val emissionTextViewValue = getString(R.string.carbon_emission_unit, emissions)
            val modalBottomSheet = VehicleDetailsBottomSheetFragment(emissionTextViewValue)

            activity?.let { activity ->
                modalBottomSheet.show(
                    activity.supportFragmentManager,
                    VehicleDetailsBottomSheetFragment.TAG
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}