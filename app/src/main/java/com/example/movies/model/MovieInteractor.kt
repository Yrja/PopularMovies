package com.example.movies.model

import com.example.movies.model.entity.MovieEntry

interface MovieInteractor {
    suspend fun getMoviesByGenres(): List<MovieEntry>
}