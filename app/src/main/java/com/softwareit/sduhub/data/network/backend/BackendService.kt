package com.softwareit.sduhub.data.network.backend

import com.softwareit.sduhub.data.network.model.ApiFaq
import com.softwareit.sduhub.data.network.model.ApiInternship
import com.softwareit.sduhub.data.network.model.ApiNews
import com.softwareit.sduhub.data.network.model.ApiStudent
import com.softwareit.sduhub.data.network.model.ApiUserCredential
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface BackendService {

    @POST("v1/auth")
    suspend fun authStudent(@Body credentials: ApiUserCredential): ApiStudent

    @GET("v1/news")
    suspend fun getNews(): List<ApiNews>

    @GET("v1/internships")
    suspend fun getInternships(): List<ApiInternship>

    @GET("v1/internships/{id}")
    suspend fun getInternshipById(@Path("id") id: Int): ApiInternship

    @GET("v1/faq")
    suspend fun getFaqItems(): List<ApiFaq>

}