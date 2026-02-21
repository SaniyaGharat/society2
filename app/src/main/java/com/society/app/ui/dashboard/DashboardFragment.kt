package com.society.app.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.society.app.R
import com.society.app.databinding.FragmentDashboardBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DashboardViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.complaintCount.observe(viewLifecycleOwner) { count ->
            binding.tvOpenComplaints.text = "Open: $count"
        }

        viewModel.recentNoticesCount.observe(viewLifecycleOwner) { count ->
            binding.tvRecentNotices.text = "Recent: $count"
        }

        binding.cardComplaints.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_complaintsListFragment)
        }
        
        binding.cardNotices.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_announcementsListFragment)
        }

        binding.cardDirectory.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_residentListFragment)
        }

        binding.cardVisitors.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_visitorListFragment)
        }

        binding.chipTreasurerDashboard.setOnClickListener {
            findNavController().navigate(R.id.treasurerDashboardFragment)
        }

        binding.chipReports.setOnClickListener {
            findNavController().navigate(R.id.reportsFragment)
        }

        binding.chipSearch.setOnClickListener {
            findNavController().navigate(R.id.searchFragment)
        }

        binding.chipIntegration.setOnClickListener {
            findNavController().navigate(R.id.integrationFragment)
        }

        binding.chipOptimization.setOnClickListener {
            findNavController().navigate(R.id.optimizationFragment)
        }

        binding.chipAccess.setOnClickListener {
             findNavController().navigate(R.id.roleDemoFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
