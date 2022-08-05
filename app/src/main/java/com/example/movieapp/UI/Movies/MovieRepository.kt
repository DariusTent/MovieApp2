package com.example.movieapp.UI.Movies

import com.example.movieapp.database.Database
import com.example.movieapp.network.APIClient

class MovieRepository private constructor() {
    companion object{
        val instance = MovieRepository()
    }

    private val movieRemoteDataSource = MovieRemoteDataSource(APIClient.instance.retrofit)
    private val movieLocalDataSource = MovieLocalDataSource(Database.instance)

    fun getAllRemoteMovies() = movieRemoteDataSource.getMovies()

    fun getSearchedMovies(query: String) = movieRemoteDataSource.getSearchedMovies(query)

    fun getAllLocalMovies() = movieLocalDataSource.getAll()
    fun saveLocal(movie: Movie) = movieLocalDataSource.save(movie)
    fun saveAllLocal(movies: List<Movie>) = movieLocalDataSource.saveAll(movies)
    fun deleteLocal(movie: Movie) = movieLocalDataSource.delete(movie)
    fun deleteAllLocal() = movieLocalDataSource.deleteAll()
    fun deleteAllLocal(movies: List<Movie>) = movieLocalDataSource.deleteAll(movies)
    fun replaceAllLocal(movies: List<Movie>) = movieLocalDataSource.replaceAll(movies)
    fun replaceMovieLocal(movie : Movie) = movieLocalDataSource.replaceMovie(movie)
    fun getCount() = movieLocalDataSource.getCount()
    fun getPreference(withCast:String,withGenre:String)=movieRemoteDataSource.getPreference(withCast, withGenre)
    fun getPreference() = movieRemoteDataSource.getMovies()
    fun getFavourite() = movieLocalDataSource.getFavourite()
    fun getWatched() = movieLocalDataSource.getWatched()

    fun getMovieById(id: Int) = movieLocalDataSource.getMovieById(id)
}