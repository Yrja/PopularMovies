package com.example.movies.model

import com.example.movies.model.entity.Genre
import com.example.movies.model.entity.Movie

class MovieRepositoryImpl(
    private val movieDatabase: MovieDatabase,
    private val api: API,
    private val dataMapper: DataMapper
) : MovieRepository {

    override suspend fun getGenres(): List<Genre> {
        return try {
            val genres = api.getGenres().genres
            movieDatabase.moviesDao().insertGenres(dataMapper.convertGenresToDBEntity(genres))
            genres
        } catch (e: Throwable) {
            dataMapper.convertDBEntityToGenres(movieDatabase.moviesDao().getGenres())
        }
    }

    override suspend fun getMovies(): List<Movie> {
        return try {
            val movies = api.getMovies().results
            movieDatabase.moviesDao().insertMovies(dataMapper.convertMoviesToDBEntity(movies))
            movies
        } catch (e: Throwable) {
            dataMapper.convertDBEntityToMovies(movieDatabase.moviesDao().getMovies())
        }
    }
}