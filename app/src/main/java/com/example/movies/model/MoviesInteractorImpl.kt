package com.example.movies.model

import com.example.movies.model.entity.Genre
import com.example.movies.model.entity.Movie

class MoviesInteractorImpl(private val movieRepository: MovieRepository): MovieInteractor {

    override suspend fun getMoviesByGenres(): HashMap<Genre, List<Movie>> {
        val genres = movieRepository.getGenres().genres
        val movies = movieRepository.getMovies().movies
        val map = HashMap<Genre, List<Movie>>()

        genres.forEach { genre ->
            val moviesList = ArrayList<Movie>()
            movies.forEach { movie ->
                if (movie.genre_ids.contains(genre.id)) {
                    moviesList.add(movie)
                }
            }
            map[genre] = moviesList
        }
        return map
    }
}