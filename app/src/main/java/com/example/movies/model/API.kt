package com.example.movies.model

import com.example.movies.model.entity.Genres
import com.example.movies.model.entity.Movies
import retrofit2.http.GET

interface API {
    @GET("/genre/movie/list")
    suspend fun getGenres(): Genres

    @GET("/movie/popular")
    suspend fun getMovies():Movies
}