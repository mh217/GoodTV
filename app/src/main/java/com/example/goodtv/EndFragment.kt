package com.example.goodtv

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentTransaction


class EndFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_end, container, false)


        val back = view.findViewById<Button>(R.id.GobackBtn)

        back.setOnClickListener {
            val previousFrag = MoreFrag()
            val fragmentTransation: FragmentTransaction? = activity?.supportFragmentManager?.beginTransaction()
            fragmentTransation?.replace(R.id.fragmentContainerView,previousFrag)
            fragmentTransation?.commit()
        }


        return view
    }


}