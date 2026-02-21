package com.society.app.ui.finance

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.society.app.data.api.SocietyApi
import com.society.app.data.model.PaymentHistoryItem
import com.society.app.databinding.FragmentMonthlyHistoryBinding
import com.society.app.databinding.ItemLedgerEntryBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@AndroidEntryPoint
class MonthlyHistoryFragment : Fragment() {

    private var _binding: FragmentMonthlyHistoryBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var api: SocietyApi
    private lateinit var adapter: HistoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMonthlyHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        adapter = HistoryAdapter()
        binding.rvHistory.layoutManager = LinearLayoutManager(context)
        binding.rvHistory.adapter = adapter

        // Default to current date
        val calendar = Calendar.getInstance()
        val currentMonth = calendar.get(Calendar.MONTH) + 1
        val currentYear = calendar.get(Calendar.YEAR)
        
        binding.etMonth.setText(currentMonth.toString())
        binding.etYear.setText(currentYear.toString())

        loadHistory(currentMonth, currentYear)

        binding.btnFilter.setOnClickListener {
            val m = binding.etMonth.text.toString().toIntOrNull()
            val y = binding.etYear.text.toString().toIntOrNull()
            
            if (m != null && y != null) {
                loadHistory(m, y)
            } else {
                Toast.makeText(context, "Invalid Month/Year", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadHistory(month: Int, year: Int) {
        lifecycleScope.launch {
            try {
                val response = api.getMonthlyPaymentHistory(month, year)
                if (response.isSuccessful && response.body() != null) {
                    adapter.submitList(response.body()!!)
                } else {
                    Toast.makeText(context, "No records found", Toast.LENGTH_SHORT).show()
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

    class HistoryAdapter : androidx.recyclerview.widget.ListAdapter<PaymentHistoryItem, HistoryAdapter.ViewHolder>(DiffCallback()) {
        class ViewHolder(val binding: ItemLedgerEntryBinding) : RecyclerView.ViewHolder(binding.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
             val binding = ItemLedgerEntryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
             return ViewHolder(binding)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = getItem(position)
            holder.binding.tvDate.text = "${item.paymentDate.take(10)}"
            holder.binding.tvDescription.text = "${item.residentName} (${item.apartmentNumber})"
            holder.binding.tvAmount.text = "₹${item.amount}"
            holder.binding.tvBalance.text = "${item.paymentMode} - ${item.status}"
            
             if (item.status == "VERIFIED") {
                holder.binding.tvBalance.setTextColor(android.graphics.Color.GREEN)
            } else {
                holder.binding.tvBalance.setTextColor(android.graphics.Color.GRAY)
            }
        }
        
        class DiffCallback : androidx.recyclerview.widget.DiffUtil.ItemCallback<PaymentHistoryItem>() {
            override fun areItemsTheSame(oldItem: PaymentHistoryItem, newItem: PaymentHistoryItem) = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: PaymentHistoryItem, newItem: PaymentHistoryItem) = oldItem == newItem
        }
    }
}
