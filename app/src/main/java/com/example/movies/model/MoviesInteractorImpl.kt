package com.example.movies.model

import com.example.movies.model.entity.Genre
import com.example.movies.model.entity.Movie
import com.example.movies.model.entity.MovieEntry
import com.example.movies.model.entity.response.MovieEntryResponse

class MoviesInteractorImpl(
    private val movieRepository: MovieRepository
) : MovieInteractor {

    override suspend fun getMoviesByGenres(): MovieEntryResponse {
        val genreResponse = movieRepository.getGenres()
        val movieResponse = movieRepository.getMovies()
        var entries: List<MovieEntry> = ArrayList()
        var error:Throwable? = null
        genreResponse.error?.let {
            error = it
        } ?: movieResponse.error?.let {
            error = it
        }
        genreResponse.genre?.let { genres ->
            movieResponse.movies?.let { movies ->
                entries = filterMoviesByGenres(genres, movies)
            }
        }
        return MovieEntryResponse(entries, error)
    }

    private fun filterMoviesByGenres(genres: List<Genre>, movies: List<Movie>): List<MovieEntry> {
        val map = ArrayList<MovieEntry>()
        genres.forEach { genre ->
            val moviesList = ArrayList<Movie>()
            movies.sortedBy { it.title }
                .forEach { movie ->
                    if (movie.genre_ids.contains(genre.id)) {
                        moviesList.add(movie)
                    }
                }
            map.add(MovieEntry(genre, moviesList))
        }
        return map.filter { it.movies.isNotEmpty() }
            .sortedBy { it.genre.name }
    }
}