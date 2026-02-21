package com.society.app.data.model

import com.google.gson.annotations.SerializedName

data class Amenity(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("capacity") val capacity: Int,
    @SerializedName("is_available") val isAvailable: Boolean,
    @SerializedName("hourly_rate") val hourlyRate: Double
)
