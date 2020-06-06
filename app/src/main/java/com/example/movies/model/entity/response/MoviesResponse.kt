package com.example.movies.model.entity.response

import com.example.movies.model.entity.Movie

data class MoviesResponse(var movies: List<Movie>?, var error: Throwable? = null)