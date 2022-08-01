package com.example.movieapp.UI.Movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.utils.Constants

class MoviesAdapter(private val moviesList: List<Movie>) :
    RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.txtMovieTitle)
        val parentView: ConstraintLayout = view.findViewById(R.id.MovieItem)
        val txtDescription: TextView = view.findViewById(R.id.txtMovieDescription)
        val imageView:ImageView = view.findViewById(R.id.ImgMovie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = moviesList[position]
        Glide.with(holder.parentView.context).load( Constants.LinkPoza + moviesList[position].image).into(holder.imageView)
        holder.title.text = movie.title
        holder.txtDescription.text = movie.description

    }

    override fun getItemCount() = moviesList.size
}