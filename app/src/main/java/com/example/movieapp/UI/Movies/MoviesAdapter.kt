package com.example.movieapp.UI.Movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R

class MoviesAdapter(private val moviesList: List<Movie>) :
    RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val movieName: TextView = view.findViewById(R.id.tvName)
        val parentView: ConstraintLayout = view.findViewById(R.id.parent)
        val starIcon: ImageView = view.findViewById(R.id.star)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = moviesList[position]
        holder.movieName.text = movie.title

        selectMovie(holder,movie)

        holder.parentView.setOnClickListener{
            movie.isSelected = !movie.isSelected
            selectMovie(holder,movie)

        }
    }

    private fun selectMovie(holder: ViewHolder,movie: Movie){
        holder.parentView.setBackgroundColor(when(movie.isSelected){
            true -> ContextCompat.getColor(holder.parentView.context,R.color.purple_200)
            else -> ContextCompat.getColor(holder.parentView.context,R.color.white)

        })

        holder.movieName.setTextColor(when(movie.isSelected){
            true -> ContextCompat.getColor(holder.parentView.context,R.color.white)
            else -> ContextCompat.getColor(holder.parentView.context,R.color.purple_200)
        })
    }

    override fun getItemCount() = moviesList.size
}