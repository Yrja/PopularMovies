package com.example.movies.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.movies.model.entity.db.GenreEntity
import com.example.movies.model.entity.db.MovieEntity

@Dao
abstract class MovieDao {
    @Insert
    abstract suspend fun insertGenres(genres: List<GenreEntity>)

    @Insert()
    abstract suspend fun insertMovies(moviesEntity: List<MovieEntity>)

    @Query("select * from GenreEntity")
    abstract suspend fun getGenres(): List<GenreEntity>


    @Query("select * from MovieEntity")
    abstract suspend fun getMovies(): List<MovieEntity>

}