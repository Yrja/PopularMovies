package com.example.movies.model

import com.example.movies.model.entity.Genre
import com.example.movies.model.entity.Movie
import com.example.movies.model.entity.db.GenreEntity
import com.example.movies.model.entity.db.MovieEntity
import org.junit.Test

import org.junit.Assert.*

class DataMapperTest {

    private val genresList = listOf(
        Genre(1, "Comedy"),
        Genre(2, "Thriller"),
        Genre(3, "Cartoon"),
        Genre(4, "Action")
    )

    private val moviesList = listOf(
        createMovieMock(listOf(1), "Movie1"),
        createMovieMock(listOf(1, 2, 3), "Movie2"),
        createMovieMock(listOf(1, 2), "Movie3"),
        createMovieMock(listOf(2), "Movie4"),
        createMovieMock(listOf(2, 3), "Movie5")
    )

    private val genreEntityList = listOf(
        GenreEntity(1, "Comedy"),
        GenreEntity(2, "Thriller"),
        GenreEntity(3, "Cartoon"),
        GenreEntity(4, "Action")
    )

    private val movieEntityList = listOf(
        createMovieEntityMock(listOf(1), "Movie1"),
        createMovieEntityMock(listOf(1, 2, 3), "Movie2"),
        createMovieEntityMock(listOf(1, 2), "Movie3"),
        createMovieEntityMock(listOf(2), "Movie4"),
        createMovieEntityMock(listOf(2, 3), "Movie5")
    )

    private fun createMovieEntityMock(ids: List<Int>, name: String): MovieEntity {
        return MovieEntity(false, "", ids, 0, "", "", "", 0.1, " ", " ", name, true, 0.0, 0)
    }

    private fun createMovieMock(ids: List<Int>, name: String): Movie {
        return Movie(false, "", ids, 0, "", "", "", 0.1, " ", " ", name, true, 0.0, 0)
    }

    private val mapper = DataMapper()

    @Test
    fun convertGenresToDBEntity() {
        val expectedResult = listOf(
            GenreEntity(1, "Comedy"),
            GenreEntity(2, "Thriller"),
            GenreEntity(3, "Cartoon"),
            GenreEntity(4, "Action")
        )
        val result = mapper.convertGenresToDBEntity(genresList)
        assertEquals(expectedResult, result)
    }

    @Test
    fun convertMoviesToDBEntity() {
        val expectedResult = listOf(
            createMovieEntityMock(listOf(1), "Movie1"),
            createMovieEntityMock(listOf(1, 2, 3), "Movie2"),
            createMovieEntityMock(listOf(1, 2), "Movie3"),
            createMovieEntityMock(listOf(2), "Movie4"),
            createMovieEntityMock(listOf(2, 3), "Movie5")
        )
        val actualResult = mapper.convertMoviesToDBEntity(moviesList)
        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun convertDBEntityToGenres() {
        val expectedResult = listOf(
            Genre(1, "Comedy"),
            Genre(2, "Thriller"),
            Genre(3, "Cartoon"),
            Genre(4, "Action")
        )
        val actualResult = mapper.convertDBEntityToGenres(genreEntityList)
        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun convertDBEntityToMovies() {
        val expectedResult = listOf(
            createMovieMock(listOf(1), "Movie1"),
            createMovieMock(listOf(1, 2, 3), "Movie2"),
            createMovieMock(listOf(1, 2), "Movie3"),
            createMovieMock(listOf(2), "Movie4"),
            createMovieMock(listOf(2, 3), "Movie5")
        )
        val actualResult = mapper.convertDBEntityToMovies(movieEntityList)
        assertEquals(expectedResult, actualResult)
    }
}