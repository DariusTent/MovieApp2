package com.example.movieapp.UI.Movies

class MovieMapper {
    fun map(movieResponse: MovieResponse): Movie {
        return Movie(
            id = movieResponse.id,
            title = movieResponse.title,
            description = movieResponse.description,
            image = movieResponse.image,
            date = movieResponse.date,
            isFavorite = false,
            isWatched = false
        )
    }
}