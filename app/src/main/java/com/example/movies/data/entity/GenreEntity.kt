package com.example.movies.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "genre_entity")
data class GenreEntity(
    @PrimaryKey
    val id: Int,
    val name: String
)