package com.example.movies.model

import com.example.movies.model.entity.Genre
import com.example.movies.model.entity.Genres
import com.example.movies.model.entity.Movie
import com.example.movies.model.entity.Movies
import com.example.movies.model.entity.db.GenreEntity
import com.example.movies.model.entity.db.MovieEntity
import com.example.movies.model.entity.response.GenresResponse
import com.example.movies.model.entity.response.MoviesResponse
import com.nhaarman.mockitokotlin2.never
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MovieRepositoryImplTest {
    @Mock
    lateinit var api: API

    @Mock
    lateinit var mapper: DataMapper

    @Mock
    lateinit var database: MovieDatabase

    @Mock
    lateinit var moviesDao: MovieDao

    lateinit var repository: MovieRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        repository = MovieRepositoryImpl(database, api, mapper)
        runBlocking {
            Mockito.`when`(database.moviesDao()).thenReturn(moviesDao)
            /**
             * created mockups for genres
             * **/
            Mockito.`when`(api.getGenres()).thenReturn(genresResultAPI)
            Mockito.`when`(mapper.convertGenresToDBEntity(genresResultAPI.genres))
                .thenReturn(resultGenresMapper)
            Mockito.`when`(moviesDao.getGenres()).thenReturn(resultGenresMapper)
            /**
             *created mockups for movies
             **/
            Mockito.`when`(api.getMovies()).thenReturn(moviesResultAPI)
            Mockito.`when`(mapper.convertMoviesToDBEntity(moviesResultAPI.results))
                .thenReturn(resultMoviesMapper)
            Mockito.`when`(moviesDao.getMovies()).thenReturn(resultMoviesMapper)
        }
    }

    private val genresResultAPI = Genres(
        listOf(
            Genre(1, "Comedy"),
            Genre(2, "Thriller"),
            Genre(3, "Cartoon"),
            Genre(4, "Action")
        )
    )

    private val moviesResultAPI = Movies(
        1,
        listOf(
            createMovieMock(listOf(1), "Movie1"),
            createMovieMock(listOf(1, 2, 3), "Movie2"),
            createMovieMock(listOf(1, 2), "Movie3"),
            createMovieMock(listOf(2), "Movie4"),
            createMovieMock(listOf(2, 3), "Movie5")
        ),
        20,
        20
    )

    private fun createMovieMock(ids: List<Int>, name: String): Movie {
        return Movie(false, "", ids, 0, "", "", "", 0.1, " ", " ", name, true, 0.0, 0)
    }

    private val resultGenresMapper = listOf(
        GenreEntity(1, "Comedy"),
        GenreEntity(2, "Thriller"),
        GenreEntity(3, "Cartoon"),
        GenreEntity(4, "Action")
    )

    private val resultMoviesMapper = listOf(
        createMovieEntityMock(listOf(1), "Movie1"),
        createMovieEntityMock(listOf(1, 2, 3), "Movie2"),
        createMovieEntityMock(listOf(1, 2), "Movie3"),
        createMovieEntityMock(listOf(2), "Movie4"),
        createMovieEntityMock(listOf(2, 3), "Movie5")
    )

    private fun createMovieEntityMock(ids: List<Int>, name: String): MovieEntity {
        return MovieEntity(false, "", ids, 0, "", "", "", 0.1, " ", " ", name, true, 0.0, 0)
    }


    @Test
    fun checkIfGenresAreInsertedIntoDB() {
        runBlocking {
            repository.getGenres()
            Mockito.verify(moviesDao).insertGenres(resultGenresMapper)
        }
    }

    @Test
    fun checkIfMoviesAreInsertedIntoDB() {
        runBlocking {
            repository.getMovies()
            Mockito.verify(moviesDao).insertMovies(resultMoviesMapper)
        }
    }

    @Test
    fun checkIfGenresAreNeverInsertedIntoDBIfThereWasAnExceptionBefore() {
        runBlocking {
            Mockito.`when`(api.getGenres()).thenThrow(IndexOutOfBoundsException())
            repository.getGenres()
            Mockito.verify(moviesDao, never()).insertGenres(resultGenresMapper)
        }
    }

    @Test
    fun checkIfMoviesAreNeverInsertedIntoDBIfThereWasAnExceptionBefore() {
        runBlocking {
            Mockito.`when`(api.getMovies()).thenThrow(IndexOutOfBoundsException())
            repository.getMovies()
            Mockito.verify(moviesDao, never()).insertMovies(resultMoviesMapper)
        }
    }

    private val exception = IndexOutOfBoundsException()

    private val expectedGenresResult = GenresResponse(
        listOf(
            Genre(1, "Comedy"),
            Genre(2, "Thriller"),
            Genre(3, "Cartoon"),
            Genre(4, "Action")
        ), exception
    )

    private val expectedGenresMapperResult = listOf(
        Genre(1, "Comedy"),
        Genre(2, "Thriller"),
        Genre(3, "Cartoon"),
        Genre(4, "Action")
    )

    @Test
    fun checkResultIfThereWillBeExceptionOnUploadingGenresFromDB() {
        runBlocking {
            Mockito.`when`(api.getGenres()).thenThrow(exception)
            Mockito.`when`(mapper.convertDBEntityToGenres(resultGenresMapper))
                .thenReturn(expectedGenresMapperResult)
            Assert.assertEquals(expectedGenresResult, repository.getGenres())
        }
    }

    private val expectedMoviesResult = MoviesResponse(
        listOf(
            createMovieMock(listOf(1), "Movie1"),
            createMovieMock(listOf(1, 2, 3), "Movie2"),
            createMovieMock(listOf(1, 2), "Movie3"),
            createMovieMock(listOf(2), "Movie4"),
            createMovieMock(listOf(2, 3), "Movie5")
        ), exception
    )

    @Test
    fun checkResultIfThereWillBeExceptionOnUploadingMoviesFromDB() {
        runBlocking {
            Mockito.`when`(api.getMovies()).thenThrow(exception)
            Mockito.`when`(mapper.convertDBEntityToMovies(resultMoviesMapper))
                .thenReturn(moviesResultAPI.results)
            Assert.assertEquals(expectedMoviesResult, repository.getMovies())
        }
    }

}