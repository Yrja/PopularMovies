package com.example.movies.model.entity

data class Movies(
    val page: Int,
    val movies: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)