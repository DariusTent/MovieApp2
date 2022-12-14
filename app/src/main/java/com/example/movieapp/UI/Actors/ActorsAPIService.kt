package com.example.movieapp.UI.Actors

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ActorsAPIService {

    @GET("person/popular")
    fun getActors(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: String
    ): Call<ActorsListResponse>
}