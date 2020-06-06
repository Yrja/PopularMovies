package com.example.movies.model.entity.response

import com.example.movies.model.entity.Genre

data class GenresResponse(var genre: List<Genre>?, var error: Throwable? = null)