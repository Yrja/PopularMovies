package com.example.movies.data

import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class IdsConverter {
    private val adapter by lazy {
        val moshi = Moshi.Builder().build()
        val type = Types.newParameterizedType(List::class.java, Int::class.java)
        moshi.adapter<List<Int>>(type)
    }

    @TypeConverter
    fun fromIdList(ids: List<Int>): String {
        return adapter.toJson(ids)
    }

    @TypeConverter
    fun toIdList(ids: String): List<Int> {
        return adapter.fromJson(ids) ?: emptyList()
    }
}