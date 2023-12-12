package com.example.myexampleapp.api

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object MyRetrofitClient {
    private const val BASE_URL = "https://random-d.uk/api/v2/"

    fun getClient(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
}