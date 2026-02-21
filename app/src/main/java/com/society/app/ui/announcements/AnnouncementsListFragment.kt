package com.society.app.ui.announcements

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.society.app.R
import com.society.app.databinding.FragmentAnnouncementsListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnnouncementsListFragment : Fragment() {

    private var _binding: FragmentAnnouncementsListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AnnouncementsViewModel by viewModels()
    private lateinit var adapter: AnnouncementAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAnnouncementsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = AnnouncementAdapter { announcement ->
            // Navigate to detail
            val bundle = Bundle().apply {
                putString("id", announcement.id)
                putString("title", announcement.title)
                putString("content", announcement.content)
            }
            findNavController().navigate(R.id.action_announcementsListFragment_to_announcementDetailFragment, bundle)
        }

        binding.rvAnnouncements.layoutManager = LinearLayoutManager(context)
        binding.rvAnnouncements.adapter = adapter

        viewModel.announcements.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
