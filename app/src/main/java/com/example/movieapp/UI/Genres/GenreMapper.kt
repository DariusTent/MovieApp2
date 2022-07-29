package com.example.movieapp.UI.Genres

class GenreMapper {
    fun map(genreResponse: GenreResponse): Genre{
        return Genre(
            id = genreResponse.id,
            name = genreResponse.name,
            isSelected = false
        )
    }
}