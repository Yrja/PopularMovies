package com.example.movies.view

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movies.R
import com.example.movies.model.entity.MovieEntry
import kotlinx.android.synthetic.main.custom_view_layout.view.*

class CustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        inflate(context, R.layout.custom_view_layout, this)
        moviesListRV.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = MovieAdapter(emptyList())
        }
    }

    fun setMoviesByGenre(entry: MovieEntry) {
        genreTitleTV.text = entry.genre.name
        moviesListRV.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = MovieAdapter(entry.movies)
        }
    }
}