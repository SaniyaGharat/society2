package com.society.app.data.repository

import com.society.app.data.model.Resident
import javax.inject.Inject
import javax.inject.Singleton

import com.society.app.data.api.SocietyApi

@Singleton
class ResidentRepository @Inject constructor(
    private val api: SocietyApi
) {

    private var cachedResidents: List<Resident> = emptyList()

    suspend fun getResidents(): Result<List<Resident>> {
        return try {
            val response = api.getResidents()
            if (response.isSuccessful && response.body() != null) {
                cachedResidents = response.body()!!
                Result.success(cachedResidents)
            } else {
                Result.failure(Exception("Failed to load residents"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun searchResidents(query: String): Result<List<Resident>> {
        if (cachedResidents.isEmpty()) {
            getResidents()
        }
        val filtered = cachedResidents.filter { 
            it.name.contains(query, ignoreCase = true) || it.unitNumber.contains(query, ignoreCase = true)
        }
        return Result.success(filtered)
    }
}
