package com.example.movies.model

import com.example.movies.model.entity.Genre
import com.example.movies.model.entity.Genres
import com.example.movies.model.entity.Movie
import com.example.movies.model.entity.Movies
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class MoviesInteractorTest {

  private  val repository: MovieRepository = object : MovieRepository {
        override suspend fun getGenres(): Genres {
            return Genres(
                listOf(
                    Genre(1, "Comedy"),
                    Genre(2, "Thriller"),
                    Genre(3, "Cartoon"),
                    Genre(4, "Hz")
                )
            )
        }

        override suspend fun getMovies(): Movies {
            return Movies(
                0,
                listOf(
                    createMovieMock(listOf(1), "Hello1"),
                    createMovieMock(listOf(1, 2, 3), "Hello2"),
                    createMovieMock(listOf(1, 2), "Hello3"),
                    createMovieMock(listOf(2), "Hello4"),
                    createMovieMock(listOf(2, 3), "Hello5")
                ),
                0,
                0
            )
        }
    }

    private val interactor = MoviesInteractor(repository)

    @Test
    fun moviesListShouldBeSplitByGenres() {
        val expectedResult = hashMapOf(
            Genre(1, "Comedy") to listOf(
                createMovieMock(listOf(1), "Hello1"),
                createMovieMock(listOf(1, 2, 3), "Hello2"),
                createMovieMock(listOf(1, 2), "Hello3")
            ),
            Genre(2, "Thriller") to listOf(
                createMovieMock(listOf(1, 2, 3), "Hello2"),
                createMovieMock(listOf(1, 2), "Hello3"),
                createMovieMock(listOf(2), "Hello4"),
                createMovieMock(listOf(2, 3), "Hello5")
            ),
            Genre(3, "Cartoon") to listOf(
                createMovieMock(listOf(1, 2, 3), "Hello2"),
                createMovieMock(listOf(2, 3), "Hello5")
            ),
            Genre(4, "Hz") to emptyList()
        )

        runBlocking {
            val genres = interactor.getMoviesByGenres()
            assertEquals(expectedResult, genres)
        }
    }

    private fun createMovieMock(ids: List<Int>, name: String): Movie {
        return Movie(false, "", ids, 0, "", "", "", 0.1, " ", " ", name, true, 0, 0)
    }
}