package com.example.goodtv

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.fragment.app.FragmentTransaction
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class MoreFrag : Fragment() {

    private val db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_more, container, false)

        val end = view.findViewById<Button>(R.id.NextBtn)
        val back = view.findViewById<ImageButton>(R.id.backBtnn)

        end.setOnClickListener {
            val endFrag = EndFragment()
            val fragmentTransation: FragmentTransaction? = activity?.supportFragmentManager?.beginTransaction()
            fragmentTransation?.replace(R.id.fragmentContainerView,endFrag)
            fragmentTransation?.commit()
        }

        back.setOnClickListener {
            val backTo = Intent(context,MainActivity::class.java)
            startActivity(backTo)
        }



        return view
    }

}