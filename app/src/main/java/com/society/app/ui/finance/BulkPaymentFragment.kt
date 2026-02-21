package com.society.app.ui.finance

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.society.app.data.api.SocietyApi
import com.society.app.data.model.BulkPaymentItem
import com.society.app.data.model.BulkPaymentRequest
import com.society.app.data.model.ResidentBalance
import com.society.app.databinding.FragmentBulkPaymentBinding
import com.society.app.databinding.ItemLedgerEntryBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class BulkPaymentFragment : Fragment() {

    private var _binding: FragmentBulkPaymentBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var api: SocietyApi
    private lateinit var adapter: BulkSelectionAdapter
    private var residents: List<ResidentBalance> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBulkPaymentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        adapter = BulkSelectionAdapter()
        binding.rvResidents.layoutManager = LinearLayoutManager(context)
        binding.rvResidents.adapter = adapter

        loadResidents()

        binding.btnProcessBulk.setOnClickListener {
            processPayments()
        }
    }

    private fun loadResidents() {
        lifecycleScope.launch {
            try {
                // Reusing balance endpoint to get residents with dues
                val response = api.getAllResidentBalances()
                if (response.isSuccessful && response.body() != null) {
                    // Filter those with dues > 0
                    residents = response.body()!!.filter { it.balance > 0 }
                    adapter.submitList(residents)
                }
            } catch (e: Exception) {
                Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun processPayments() {
        val selected = adapter.getSelectedItems()
        if (selected.isEmpty()) {
            Toast.makeText(context, "No residents selected", Toast.LENGTH_SHORT).show()
            return
        }

        val paymentItems = selected.map { 
            BulkPaymentItem(
                residentId = it.id,
                amount = it.balance, // Paying full balance for demo
                mode = "CASH",
                note = "Bulk Settlement"
            )
        }
        
        val request = BulkPaymentRequest(paymentItems)

        lifecycleScope.launch {
            try {
                val response = api.processBulkPayments(request)
                if (response.isSuccessful) {
                    Toast.makeText(context, "Bulk Processing Complete", Toast.LENGTH_LONG).show()
                    loadResidents() // Refresh
                } else {
                    Toast.makeText(context, "Failed to process", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    class BulkSelectionAdapter : androidx.recyclerview.widget.ListAdapter<ResidentBalance, BulkSelectionAdapter.ViewHolder>(DiffCallback()) {
        private val selectedItems = mutableSetOf<ResidentBalance>()

        class ViewHolder(val binding: ItemLedgerEntryBinding) : RecyclerView.ViewHolder(binding.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
             val binding = ItemLedgerEntryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
             return ViewHolder(binding)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = getItem(position)
            
            // Hacky check box since we are reusing layout. In real app, create new item layout.
            // Using existing views to simulate checking
            holder.binding.tvDate.text = "Flat ${item.apartmentNumber}"
            holder.binding.tvDescription.text = item.name
            holder.binding.tvAmount.text = "Due: ₹${item.balance}"
            
            val isSelected = selectedItems.contains(item)
            holder.binding.root.setBackgroundColor(
                if (isSelected) android.graphics.Color.LTGRAY else android.graphics.Color.WHITE
            )

            holder.binding.root.setOnClickListener {
                if (isSelected) selectedItems.remove(item) else selectedItems.add(item)
                notifyItemChanged(position)
            }
            
            holder.binding.tvBalance.text = if (isSelected) "SELECTED" else "Tap to Select"
            holder.binding.tvBalance.setTextColor(if (isSelected) android.graphics.Color.BLUE else android.graphics.Color.GRAY)
        }
        
        fun getSelectedItems(): List<ResidentBalance> = selectedItems.toList()
        
        class DiffCallback : androidx.recyclerview.widget.DiffUtil.ItemCallback<ResidentBalance>() {
            override fun areItemsTheSame(oldItem: ResidentBalance, newItem: ResidentBalance) = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: ResidentBalance, newItem: ResidentBalance) = oldItem == newItem
        }
    }
}
