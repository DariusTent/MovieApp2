package com.example.movieapp.UI.search_movies

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.UI.Actors.Actor
import com.example.movieapp.UI.Actors.ActorRepository
import com.example.movieapp.UI.Genres.Genre
import com.example.movieapp.UI.Genres.GenreRepository
import com.example.movieapp.UI.Genres.GenresAdapter
import com.example.movieapp.UI.Movies.Movie
import com.example.movieapp.UI.Movies.MovieRepository
import com.example.movieapp.UI.Movies.MoviesAdapter
import com.example.movieapp.UI.trending.TrendingViewModel
import com.example.movieapp.databinding.FragmentSearchMoviesBinding
import com.example.movieapp.databinding.FragmentTrendingBinding
import com.example.movieapp.ui.home.SearchViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TrendingMoviesFragment : Fragment() {

    private var _binding: FragmentTrendingBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var movies: List<Movie> = emptyList()
    private val movieRepository = MovieRepository.instance

    private val genreRepository = GenreRepository.instance
    private val actorRepository = ActorRepository.instance
    private var genreIds = ""
    private var actorIds = ""




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(TrendingViewModel::class.java)

        _binding = FragmentTrendingBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        preselectSavedGenres()
    }


    private fun preselectSavedGenres() {
        GlobalScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                getMovies()
            }
        }
    }

    private fun getMovies() {
        GlobalScope.launch(Dispatchers.IO) {
            val savedGenresIds: List<Int> = genreRepository.getAllLocalIds()
            val savedActorsIds: List<Int> = actorRepository.getAllLocalIds()
            genreIds = savedGenresIds.joinToString(separator = "|") { "$it" }
            actorIds = savedActorsIds.joinToString(separator = "|") { "$it" }
            movies = movieRepository.getPreference(actorIds,genreIds)
            withContext(Dispatchers.Main) {
                moviesLoaded(movies)

            }
        }
    }

    private fun moviesLoaded(movies: List<Movie>) {
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val rvMovies = binding.rvMovies
        rvMovies.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvMovies.adapter = MoviesAdapter(movies)
    }
}