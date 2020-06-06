package com.example.movies.model

import com.example.movies.Utils.genreEntityList
import com.example.movies.Utils.genresList
import com.example.movies.Utils.movieEntityList
import com.example.movies.Utils.moviesList
import com.example.movies.data.EntityMapper
import org.junit.Assert.assertEquals
import org.junit.Test

class DataMapperTest {

    private val mapper = EntityMapper()

    @Test
    fun `convert genres to db entity`() {
        val expectedResult = genreEntityList
        val result = mapper.convertGenresToDbEntity(genresList)
        assertEquals(expectedResult, result)
    }

    @Test
    fun `convert movies to db entity`() {
        val expectedResult = movieEntityList
        val result = mapper.convertMoviesToDbEntity(moviesList)
        assertEquals(expectedResult, result)
    }

    @Test
    fun `convert db entity to genres`() {
        val expectedResult = genresList
        val result = mapper.convertDbEntityToGenres(genreEntityList)
        assertEquals(expectedResult, result)
    }

    @Test
    fun `convert db entity to movies`() {
        val expectedResult = moviesList
        val result = mapper.convertDbEntityToMovies(movieEntityList)
        assertEquals(expectedResult, result)
    }
}