package com.society.app.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.society.app.data.model.User
import com.society.app.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _user = MutableLiveData<User?>()
    val user: LiveData<User?> = _user

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun login(email: String, pass: String) {
        val result = repository.login(email, pass)
        result.onSuccess {
            _user.value = it
        }.onFailure {
            _error.value = it.message
        }
    }

    fun register(name: String, email: String, pass: String) {
        val result = repository.register(name, email, pass)
        result.onSuccess {
             _user.value = it
        }
    }
}
