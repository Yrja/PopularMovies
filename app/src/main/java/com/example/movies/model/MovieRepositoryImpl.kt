package com.example.movies.model

import com.example.movies.model.entity.response.GenresResponse
import com.example.movies.model.entity.response.MoviesResponse

class MovieRepositoryImpl(
    private val movieDatabase: MovieDatabase,
    private val api: API,
    private val dataMapper: DataMapper
) : MovieRepository {

    override suspend fun getGenres(): GenresResponse {
        return try {
            val genres = api.getGenres().genres
            movieDatabase.moviesDao().insertGenres(dataMapper.convertGenresToDBEntity(genres))
            GenresResponse(genres)
        } catch (e: Throwable) {
            val genres = dataMapper.convertDBEntityToGenres(movieDatabase.moviesDao().getGenres())
            GenresResponse(genres, e)
        }
    }

    override suspend fun getMovies(): MoviesResponse {
        return try {
            val movies = api.getMovies().results
            movieDatabase.moviesDao().insertMovies(dataMapper.convertMoviesToDBEntity(movies))
            MoviesResponse(movies)
        } catch (e: Throwable) {
            val movies =
                dataMapper.convertDBEntityToMovies(movieDatabase.moviesDao().getMovies())
            MoviesResponse(movies, e)
        }
    }
}