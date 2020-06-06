package com.example.movies.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.movies.Utils.DB_VERSION
import com.example.movies.data.entity.GenreEntity
import com.example.movies.data.entity.MovieEntity

@Database(entities = [MovieEntity::class, GenreEntity::class], version = DB_VERSION)
@TypeConverters(IdsConverter::class)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}