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
import com.society.app.data.model.GenerateBillRequest
import com.society.app.data.model.MaintenanceSetting
import com.society.app.databinding.FragmentRecurringExpenseBinding
import com.society.app.databinding.ItemLedgerEntryBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class RecurringExpenseFragment : Fragment() {

    private var _binding: FragmentRecurringExpenseBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var api: SocietyApi
    private lateinit var adapter: SettingsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecurringExpenseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        adapter = SettingsAdapter()
        binding.rvSettings.layoutManager = LinearLayoutManager(context)
        binding.rvSettings.adapter = adapter

        loadSettings()

        binding.btnAddSetting.setOnClickListener {
            val category = binding.etCategory.text.toString()
            val amountStr = binding.etAmount.text.toString()

            if (category.isNotEmpty() && amountStr.isNotEmpty()) {
                val amount = amountStr.toDoubleOrNull() ?: 0.0
                addSetting(MaintenanceSetting(category = category, amount = amount))
            }
        }

        binding.btnGenerateBills.setOnClickListener {
            generateBills()
        }
    }

    private fun loadSettings() {
        lifecycleScope.launch {
            try {
                val response = api.getMaintenanceSettings()
                if (response.isSuccessful && response.body() != null) {
                    adapter.submitList(response.body()!!)
                }
            } catch (e: Exception) {
                Toast.makeText(context, "Error loading settings", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun addSetting(setting: MaintenanceSetting) {
        lifecycleScope.launch {
            try {
                val response = api.addMaintenanceSetting(setting)
                if (response.isSuccessful) {
                    Toast.makeText(context, "Setting added", Toast.LENGTH_SHORT).show()
                    binding.etCategory.text?.clear()
                    binding.etAmount.text?.clear()
                    loadSettings()
                } else {
                    Toast.makeText(context, "Failed to add", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun generateBills() {
        lifecycleScope.launch {
            try {
                // Hardcoded month/year for demo
                val response = api.generateMonthlyBills(GenerateBillRequest(month = "April", year = 2024))
                if (response.isSuccessful) {
                    Toast.makeText(context, response.body()?.msg ?: "Success", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Failed to generate", Toast.LENGTH_SHORT).show()
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

    // Simple inner adapter class for demo
    class SettingsAdapter : androidx.recyclerview.widget.ListAdapter<MaintenanceSetting, SettingsAdapter.ViewHolder>(DiffCallback()) {
        class ViewHolder(val binding: ItemLedgerEntryBinding) : RecyclerView.ViewHolder(binding.root) // Reuse existing item layout

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
             val binding = ItemLedgerEntryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
             return ViewHolder(binding)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = getItem(position)
            holder.binding.tvDescription.text = item.category
            holder.binding.tvAmount.text = "₹${item.amount}"
            holder.binding.tvDate.text = item.frequency
            holder.binding.tvBalance.visibility = View.GONE
        }
        
        class DiffCallback : androidx.recyclerview.widget.DiffUtil.ItemCallback<MaintenanceSetting>() {
            override fun areItemsTheSame(oldItem: MaintenanceSetting, newItem: MaintenanceSetting) = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: MaintenanceSetting, newItem: MaintenanceSetting) = oldItem == newItem
        }
    }
}
