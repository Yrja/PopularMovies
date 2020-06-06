package com.example.movies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.model.MoviesInteractor
import com.example.movies.model.entity.PopularMovies
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel(private val interactor: MoviesInteractor) : ViewModel() {

    private val movieLiveData = MutableLiveData<List<PopularMovies>>()

    private val errorMessageLiveData = MutableLiveData<Throwable>()

    fun getMovieLiveData(): LiveData<List<PopularMovies>> {
        return movieLiveData
    }

    fun getErrorLiveData(): LiveData<Throwable> {
        return errorMessageLiveData
    }

    fun displayMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val popularMovies = interactor.getPopularMovies()
                movieLiveData.postValue(popularMovies.result)
                popularMovies.error?.let {
                    errorMessageLiveData.postValue(it)
                }
            } catch (error: Throwable) {
                errorMessageLiveData.postValue(error)
            }
        }
    }
}