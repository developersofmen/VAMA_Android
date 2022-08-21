package com.vama.repositories.datasource.remote

import AlbumListResponse
import com.google.gson.Gson
import com.vama.BuildConfig
import java.net.HttpURLConnection
import java.net.URL

object NetworkService {
    private const val HTTP_METHOD = "GET"

    fun callAlbumsListAPI(): ApiResponseHandler<AlbumListResponse> {
        var result: ApiResponseHandler<AlbumListResponse>? = null
        val url = URL(BuildConfig.BASE_URL + WebServiceConstant.API_FETCH_ALBUMS_LIST)
        val httpURLConnection = url.openConnection() as HttpURLConnection
        httpURLConnection.setRequestProperty(
            "Accept",
            "application/json"
        )
        httpURLConnection.requestMethod = HTTP_METHOD
        httpURLConnection.doInput = true
        httpURLConnection.doOutput = false
        // Check if the connection is successful
        val responseCode = httpURLConnection.responseCode
        if (responseCode == HttpURLConnection.HTTP_OK) {
            val response = httpURLConnection.inputStream.bufferedReader()
                .use { it.readText() }
            val albumListResponse = Gson().fromJson(response, AlbumListResponse::class.java)
            result = ApiResponseHandler.Success(data = albumListResponse)
        } else {
            result = ApiResponseHandler.Error(responseCode.toString())
        }

        return result
    }
}