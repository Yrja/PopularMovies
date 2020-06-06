package com.example.movies.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movies.MovieApplication
import com.example.movies.R
import com.example.movies.Utils
import com.example.movies.model.APIFactory
import com.example.movies.model.DataMapper
import com.example.movies.model.MovieRepositoryImpl
import com.example.movies.model.MoviesInteractorImpl
import com.example.movies.viewmodel.MovieViewModel
import com.example.movies.viewmodel.MovieViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MovieViewModel
    private lateinit var viewModelFactory: MovieViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModelFactory = MovieViewModelFactory(
            MoviesInteractorImpl(
                MovieRepositoryImpl(
                    (application as MovieApplication).getDB(),
                    APIFactory.api,
                    DataMapper()
                )
            )
        )
        viewModel = ViewModelProvider(this, viewModelFactory).get(MovieViewModel::class.java)

        viewModel.displayMovies()
        viewModel.getMovieLiveData().observe(this, Observer {
            genresList.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = GenreListAdapter(it)
            }
        })
        viewModel.getErrorLiveData().observe(this, Observer {
            displayError(it)
        })
    }

    private fun displayError(errorMessage: String?) {
        errorMessage?.let {
            if (it == Utils.NETWORK_EXCEPTION) {
                Toast.makeText(this, R.string.no_network_message, Toast.LENGTH_SHORT).show()
            } else Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        } ?: Toast.makeText(this, R.string.error_message, Toast.LENGTH_SHORT).show()

    }
}