package com.example.movieapp.UI.Actors

import com.example.movieapp.database.Database

class ActorLocalDataSource(database: Database) {
    private val actorDAO: ActorDAO = database.movieAppDatabase.actorsDao()

    fun getAll() = actorDAO.getAll()
    fun save(actor: Actor) = actorDAO.save(actor)
    fun saveAll(actors: List<Actor>) = actorDAO.saveAll(actors)
    fun delete(actor: Actor) = actorDAO.delete(actor)
    fun deleteAll() = actorDAO.deleteAll()
    fun deleteAll(actors: List<Actor>) = actorDAO.deleteAll(actors)
    fun replaceAll(actors: List<Actor>) = actorDAO.replaceAll(actors)
    fun getCount() = actorDAO.getCount()
    fun getAllIds()= actorDAO.getAllIds()
}
