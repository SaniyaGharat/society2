package com.society.app.ui.finance

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.society.app.data.model.LedgerEntry
import com.society.app.databinding.ItemLedgerEntryBinding

class LedgerAdapter : ListAdapter<LedgerEntry, LedgerAdapter.LedgerViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LedgerViewHolder {
        val binding = ItemLedgerEntryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LedgerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LedgerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class LedgerViewHolder(private val binding: ItemLedgerEntryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(entry: LedgerEntry) {
            binding.tvDate.text = entry.date.take(10) // Simple date formatting
            binding.tvDescription.text = entry.description
            
            if (entry.debit > 0) {
                binding.tvAmount.text = "- ₹${entry.debit}"
                binding.tvAmount.setTextColor(android.graphics.Color.RED)
            } else {
                binding.tvAmount.text = "+ ₹${entry.credit}"
                binding.tvAmount.setTextColor(android.graphics.Color.GREEN)
            }
            
            binding.tvBalance.text = "Bal: ₹${entry.balance}"
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<LedgerEntry>() {
        override fun areItemsTheSame(oldItem: LedgerEntry, newItem: LedgerEntry): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: LedgerEntry, newItem: LedgerEntry): Boolean {
            return oldItem == newItem
        }
    }
}
