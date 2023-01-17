package com.example.goodtv

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide



class MovieAdapter (private val movieList: ArrayList<Movie>) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {


        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recyclerviewmovie, parent, false)
        return MovieViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        when(holder) {
            is MovieViewHolder -> {
                holder.bind(position, movieList[position])
            }
        }

        holder.itemView.setOnClickListener {
            val intent =Intent (holder.itemView.context, Movie_details::class.java)
            intent.putExtra("movieID",movieList[position].Id)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
       return  movieList.size
    }



    class MovieViewHolder(val view:View):RecyclerView.ViewHolder(view) {

        val movieName = view.findViewById<TextView>(R.id.NameTextView)
        val moviePoster = view.findViewById<ImageView>(R.id.PosterView)
        val movieRating = view.findViewById<TextView>(R.id.RatingView)
        val watched = view.findViewById<TextView>(R.id.Watched)

        fun bind(index: Int,  movie: Movie) {
            Glide
                .with(view.context)
                .load(movie.Poster)
                .into(moviePoster)
            movieName.text=movie.Name.toString()
            movieRating.text=movie.Rating.toString()
            watched.text=movie.Watched.toString()

        }

    }
}




