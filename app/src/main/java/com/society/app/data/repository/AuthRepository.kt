package com.society.app.data.repository

import com.society.app.data.model.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor() {

    // Mock login for Phase 1
    fun login(email: String, password: String): Result<User> {
        return if (email.isNotEmpty() && password.length >= 6) {
            Result.success(User("1", "Test User", email, "RESIDENT"))
        } else {
            Result.failure(Exception("Invalid credentials"))
        }
    }

    fun register(name: String, email: String, password: String): Result<User> {
        return Result.success(User("2", name, email, "RESIDENT"))
    }
}
