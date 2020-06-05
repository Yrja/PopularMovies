package com.example.movies.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.R
import com.example.movies.model.entity.MovieEntry
import kotlinx.android.synthetic.main.genre_item.view.*

class GenreListAdapter(private val movieEntries: List<MovieEntry>) :
    RecyclerView.Adapter<GenreListAdapter.GenreListViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.genre_item, parent, false)
        return GenreListViewHolder(view)
    }

    override fun getItemCount() = movieEntries.size

    override fun onBindViewHolder(holder: GenreListViewHolder, position: Int) {
       holder.bind(movieEntries[position])
    }

    class GenreListViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        fun bind(entry:MovieEntry) {
           itemView.movieListItem.setMoviesByGenre(entry)
        }
    }
}