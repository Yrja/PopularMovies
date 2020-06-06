package com.example.movies.model.entity

data class Result<T>(val result: T, val error: Throwable? = null)