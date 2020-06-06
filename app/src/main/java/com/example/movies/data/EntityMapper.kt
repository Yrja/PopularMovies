package com.example.movies.data

import com.example.movies.model.entity.Genre
import com.example.movies.model.entity.Movie
import com.example.movies.data.entity.GenreEntity
import com.example.movies.data.entity.MovieEntity

class EntityMapper {

    fun convertGenresToDbEntity(genres: List<Genre>): List<GenreEntity> {
        val dbGenres = ArrayList<GenreEntity>()
        genres.forEach {
            val entity = GenreEntity(it.id, it.name)
            dbGenres.add(entity)
        }
        return dbGenres
    }

    fun convertMoviesToDbEntity(movies: List<Movie>): List<MovieEntity> {
        val dbMovies = ArrayList<MovieEntity>()
        movies.forEach {
            val entity = MovieEntity(
                it.adult,
                it.backdropPath,
                it.genreIds,
                it.id,
                it.originalLanguage,
                it.originalTitle,
                it.overview,
                it.popularity,
                it.posterPath,
                it.releaseDate,
                it.title,
                it.video,
                it.voteAverage,
                it.voteCount
            )
            dbMovies.add(entity)
        }
        return dbMovies
    }

    fun convertDbEntityToGenres(dbGenres: List<GenreEntity>): List<Genre> {
        val genres = ArrayList<Genre>()
        dbGenres.forEach {
            val entity = Genre(it.id, it.name)
            genres.add(entity)
        }
        return genres
    }

    fun convertDbEntityToMovies(dbMovies: List<MovieEntity>): List<Movie> {
        val movies = ArrayList<Movie>()
        dbMovies.forEach {
            val entity = Movie(
                it.adult,
                it.backdropPath,
                it.genreIds,
                it.id,
                it.originalLanguage,
                it.originalTitle,
                it.overview,
                it.popularity,
                it.posterPath,
                it.releaseDate,
                it.title,
                it.video,
                it.voteAverage,
                it.voteCount
            )
            movies.add(entity)
        }
        return movies
    }
}