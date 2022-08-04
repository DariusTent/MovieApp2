package com.example.movieapp.UI.moviedetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.UI.Movies.Movie
import com.example.movieapp.UI.Movies.MovieRemoteDataSource
import com.example.movieapp.moviedetails.MovieDetails
import com.example.movieapp.network.APIClient

class MovieDetailsViewModel : ViewModel() {
    val currentMovieId = MutableLiveData<Int>()
    var movie: MovieDetails? = null

    private val movieRemoteDataSource = MovieRemoteDataSource(APIClient.instance.retrofit)

    fun getMovieDetails() : MovieDetails? {
        return currentMovieId.value?.let { movieRemoteDataSource.getMovieDetails(it) }
    }

}