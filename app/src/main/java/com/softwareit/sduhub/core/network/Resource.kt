package com.softwareit.sduhub.core.network

sealed class Resource<out T> {
    data object Initial : Resource<Nothing>()
    data object Loading : Resource<Nothing>()
    data class Failure(val cause: Exception) : Resource<Nothing>()
    data class Success<T>(val data: T) : Resource<T>()
}

fun <T> Result<T>.toResource(): Resource<T> {
    return fold({ Resource.Success(it) }, { Resource.Failure(Exception(it)) })
}