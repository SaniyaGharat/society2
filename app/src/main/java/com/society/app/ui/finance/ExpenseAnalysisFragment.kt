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
import com.society.app.data.model.ExpenseTrend
import com.society.app.databinding.FragmentExpenseAnalysisBinding
import com.society.app.databinding.ItemLedgerEntryBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ExpenseAnalysisFragment : Fragment() {

    private var _binding: FragmentExpenseAnalysisBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var api: SocietyApi
    private lateinit var adapter: TrendAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExpenseAnalysisBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        adapter = TrendAdapter()
        binding.rvTrends.layoutManager = LinearLayoutManager(context)
        binding.rvTrends.adapter = adapter

        loadTrends()
    }

    private fun loadTrends() {
        lifecycleScope.launch {
            try {
                val response = api.getExpenseTrends()
                if (response.isSuccessful && response.body() != null) {
                    adapter.submitList(response.body()!!)
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

    class TrendAdapter : androidx.recyclerview.widget.ListAdapter<ExpenseTrend, TrendAdapter.ViewHolder>(DiffCallback()) {
        class ViewHolder(val binding: ItemLedgerEntryBinding) : RecyclerView.ViewHolder(binding.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
             val binding = ItemLedgerEntryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
             return ViewHolder(binding)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = getItem(position)
            holder.binding.tvDescription.text = item.category
            
            val icon = if (item.trend == "UP") "▲" else if (item.trend == "DOWN") "▼" else "▬"
            val color = if (item.trend == "UP") android.graphics.Color.RED else if (item.trend == "DOWN") android.graphics.Color.GREEN else android.graphics.Color.GRAY
            
            holder.binding.tvAmount.text = "${icon} ${item.changePercentage}%"
            holder.binding.tvAmount.setTextColor(color)
            
            holder.binding.tvDate.text = "Prev: ₹${item.previousMonthTotal}"
            holder.binding.tvBalance.text = "Curr: ₹${item.currentMonthTotal}"
        }
        
        class DiffCallback : androidx.recyclerview.widget.DiffUtil.ItemCallback<ExpenseTrend>() {
            override fun areItemsTheSame(oldItem: ExpenseTrend, newItem: ExpenseTrend) = oldItem.category == newItem.category
            override fun areContentsTheSame(oldItem: ExpenseTrend, newItem: ExpenseTrend) = oldItem == newItem
        }
    }
}
