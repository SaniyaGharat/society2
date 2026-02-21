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
import com.society.app.data.model.Defaulter
import com.society.app.databinding.FragmentPenaltyBinding
import com.society.app.databinding.ItemLedgerEntryBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class PenaltyFragment : Fragment() {

    private var _binding: FragmentPenaltyBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var api: SocietyApi
    private lateinit var adapter: DefaulterAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPenaltyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        adapter = DefaulterAdapter()
        binding.rvDefaulters.layoutManager = LinearLayoutManager(context)
        binding.rvDefaulters.adapter = adapter

        loadStats()

        binding.btnCalculate.setOnClickListener {
            calculatePenalties()
        }
    }

    private fun loadStats() {
        lifecycleScope.launch {
            try {
                val response = api.getPenaltyStats()
                if (response.isSuccessful && response.body() != null) {
                    val stats = response.body()!!
                    binding.tvStats.text = """
                        Total Penalties: ₹${stats.totalPenalties}
                        Collected: ₹${stats.collected}
                        Outstanding: ₹${stats.outstanding}
                    """.trimIndent()
                    
                    adapter.submitList(stats.defaulters)
                }
            } catch (e: Exception) {
                Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun calculatePenalties() {
        lifecycleScope.launch {
            try {
                val response = api.calculatePenalties()
                if (response.isSuccessful && response.body() != null) {
                    val result = response.body()!!
                    Toast.makeText(context, "${result.msg}. Applied: ₹${result.totalPenaltyAmount}", Toast.LENGTH_LONG).show()
                    loadStats() // Refresh
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

    class DefaulterAdapter : androidx.recyclerview.widget.ListAdapter<Defaulter, DefaulterAdapter.ViewHolder>(DiffCallback()) {
        class ViewHolder(val binding: ItemLedgerEntryBinding) : RecyclerView.ViewHolder(binding.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
             val binding = ItemLedgerEntryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
             return ViewHolder(binding)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = getItem(position)
            holder.binding.tvDate.text = "${item.days} days late"
            holder.binding.tvDescription.text = item.name
            holder.binding.tvAmount.text = "₹${item.amount}"
            holder.binding.tvAmount.setTextColor(android.graphics.Color.RED)
            holder.binding.tvBalance.visibility = View.GONE
        }
        
        class DiffCallback : androidx.recyclerview.widget.DiffUtil.ItemCallback<Defaulter>() {
            override fun areItemsTheSame(oldItem: Defaulter, newItem: Defaulter) = oldItem.name == newItem.name
            override fun areContentsTheSame(oldItem: Defaulter, newItem: Defaulter) = oldItem == newItem
        }
    }
}
