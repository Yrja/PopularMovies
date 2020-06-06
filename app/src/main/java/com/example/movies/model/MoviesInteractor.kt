package com.example.movies.model

import com.example.movies.model.entity.PopularMovies
import com.example.movies.model.entity.Result

interface MoviesInteractor {
    suspend fun getPopularMovies(): Result<List<PopularMovies>>
}