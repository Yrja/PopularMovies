package com.example.movies.model

import com.example.movies.Utils.exception
import com.example.movies.Utils.genreEntityList
import com.example.movies.Utils.genresList
import com.example.movies.Utils.movieEntityList
import com.example.movies.Utils.moviesList
import com.example.movies.data.*
import com.example.movies.model.entity.Genres
import com.example.movies.model.entity.MoviesPage
import com.example.movies.model.entity.Result
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
    lateinit var api: MovieApi

    @Mock
    lateinit var mapper: EntityMapper

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
            Mockito.`when`(database.movieDao()).thenReturn(moviesDao)
            /**
             * creating mocks for genres
             **/
            val genresApiResponse = Genres(genresList)
            Mockito.`when`(api.getGenres()).thenReturn(genresApiResponse)
            Mockito.`when`(mapper.convertGenresToDbEntity(genresApiResponse.genres))
                .thenReturn(genreEntityList)
            Mockito.`when`(moviesDao.getGenres()).thenReturn(genreEntityList)
            /**
             * creating mocks for movies
             **/
            val moviesApiResponse = MoviesPage(1, moviesList, 20, 20)
            Mockito.`when`(api.getMovies()).thenReturn(moviesApiResponse)
            Mockito.`when`(mapper.convertMoviesToDbEntity(moviesApiResponse.results))
                .thenReturn(movieEntityList)
            Mockito.`when`(moviesDao.getMovies()).thenReturn(movieEntityList)
        }
    }

    @Test
    fun `check if genres will be inserted into db`() {
        runBlocking {
            repository.getGenres()
            Mockito.verify(moviesDao).insertGenres(genreEntityList)
        }
    }

    @Test
    fun `check if movies will be inserted into db`() {
        runBlocking {
            repository.getMovies()
            Mockito.verify(moviesDao).insertMovies(movieEntityList)
        }
    }

    @Test
    fun `check if genres will not be inserted into db in case if exception was thrown for api`() {
        runBlocking {
            Mockito.`when`(api.getGenres()).thenThrow(IndexOutOfBoundsException())
            repository.getGenres()
            Mockito.verify(moviesDao, never()).insertGenres(genreEntityList)
        }
    }

    @Test
    fun `check if movies will not be inserted into db in case if exception was thrown for api`() {
        runBlocking {
            Mockito.`when`(api.getMovies()).thenThrow(IndexOutOfBoundsException())
            repository.getMovies()
            Mockito.verify(moviesDao, never()).insertMovies(movieEntityList)
        }
    }

    @Test
    fun `repository should return db genres in case if exception was thrown for api`() {
        val expectedGenresResult = Result(genresList, exception)
        runBlocking {
            Mockito.`when`(api.getGenres()).thenThrow(exception)
            Mockito.`when`(mapper.convertDbEntityToGenres(genreEntityList))
                .thenReturn(genresList)
            Assert.assertEquals(expectedGenresResult, repository.getGenres())
        }
    }

    @Test
    fun `repository should return db movies in case if exception was thrown for api`() {
        val expectedMoviesResult = Result(moviesList, exception)
        runBlocking {
            Mockito.`when`(api.getMovies()).thenThrow(exception)
            Mockito.`when`(mapper.convertDbEntityToMovies(movieEntityList))
                .thenReturn(moviesList)
            Assert.assertEquals(expectedMoviesResult, repository.getMovies())
        }
    }
}