package com.example.goodtv

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CommentAdapter (private val commentList: ArrayList<Comment>) : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentAdapter.CommentViewHolder {


        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recyclerviewcomment, parent, false)
        return CommentViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        when(holder) {
            is CommentViewHolder -> {
                holder.bind(position, commentList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return  commentList.size
    }


    class CommentViewHolder(val view: View):RecyclerView.ViewHolder(view) {

        val userName = view.findViewById<TextView>(R.id.UserName)
        val comment = view.findViewById<TextView>(R.id.CommentText)



        fun bind(index: Int,  comments: Comment) {
            userName.text=comments.Name.toString()
            comment.text=comments.Comment.toString()
        }

    }

}