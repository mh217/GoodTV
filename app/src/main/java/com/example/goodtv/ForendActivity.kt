package com.example.goodtv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ForendActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forend)

        val additional = ForEndFragment()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainerEnd, additional)
            commit()
        }
    }
}