package com.society.app.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.society.app.data.repository.AnnouncementRepository
import com.society.app.data.repository.ComplaintRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val complaintRepository: ComplaintRepository,
    private val announcementRepository: AnnouncementRepository
) : ViewModel() {

    private val _complaintCount = MutableLiveData<Int>()
    val complaintCount: LiveData<Int> = _complaintCount

    private val _recentNoticesCount = MutableLiveData<Int>()
    val recentNoticesCount: LiveData<Int> = _recentNoticesCount

    init {
        loadDashboardData()
    }
    private fun loadDashboardData() {
        viewModelScope.launch {
            // Fetch complaints count
            val complaintsResult = complaintRepository.getComplaints()
            complaintsResult.onSuccess {
                _complaintCount.value = it.count { c -> c.status != "RESOLVED" }
            }

            // Fetch notices count
            val noticesResult = announcementRepository.getAnnouncements()
            noticesResult.onSuccess {
                _recentNoticesCount.value = it.size
            }
        }
    }
}
