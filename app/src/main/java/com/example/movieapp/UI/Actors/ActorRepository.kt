package com.example.movieapp.UI.Actors

import com.example.movieapp.database.Database
import com.example.movieapp.network.APIClient

class ActorRepository private constructor() {
    companion object{
        val instance = ActorRepository()
    }

    private val actorRemoteDataSource = ActorRemoteDataSource(APIClient.instance.retrofit)
    private val actorLocalDataSource = ActorLocalDataSource(Database.instance)

    fun getAllRemoteActors() = actorRemoteDataSource.getActors()

    fun getAllLocalActors() = actorLocalDataSource.getAll()
    fun saveLocal(actor: Actor) = actorLocalDataSource.save(actor)
    fun saveAllLocal(actors: List<Actor>) = actorLocalDataSource.saveAll(actors)
    fun deleteLocal(actor: Actor) = actorLocalDataSource.delete(actor)
    fun deleteAllLocal() = actorLocalDataSource.deleteAll()
    fun deleteAllLocal(actors: List<Actor>) = actorLocalDataSource.deleteAll(actors)
    fun replaceAllLocal(actors: List<Actor>) = actorLocalDataSource.replaceAll(actors)
    suspend fun getCount() = actorLocalDataSource.getCount()
    fun getAllLocalIds() = actorLocalDataSource.getAllIds()


}