package com.society.app.data.repository

import com.society.app.data.api.SocietyApi
import com.society.app.data.model.Complaint
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ComplaintRepository @Inject constructor(
    private val api: SocietyApi
) {

    suspend fun getComplaints(): Result<List<Complaint>> {
        return try {
            val response = api.getComplaints()
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Failed to fetch complaints"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun addComplaint(complaint: Complaint): Result<Complaint> {
        return try {
            val response = api.createComplaint(complaint)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Failed to create complaint"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
