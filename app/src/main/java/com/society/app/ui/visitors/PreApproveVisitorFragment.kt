package com.society.app.ui.visitors

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.society.app.R
import com.society.app.databinding.FragmentPreApproveVisitorBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PreApproveVisitorFragment : Fragment() {

    private var _binding: FragmentPreApproveVisitorBinding? = null
    private val binding get() = _binding!!
    private val viewModel: VisitorViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPreApproveVisitorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnApprove.setOnClickListener {
            val name = binding.etVisitorName.text.toString()
            val type = when (binding.rgType.checkedRadioButtonId) {
                R.id.rbDelivery -> "DELIVERY"
                R.id.rbService -> "SERVICE"
                else -> "GUEST"
            }

            if (name.isNotEmpty()) {
                viewModel.preApprove(name, type)
                Toast.makeText(context, "Visitor Pre-Approved", Toast.LENGTH_SHORT).show()
                findNavController().popBackStack()
            } else {
                Toast.makeText(context, "Enter visitor name", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
