package com.society.app.ui.amenities

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.society.app.data.model.Amenity
import com.society.app.databinding.ItemAmenityBinding

class AmenitiesAdapter(private val onClick: (Amenity) -> Unit) : RecyclerView.Adapter<AmenitiesAdapter.AmenityViewHolder>() {

    private var amenities: List<Amenity> = emptyList()

    fun submitList(newList: List<Amenity>) {
        amenities = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AmenityViewHolder {
        val binding = ItemAmenityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AmenityViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: AmenityViewHolder, position: Int) {
        holder.bind(amenities[position])
    }

    override fun getItemCount() = amenities.size

    class AmenityViewHolder(
        private val binding: ItemAmenityBinding,
        val onClick: (Amenity) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(amenity: Amenity) {
            binding.tvAmenityName.text = amenity.name
            binding.tvDescription.text = amenity.description
            binding.tvAvailability.text = if (amenity.isAvailable) "AVAILABLE" else "RESERVED"
            binding.tvRate.text = "₹${amenity.hourlyRate}/hr"
            binding.tvCapacity.text = "Capacity: ${amenity.capacity}"
            
            binding.root.setOnClickListener {
                if (amenity.isAvailable) {
                    onClick(amenity)
                }
            }
        }
    }
}
