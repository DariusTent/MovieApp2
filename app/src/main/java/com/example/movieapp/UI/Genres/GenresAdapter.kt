package com.example.movieapp.UI.Genres

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R

class GenresAdapter(private val genresList: List<Genre>) :
    RecyclerView.Adapter<GenresAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val genreName: TextView = view.findViewById(R.id.tvName)
        val parentView: ConstraintLayout = view.findViewById(R.id.parent)
        val starIcon: ImageView = view.findViewById(R.id.star)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_genre, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val genre = genresList[position]
        holder.genreName.text = genre.name

        selectGenre(holder,genre)

        holder.parentView.setOnClickListener{
            genre.isSelected = !genre.isSelected
            selectGenre(holder,genre)

        }
    }

    private fun selectGenre(holder: ViewHolder,genre:Genre){
        holder.parentView.setBackgroundColor(when(genre.isSelected){
            true -> ContextCompat.getColor(holder.parentView.context,R.color.purple_200)
            else -> ContextCompat.getColor(holder.parentView.context,R.color.white)

        })

        holder.genreName.setTextColor(when(genre.isSelected){
            true -> ContextCompat.getColor(holder.parentView.context,R.color.white)
            else -> ContextCompat.getColor(holder.parentView.context,R.color.purple_200)
        })
    }

    override fun getItemCount() = genresList.size
}