package com.society.app.ui.visitors

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.society.app.data.model.Visitor
import com.society.app.data.repository.VisitorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Date
import java.util.UUID
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

@HiltViewModel
class VisitorViewModel @Inject constructor(
    private val repository: VisitorRepository
) : ViewModel() {

    private val _visitors = MutableLiveData<List<Visitor>>()
    val visitors: LiveData<List<Visitor>> = _visitors

    init {
        loadVisitors()
    }

    fun loadVisitors() {
        viewModelScope.launch {
            val result = repository.getVisitors()
            result.onSuccess {
                _visitors.value = it
            }
        }
    }

    fun preApprove(name: String, type: String) {
        viewModelScope.launch {
            val newVisitor = Visitor(
                id = 0, // Backend auto-generates
                name = name,
                type = type,
                status = "EXPECTED",
                visitDate = Date().toString(),
                residentId = 1 // Hardcoded to current user for demo
            )
            repository.preApproveVisitor(newVisitor)
            loadVisitors()
        }
    }
}
