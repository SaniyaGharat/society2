package com.society.app.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.society.app.data.api.SocietyApi
import com.society.app.databinding.FragmentRoleDemoBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class RoleDemoFragment : Fragment() {

    private var _binding: FragmentRoleDemoBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var api: SocietyApi

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRoleDemoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnVerifyAccess.setOnClickListener {
            val role = when (binding.rgRoles.checkedRadioButtonId) {
                binding.rbTreasurer.id -> "TREASURER"
                binding.rbAdmin.id -> "ADMIN"
                else -> "RESIDENT"
            }
            checkAccess(role)
        }
    }

    private fun checkAccess(role: String) {
        lifecycleScope.launch {
            try {
                // Pass role in header as simulated by backend middleware logic
                val response = api.checkTreasurerAccess(role)
                if (response.isSuccessful) {
                    binding.tvAccessResult.text = "✓ Access GRANTED\n(${response.body()?.msg})"
                    binding.tvAccessResult.setTextColor(android.graphics.Color.GREEN)
                } else {
                    binding.tvAccessResult.text = "✗ Access DENIED\n(HTTP ${response.code()})"
                    binding.tvAccessResult.setTextColor(android.graphics.Color.RED)
                }
            } catch (e: Exception) {
                binding.tvAccessResult.text = "Error: ${e.message}"
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
