package com.example.movieapp.UI.gallery

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.UI.Movies.Movie
import com.example.movieapp.UI.Movies.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WatchedListFragment : Fragment(R.layout.fragment_watched_list) {

    private val movieRep: MovieRepository = MovieRepository.instance
    private var movies:MutableList<Movie> = mutableListOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeListOfMovies(view)
    }

    private fun setupRecyclerView(view: View){
        val linearLayoutManager = LinearLayoutManager(view.context)
        val recyclerView = view.findViewById<RecyclerView>(R.id.rvWatchedMoviesList)

        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        linearLayoutManager.reverseLayout = false

        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = WatchedMoviesAdapter(movies)
    }

    private fun initializeListOfMovies(view: View){
        GlobalScope.launch(Dispatchers.IO){
            movies = movieRep.getWatched().toMutableList()
            withContext(Dispatchers.Main){
                setupRecyclerView(view)
            }
        }
    }
}