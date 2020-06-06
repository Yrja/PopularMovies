package com.example.movies

import android.app.Application
import androidx.room.Room
import com.example.movies.data.MovieDatabase

class MovieApplication : Application() {

    private lateinit var database: MovieDatabase

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            applicationContext,
            MovieDatabase::class.java, Utils.DB_NAME
        ).build()
    }

    fun getDB(): MovieDatabase {
        return database
    }
}