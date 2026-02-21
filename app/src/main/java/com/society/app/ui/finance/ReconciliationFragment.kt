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
import com.society.app.data.model.BankStatement
import com.society.app.data.model.BankTransaction
import com.society.app.databinding.FragmentReconciliationBinding
import com.society.app.databinding.ItemLedgerEntryBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ReconciliationFragment : Fragment() {

    private var _binding: FragmentReconciliationBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var api: SocietyApi
    private lateinit var adapter: UnmatchedAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReconciliationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        adapter = UnmatchedAdapter()
        binding.rvUnmatched.layoutManager = LinearLayoutManager(context)
        binding.rvUnmatched.adapter = adapter

        binding.btnUploadStatement.setOnClickListener {
            simulateUpload()
        }
    }

    private fun simulateUpload() {
        // Create dummy statement
        val transactions = listOf(
            BankTransaction("2024-04-10", 5000.0, "NEFT Transfer", "TXN12345"), // Should match if payment exists
            BankTransaction("2024-04-12", 100.0, "Interest Credit", "INT999"),   // Unmatched
            BankTransaction("2024-04-15", 5000.0, "Cash Deposit", "DEP111")      // Maybe Match
        )
        val statement = BankStatement(transactions)

        lifecycleScope.launch {
            try {
                val response = api.processReconciliation(statement)
                if (response.isSuccessful && response.body() != null) {
                    val result = response.body()!!
                    
                    binding.layoutStats.visibility = View.VISIBLE
                    binding.tvTotalProcessed.text = "Processed: ${result.stats.total}"
                    binding.tvMatched.text = "Matched: ${result.stats.matched}"
                    binding.tvUnmatched.text = "Unmatched: ${result.stats.unmatched}"
                    
                    adapter.submitList(result.unmatched)
                    Toast.makeText(context, "Reconciliation Complete", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Reconciliation Failed", Toast.LENGTH_SHORT).show()
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

    class UnmatchedAdapter : androidx.recyclerview.widget.ListAdapter<BankTransaction, UnmatchedAdapter.ViewHolder>(DiffCallback()) {
        class ViewHolder(val binding: ItemLedgerEntryBinding) : RecyclerView.ViewHolder(binding.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
             val binding = ItemLedgerEntryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
             return ViewHolder(binding)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = getItem(position)
            holder.binding.tvDate.text = item.date
            holder.binding.tvDescription.text = "${item.description} (${item.refNumber})"
            holder.binding.tvAmount.text = "₹${item.amount}"
            holder.binding.tvBalance.visibility = View.GONE
        }
        
        class DiffCallback : androidx.recyclerview.widget.DiffUtil.ItemCallback<BankTransaction>() {
            override fun areItemsTheSame(oldItem: BankTransaction, newItem: BankTransaction) = oldItem.refNumber == newItem.refNumber
            override fun areContentsTheSame(oldItem: BankTransaction, newItem: BankTransaction) = oldItem == newItem
        }
    }
}
