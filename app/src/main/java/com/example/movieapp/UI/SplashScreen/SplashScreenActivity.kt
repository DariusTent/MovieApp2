package com.example.movieapp.UI.SplashScreen

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.movieapp.R
import com.example.movieapp.SearchActivity
import com.example.movieapp.UI.Actors.ActorRepository
import com.example.movieapp.UI.Genres.Genre
import com.example.movieapp.UI.Genres.GenreRepository
import com.example.movieapp.UI.Movies.MovieRepository
import com.example.movieapp.UI.onboardingScreen.OnboardingActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val DELAY = 500L

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    private var handler: Handler? = null
    private var runnable: Runnable? = null
    private val genreRepository = GenreRepository.instance
    private val actorRepository = ActorRepository.instance
    private val movieRepository = MovieRepository.instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)
        initHandlerToOpenNextActivity()
    }

    private fun initHandlerToOpenNextActivity() {
        handler = Handler(Looper.getMainLooper())
        runnable = Runnable {
            openNextScreen()
        }

        handler?.postDelayed(runnable!!, DELAY)
    }

    private fun openNextScreen() {
        isSaved()

        finish()
    }

    private fun isSaved() {
        GlobalScope.launch(Dispatchers.IO){
            val genreCount = genreRepository.getCount()
            val actorCount = actorRepository.getCount()
            val movieCount = movieRepository.getCount()
            withContext(Dispatchers.Main){
                verifyIsSaved(genreCount, actorCount)

            }
        }
    }

    private fun verifyIsSaved(genreCount: Int, actorCount: Int){
        val isSaved = genreCount > 0 && actorCount> 0
        if(isSaved)
            SearchActivity.open(this)
        else
            OnboardingActivity.open(this)

    }

    override fun onDestroy() {
        removeHandler()
        super.onDestroy()
    }

    override fun onBackPressed() {
        removeHandler()
        super.onBackPressed()
    }

    private fun removeHandler() {
        if (handler != null && runnable != null) {
            handler?.removeCallbacks(runnable!!)
            runnable = null
            handler = null
        }
    }
}