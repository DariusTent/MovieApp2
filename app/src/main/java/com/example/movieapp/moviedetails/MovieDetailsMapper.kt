package com.example.movieapp.moviedetails

import com.example.movieapp.UI.Movies.Movie
import com.example.movieapp.UI.Movies.MovieResponse

class MovieDetailsMapper {
    fun map(movieResponse: MovieDetailsResponse): MovieDetails {
        return MovieDetails(
            id = movieResponse.id,
            title = movieResponse.title,
            backdropPath = movieResponse.backdropPath,
            overview = movieResponse.overview ,
            posterPath = movieResponse.posterPath,
            releaseDate = movieResponse.releaseDate,
            videos = movieResponse.videos
        )
    }
}