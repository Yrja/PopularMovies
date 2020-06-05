package com.example.movies.model.entity.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GenreEntity(
    @PrimaryKey
    val id: Int,
    val name: String
)