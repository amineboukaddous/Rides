package com.example.rides.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.rides.R
import com.example.rides.data.Vehicle
import com.example.rides.databinding.FragmentVehicleSearchBinding
import com.example.rides.ui.search.adapter.VehicleAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VehicleSearchFragment : Fragment() {
    private var _binding: FragmentVehicleSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<VehicleSearchViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVehicleSearchBinding.inflate(inflater, container, false)
        binding.vehicleRecyclerView.adapter = VehicleAdapter(listOf<Vehicle>())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.vehicleList.observe(viewLifecycleOwner){ it ->
            if(it.isNotEmpty()){
                val vehicleAdapter = VehicleAdapter(it)
                binding.vehicleSwipeRefresh.visibility = View.VISIBLE
                binding.progressBar.visibility = View.GONE
                binding.vehicleRecyclerView.adapter = vehicleAdapter
                binding.inputField.error = null

                vehicleAdapter.listener = { vehicle ->
                    vehicle?.let {
                        val action = VehicleSearchFragmentDirections.actionVehicleSearchFragmentToVehicleDetailsFragment(it)

                        findNavController().navigate(action)
                    }
                }
            }
        }

        binding.submitButton.setOnClickListener {
            val editText = binding.inputField.editText
            val inputFieldValue:String = editText?.text.toString()

            if(inputFieldValue.isNotEmpty()){
                val vehicleCount = Integer.parseInt(inputFieldValue)

                if(viewModel.isSearchInputValid(vehicleCount)){
                    binding.progressBar.visibility = View.VISIBLE
                    viewModel.loadVehicles(vehicleCount)
                } else{
                    binding.inputField.error = getString(R.string.bad_input_field_search)
                }
            } else{
                binding.inputField.error = getString(R.string.empty_input_field)
            }
        }

        binding.inputField.editText?.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) binding.inputField.error = null
        }

        binding.vehicleSwipeRefresh.setOnRefreshListener {
            val vehicleCount: Int = viewModel.vehicleList.value?.size ?: 0

            viewModel.loadVehicles(vehicleCount)
            binding.vehicleSwipeRefresh.isRefreshing = false
        }
    }
}