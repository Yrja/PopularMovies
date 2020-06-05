package com.example.movies.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movies.model.MovieInteractor

class MovieViewModelFactory (private val interactor: MovieInteractor) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MovieInteractor::class.java).newInstance(interactor)
    }
}