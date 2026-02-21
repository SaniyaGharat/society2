package com.society.app.data.repository

import com.society.app.data.model.Announcement
import java.util.Date
import javax.inject.Inject
import javax.inject.Singleton

import com.society.app.data.api.SocietyApi

@Singleton
class AnnouncementRepository @Inject constructor(
    private val api: SocietyApi
) {

    suspend fun getAnnouncements(): Result<List<Announcement>> {
        return try {
            val response = api.getAnnouncements()
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Failed to load announcements"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
