package com.society.app.ui.finance

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.society.app.databinding.FragmentPaymentHistoryBinding
import com.society.app.data.api.SocietyApi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class PaymentHistoryFragment : Fragment() {

    private var _binding: FragmentPaymentHistoryBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var api: SocietyApi
    private lateinit var adapter: LedgerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPaymentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = LedgerAdapter()
        binding.rvPaymentHistory.layoutManager = LinearLayoutManager(context)
        binding.rvPaymentHistory.adapter = adapter
        
        // TODO: Get resident ID from session/logged in user. Using hardcoded "1" for demo.
        loadLedger("1")
    }

    private fun loadLedger(residentId: String) {
        lifecycleScope.launch {
            try {
                val response = api.getLedger(residentId)
                if (response.isSuccessful && response.body() != null) {
                    adapter.submitList(response.body()!!)
                } else {
                    Toast.makeText(context, "Failed to load ledger", Toast.LENGTH_SHORT).show()
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
