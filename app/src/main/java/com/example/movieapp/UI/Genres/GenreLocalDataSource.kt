package com.example.movieapp.UI.Genres

import com.example.movieapp.database.Database

class GenreLocalDataSource(database: Database) {
    private val genreDAO: GenreDAO = database.movieAppDatabase.genresDao()
    fun getAllIds() = genreDAO.getAllIds()
    fun getAll() = genreDAO.getAll()
    fun save(genre: Genre) = genreDAO.save(genre)
    fun saveAll(genres: List<Genre>) = genreDAO.saveAll(genres)
    fun delete(genre: Genre) = genreDAO.delete(genre)
    fun deleteAll() = genreDAO.deleteAll()
    fun deleteAll(genres: List<Genre>) = genreDAO.deleteAll(genres)
    fun replaceAll(genres: List<Genre>) = genreDAO.replaceAll(genres)
    fun getCount() = genreDAO.getCount()
}
