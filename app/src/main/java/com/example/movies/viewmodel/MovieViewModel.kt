package com.example.movies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.model.MovieInteractor
import com.example.movies.model.entity.MovieEntry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieViewModel(private val interactor: MovieInteractor) : ViewModel() {

    private val movieLiveData = MutableLiveData<List<MovieEntry>>()

    fun getLiveData(): LiveData<List<MovieEntry>> {
        return movieLiveData
    }

    fun displayMovies() {
        viewModelScope.launch {
            try {
                val movies: List<MovieEntry> = interactor.getMoviesByGenres()
                movieLiveData.postValue(movies)
            } catch (error: Throwable){
                error.printStackTrace()
            }
        }
    }
}