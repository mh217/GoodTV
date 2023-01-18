package com.example.goodtv

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val watchedBtn=findViewById<Button>(R.id.WatchedBtn)
        val toWatchBtn=findViewById<Button>(R.id.ToWatchBtn)
        val searchBtn = findViewById<ImageButton>(R.id.SearchBtnHome)


        watchedBtn.setOnClickListener {
            val watchedAct =Intent(this, WatchedMovies::class.java)
            startActivity(watchedAct)
            finish()
        }

        toWatchBtn.setOnClickListener {
            val toWatchAct =Intent(this,ToWatchMovies::class.java)
            startActivity(toWatchAct)
            finish()
        }

        searchBtn.setOnClickListener {
            val searchAct =Intent(this, SearchMovies::class.java)
            startActivity(searchAct)
            finish()
        }
    }
}