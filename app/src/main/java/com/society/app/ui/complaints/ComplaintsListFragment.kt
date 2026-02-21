package com.society.app.ui.complaints

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.society.app.R
import com.society.app.databinding.FragmentComplaintsListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ComplaintsListFragment : Fragment() {

    private var _binding: FragmentComplaintsListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ComplaintViewModel by viewModels()
    private val adapter = ComplaintAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentComplaintsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvComplaints.layoutManager = LinearLayoutManager(context)
        binding.rvComplaints.adapter = adapter

        viewModel.complaints.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        binding.fabAddComplaint.setOnClickListener {
            findNavController().navigate(R.id.action_complaintsListFragment_to_addComplaintFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
