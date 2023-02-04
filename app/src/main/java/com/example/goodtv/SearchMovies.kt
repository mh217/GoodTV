package com.example.goodtv

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SearchMovies : AppCompatActivity(){

    private val db = Firebase.firestore
    private lateinit var movieAdapter: MovieAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_movies)


        val movieRecyclerView =findViewById<RecyclerView>(R.id.MovieRecycleView)
        val searchButton = findViewById<ImageButton>(R.id.SearchBtnSearch)
        val homeBtn = findViewById<ImageButton>(R.id.HomeBtnHomee)
        val moreBtn = findViewById<ImageButton>(R.id.MoreBtn)




        homeBtn.setOnClickListener {
            val toWatchAct = Intent(this,MainActivity::class.java)
            startActivity(toWatchAct)
        }

        moreBtn.setOnClickListener {
            val searchAct = Intent(this, Detailed::class.java)
            startActivity(searchAct)
        }

        db.collection("Movies")
                .get()
                .addOnSuccessListener { result ->
                    val movieArrayList : ArrayList<Movie> = ArrayList()
                    for(data in result.documents) {
                        val movie = data.toObject(Movie::class.java)
                        if(movie != null) {
                            movie.Id = data.id
                            movieArrayList.add(movie)
                        }
                    }

                    movieAdapter= MovieAdapter(movieArrayList)
                    movieRecyclerView.apply {
                        layoutManager = LinearLayoutManager(this@SearchMovies)
                        adapter = movieAdapter
                    }

                    searchButton.setOnClickListener() {
                        val sortedList2: ArrayList<Movie> = ArrayList()
                        val searchEdit = findViewById<EditText>(R.id.SearchEditText)
                        val search2=searchEdit.text.toString().replaceFirstChar { it.lowercase() }
                        val search3=searchEdit.text.toString().replaceFirstChar { it.uppercase() }

                        if (searchEdit.text.toString() != "") {
                            searchEdit
                            for (data in movieArrayList) {
                                if (data.Name?.contains(search3) == true || data.Name?.contains(search2)== true)
                                    sortedList2.add(data)
                            }
                            if(sortedList2.isEmpty()){

                                Toast.makeText(this,"Movie not found",Toast.LENGTH_LONG).show()
                            }
                            else{
                                movieAdapter = MovieAdapter(sortedList2 as ArrayList<Movie>)
                                movieRecyclerView.apply {
                                    layoutManager = LinearLayoutManager(this@SearchMovies)
                                    adapter = movieAdapter
                                }
                            }

                        }
                        else{
                            movieAdapter = MovieAdapter(movieArrayList)
                            movieRecyclerView.apply {
                                layoutManager = LinearLayoutManager(this@SearchMovies)
                                adapter = movieAdapter
                            }
                        }
                    }
                }

            .addOnFailureListener {exception ->
                Log.w("SearchMovies", "Error getting documents.", exception)
            }

    }
}