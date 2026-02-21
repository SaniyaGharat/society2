package com.society.app.ui.visitors

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.society.app.data.model.Visitor
import com.society.app.databinding.ItemVisitorBinding

class VisitorAdapter : RecyclerView.Adapter<VisitorAdapter.VisitorViewHolder>() {

    private var visitors: List<Visitor> = emptyList()

    fun submitList(newList: List<Visitor>) {
        visitors = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VisitorViewHolder {
        val binding = ItemVisitorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VisitorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VisitorViewHolder, position: Int) {
        holder.bind(visitors[position])
    }

    override fun getItemCount() = visitors.size

    class VisitorViewHolder(private val binding: ItemVisitorBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(visitor: Visitor) {
            binding.tvVisitorName.text = visitor.name
            binding.tvType.text = visitor.type
            binding.tvStatus.text = visitor.status
            binding.tvDate.text = visitor.visitDate.toString()
        }
    }
}
