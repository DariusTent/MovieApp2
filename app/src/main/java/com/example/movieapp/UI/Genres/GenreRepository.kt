package com.example.movieapp.UI.Genres

import com.example.movieapp.database.Database
import com.example.movieapp.network.APIClient

class GenreRepository private constructor() {
    companion object{
        val instance = GenreRepository()
    }

    private val genreRemoteDataSource = GenreRemoteDataSource(APIClient.instance.retrofit)
    private val genreLocalDataSource = GenreLocalDataSource(Database.instance)

    fun getAllRemoteGenres() = genreRemoteDataSource.getGenres()
    fun getAllLocalIds() = genreLocalDataSource.getAllIds()
    fun getAllLocalGenres() = genreLocalDataSource.getAll()
    fun saveLocal(genre: Genre) = genreLocalDataSource.save(genre)
    fun saveAllLocal(genres: List<Genre>) = genreLocalDataSource.saveAll(genres)
    fun deleteLocal(genre: Genre) = genreLocalDataSource.delete(genre)
    fun deleteAllLocal() = genreLocalDataSource.deleteAll()
    fun deleteAllLocal(genres: List<Genre>) = genreLocalDataSource.deleteAll(genres)
    fun replaceAllLocal(genres: List<Genre>) = genreLocalDataSource.replaceAll(genres)
    suspend fun getCount() = genreLocalDataSource.getCount()
}