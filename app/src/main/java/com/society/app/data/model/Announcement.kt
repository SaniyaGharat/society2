package com.society.app.data.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class Announcement(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("description") val content: String,
    @SerializedName("date") val datePosted: String, // Date as String
    @SerializedName("type") val type: String // "GENERAL", "EVENT", "MAINTENANCE"
)
