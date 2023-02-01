package com.example.goodtv

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase



class Comments : Fragment() {

    private val db = Firebase.firestore
    private lateinit var commentAdapter: CommentAdapter




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_comments, container, false)


        val commentRecyclerView = view.findViewById<RecyclerView>(R.id.RecyclerComments)
        val inputName = view.findViewById<EditText>(R.id.InputName)
        val inputComment = view.findViewById<EditText>(R.id.InputComment)
        val back = view.findViewById<ImageButton>(R.id.backBtninFrag)
        val comment = view.findViewById<ImageButton>(R.id.CommentBtn)


        val movieId = arguments?.getString("Id").toString()
        back.setOnClickListener {
            val backTo = Intent(context,SearchMovies::class.java)
            startActivity(backTo)
        }



        comment.setOnClickListener {
            val name=inputName.text.toString()
            val commente=inputComment.text.toString()
            val ref = FirebaseFirestore.getInstance().collection("Movies").document(movieId).collection("Comment")
            if(name.trim().isNotEmpty()){
                ref.add(mapOf(
                    "Name" to name,
                    "Comment" to commente
                ))

            }

            if(name.trim().isEmpty() && commente.trim().isNotEmpty()){
                ref.add(mapOf(
                    "Name" to "Anonymous",
                    "Comment" to commente
                ))


                inputName.text.clear()
                inputComment.text.clear()
            }

            if(name.trim().isEmpty() && commente.trim().isEmpty()) {
                Toast.makeText(context,"Enter a comment", Toast.LENGTH_LONG).show()
            }


            db.collection("Movies")
                .document(movieId)
                .collection("Comment")
                .get()
                .addOnSuccessListener { result ->
                    val commentsArrayList: ArrayList<Comment> = ArrayList()
                    for (data in result.documents) {
                        val comment = data.toObject(Comment::class.java)
                        if (comment != null) {
                            comment.Id = data.id
                            commentsArrayList.add(comment)
                        }
                    }

                    commentAdapter = CommentAdapter(commentsArrayList)
                    commentRecyclerView.apply {
                        layoutManager = LinearLayoutManager(context)
                        adapter = commentAdapter
                    }

                }
                .addOnFailureListener {exception ->
                    Log.w("Comments", "Error getting documents.", exception)
                }

        }





        db.collection("Movies")
            .document(movieId)
            .collection("Comment")
            .get()
            .addOnSuccessListener { result ->
                val commentsArrayList: ArrayList<Comment> = ArrayList()
                for (data in result.documents) {
                    val comment = data.toObject(Comment::class.java)
                    if (comment != null) {
                        comment.Id = data.id
                        commentsArrayList.add(comment)
                    }
                }

                commentAdapter = CommentAdapter(commentsArrayList)
                commentRecyclerView.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = commentAdapter
                }

            }
            .addOnFailureListener {exception ->
                Log.w("Comments", "Error getting documents.", exception)
            }

        return view

    }
}