package com.society.app.ui.amenities

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.society.app.data.model.Amenity
import com.society.app.data.repository.AmenityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AmenitiesViewModel @Inject constructor(
    private val repository: AmenityRepository
) : ViewModel() {

    private val _amenities = MutableLiveData<List<Amenity>>()
    val amenities: LiveData<List<Amenity>> = _amenities

    private val _bookingStatus = MutableLiveData<Result<Unit>?>()
    val bookingStatus: LiveData<Result<Unit>?> = _bookingStatus

    init {
        loadAmenities()
    }

    fun loadAmenities() {
        viewModelScope.launch {
            val result = repository.getAmenities()
            result.onSuccess {
                _amenities.value = it
            }
        }
    }

    fun bookAmenity(amenityId: Int, residentId: Int, date: String, timeSlot: String) {
        viewModelScope.launch {
            val result = repository.bookAmenity(amenityId, residentId, date, timeSlot)
            _bookingStatus.value = result
        }
    }

    fun clearBookingStatus() {
        _bookingStatus.value = null
    }
}
