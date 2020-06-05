package com.example.movies.view

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movies.R
import com.example.movies.model.entity.Genre
import com.example.movies.model.entity.Movie
import kotlinx.android.synthetic.main.custom_view_layout.view.*

class CustomView:FrameLayout {

    init {
        inflate(context, R.layout.custom_view_layout, this)
    }

    private

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    fun setMoviesByGenre(entry: Map.Entry<Genre,List<Movie>>){
        genreTitleTV.text = entry.key.name
        moviesListRV.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = MovieAdapter(entry.value)
        }
    }
}