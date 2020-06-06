package com.example.movies.model

import com.example.movies.Utils.genresList
import com.example.movies.Utils.moviesList
import com.example.movies.Utils.popularMovies
import com.example.movies.model.entity.*
import com.example.movies.model.entity.Result
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class MoviesInteractorImplTest {

    @Test
    fun `movies should be grouped by genre and sorted by title`() {
        val repository: MovieRepository = object : MovieRepository {
            override suspend fun getGenres(): Result<List<Genre>?> = Result(genresList)
            override suspend fun getMovies(): Result<List<Movie>?> = Result(moviesList)
        }
        val interactor = MoviesInteractorImpl(repository)
        val expectedResult = Result(popularMovies)
        runBlocking {
            val genres = interactor.getPopularMovies()
            assertEquals(expectedResult, genres)
        }
    }

    @Test
    fun `interactor result should contain error when error was thrown for genres`() {
        val error = Throwable("Network error")
        val expectedResult = Result(popularMovies, error)
        val repositoryWithError: MovieRepository = object : MovieRepository {
            override suspend fun getGenres(): Result<List<Genre>?> = Result(genresList, error)
            override suspend fun getMovies(): Result<List<Movie>?> = Result(moviesList)
        }
        val interactorWithError = MoviesInteractorImpl(repositoryWithError)
        runBlocking {
            val genres = interactorWithError.getPopularMovies()
            assertEquals(expectedResult, genres)
        }
    }
}