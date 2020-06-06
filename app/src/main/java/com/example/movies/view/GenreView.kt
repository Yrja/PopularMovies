package com.example.movies.view

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movies.R
import com.example.movies.model.entity.PopularMovies
import kotlinx.android.synthetic.main.custom_view_layout.view.*

class GenreView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var movieAdapter = MovieAdapter()

    init {
        inflate(context, R.layout.custom_view_layout, this)
        moviesListRV.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = movieAdapter
        }
    }

    fun setMoviesByGenre(popularMovies: PopularMovies) {
        genreTitleTV.text = popularMovies.genre.name
        movieAdapter.movies = popularMovies.movies.toMutableList()
    }
}