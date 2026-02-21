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
import com.society.app.data.model.ForecastItem
import com.society.app.databinding.FragmentForecastBinding
import com.society.app.databinding.ItemLedgerEntryBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ForecastFragment : Fragment() {

    private var _binding: FragmentForecastBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var api: SocietyApi
    private lateinit var adapter: ForecastAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentForecastBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        adapter = ForecastAdapter()
        binding.rvForecast.layoutManager = LinearLayoutManager(context)
        binding.rvForecast.adapter = adapter

        loadForecast()
    }

    private fun loadForecast() {
        lifecycleScope.launch {
            try {
                val response = api.getFinancialForecast()
                if (response.isSuccessful && response.body() != null) {
                    val result = response.body()!!
                    
                    binding.tvAverages.text = """
                        Avg Monthly Income: ₹${result.averageMonthlyIncome}
                        Avg Monthly Expense: ₹${result.averageMonthlyExpense}
                    """.trimIndent()
                    
                    adapter.submitList(result.forecast)
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

    class ForecastAdapter : androidx.recyclerview.widget.ListAdapter<ForecastItem, ForecastAdapter.ViewHolder>(DiffCallback()) {
        class ViewHolder(val binding: ItemLedgerEntryBinding) : RecyclerView.ViewHolder(binding.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
             val binding = ItemLedgerEntryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
             return ViewHolder(binding)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = getItem(position)
            holder.binding.tvDate.text = "Month ${item.month}"
            holder.binding.tvDescription.text = "Projected Savings"
            holder.binding.tvAmount.text = "₹${item.projectedSavings}"
            holder.binding.tvBalance.text = "In: ${item.projectedIncome} | Out: ${item.projectedExpense}"
        }
        
        class DiffCallback : androidx.recyclerview.widget.DiffUtil.ItemCallback<ForecastItem>() {
            override fun areItemsTheSame(oldItem: ForecastItem, newItem: ForecastItem) = oldItem.month == newItem.month
            override fun areContentsTheSame(oldItem: ForecastItem, newItem: ForecastItem) = oldItem == newItem
        }
    }
}
