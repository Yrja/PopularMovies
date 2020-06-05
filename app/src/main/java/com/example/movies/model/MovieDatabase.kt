package com.example.movies.model

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.movies.Utils
import com.example.movies.data.IdsConverter
import com.example.movies.model.entity.db.GenreEntity
import com.example.movies.model.entity.db.MovieEntity

@Database(entities = [MovieEntity::class, GenreEntity::class], version = Utils.DB_VERSION)
@TypeConverters(IdsConverter::class)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun moviesDao(): MovieDao
}