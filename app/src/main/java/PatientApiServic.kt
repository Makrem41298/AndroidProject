// PatientApiService.kt
package com.gfg.recyclerview_kotlin

import retrofit2.http.GET

interface PatientApiService {
    @GET("patients")
    suspend fun getPatients(): List<Patient>
}