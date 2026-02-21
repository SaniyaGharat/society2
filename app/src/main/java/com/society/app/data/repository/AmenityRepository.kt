package com.society.app.data.repository

import com.society.app.data.api.SocietyApi
import com.society.app.data.model.Amenity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AmenityRepository @Inject constructor(
    private val api: SocietyApi
) {
    suspend fun getAmenities(): Result<List<Amenity>> {
        return try {
            val response = api.getAmenities()
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Failed to fetch amenities"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun bookAmenity(amenityId: Int, residentId: Int, date: String, timeSlot: String): Result<Unit> {
        return try {
            val bookingRequest = mapOf(
                "amenity_id" to amenityId.toString(),
                "resident_id" to residentId.toString(),
                "date" to date,
                "time_slot" to timeSlot
            )
            val response = api.bookAmenity(bookingRequest)
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Failed to book amenity"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
