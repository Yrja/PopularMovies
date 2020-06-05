package com.example.movies.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movies.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        GlobalScope.launch(Dispatchers.IO) {
            val str = someAsyncWork()
            withContext(Dispatchers.Main){
                //vTest.text = str
            }
        }
    }

    suspend fun someAsyncWork(): String {
        return "Async work was done"
    }
}