package com.example.movies.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movies.model.entity.db.GenreEntity
import com.example.movies.model.entity.db.MovieEntity

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