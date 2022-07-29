package com.example.movieapp.UI.Actors

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R

class ActorsAdapter(private val actorsList: List<Actor>) :
    RecyclerView.Adapter<ActorsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val actorName: TextView = view.findViewById(R.id.tvName)
        val parentView: ConstraintLayout = view.findViewById(R.id.parent)
        val starIcon: ImageView = view.findViewById(R.id.star)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_actor, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val actor = actorsList[position]
        holder.actorName.text = actor.name

        selectGenre(holder,actor)

        holder.parentView.setOnClickListener{
            actor.isSelected = !actor.isSelected
            selectGenre(holder,actor)

        }
    }

    private fun selectGenre(holder: ActorsAdapter.ViewHolder, actor: Actor){
        holder.parentView.setBackgroundColor(when(actor.isSelected){
            true -> ContextCompat.getColor(holder.parentView.context,R.color.purple_200)
            else -> ContextCompat.getColor(holder.parentView.context,R.color.white)

        })

        holder.actorName.setTextColor(when(actor.isSelected){
            true -> ContextCompat.getColor(holder.parentView.context,R.color.white)
            else -> ContextCompat.getColor(holder.parentView.context,R.color.purple_200)
        })
    }

    override fun getItemCount() = actorsList.size
}