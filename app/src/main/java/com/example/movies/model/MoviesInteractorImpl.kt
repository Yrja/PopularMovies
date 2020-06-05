package com.example.movies.model

import com.example.movies.model.entity.Movie
import com.example.movies.model.entity.MovieEntry

class MoviesInteractorImpl(private val movieRepository: MovieRepository) : MovieInteractor {

    override suspend fun getMoviesByGenres(): List<MovieEntry> {
        val genres = movieRepository.getGenres().genres
        val movies = movieRepository.getMovies().results
        val map = ArrayList<MovieEntry>()

        genres.forEach { genre ->
            val moviesList = ArrayList<Movie>()
            movies.forEach { movie ->
                if (movie.genre_ids.contains(genre.id)) {
                    moviesList.add(movie)
                }
            }
            map.add(MovieEntry(genre, moviesList))
        }
        return map.filter { it.movies.isNotEmpty() }
    }
}