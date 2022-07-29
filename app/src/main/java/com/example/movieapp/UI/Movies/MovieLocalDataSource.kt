package com.example.movieapp.UI.Movies

import com.example.movieapp.database.Database

class MovieLocalDataSource(database: Database) {
    private val movieDAO: MovieDAO = database.movieAppDatabase.moviesDao()

    fun getAll() = movieDAO.getAll()
    fun save(movie: Movie) = movieDAO.save(movie)
    fun saveAll(movies: List<Movie>) = movieDAO.saveAll(movies)
    fun delete(movie: Movie) = movieDAO.delete(movie)
    fun deleteAll() = movieDAO.deleteAll()
    fun deleteAll(movies: List<Movie>) = movieDAO.deleteAll(movies)
    fun replaceAll(movies: List<Movie>) = movieDAO.replaceAll(movies)
    fun getCount() = movieDAO.getCount()
}