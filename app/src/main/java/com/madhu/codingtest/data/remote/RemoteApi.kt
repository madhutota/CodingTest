package com.madhu.codingtest.data.remote

import com.madhu.codingtest.data.remote.model.RemoteCharacterPage
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteApi {
    @GET("character")
    suspend fun fetchCharacters(
        @Query("page") page: Int = 1
    ):RemoteCharacterPage
}