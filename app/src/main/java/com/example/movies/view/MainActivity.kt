package com.example.movies.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movies.R
import com.example.movies.model.MovieRepositoryImpl
import com.example.movies.model.MoviesInteractorImpl
import com.example.movies.viewmodel.MovieViewModel
import com.example.movies.viewmodel.MovieViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MovieViewModel
    private lateinit var viewModelFactory:MovieViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModelFactory = MovieViewModelFactory(MoviesInteractorImpl(MovieRepositoryImpl()))
        viewModel = ViewModelProvider(this, viewModelFactory).get(MovieViewModel::class.java)

        viewModel.displayMovies()
        viewModel.getLiveData().observe(this, Observer {
            genresList.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = GenreListAdapter(it)
            }
        })
    }
}