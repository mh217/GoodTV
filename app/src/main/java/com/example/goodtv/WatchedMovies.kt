package com.example.goodtv

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class WatchedMovies : AppCompatActivity(){

    private val db = Firebase.firestore
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_watched_movies)


        val movieRecyclerView =findViewById<RecyclerView>(R.id.RecyclerViewMovies)

        db.collection("Movies")
            .whereEqualTo("Watched", true)
            .get()
            .addOnSuccessListener { result ->
                val movieArrayList = ArrayList<Movie>()
                for(data in result.documents) {
                    val movie = data.toObject(Movie::class.java)
                    if(movie != null) {
                        movie.Id = data.id
                        movieArrayList.add(movie)
                    }
                }

                movieAdapter= MovieAdapter(movieArrayList)
                movieRecyclerView.apply {
                    layoutManager = LinearLayoutManager(this@WatchedMovies)
                    adapter = movieAdapter
                }
            }




        val homeBtn=findViewById<ImageButton>(R.id.HomeBtnHome)
        val searchBtn = findViewById<ImageButton>(R.id.SearchBtnHomee)
        homeBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }

        searchBtn.setOnClickListener {
            val intent = Intent(this, SearchMovies::class.java)
            startActivity(intent)

        }
        


    }
}