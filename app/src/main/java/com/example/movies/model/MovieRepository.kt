package com.example.movies.model

import com.example.movies.model.entity.Genre
import com.example.movies.model.entity.Movie

interface MovieRepository {

    suspend fun getGenres(): List<Genre>

    suspend fun getMovies(): List<Movie>
}