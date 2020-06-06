package com.example.movies.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.movies.Utils.exception
import com.example.movies.Utils.popularMovies
import com.example.movies.model.MoviesInteractor
import com.example.movies.model.entity.PopularMovies
import com.example.movies.model.entity.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.withContext
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class MovieViewModelTest {

    @Mock
    lateinit var interactor: MoviesInteractor

    private lateinit var viewModel: MovieViewModel

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var errorObserver: Observer<Throwable>

    @Mock
    lateinit var moviesObserver: Observer<List<PopularMovies>>

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = MovieViewModel(interactor)
        Dispatchers.setMain(Dispatchers.Unconfined)
        viewModel.getErrorLiveData().observeForever(errorObserver)
        viewModel.getMovieLiveData().observeForever(moviesObserver)
    }

    @Test
    fun `should post interactor result when display movies`() {
        val expectedResult = Result(popularMovies)
        runBlocking {
            Mockito.`when`(interactor.getPopularMovies()).thenReturn(expectedResult)
            viewModel.displayMovies()
            withContext(Dispatchers.Main) {
                verify(moviesObserver)?.onChanged(expectedResult.result)
            }
        }
    }

    @Test
    fun `should post error if interactor return it`() {
        val expectedResult = Result(popularMovies, exception)
        runBlocking {
            Mockito.`when`(interactor.getPopularMovies()).thenReturn(expectedResult)
            viewModel.displayMovies()
            withContext(Dispatchers.Main) {
                verify(errorObserver)?.onChanged(expectedResult.error)
            }
        }
    }
}