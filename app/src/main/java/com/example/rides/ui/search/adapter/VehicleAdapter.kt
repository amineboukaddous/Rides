package com.example.rides.ui.search.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.rides.R
import com.example.rides.data.Vehicle
import com.example.rides.databinding.ItemVehiclesBinding
import com.google.android.material.card.MaterialCardView

class VehicleAdapter(
    private val vehicleList: List<Vehicle>
) : RecyclerView.Adapter<VehicleAdapter.VehicleViewHolder>() {

    lateinit var listener: ((Vehicle) -> Unit)

    class VehicleViewHolder(private val binding: ItemVehiclesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(vehicle: Vehicle) {
            binding.vehicle = vehicle
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemVehiclesBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_vehicles, parent, false)

        return VehicleViewHolder(binding)
    }

    override fun getItemCount(): Int = vehicleList.size

    override fun onBindViewHolder(holder: VehicleViewHolder, position: Int) {
        val cardView = holder.itemView.findViewById<MaterialCardView>(R.id.cardView)
        val vehicle = vehicleList[position]
        cardView.setOnClickListener {
            listener.invoke(vehicle)
        }

        holder.bind(vehicle)
    }
}