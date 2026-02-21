package com.society.app.ui.complaints

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.society.app.data.api.SocietyApi
import com.society.app.data.model.Complaint
import com.society.app.data.model.ConvertExpenseRequest
import com.society.app.data.model.CreateComplaintRequest
import com.society.app.databinding.FragmentIntegrationBinding
import com.society.app.databinding.ItemLedgerEntryBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class IntegrationFragment : Fragment() {

    private var _binding: FragmentIntegrationBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var api: SocietyApi
    private lateinit var adapter: ComplaintAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentIntegrationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        adapter = ComplaintAdapter(::onComplaintClicked)
        binding.rvComplaints.layoutManager = LinearLayoutManager(context)
        binding.rvComplaints.adapter = adapter

        loadComplaints()

        binding.btnNewComplaint.setOnClickListener {
            createDemoComplaint()
        }
    }

    private fun loadComplaints() {
        lifecycleScope.launch {
            try {
                val response = api.getMaintenanceComplaints()
                if (response.isSuccessful && response.body() != null) {
                    adapter.submitList(response.body()!!)
                }
            } catch (e: Exception) {
                Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun createDemoComplaint() {
        // Simulating creating a water leak complaint for resident 1
        lifecycleScope.launch {
            try {
                val req = CreateComplaintRequest(1, "Water Leakage", "Pipe burst in kitchen", "PLUMBING")
                val response = api.createComplaint(req)
                if (response.isSuccessful) {
                    Toast.makeText(context, "Complaint Created", Toast.LENGTH_SHORT).show()
                    loadComplaints()
                }
            } catch (e: Exception) {
                Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun onComplaintClicked(complaint: Complaint) {
        if (complaint.status == "CONVERTED_TO_EXPENSE") {
             Toast.makeText(context, "Already converted to expense", Toast.LENGTH_SHORT).show()
             return
        }

        AlertDialog.Builder(requireContext())
            .setTitle("Resolve & Convert to Expense?")
            .setMessage("Do you want to close this complaint and record a maintenance expense?")
            .setPositiveButton("Convert") { _, _ ->
                convertComplaint(complaint)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun convertComplaint(complaint: Complaint) {
        lifecycleScope.launch {
            try {
                // Hardcoded cost for demo
                val req = ConvertExpenseRequest(complaint.id, 500.0, "Repair for: ${complaint.title}", complaint.category)
                val response = api.convertToExpense(req)
                if (response.isSuccessful) {
                    Toast.makeText(context, "Converted to Expense!", Toast.LENGTH_SHORT).show()
                    loadComplaints()
                } else {
                     Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
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

    class ComplaintAdapter(private val onClick: (Complaint) -> Unit) : androidx.recyclerview.widget.ListAdapter<Complaint, ComplaintAdapter.ViewHolder>(DiffCallback()) {
        class ViewHolder(val binding: ItemLedgerEntryBinding) : RecyclerView.ViewHolder(binding.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
             val binding = ItemLedgerEntryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
             return ViewHolder(binding)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = getItem(position)
            holder.binding.tvDate.text = "${item.category.uppercase()} - ${item.status}"
            holder.binding.tvDescription.text = "${item.title} (${item.residentName})"
            holder.binding.tvAmount.text = "Tap to Resolve"
            holder.binding.tvBalance.text = item.description

            if (item.status == "CONVERTED_TO_EXPENSE") {
                 holder.binding.tvDate.setTextColor(android.graphics.Color.GREEN)
                 holder.binding.tvAmount.visibility = View.GONE
            } else {
                 holder.binding.tvDate.setTextColor(android.graphics.Color.RED)
                 holder.binding.tvAmount.visibility = View.VISIBLE
            }

            holder.itemView.setOnClickListener { onClick(item) }
        }
        
        class DiffCallback : androidx.recyclerview.widget.DiffUtil.ItemCallback<Complaint>() {
            override fun areItemsTheSame(oldItem: Complaint, newItem: Complaint) = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Complaint, newItem: Complaint) = oldItem == newItem
        }
    }
}
