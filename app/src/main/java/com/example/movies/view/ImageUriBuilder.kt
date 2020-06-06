package com.example.movies.view

import android.net.Uri
import com.example.movies.Utils.API_KEY
import com.example.movies.Utils.IMAGE_URI_AUTHORITY
import com.example.movies.Utils.IMAGE_URI_SCHEME
import com.example.movies.Utils.IMAGE_URI_PATH
import com.example.movies.Utils.API_KEY_QUERY_KEY

object ImageUriBuilder {
    fun buildImageUri(path: String): String {
        val builder = Uri.Builder()
        builder.scheme(IMAGE_URI_SCHEME)
            .authority(IMAGE_URI_AUTHORITY)
            .appendEncodedPath(IMAGE_URI_PATH)
            .appendEncodedPath(path)
            .appendQueryParameter(API_KEY_QUERY_KEY, API_KEY)
        return builder.build().toString()
    }
}