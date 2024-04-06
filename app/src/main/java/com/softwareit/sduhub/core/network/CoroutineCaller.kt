package com.softwareit.sduhub.core.network

import com.softwareit.sduhub.core.network.model.ApiError
import com.softwareit.sduhub.core.network.model.ApiException
import com.softwareit.sduhub.core.network.model.ConnectionException
import com.softwareit.sduhub.core.network.model.JSONException
import com.softwareit.sduhub.core.network.model.ServerException
import com.softwareit.sduhub.utils.common.data.network.fromJson
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.Moshi
import org.koin.java.KoinJavaComponent
import retrofit2.HttpException
import java.net.ConnectException
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.UnknownHostException


interface CoroutineCaller {

    suspend fun <T> apiCall(result: suspend () -> T): Result<T> =
        runCatching { result() }.fold(::handleSuccess, ::handleError)

    suspend fun <FROM, TO> mappedApiCall(
        mapper: BaseMapper<FROM, TO>,
        call: suspend () -> FROM
    ): Result<TO> = apiCall { mapper.map(call()) }


    fun <R> handleSuccess(result: R): Result<R> = Result.success(result)
    private fun <R> handleError(throwable: Throwable): Result<R> =
        when (throwable) {
            is ConnectException,
            is UnknownHostException,
            is SocketTimeoutException -> Result.failure(ConnectionException())

            is JsonDataException -> Result.failure(JSONException())

            is HttpException -> handleHttpException(throwable)

            else -> Result.failure(throwable)
        }


    private fun <T> handleHttpException(exception: HttpException): Result<T> =
        when (exception.code()) {
            HttpURLConnection.HTTP_NOT_FOUND,
            HttpURLConnection.HTTP_INTERNAL_ERROR -> Result.failure(ServerException())

            else -> exception.parseError()?.let { Result.failure(ApiException(it)) }
                ?: Result.failure(JSONException())
        }


    private fun HttpException.parseError(): ApiError? =
        try {
            response()?.errorBody()?.string()?.let { json ->
                val moshi: Moshi by KoinJavaComponent.inject(Moshi::class.java)
                moshi.fromJson(ApiError::class.java, json)
            }
        } catch (e: Exception) {
            null
        }
}
