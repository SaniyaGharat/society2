package com.society.app.ui.complaints

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.society.app.databinding.FragmentAddComplaintBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddComplaintFragment : Fragment() {

    private var _binding: FragmentAddComplaintBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ComplaintViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddComplaintBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSubmit.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val desc = binding.etDesc.text.toString()
            val category = binding.etCategory.text.toString()

            if (title.isNotEmpty() && desc.isNotEmpty()) {
                viewModel.addComplaint(title, desc, category, "MEDIUM")
                Toast.makeText(context, "Complaint Submitted", Toast.LENGTH_SHORT).show()
                findNavController().popBackStack()
            } else {
                Toast.makeText(context, "Please fill required fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
