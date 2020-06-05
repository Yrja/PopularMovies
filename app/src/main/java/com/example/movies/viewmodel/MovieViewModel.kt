package com.example.movies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.model.MovieInteractor
import com.example.movies.model.entity.Genre
import com.example.movies.model.entity.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel(private val interactor: MovieInteractor) : ViewModel() {

    private val movieLiveData = MutableLiveData<HashMap<Genre, List<Movie>>>()

    fun getLiveData(): LiveData<HashMap<Genre, List<Movie>>> {
        return movieLiveData
    }

    fun displayMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            movieLiveData.postValue(interactor.getMoviesByGenres())
        }

    }
}