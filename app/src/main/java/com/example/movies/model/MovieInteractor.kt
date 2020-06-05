package com.example.movies.model

import com.example.movies.model.entity.Genre
import com.example.movies.model.entity.Movie

interface MovieInteractor {
    suspend fun getMoviesByGenres(): HashMap<Genre, List<Movie>>
}