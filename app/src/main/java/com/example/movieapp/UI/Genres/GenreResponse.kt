package com.example.movieapp.UI.Genres

import com.google.gson.annotations.SerializedName

class GenreResponse(
        @SerializedName("id")
        var id: Int,
        @SerializedName("name")
        var name: String
)