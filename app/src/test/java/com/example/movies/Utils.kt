package com.example.movies

import com.example.movies.data.entity.GenreEntity
import com.example.movies.data.entity.MovieEntity
import com.example.movies.model.entity.Genre
import com.example.movies.model.entity.Movie
import com.example.movies.model.entity.PopularMovies

object Utils {
    val genresList = listOf(
        Genre(1, "Comedy"),
        Genre(2, "Thriller"),
        Genre(3, "Cartoon"),
        Genre(4, "Action")
    )

    val moviesList = listOf(
        createMovieMock(listOf(1), "Movie1"),
        createMovieMock(listOf(1, 2, 3), "Movie2"),
        createMovieMock(listOf(1, 2), "Movie3"),
        createMovieMock(listOf(2), "Movie4"),
        createMovieMock(listOf(2, 3), "Movie5")
    )

    val genreEntityList = listOf(
        GenreEntity(1, "Comedy"),
        GenreEntity(2, "Thriller"),
        GenreEntity(3, "Cartoon"),
        GenreEntity(4, "Action")
    )

    val movieEntityList = listOf(
        createMovieEntityMock(listOf(1), "Movie1"),
        createMovieEntityMock(listOf(1, 2, 3), "Movie2"),
        createMovieEntityMock(listOf(1, 2), "Movie3"),
        createMovieEntityMock(listOf(2), "Movie4"),
        createMovieEntityMock(listOf(2, 3), "Movie5")
    )

    val popularMovies = listOf(
        PopularMovies(
            Genre(3, "Cartoon"), listOf(
                createMovieMock(listOf(1, 2, 3), "Movie2"),
                createMovieMock(listOf(2, 3), "Movie5")
            )
        ),
        PopularMovies(
            Genre(1, "Comedy"), listOf(
                createMovieMock(listOf(1), "Movie1"),
                createMovieMock(listOf(1, 2, 3), "Movie2"),
                createMovieMock(listOf(1, 2), "Movie3")
            )
        ),
        PopularMovies(
            Genre(2, "Thriller"), listOf(
                createMovieMock(listOf(1, 2, 3), "Movie2"),
                createMovieMock(listOf(1, 2), "Movie3"),
                createMovieMock(listOf(2), "Movie4"),
                createMovieMock(listOf(2, 3), "Movie5")
            )
        )
    )

    val exception = IndexOutOfBoundsException()

    private fun createMovieEntityMock(ids: List<Int>, name: String): MovieEntity {
        return MovieEntity(false, "", ids, 0, "", "", "", 0.1, " ", " ", name, true, 0.0, 0)
    }

    private fun createMovieMock(ids: List<Int>, name: String): Movie {
        return Movie(false, "", ids, 0, "", "", "", 0.1, " ", " ", name, true, 0.0, 0)
    }
}