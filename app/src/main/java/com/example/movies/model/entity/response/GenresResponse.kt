package com.example.movies.model.entity.response

import com.example.movies.model.entity.Genre

class GenresResponse(var genre: List<Genre>?, var error: Throwable? = null)