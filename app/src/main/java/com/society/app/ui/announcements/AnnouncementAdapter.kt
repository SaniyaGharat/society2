package com.society.app.ui.announcements

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.society.app.data.model.Announcement
import com.society.app.databinding.ItemAnnouncementBinding

class AnnouncementAdapter(private val onClick: (Announcement) -> Unit) : RecyclerView.Adapter<AnnouncementAdapter.AnnouncementViewHolder>() {

    private var announcements: List<Announcement> = emptyList()

    fun submitList(newList: List<Announcement>) {
        announcements = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnnouncementViewHolder {
        val binding = ItemAnnouncementBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AnnouncementViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: AnnouncementViewHolder, position: Int) {
        holder.bind(announcements[position])
    }

    override fun getItemCount() = announcements.size

    class AnnouncementViewHolder(
        private val binding: ItemAnnouncementBinding,
        val onClick: (Announcement) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(announcement: Announcement) {
            binding.tvAnnouncementTitle.text = announcement.title
            binding.tvType.text = announcement.type
            binding.tvDate.text = announcement.datePosted.toString()
            binding.tvContentPreview.text = announcement.content
            
            binding.root.setOnClickListener {
                onClick(announcement)
            }
        }
    }
}
