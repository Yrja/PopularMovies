package com.example.movies.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.movies.model.MovieInteractor
import com.example.movies.model.entity.response.MovieEntryResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.withContext
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MovieViewModelTest {

    @Mock
    lateinit var interactor: MovieInteractor

    lateinit var viewModel: MovieViewModel

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        viewModel = MovieViewModel(interactor)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    private val result = MovieEntryResponse()

    @Test
    fun testViewModel() {
        runBlocking {
            Mockito.`when`(interactor.getMoviesByGenres()).thenReturn(result)
            viewModel.displayMovies()
            Mockito.verify(interactor).getMoviesByGenres()
            withContext(Dispatchers.Main){
                Assert.assertEquals(viewModel.getMovieLiveData().value,result.filteredMovies)
            }
        }
    }
}