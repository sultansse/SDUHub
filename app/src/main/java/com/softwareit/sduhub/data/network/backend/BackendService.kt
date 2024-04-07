package com.softwareit.sduhub.data.network.backend

import com.softwareit.sduhub.data.network.model.ApiFaq
import com.softwareit.sduhub.data.network.model.ApiInternship
import com.softwareit.sduhub.data.network.model.ApiNews
import com.softwareit.sduhub.data.network.model.ApiStudent
import retrofit2.http.GET
import retrofit2.http.Path

interface BackendService {

    @GET("student") // TODO change to real endpoint
    suspend fun getStudent(): ApiStudent

    @GET("v1/news")
    suspend fun getNews(): List<ApiNews>

    @GET("v1/internships")
    suspend fun getInternships(): List<ApiInternship>

    @GET("v1/internships/{id}")
    suspend fun getInternshipById(@Path("id") id: Int): ApiInternship

    @GET("v1/faq")
    suspend fun getFaqItems(): List<ApiFaq>

}