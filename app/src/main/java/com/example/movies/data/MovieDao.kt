package com.example.movies.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movies.data.entity.GenreEntity
import com.example.movies.data.entity.MovieEntity

@Dao
abstract class MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertGenres(genres: List<GenreEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertMovies(moviesEntity: List<MovieEntity>)

    @Query("select * from genre_entity")
    abstract suspend fun getGenres(): List<GenreEntity>

    @Query("select * from movie_entity")
    abstract suspend fun getMovies(): List<MovieEntity>
}