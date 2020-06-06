package com.example.movies.data

import com.example.movies.model.entity.Genres
import com.example.movies.model.entity.MoviesPage
import retrofit2.http.GET

interface MovieApi {
    @GET("/3/genre/movie/list")
    suspend fun getGenres(): Genres

    @GET("/3/movie/popular")
    suspend fun getMovies(): MoviesPage
}