package com.example.myexampleapp.api

import com.example.myexampleapp.model.DuckImageDTO
import retrofit2.Response
import retrofit2.http.GET

interface MyApi {
    @GET("random")
    suspend fun getDuckImage(): Response<DuckImageDTO>
}