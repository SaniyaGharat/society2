package com.society.app.ui.directory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.society.app.data.api.SocietyApi
import com.society.app.databinding.FragmentResidentProfileBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ResidentProfileFragment : Fragment() {

    private var _binding: FragmentResidentProfileBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var api: SocietyApi

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResidentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // TODO: Pass resident ID via arguments. Using "1" for demo.
        loadProfile("1")
    }

    private fun loadProfile(userId: String) {
        lifecycleScope.launch {
            try {
                val response = api.getResidentProfile(userId)
                if (response.isSuccessful && response.body() != null) {
                    val profile = response.body()!!
                    
                    binding.tvName.text = profile.user.name
                    binding.tvApartment.text = "Flat: ${profile.user.apartmentNumber}"
                    binding.tvContact.text = "${profile.user.email} | ${profile.user.phone}"
                    
                    binding.tvTotalPaid.text = "₹${profile.stats.totalPaid}"
                    binding.tvOutstanding.text = "₹${profile.stats.outstanding}"
                    
                    if (profile.lastPayment != null) {
                        binding.tvLastPayment.text = "₹${profile.lastPayment.amount} via ${profile.lastPayment.paymentMode} on ${profile.lastPayment.paymentDate.take(10)}"
                    } else {
                        binding.tvLastPayment.text = "No recorded payments found."
                    }
                } else {
                    Toast.makeText(context, "Failed to load profile", Toast.LENGTH_SHORT).show()
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
