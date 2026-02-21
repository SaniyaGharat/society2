package com.society.app.ui.directory

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.society.app.databinding.FragmentResidentListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResidentListFragment : Fragment() {

    private var _binding: FragmentResidentListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ResidentViewModel by viewModels()
    private val adapter = ResidentAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResidentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvResidents.layoutManager = LinearLayoutManager(context)
        binding.rvResidents.adapter = adapter

        viewModel.residents.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.search(s.toString())
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
