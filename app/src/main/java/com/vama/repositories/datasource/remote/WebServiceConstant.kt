package com.vama.repositories.datasource.remote

object WebServiceConstant {
    private const val RESULT_LIMIT = 100
    const val API_FETCH_ALBUMS_LIST = "/us/music/most-played/$RESULT_LIMIT/albums.json"
}