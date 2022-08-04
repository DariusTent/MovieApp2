package com.example.movieapp.UI.Movies

import com.example.movieapp.moviedetails.MovieDetails
import com.example.movieapp.moviedetails.MovieDetailsMapper
import com.example.movieapp.network.executeAndDeliver
import com.example.movieapp.utils.Constants
import com.example.movieapp.utils.Constants.API_KEY
import com.example.movieapp.utils.Constants.LANGUAGE
import retrofit2.Retrofit

class MovieRemoteDataSource(retrofit: Retrofit) {
    private val apiService: MoviesAPIService = retrofit.create(MoviesAPIService::class.java)
    private val movieMapper = MovieMapper()
    private val movieDetailsMapper = MovieDetailsMapper()

    fun getMovies(): List<Movie> {
        return apiService.getMovies(API_KEY, LANGUAGE)
            .executeAndDeliver()
            .movies
            .map { movieMapper.map(it) }
    }

    fun getSearchedMovies(query:String): List<Movie>{
        return apiService.getSearchedMovies(Constants.API_KEY, Constants.LANGUAGE, query)
            .executeAndDeliver()
            .movies
            .map { movieMapper.map(it) }
    }

    fun getPreference(withCast:String, withGenres:String): List<Movie> {
        return apiService.moviePreference(Constants.API_KEY, Constants.LANGUAGE,withCast,withGenres)
            .executeAndDeliver()
            .movies
            .map { movieMapper.map(it) }
    }

    fun getMovieDetails(movieId : Int): MovieDetails{
        return apiService.getMovieDetails(movieId, Constants.API_KEY,Constants.LANGUAGE,Constants.APPEND_TO_RESPONSE)
            .executeAndDeliver()
            .let { movieDetailsMapper.map(it) }
    }
}