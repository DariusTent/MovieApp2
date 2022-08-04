package com.example.movieapp.UI.Actors

import androidx.room.*

@Dao
interface ActorDAO {

    @Query("SELECT * from actors")
    fun getAll(): List<Actor>

    @Query("SELECT id from actors")
    fun getAllIds():List<Int>

    @Insert
    fun save(actor: Actor)

    @Insert
    fun saveAll(actors: List<Actor>)

    @Delete
    fun delete(actor: Actor)

    @Delete
    fun deleteAll(actors: List<Actor>)

    @Query("DELETE FROM actors")
    fun deleteAll()

    @Transaction
    fun replaceAll(actors: List<Actor>){
        deleteAll()
        saveAll(actors)
    }

    @Query("SELECT COUNT(id) FROM actors")
    fun getCount() : Int
}