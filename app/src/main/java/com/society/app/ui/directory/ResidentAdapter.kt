package com.society.app.ui.directory

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.society.app.data.model.Resident
import com.society.app.databinding.ItemResidentBinding

class ResidentAdapter : RecyclerView.Adapter<ResidentAdapter.ResidentViewHolder>() {

    private var residents: List<Resident> = emptyList()

    fun submitList(newList: List<Resident>) {
        residents = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResidentViewHolder {
        val binding = ItemResidentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ResidentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ResidentViewHolder, position: Int) {
        holder.bind(residents[position])
    }

    override fun getItemCount() = residents.size

    class ResidentViewHolder(private val binding: ItemResidentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(resident: Resident) {
            binding.tvName.text = resident.name
            binding.tvUnit.text = resident.unitNumber
            binding.tvRole.text = resident.role
        }
    }
}
