package com.example.movies.model.entity.response

import com.example.movies.model.entity.MovieEntry

data class MovieEntryResponse(
    val filteredMovies: List<MovieEntry> = emptyList(),
    val error: Throwable? = null
)