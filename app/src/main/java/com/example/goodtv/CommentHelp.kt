package com.example.goodtv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class CommentHelp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment_help)

        val additional = Comments()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainerView2,additional)
            commit()
        }
    }
}