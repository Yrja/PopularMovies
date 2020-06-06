package com.example.movies.model

import com.example.movies.model.entity.Genre
import com.example.movies.model.entity.Movie
import com.example.movies.model.entity.PopularMovies
import com.example.movies.model.entity.Result

class MoviesInteractorImpl(
    private val movieRepository: MovieRepository
) : MoviesInteractor {

    override suspend fun getPopularMovies(): Result<List<PopularMovies>> {
        val genresResult = movieRepository.getGenres()
        val moviesResult = movieRepository.getMovies()
        val error = genresResult.error ?: moviesResult.error
        val entries = genresResult.result?.let { genres ->
            moviesResult.result?.let { movies ->
                groupMoviesByGenres(genres.sortedBy { it.name }, movies.sortedBy { it.title })
            }
        } ?: emptyList()
        return Result(
            entries.filter { it.movies.isNotEmpty() },
            error
        )
    }

    private fun groupMoviesByGenres(
        genres: List<Genre>,
        movies: List<Movie>
    ): List<PopularMovies> {
        val result = ArrayList<PopularMovies>()
        genres.forEach { genre ->
            val moviesList = ArrayList<Movie>()
            movies.forEach { movie ->
                if (movie.genreIds.contains(genre.id)) {
                    moviesList.add(movie)
                }
            }
            result.add(PopularMovies(genre, moviesList))
        }
        return result
    }
}