package com.softwareit.sduhub.data.network.backend

import com.softwareit.sduhub.ui.screens.resources_screen.InternshipItemDTO
import com.softwareit.sduhub.ui.screens.resources_screen.ResourceDTO
import com.squareup.moshi.JsonClass
import retrofit2.http.GET
import retrofit2.http.Path

interface BackendService {

    @GET("v1/student")
    suspend fun getStudent(): Student

    @GET("v1/news")
    suspend fun getNews(): List<NewsItemDTO>

    /**
     * currently not used
    * */
    @GET("v1/news/{id}")
    suspend fun getNewsById(@Path("id") id: Int): NewsItemDTO

    @GET("v1/internships")
    suspend fun getInternships(): List<InternshipItemDTO>

    @GET("v1/internships/{id}")
    suspend fun getInternshipById(@Path("id") id: Int): InternshipItemDTO
}

// TODO use different data classes for different layers: ui-compose, domain, local, network
@JsonClass(generateAdapter = true)
data class Student(
    val fullname: String,
    val studentId: Int,
    val faculty: String,
)

@JsonClass(generateAdapter = true)
data class NewsItemDTO(
    val id: String,
    val imageUrl: String,
    val title: String,
    val announce: String,
    val date: String,
    val link: String,
) : ResourceDTO
