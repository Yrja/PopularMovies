package com.example.movies.model

import com.example.movies.model.entity.response.GenresResponse
import com.example.movies.model.entity.response.MoviesResponse

interface MovieRepository {

    suspend fun getGenres(): GenresResponse

    suspend fun getMovies(): MoviesResponse
}