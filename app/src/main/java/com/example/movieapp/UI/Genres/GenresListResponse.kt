package com.example.movieapp.UI.Genres

import com.google.gson.annotations.SerializedName

class GenresListResponse (
    @SerializedName("genres")
    val genres: List<GenreResponse>
)