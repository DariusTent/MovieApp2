package com.example.movieapp.UI.Movies

import com.example.movieapp.ui.Movies.MoviesListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesAPIService {
    @GET("search/movie")
    fun getMovies(@Query("api_key") apiKey : String,
                  @Query("language") language : String,

    ): Call<MoviesListResponse>
}