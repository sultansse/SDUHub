package com.softwareit.sduhub.core.network.model

class ApiException(val error: ApiError) : Exception(error.message)

class JSONException : Exception()

class ConnectionException : Exception()

class ServerException : Exception()