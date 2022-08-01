package com.example.movieapp.UI.Actors

import com.example.movieapp.UI.Movies.Movie
import com.example.movieapp.network.executeAndDeliver
import com.example.movieapp.utils.Constants
import com.example.movieapp.utils.Constants.API_KEY
import com.example.movieapp.utils.Constants.LANGUAGE
import com.example.movieapp.utils.Constants.PAGE
import retrofit2.Retrofit

class ActorRemoteDataSource(retrofit: Retrofit) {
    private val apiService: ActorsAPIService = retrofit.create(ActorsAPIService::class.java)
    private val actorMapper = ActorMapper()

    fun getActors(): List<Actor> {
        return apiService.getActors(API_KEY, LANGUAGE, PAGE)
            .executeAndDeliver()
            .actors
            .map { actorMapper.map(it) }
    }


}