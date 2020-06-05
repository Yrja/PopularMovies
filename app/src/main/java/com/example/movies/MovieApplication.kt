package com.example.movies

import android.app.Application
import androidx.room.Room
import com.example.movies.model.MovieDatabase

class MovieApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        val db = Room.databaseBuilder(
            applicationContext,
            MovieDatabase::class.java, Utils.DB_NAME
        ).build()
    }
}