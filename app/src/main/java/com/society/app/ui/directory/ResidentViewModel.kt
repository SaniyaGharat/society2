package com.society.app.ui.directory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.society.app.data.model.Resident
import com.society.app.data.repository.ResidentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

@HiltViewModel
class ResidentViewModel @Inject constructor(
    private val repository: ResidentRepository
) : ViewModel() {

    private val _residents = MutableLiveData<List<Resident>>()
    val residents: LiveData<List<Resident>> = _residents

    init {
        loadResidents()
    }

    fun loadResidents() {
        viewModelScope.launch {
            val result = repository.getResidents()
            result.onSuccess {
                _residents.value = it
            }
        }
    }

    fun search(query: String) {
        viewModelScope.launch {
            val result = repository.searchResidents(query)
            result.onSuccess {
                 _residents.value = it
            }
        }
    }
}
