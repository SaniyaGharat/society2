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
import com.society.app.data.model.CategoryStat
import com.society.app.data.model.Expense
import com.society.app.databinding.FragmentExpenseBinding
import com.society.app.databinding.ItemLedgerEntryBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@AndroidEntryPoint
class ExpenseFragment : Fragment() {

    private var _binding: FragmentExpenseBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var api: SocietyApi
    private lateinit var adapter: CategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExpenseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        adapter = CategoryAdapter()
        binding.rvCategories.layoutManager = LinearLayoutManager(context)
        binding.rvCategories.adapter = adapter

        loadStats()

        binding.btnAddExpense.setOnClickListener {
            val category = binding.etCategory.text.toString()
            val amountStr = binding.etAmount.text.toString()
            val description = binding.etDescription.text.toString()
            val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

            if (category.isNotEmpty() && amountStr.isNotEmpty()) {
                val amount = amountStr.toDoubleOrNull() ?: 0.0
                addExpense(Expense(category = category, amount = amount, description = description, expenseDate = date))
            }
        }
    }

    private fun loadStats() {
        lifecycleScope.launch {
            try {
                val response = api.getExpenseCategoryStats()
                if (response.isSuccessful && response.body() != null) {
                    adapter.submitList(response.body()!!)
                }
            } catch (e: Exception) {
                Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun addExpense(expense: Expense) {
        lifecycleScope.launch {
            try {
                val response = api.addExpense(expense)
                if (response.isSuccessful) {
                    Toast.makeText(context, "Expense Added", Toast.LENGTH_SHORT).show()
                    binding.etCategory.text?.clear()
                    binding.etAmount.text?.clear()
                    binding.etDescription.text?.clear()
                    loadStats() // Refresh
                } else {
                    Toast.makeText(context, "Failed to add", Toast.LENGTH_SHORT).show()
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

    class CategoryAdapter : androidx.recyclerview.widget.ListAdapter<CategoryStat, CategoryAdapter.ViewHolder>(DiffCallback()) {
        class ViewHolder(val binding: ItemLedgerEntryBinding) : RecyclerView.ViewHolder(binding.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
             val binding = ItemLedgerEntryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
             return ViewHolder(binding)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = getItem(position)
            holder.binding.tvDescription.text = item.category
            holder.binding.tvAmount.text = "₹${item.total}"
            holder.binding.tvDate.visibility = View.GONE
            holder.binding.tvBalance.visibility = View.GONE
        }
        
        class DiffCallback : androidx.recyclerview.widget.DiffUtil.ItemCallback<CategoryStat>() {
            override fun areItemsTheSame(oldItem: CategoryStat, newItem: CategoryStat) = oldItem.category == newItem.category
            override fun areContentsTheSame(oldItem: CategoryStat, newItem: CategoryStat) = oldItem == newItem
        }
    }
}
