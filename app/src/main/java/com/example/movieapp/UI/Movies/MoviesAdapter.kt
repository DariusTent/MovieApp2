package com.example.movieapp.UI.Movies

import android.view.LayoutInflater
import android.view.ScrollCaptureCallback
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.UI.moviedetails.MovieDetailsViewModel
import com.example.movieapp.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MoviesAdapter(private val moviesList: List<Movie>,
                    private val detailsCallback: (() -> Unit)?,
                    private val viewModel: MovieDetailsViewModel
) :
    RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var favourite: Boolean = false
        var watched: Boolean = false
        val title: TextView = view.findViewById(R.id.txtMovieTitle)
        val parentView: ConstraintLayout = view.findViewById(R.id.MovieItem)
        val txtDescription: TextView = view.findViewById(R.id.txtMovieDescription)
        val imageView:ImageView = view.findViewById(R.id.ImgMovie)
        val itemBtnFavourite : ImageButton = view.findViewById(R.id.btnFavorite)
        val itemBtnWatched : ImageButton= view.findViewById(R.id.btnWatched)

    }

    private val moviesRep: MovieRepository = MovieRepository.instance

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false)

        return ViewHolder(view)
    }

    private fun updateFavoriteButton(holder: ViewHolder) {
        holder.itemBtnFavourite.setImageResource(when(holder.favourite) {
            true -> R.drawable.ic_favourite_full
            else -> R.drawable.ic_favourite_empty
        })
    }

    private fun updateWatchedButton(holder: ViewHolder) {
        holder.itemBtnWatched.setImageResource(when(holder.watched) {
            true -> R.drawable.ic_saved_full
            else -> R.drawable.ic_saved_empty
        })
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = moviesList[position]
        Glide.with(holder.itemView.context).load( Constants.LinkPoza + moviesList[position].image).into(holder.imageView)
        holder.title.text = movie.title
        holder.txtDescription.text = movie.description

        holder.parentView.setOnClickListener{
            viewModel.currentMovieId.postValue(movie.id)
            detailsCallback?.invoke()
        }

        holder.favourite = movie.isFavorite
        holder.watched = movie.isWatched

        updateFavoriteButton(holder)
        updateWatchedButton(holder)

        holder.itemBtnFavourite.setOnClickListener{
            holder.favourite =!holder.favourite
            movie.isFavorite = holder.favourite
            updateFavoriteButton(holder)
            updateDatabase(moviesList[position])
        }

        holder.itemBtnWatched.setOnClickListener{
            holder.watched =!holder.watched
            movie.isWatched = holder.watched
            updateWatchedButton(holder)
            updateDatabase(moviesList[position])

        }
    }

    private fun filterWithFlags() = moviesList.filter { it.isFavorite || it.isWatched }

    private fun updateDatabase(item: Movie) {
        GlobalScope.launch(Dispatchers.IO) {
            val saved = ArrayList<Movie>(moviesRep.getAllLocalMovies())
            val filtered = ArrayList<Movie>(filterWithFlags())

            saved.remove(item)

            moviesRep.replaceAllLocal(saved.union(filtered).toList())
        }
    }

    override fun getItemCount() = moviesList.size
}