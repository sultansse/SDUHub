package com.softwareit.sduhub.data.network.backend

import com.squareup.moshi.JsonClass
import retrofit2.http.GET

interface BackendService {

    @GET("v1/student")
    suspend fun getStudent(): Student

}

// TODO use different data classes for different layers: ui-compose, domain, local, network
@JsonClass(generateAdapter = true)
data class Student(
    val fullname: String,
    val studentId: Int,
    val faculty: String,
)