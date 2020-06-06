package com.example.movies.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movies.MovieApplication
import com.example.movies.R
import com.example.movies.data.ApiCreator
import com.example.movies.data.EntityMapper
import com.example.movies.data.MovieRepositoryImpl
import com.example.movies.model.MoviesInteractorImpl
import com.example.movies.viewmodel.MovieViewModel
import com.example.movies.viewmodel.MovieViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MovieViewModel

    private lateinit var genresAdapter: GenreAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        genresAdapter = GenreAdapter()
        genresList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = genresAdapter
        }
        val viewModelFactory = MovieViewModelFactory(
            MoviesInteractorImpl(
                MovieRepositoryImpl(
                    (application as MovieApplication).getDB(),
                    ApiCreator.api,
                    EntityMapper()
                )
            )
        )
        viewModel = ViewModelProvider(this, viewModelFactory).get(MovieViewModel::class.java)
        viewModel.getMovieLiveData().observe(this, Observer {
            genresAdapter.genres = it.toMutableList()
        })
        viewModel.getErrorLiveData().observe(this, Observer {
            displayError(it)
        })
        viewModel.displayMovies()
    }

    private fun displayError(error: Throwable) {
        if (error is SocketTimeoutException || error is UnknownHostException || error is ConnectException) {
            Toast.makeText(this, R.string.no_network_message, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, R.string.error_message, Toast.LENGTH_SHORT).show()
        }
    }
}