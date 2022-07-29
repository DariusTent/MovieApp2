package com.example.movieapp.UI.Movies

import com.google.gson.annotations.SerializedName

class MovieResponse(
    @SerializedName("id")
    var id: Int,
    @SerializedName("title")
    var title: String
)