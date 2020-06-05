package com.example.movies.model

import com.example.movies.model.entity.Genres
import com.example.movies.model.entity.Movies

class MovieRepositoryImpl() : MovieRepository {
    override suspend fun getGenres(): Genres {
        return APIFactory.api.getGenres()
    }

    override suspend fun getMovies(): Movies {
        return APIFactory.api.getMovies()
    }
}