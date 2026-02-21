package com.society.app.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.society.app.databinding.FragmentTreasurerDashboardBinding
import com.society.app.data.api.SocietyApi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class TreasurerDashboardFragment : Fragment() {

    private var _binding: FragmentTreasurerDashboardBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var api: SocietyApi

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTreasurerDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
    }

    private fun loadData() {
        lifecycleScope.launch {
            try {
                val response = api.getFinancialStats()
                if (response.isSuccessful && response.body() != null) {
                    val stats = response.body()!!
                    binding.tvTotalCollections.text = "₹${stats.totalCollections}"
                    binding.tvOutstandingDues.text = "₹${stats.outstandingDues}"
                } else {
                    Toast.makeText(context, "Failed to load stats", Toast.LENGTH_SHORT).show()
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
