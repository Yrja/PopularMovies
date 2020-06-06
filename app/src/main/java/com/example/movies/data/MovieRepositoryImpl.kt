package com.example.movies.data

import com.example.movies.model.MovieRepository
import com.example.movies.model.entity.Genre
import com.example.movies.model.entity.Movie
import com.example.movies.model.entity.Result

class MovieRepositoryImpl(
    private val movieDatabase: MovieDatabase,
    private val api: MovieApi,
    private val entityMapper: EntityMapper
) : MovieRepository {

    override suspend fun getGenres(): Result<List<Genre>?> {
        return try {
            val genres = api.getGenres().genres
            movieDatabase.movieDao().insertGenres(entityMapper.convertGenresToDbEntity(genres))
            Result(genres)
        } catch (e: Throwable) {
            val genres = entityMapper.convertDbEntityToGenres(movieDatabase.movieDao().getGenres())
            Result(genres, e)
        }
    }

    override suspend fun getMovies(): Result<List<Movie>?> {
        return try {
            val movies = api.getMovies().results
            movieDatabase.movieDao().insertMovies(entityMapper.convertMoviesToDbEntity(movies))
            Result(movies)
        } catch (e: Throwable) {
            val movies =
                entityMapper.convertDbEntityToMovies(movieDatabase.movieDao().getMovies())
            Result(movies, e)
        }
    }
}