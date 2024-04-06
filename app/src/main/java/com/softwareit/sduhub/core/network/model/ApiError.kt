package com.softwareit.sduhub.core.network.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class ApiError(val message: String, val code: Int?, val errors: List<ApiErrors>?) {
    @JsonClass(generateAdapter = true)
    class ApiErrors(val field: String, val messages: List<ApiErrorMessage>) {
        @JsonClass(generateAdapter = true)
        class ApiErrorMessage(val text: String, val type: String)
    }
}