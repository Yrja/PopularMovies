package com.example.movies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.Utils
import com.example.movies.model.MovieInteractor
import com.example.movies.model.entity.MovieEntry
import com.example.movies.model.entity.response.MovieEntryResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class MovieViewModel(private val interactor: MovieInteractor) : ViewModel() {

    private val movieLiveData = MutableLiveData<List<MovieEntry>>()
    private val errorMessageLiveData = MutableLiveData<String>()

    fun getMovieLiveData(): LiveData<List<MovieEntry>> {
        return movieLiveData
    }

    fun getErrorLiveData(): LiveData<String> {
        return errorMessageLiveData
    }

    fun displayMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response: MovieEntryResponse = interactor.getMoviesByGenres()
                movieLiveData.postValue(response.filteredMovies)
                response.error?.let {
                    if (it is SocketTimeoutException || it is UnknownHostException || it is ConnectException) {
                        errorMessageLiveData.postValue(Utils.NETWORK_EXCEPTION)
                    } else {
                        errorMessageLiveData.postValue(it.localizedMessage)
                    }
                }
            } catch (error: Throwable) {
                error.printStackTrace()
                errorMessageLiveData.postValue(error.localizedMessage)

            }
        }
    }
}