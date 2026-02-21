package com.society.app.data.model

import com.google.gson.annotations.SerializedName

data class Complaint(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("category") val category: String,
    @SerializedName("status") val status: String,
    @SerializedName("resident_name") val residentName: String? = null,
    @SerializedName("apartment_number") val apartmentNumber: String? = null,
    @SerializedName("priority") val priority: String? = "MEDIUM",
    @SerializedName("date_created") val dateCreated: String? = null
)
