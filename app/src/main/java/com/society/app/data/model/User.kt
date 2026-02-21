package com.society.app.data.model

data class User(
    val id: String,
    val name: String,
    val email: String,
    val role: String // "ADMIN", "RESIDENT"
)
