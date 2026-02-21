package com.society.app.ui.complaints

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.society.app.data.model.Complaint
import com.society.app.databinding.ItemComplaintBinding

class ComplaintAdapter : RecyclerView.Adapter<ComplaintAdapter.ComplaintViewHolder>() {

    private var complaints: List<Complaint> = emptyList()

    fun submitList(newList: List<Complaint>) {
        complaints = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComplaintViewHolder {
        val binding = ItemComplaintBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ComplaintViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ComplaintViewHolder, position: Int) {
        holder.bind(complaints[position])
    }

    override fun getItemCount() = complaints.size

    class ComplaintViewHolder(private val binding: ItemComplaintBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(complaint: Complaint) {
            binding.tvComplaintTitle.text = complaint.title
            binding.tvDescription.text = complaint.description
            binding.tvStatus.text = complaint.status
            binding.tvCategory.text = complaint.category
        }
    }
}
