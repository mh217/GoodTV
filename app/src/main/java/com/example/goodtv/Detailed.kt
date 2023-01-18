package com.example.goodtv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Detailed : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed)

        val additional = MoreFrag()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainerView,additional)
            commit()
        }

    }
}