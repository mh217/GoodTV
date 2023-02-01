package com.example.goodtv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class CommentHelp : AppCompatActivity() {


    private var movieId=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment_help)


        movieId = intent.getStringExtra("movieidd")!!

        val additional = Comments()

        supportFragmentManager.beginTransaction().apply {
            var bundle =Bundle()
            bundle.putString("Id", movieId)
            additional.arguments=bundle
            replace(R.id.fragmentContainerView2,additional)
            commit()
        }
    }
}