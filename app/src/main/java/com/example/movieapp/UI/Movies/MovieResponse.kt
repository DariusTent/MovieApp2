package com.example.movieapp.UI.Movies

import com.google.gson.annotations.SerializedName

class MovieResponse(
    @SerializedName("id")
    var id: Int,
    @SerializedName("title")
    var title: String,
    @SerializedName("overview")
    var description: String?,
    @SerializedName("poster_path")
    var image: String?,
    @SerializedName("release_date")
    var date: String?
)