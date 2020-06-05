package com.example.movies.model

import com.example.movies.model.entity.Genres
import com.example.movies.model.entity.Movies
import retrofit2.http.GET

interface API {
    @GET("/3/genre/movie/list")
    suspend fun getGenres(): Genres

    @GET("/3/movie/popular")
    suspend fun getMovies():Movies
}