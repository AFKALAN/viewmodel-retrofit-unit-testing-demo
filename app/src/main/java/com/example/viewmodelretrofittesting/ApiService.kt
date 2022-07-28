package com.example.viewmodelretrofittesting

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("search")
    suspend fun getSearch(@Query("term") term: String): Response<SearchArtistResponse>
}