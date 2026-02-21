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
import com.society.app.data.model.MonthlyReport
import com.society.app.databinding.FragmentReportsBinding
import com.society.app.databinding.ItemLedgerEntryBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ReportsFragment : Fragment() {

    private var _binding: FragmentReportsBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var api: SocietyApi
    private lateinit var adapter: ReportAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReportsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        adapter = ReportAdapter()
        binding.rvReports.layoutManager = LinearLayoutManager(context)
        binding.rvReports.adapter = adapter

        loadReports()
    }

    private fun loadReports() {
        lifecycleScope.launch {
            try {
                val response = api.getMonthlyFinancialReport(2024)
                if (response.isSuccessful && response.body() != null) {
                    val reports = response.body()!!
                    // Filter out empty months for cleaner view
                    val activeMonths = reports.filter { it.income > 0 || it.expense > 0 }
                    adapter.submitList(activeMonths)
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

    class ReportAdapter : androidx.recyclerview.widget.ListAdapter<MonthlyReport, ReportAdapter.ViewHolder>(DiffCallback()) {
        class ViewHolder(val binding: ItemLedgerEntryBinding) : RecyclerView.ViewHolder(binding.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
             val binding = ItemLedgerEntryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
             return ViewHolder(binding)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = getItem(position)
            holder.binding.tvDate.text = "Month ${item.month}"
            holder.binding.tvDescription.text = "Net: ₹${item.netSavings}"
            holder.binding.tvAmount.text = "In: ₹${item.income} | Out: ₹${item.expense}"
            holder.binding.tvBalance.visibility = View.GONE
        }
        
        class DiffCallback : androidx.recyclerview.widget.DiffUtil.ItemCallback<MonthlyReport>() {
            override fun areItemsTheSame(oldItem: MonthlyReport, newItem: MonthlyReport) = oldItem.month == newItem.month
            override fun areContentsTheSame(oldItem: MonthlyReport, newItem: MonthlyReport) = oldItem == newItem
        }
    }
}
