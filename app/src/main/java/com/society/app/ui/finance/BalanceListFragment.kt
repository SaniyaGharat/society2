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
import com.society.app.data.model.ResidentBalance
import com.society.app.databinding.FragmentBalanceListBinding
import com.society.app.databinding.ItemLedgerEntryBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class BalanceListFragment : Fragment() {

    private var _binding: FragmentBalanceListBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var api: SocietyApi
    private lateinit var adapter: BalanceAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBalanceListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        adapter = BalanceAdapter()
        binding.rvBalances.layoutManager = LinearLayoutManager(context)
        binding.rvBalances.adapter = adapter

        loadBalances()
    }

    private fun loadBalances() {
        lifecycleScope.launch {
            try {
                val response = api.getAllResidentBalances()
                if (response.isSuccessful && response.body() != null) {
                    adapter.submitList(response.body()!!)
                } else {
                    Toast.makeText(context, "Failed to load balances", Toast.LENGTH_SHORT).show()
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

    class BalanceAdapter : androidx.recyclerview.widget.ListAdapter<ResidentBalance, BalanceAdapter.ViewHolder>(DiffCallback()) {
        class ViewHolder(val binding: ItemLedgerEntryBinding) : RecyclerView.ViewHolder(binding.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
             val binding = ItemLedgerEntryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
             return ViewHolder(binding)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = getItem(position)
            holder.binding.tvDate.text = "Flat ${item.apartmentNumber}"
            holder.binding.tvDescription.text = item.name
            holder.binding.tvAmount.text = "Due: ₹${item.balance}"
            
            if (item.balance > 0) {
                holder.binding.tvAmount.setTextColor(android.graphics.Color.RED)
            } else {
                holder.binding.tvAmount.setTextColor(android.graphics.Color.GREEN)
            }
            
            holder.binding.tvBalance.text = "Paid: ₹${item.totalPaid}"
        }
        
        class DiffCallback : androidx.recyclerview.widget.DiffUtil.ItemCallback<ResidentBalance>() {
            override fun areItemsTheSame(oldItem: ResidentBalance, newItem: ResidentBalance) = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: ResidentBalance, newItem: ResidentBalance) = oldItem == newItem
        }
    }
}
