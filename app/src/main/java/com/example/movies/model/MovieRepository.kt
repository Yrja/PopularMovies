package com.example.movies.model

import com.example.movies.model.entity.Genre
import com.example.movies.model.entity.Movie
import com.example.movies.model.entity.Result

interface MovieRepository {
    suspend fun getGenres(): Result<List<Genre>?>
    suspend fun getMovies(): Result<List<Movie>?>
}