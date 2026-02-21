package com.society.app.data.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class Visitor(
    @SerializedName("id") val id: Int? = null, // Nullable for new visitors
    @SerializedName("name") val name: String,
    @SerializedName("type") val type: String,
    @SerializedName("status") val status: String? = "EXPECTED",
    @SerializedName("expected_date") val visitDate: String, // Date as String
    @SerializedName("resident_id") val residentId: Int
)
