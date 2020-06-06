package com.example.movies.model

import com.example.movies.model.entity.*
import com.example.movies.model.entity.response.GenresResponse
import com.example.movies.model.entity.response.MovieEntryResponse
import com.example.movies.model.entity.response.MoviesResponse
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class MoviesInteractorImplTest {

    private val repository: MovieRepository = object : MovieRepository {
        override suspend fun getGenres(): GenresResponse {
            return GenresResponse(
                listOf(
                    Genre(1, "Comedy"),
                    Genre(2, "Thriller"),
                    Genre(3, "Cartoon"),
                    Genre(4, "Action")
                )
            )
        }

        override suspend fun getMovies(): MoviesResponse {
            return MoviesResponse(
                listOf(
                    createMovieMock(listOf(1), "Movie1"),
                    createMovieMock(listOf(1, 2, 3), "Movie2"),
                    createMovieMock(listOf(1, 2), "Movie3"),
                    createMovieMock(listOf(2), "Movie4"),
                    createMovieMock(listOf(2, 3), "Movie5")
                )
            )
        }
    }

    private val interactor = MoviesInteractorImpl(repository)

    @Test
    fun moviesListShouldBeSplitByGenresAndSortedCombined() {
        val expectedResult = MovieEntryResponse(
            listOf(
                MovieEntry(
                    Genre(3, "Cartoon"), listOf(
                        createMovieMock(listOf(1, 2, 3), "Movie2"),
                        createMovieMock(listOf(2, 3), "Movie5")
                    )
                ),
                MovieEntry(
                    Genre(1, "Comedy"), listOf(
                        createMovieMock(listOf(1), "Movie1"),
                        createMovieMock(listOf(1, 2, 3), "Movie2"),
                        createMovieMock(listOf(1, 2), "Movie3")
                    )
                ),
                MovieEntry(
                    Genre(2, "Thriller"), listOf(
                        createMovieMock(listOf(1, 2, 3), "Movie2"),
                        createMovieMock(listOf(1, 2), "Movie3"),
                        createMovieMock(listOf(2), "Movie4"),
                        createMovieMock(listOf(2, 3), "Movie5")
                    )
                )
            )
        )
        runBlocking {
            val genres = interactor.getMoviesByGenres()
            assertEquals(expectedResult, genres)
        }
    }

    private fun createMovieMock(ids: List<Int>, name: String): Movie {
        return Movie(false, "", ids, 0, "", "", "", 0.1, " ", " ", name, true, 0.0, 0)
    }

    private val repositoryWithError: MovieRepository = object : MovieRepository {
        override suspend fun getGenres(): GenresResponse {
            return GenresResponse(
                listOf(
                    Genre(1, "Comedy"),
                    Genre(2, "Thriller"),
                    Genre(3, "Cartoon"),
                    Genre(4, "Action")
                ), error
            )
        }

        override suspend fun getMovies(): MoviesResponse {
            return MoviesResponse(
                listOf(
                    createMovieMock(listOf(1), "Movie1"),
                    createMovieMock(listOf(1, 2, 3), "Movie2"),
                    createMovieMock(listOf(1, 2), "Movie3"),
                    createMovieMock(listOf(2), "Movie4"),
                    createMovieMock(listOf(2, 3), "Movie5")
                )
            )
        }
    }
    private val interactorWithError = MoviesInteractorImpl(repositoryWithError)
    private val error = Throwable("Network error")


    @Test
    fun moviesListShouldBeSplitByGenresAndSortedCombinedWithError() {
        val expectedResult = MovieEntryResponse(
            listOf(
                MovieEntry(
                    Genre(3, "Cartoon"), listOf(
                        createMovieMock(listOf(1, 2, 3), "Movie2"),
                        createMovieMock(listOf(2, 3), "Movie5")
                    )
                ),
                MovieEntry(
                    Genre(1, "Comedy"), listOf(
                        createMovieMock(listOf(1), "Movie1"),
                        createMovieMock(listOf(1, 2, 3), "Movie2"),
                        createMovieMock(listOf(1, 2), "Movie3")
                    )
                ),
                MovieEntry(
                    Genre(2, "Thriller"), listOf(
                        createMovieMock(listOf(1, 2, 3), "Movie2"),
                        createMovieMock(listOf(1, 2), "Movie3"),
                        createMovieMock(listOf(2), "Movie4"),
                        createMovieMock(listOf(2, 3), "Movie5")
                    )
                )
            ), error
        )
        runBlocking {
            val genres = interactorWithError.getMoviesByGenres()
            assertEquals(expectedResult, genres)
        }
    }
}