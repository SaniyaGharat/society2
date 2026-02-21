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
import com.society.app.data.model.DefaulterProfile
import com.society.app.data.model.ReminderRequest
import com.society.app.databinding.FragmentOptimizationBinding
import com.society.app.databinding.ItemLedgerEntryBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class OptimizationFragment : Fragment() {

    private var _binding: FragmentOptimizationBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var api: SocietyApi
    private lateinit var adapter: OptimizationAdapter
    private var currentDefaulters: List<DefaulterProfile> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOptimizationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        adapter = OptimizationAdapter()
        binding.rvDefaulters.layoutManager = LinearLayoutManager(context)
        binding.rvDefaulters.adapter = adapter

        loadDefaulters()

        binding.btnRemindAll.setOnClickListener {
            if (currentDefaulters.isNotEmpty()) {
                sendReminders()
            } else {
                Toast.makeText(context, "No defaulters to remind", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadDefaulters() {
        lifecycleScope.launch {
            try {
                val response = api.getTopDefaulters()
                if (response.isSuccessful && response.body() != null) {
                    currentDefaulters = response.body()!!
                    adapter.submitList(currentDefaulters)
                }
            } catch (e: Exception) {
                Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun sendReminders() {
        val ids = currentDefaulters.map { it.id }
        val request = ReminderRequest(ids)

        lifecycleScope.launch {
            try {
                val response = api.sendReminders(request)
                if (response.isSuccessful) {
                    Toast.makeText(context, response.body()?.msg ?: "Reminders sent", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(context, "Failed to send", Toast.LENGTH_SHORT).show()
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

    class OptimizationAdapter : androidx.recyclerview.widget.ListAdapter<DefaulterProfile, OptimizationAdapter.ViewHolder>(DiffCallback()) {
        class ViewHolder(val binding: ItemLedgerEntryBinding) : RecyclerView.ViewHolder(binding.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
             val binding = ItemLedgerEntryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
             return ViewHolder(binding)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = getItem(position)
            holder.binding.tvDate.text = "Flat ${item.apartmentNumber}"
            holder.binding.tvDescription.text = "${item.name}\n${item.phone}"
            holder.binding.tvAmount.text = "Due: ₹${item.balance}"
            holder.binding.tvAmount.setTextColor(android.graphics.Color.RED)
            holder.binding.tvBalance.text = "Tap to call (Simulated)"
        }
        
        class DiffCallback : androidx.recyclerview.widget.DiffUtil.ItemCallback<DefaulterProfile>() {
            override fun areItemsTheSame(oldItem: DefaulterProfile, newItem: DefaulterProfile) = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: DefaulterProfile, newItem: DefaulterProfile) = oldItem == newItem
        }
    }
}
