package com.softwareit.sduhub.data.network.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiUserCredential(
    val username: String,
    val password: String,
)