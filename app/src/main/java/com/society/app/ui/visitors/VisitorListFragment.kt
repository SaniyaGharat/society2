package com.society.app.ui.visitors

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.society.app.R
import com.society.app.databinding.FragmentVisitorListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VisitorListFragment : Fragment() {

    private var _binding: FragmentVisitorListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: VisitorViewModel by viewModels()
    private val adapter = VisitorAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVisitorListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvVisitors.layoutManager = LinearLayoutManager(context)
        binding.rvVisitors.adapter = adapter

        viewModel.visitors.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        binding.fabPreApprove.setOnClickListener {
            findNavController().navigate(R.id.action_visitorListFragment_to_preApproveVisitorFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
