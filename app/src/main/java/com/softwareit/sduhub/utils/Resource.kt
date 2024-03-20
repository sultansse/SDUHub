package com.softwareit.sduhub.utils

import com.bumptech.glide.load.HttpException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

sealed class Resource<out T> {
    class Success<T>(val data: T) : Resource<T>()
    data class Error(
        val isNetworkError: Boolean?,
        val errorCode: Int?,
        val errorBody: String?
    ) : Resource<Nothing>()

    data object Loading : Resource<Nothing>()
}

//Coroutine caller
suspend fun <T : Any> safeApiCall(
    request: suspend () -> T
): Resource<T> {
    return withContext(Dispatchers.IO) {
        try {
//            _loadingLiveData.postValue(true)
            val response = request.invoke()
            Resource.Success(response)
        } catch (throwable: Throwable) {
            when (throwable) {
                is HttpException -> {
                    Resource.Error(false, throwable.statusCode, throwable.message)
                }

                else -> {
                    Resource.Error(true, null, throwable.message)
                }
            }
        } finally {
//            _loadingLiveData.postValue(false)
        }
    }
}