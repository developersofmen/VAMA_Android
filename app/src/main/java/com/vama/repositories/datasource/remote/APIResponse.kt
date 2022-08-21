package com.vama.repositories.datasource.remote

sealed class ApiResponseHandler<T>(
    data: T? = null,
    exception: String? = null
) {
    data class Success<T>(val data: T) : ApiResponseHandler<T>(data, null)

    data class Error<T>(
        val exception: String
    ) : ApiResponseHandler<T>(null, exception)

}