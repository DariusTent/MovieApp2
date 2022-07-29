package com.example.movieapp.ui.Movies

import com.example.movieapp.UI.Movies.MovieResponse
import com.google.gson.annotations.SerializedName

class MoviesListResponse (
    @SerializedName("results")
    val movies: List<MovieResponse>
)