package com.example.rides.ui.search.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rides.R
import com.example.rides.data.Vehicle

class VehicleAdapter(
        private val vehicleList: List<Vehicle>
    ) : RecyclerView.Adapter<VehicleAdapter.VehicleViewHolder>() {

    lateinit var listener: ((Vehicle?) -> Unit)

    class VehicleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val vinText: TextView = view.findViewById(R.id.vinText)
        val makeModelText: TextView = view.findViewById(R.id.makeModelText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_vehicles, parent, false)

        return VehicleViewHolder(adapterLayout)
    }

    override fun getItemCount(): Int = vehicleList.size

    override fun onBindViewHolder(holder: VehicleViewHolder, position: Int) {
        val currentVehicle = vehicleList[position]

        holder.vinText.text = currentVehicle.vin
        holder.makeModelText.text = currentVehicle.makeModel

        holder.itemView.setOnClickListener{
            listener.invoke(currentVehicle)
        }
    }
}