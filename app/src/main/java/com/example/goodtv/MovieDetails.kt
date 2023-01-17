package com.example.goodtv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.bumptech.glide.Glide
import com.example.goodtv.databinding.ActivityMovieDetailsBinding
import com.google.firebase.firestore.FirebaseFirestore

class Movie_details : AppCompatActivity() {

    private lateinit var binding : ActivityMovieDetailsBinding
    private var movieId=""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val watchedBtn = binding.Watchedbbtn
        val toWatchMovies = binding.ToWatchedBBtn

        movieId =intent.getStringExtra("movieID")!!

        loadMovieDetails()


        val backbtn=findViewById<ImageButton>(R.id.backBtn)
             backbtn.setOnClickListener() {
                    onBackPressed()
                }


        watchedBtn.setOnClickListener {
                val ref = FirebaseFirestore.getInstance().collection("Movies")
                ref.document(movieId)
                    .update(mapOf(
                        "Watched" to true,
                        "ToWatch" to false
                    ))
            }

        toWatchMovies.setOnClickListener {
            val ref = FirebaseFirestore.getInstance().collection("Movies")
            ref.document(movieId)
                .update(mapOf(
                    "Watched" to false,
                    "ToWatch" to true
                ))

        }
        }


    private fun loadMovieDetails() {
        val ref = FirebaseFirestore.getInstance().collection("Movies")
        ref.document(movieId)
            .get()
            .addOnSuccessListener { result->
                val name = result.get("Name")
                val rating = result.get("Rating")
                val image = result.get("Poster")
                val synopsis =result.get("Synopsis")

                Glide
                    .with(this)
                    .load(image)
                    .into(binding.IMGPoster)

                binding.NameText.text=name.toString()
                binding.RatingText.text=rating.toString()
                binding.SynopsisText.text = synopsis.toString()
            }
    }







}
