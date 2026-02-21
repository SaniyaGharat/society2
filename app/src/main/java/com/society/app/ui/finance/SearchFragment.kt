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
import com.society.app.data.model.PaymentHistoryItem
import com.society.app.databinding.FragmentSearchBinding
import com.society.app.databinding.ItemLedgerEntryBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var api: SocietyApi
    private lateinit var adapter: MonthlyHistoryFragment.HistoryAdapter // Reusing adapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        adapter = MonthlyHistoryFragment.HistoryAdapter()
        binding.rvResults.layoutManager = LinearLayoutManager(context)
        binding.rvResults.adapter = adapter

        binding.btnSearch.setOnClickListener {
            performSearch()
        }
    }

    private fun performSearch() {
        val startDate = binding.etStartDate.text.toString().takeIf { it.isNotEmpty() }
        val endDate = binding.etEndDate.text.toString().takeIf { it.isNotEmpty() }
        val minAmount = binding.etMinAmount.text.toString().toDoubleOrNull()
        val maxAmount = binding.etMaxAmount.text.toString().toDoubleOrNull()
        val mode = binding.etMode.text.toString().takeIf { it.isNotEmpty() }

        lifecycleScope.launch {
            try {
                val response = api.searchPayments(startDate, endDate, minAmount, maxAmount, mode)
                if (response.isSuccessful && response.body() != null) {
                    val results = response.body()!!
                    adapter.submitList(results)
                    if (results.isEmpty()) {
                        Toast.makeText(context, "No matches found", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(context, "Search failed", Toast.LENGTH_SHORT).show()
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
}
