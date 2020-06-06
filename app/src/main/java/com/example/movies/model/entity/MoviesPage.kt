package com.example.movies.model.entity

import com.squareup.moshi.Json

data class MoviesPage(
    val page: Int,
    val results: List<Movie>,
    @field:Json(name = "total_pages")
    val totalPages: Int,
    @field:Json(name = "total_results")
    val totalResults: Int
)