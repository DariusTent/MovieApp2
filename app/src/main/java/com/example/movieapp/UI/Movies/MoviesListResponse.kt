package com.example.movieapp.UI.Movies

import com.example.movieapp.UI.Movies.MovieResponse
import com.google.gson.annotations.SerializedName

class MoviesListResponse (
    @SerializedName("results")
    var movies: List<MovieResponse>
)