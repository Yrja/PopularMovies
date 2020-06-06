package com.example.movies.model

import com.example.movies.model.entity.response.MovieEntryResponse

interface MovieInteractor {
    suspend fun getMoviesByGenres(): MovieEntryResponse
}