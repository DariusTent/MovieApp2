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
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
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
import com.example.movieapp.UI.moviedetails.MovieDetailsViewModel
import com.example.movieapp.databinding.FragmentSearchMoviesBinding
import com.example.movieapp.ui.home.SearchViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchMoviesFragment : Fragment() {

    private var _binding: FragmentSearchMoviesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var movies: List<Movie> = emptyList()
    private val movieRepository = MovieRepository.instance
    private val genreRepository = GenreRepository.instance
    private val actorRepository = ActorRepository.instance
    private var genreIds = ""
    private var actorIds = ""

    private lateinit var viewModel:MovieDetailsViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(SearchViewModel::class.java)

        _binding = FragmentSearchMoviesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        viewModel = ViewModelProvider(requireActivity())[MovieDetailsViewModel::class.java]

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getQueryParams()
        setSearchTextListener()
    }

    private fun getQueryParams() {
        preselectSavedGenres()
    }

    private fun preselectSavedGenres() {
        GlobalScope.launch(Dispatchers.IO) {
            val savedGenresIds: List<Int> = genreRepository.getAllLocalIds()
            val savedActorsIds: List<Int> = actorRepository.getAllLocalIds()
            genreIds = savedGenresIds.joinToString(separator = "|") { "$it" }
            actorIds = savedActorsIds.joinToString(separator = "|") { "$it" }
            Log.d("Test", "Rezultat: $genreIds")
            withContext(Dispatchers.Main) {
                getMovies()
            }
        }
    }

    private fun getMovies() {
        GlobalScope.launch(Dispatchers.IO) {
            movies = movieRepository.getAllRemoteMovies()
            withContext(Dispatchers.Main) {
                preselectItems()
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
        rvMovies.adapter = MoviesAdapter(movies, { navigateToMovieDetails()} , viewModel )
    }

    private fun setSearchTextListener() {
        val search = binding.searchView
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(newText: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if ((newText?.length ?: 0) >= 1) {
                    getSearchedMovies(newText ?: "")
                } else
                    getMovies()
                return false
            }
        })
    }

    fun getSearchedMovies(query: String) {
        GlobalScope.launch(Dispatchers.IO) {
            movies = movieRepository.getSearchedMovies(query)
            withContext(Dispatchers.Main) {
                preselectItems()
                binding.rvMovies.adapter = MoviesAdapter(movies, { navigateToMovieDetails()} , viewModel )
            }
        }
    }

    private fun preselectItems() {
        GlobalScope.launch(Dispatchers.IO) {
            val saved = movieRepository.getAllLocalMovies()
            withContext(Dispatchers.Main) {
                movies.forEach {
                    val idx = saved.indexOf(it)
                    it.isFavorite = (idx != -1) && saved[idx].isFavorite
                    it.isWatched = (idx != -1) && saved[idx].isWatched
                }
            }
        }
    }

    private fun navigateToMovieDetails(){
        findNavController().navigate(R.id.fragmentMovieDetails)
    }
}