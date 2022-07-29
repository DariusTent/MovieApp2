package com.example.movieapp.UI.Actors

import com.google.gson.annotations.SerializedName

class ActorsListResponse (
    @SerializedName("results")
    val actors: List<ActorResponse>
)