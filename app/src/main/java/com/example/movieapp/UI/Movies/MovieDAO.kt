package com.example.movieapp.UI.Movies

import androidx.room.*
@Dao
interface MovieDAO {

    @Query("SELECT * from movies")
    fun getAll(): List<Movie>

    @Insert
    fun save(movie: Movie)

    @Insert
    fun saveAll(movies: List<Movie>)

    @Delete
    fun delete(movie: Movie)

    @Delete
    fun deleteAll(movies: List<Movie>)

    @Query("DELETE FROM movies")
    fun deleteAll()

    @Transaction
    fun replaceAll(movies: List<Movie>){
        deleteAll()
        saveAll(movies)
    }

    @Query("SELECT COUNT(id) FROM movies")
    fun getCount() : Int
}