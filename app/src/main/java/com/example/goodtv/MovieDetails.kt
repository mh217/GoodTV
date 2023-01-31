package com.example.goodtv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.goodtv.databinding.ActivityMovieDetailsBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Movie_details : AppCompatActivity() {

    private val db = Firebase.firestore
    private lateinit var commentAdapter: CommentAdapter
    private lateinit var binding : ActivityMovieDetailsBinding
    private var movieId=""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val watchedBtn = binding.Watchedbbtn
        val toWatchMovies = binding.ToWatchedBBtn
        val recycler = binding.RecyclerViewDetails

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


                db.collection("Comments")
                    .get()
                    .addOnSuccessListener { result2 ->
                        val commentList: ArrayList<Comment> = ArrayList()
                        for(data in result2.documents) {
                            val comment = data.toObject(Comment::class.java)
                            if(comment != null) {
                                comment.Id = data.id
                                commentList.add(comment)
                            }
                        }

                        val namesearch1 = name.toString().replaceFirstChar { it.lowercase() }
                        val namesearch2 = name.toString().replaceFirstChar { it.uppercase() }
                        val namesearch3 = name.toString().uppercase()
                        val namesearch4 = name.toString().lowercase()
                        val commentList2: ArrayList<Comment> = ArrayList()
                        for(data in commentList) {
                            if(data.Comment?.contains(namesearch1) == true || data.Comment?.contains(namesearch2)== true || data.Comment?.contains(namesearch3)== true || data.Comment?.contains(namesearch4)== true){
                                commentList2.add(data)
                            }

                                commentAdapter = CommentAdapter(commentList2 as ArrayList<Comment>)
                                binding.RecyclerViewDetails.apply {
                                    layoutManager = LinearLayoutManager(this@Movie_details)
                                    adapter=commentAdapter
                                }

                        }
                    }


            }

    }







}
