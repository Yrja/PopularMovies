package com.example.movies.model

import com.example.movies.model.entity.Genre
import com.example.movies.model.entity.Movie
import com.example.movies.model.entity.db.GenreEntity
import com.example.movies.model.entity.db.MovieEntity

class DataMapper {

    fun convertGenresToDBEntity(genres: List<Genre>): List<GenreEntity> {
        val genresListDB = ArrayList<GenreEntity>()
        genres.forEach {
            val entity = GenreEntity(it.id, it.name)
            genresListDB.add(entity)
        }
        return genresListDB
    }

    fun convertMoviesToDBEntity(movies: List<Movie>): List<MovieEntity> {
        val moviesListDB = ArrayList<MovieEntity>()
        movies.forEach {
            val entity = MovieEntity(
                it.adult,
                it.backdrop_path,
                it.genre_ids,
                it.id,
                it.original_language,
                it.original_title,
                it.overview,
                it.popularity,
                it.poster_path,
                it.release_date,
                it.title,
                it.video,
                it.vote_average,
                it.vote_count
            )
            moviesListDB.add(entity)
        }
        return moviesListDB
    }

    fun convertDBEntityToGenres(genres: List<GenreEntity>): List<Genre> {
        val genresList = ArrayList<Genre>()
        genres.forEach {
            val entity = Genre(it.id, it.name)
            genresList.add(entity)
        }
        return genresList
    }

    fun convertDBEntityToMovies(movies: List<MovieEntity>): List<Movie> {
        val moviesList = ArrayList<Movie>()
        movies.forEach {
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
            moviesList.add(entity)
        }
        return moviesList
    }
}