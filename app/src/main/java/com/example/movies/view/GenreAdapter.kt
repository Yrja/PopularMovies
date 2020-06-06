package com.example.movies.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.R
import com.example.movies.model.entity.PopularMovies
import kotlinx.android.synthetic.main.genre_item.view.*

class GenreAdapter : RecyclerView.Adapter<GenreAdapter.GenreViewHolder>() {

    var genres: MutableList<PopularMovies> = mutableListOf()
        set(value) {
            field.clear()
            field.addAll(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.genre_item, parent, false)
        return GenreViewHolder(view)
    }

    override fun getItemCount() = genres.size

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.bind(genres[position])
    }

    class GenreViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        fun bind(item: PopularMovies) {
            itemView.movieListItem.setMoviesByGenre(item)
        }
    }
}