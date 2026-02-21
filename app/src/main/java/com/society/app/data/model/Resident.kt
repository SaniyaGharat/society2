package com.society.app.data.model

import com.google.gson.annotations.SerializedName

data class Resident(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("apartment_number") val unitNumber: String,
    @SerializedName("phone") val phoneNumber: String,
    @SerializedName("email") val email: String,
    @SerializedName("role") val role: String
)
