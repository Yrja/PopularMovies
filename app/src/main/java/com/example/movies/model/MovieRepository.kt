package com.example.movies.model

import com.example.movies.model.entity.Genres
import com.example.movies.model.entity.Movies

interface MovieRepository {

    suspend fun getGenres():Genres

    suspend fun getMovies():Movies
}