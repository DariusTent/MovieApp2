package com.example.movieapp.UI.Actors

import com.google.gson.annotations.SerializedName

class ActorResponse(
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String
)