package com.example.movies.model.entity.db

import androidx.room.*
import com.example.movies.data.IdsConverter

@Entity(tableName = "movie_entity")
data class MovieEntity(
    val adult: Boolean,
    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String,
    @TypeConverters(value = [IdsConverter::class])
    @ColumnInfo(name = "genre_ids")
    val genreIds: List<Int>,
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "original_language")
    val originalLanguage: String,
    @ColumnInfo(name = "original_title")
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    @ColumnInfo(name = "poster_path")
    val posterPath: String,
    @ColumnInfo(name = "release_date")
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    @ColumnInfo(name = "vote_average")
    val voteAverage: Double,
    @ColumnInfo(name = "vote_count")
    val voteCount: Int
)