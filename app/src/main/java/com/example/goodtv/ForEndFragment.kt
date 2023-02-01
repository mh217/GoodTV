package com.example.goodtv

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton


class ForEndFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

      val view = inflater.inflate(R.layout.fragment_for_end, container, false)

        val backbtn = view.findViewById<ImageButton>(R.id.BackImgBtn)

        backbtn.setOnClickListener {
            val goback = Intent(context, SearchMovies::class.java)
            startActivity(goback)
        }



        return view
    }


}