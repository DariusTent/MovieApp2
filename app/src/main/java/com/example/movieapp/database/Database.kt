package com.example.movieapp.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.movieapp.UI.Actors.Actor
import com.example.movieapp.UI.Actors.ActorDAO
import com.example.movieapp.UI.Genres.Genre
import com.example.movieapp.UI.Genres.GenreDAO
import com.example.movieapp.UI.Movies.MovieDAO
import com.example.movieapp.UI.Movies.Movie

class Database private constructor(){
    companion object{
        val instance = Database()
    }

    @androidx.room.Database(
        entities = [Genre::class, Actor::class, Movie::class],
        version = 1
    )

    abstract class MovieAppDatabase: RoomDatabase(){
        abstract fun genresDao(): GenreDAO
        abstract fun actorsDao(): ActorDAO
        abstract fun moviesDao(): MovieDAO
    }

    lateinit var movieAppDatabase: MovieAppDatabase
        private set

    fun initialise(context: Context){
        this.movieAppDatabase = Room.databaseBuilder(
            context,
            MovieAppDatabase::class.java,
            "movie_app.db"
        ).fallbackToDestructiveMigration().build()
    }
}