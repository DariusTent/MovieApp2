package com.example.movieapp.UI.gallery

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.UI.Movies.Movie
import com.example.movieapp.UI.Movies.MovieRepository
import com.example.movieapp.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FavouriteMoviesAdapter(
    private val moviesList: MutableList<Movie>
) : RecyclerView.Adapter<FavouriteMoviesAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var favorite: Boolean = false
        var watched: Boolean = false

        val itemIvMovie = view.findViewById<ImageView>(R.id.imgMovie)!!
        val itemIvTitle = view.findViewById<TextView>(R.id.txtMovieName)!!
        val itemIvOverview = view.findViewById<TextView>(R.id.txtMovieDescription)!!
        val itemBtnDelete =view.findViewById<ImageView>(R.id.btnDelete)!!

    }

    private val moviesRep: MovieRepository = MovieRepository.instance

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie_delete, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = moviesList[position]

        // descarcati imaginea cu Glide
        Glide.with(holder.itemView.context).load(
            Constants.LinkPoza + moviesList[position].image
        ).into(holder.itemIvMovie)

        holder.itemIvTitle.text = movie.title
        holder.itemIvOverview.text = movie.description
        holder.favorite = moviesList[position].isFavorite
        holder.watched = moviesList[position].isWatched

        holder.itemBtnDelete.setOnClickListener {
            updateItem(moviesList[position])
            moviesList.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, moviesList.size)
        }
    }

    private fun updateItem(movie: Movie) {
        GlobalScope.launch(Dispatchers.IO) {
//            val savedMovie = moviesRep.getMovieById(movie.id)
//            val idx = saved.indexOf(movie)
//            if (idx != -1) saved[idx].isFavorite = !saved[idx].isFavorite
//            if (!saved[idx].isFavorite && !saved[idx].isWatched) {
//                moviesRep.deleteLocal(saved[idx])
//                saved.removeAt(idx)
//            }

            movie.isFavorite = false
            moviesRep.replaceMovieLocal(movie)
        }
    }

    override fun getItemCount() = moviesList.size
}
