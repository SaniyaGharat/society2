package com.society.app.data.repository

import com.society.app.data.api.SocietyApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VisitorRepository @Inject constructor(
    private val api: SocietyApi
) {

    suspend fun getVisitors(): Result<List<Visitor>> {
        return try {
            val response = api.getVisitors()
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Failed to load visitors"))
            }
        } catch (e: Exception) {
             Result.failure(e)
        }
    }

    suspend fun preApproveVisitor(visitor: Visitor): Result<Visitor> {
        return try {
            val response = api.preApproveVisitor(visitor)
             if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Failed to add visitor"))
            }
        } catch (e: Exception) {
             Result.failure(e)
        }
    }
}
