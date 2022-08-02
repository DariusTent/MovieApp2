package com.example.movieapp.UI.onboardingScreen

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.movieapp.R
import com.example.movieapp.SearchActivity
import com.example.movieapp.UI.Actors.ActorRepository
import com.example.movieapp.UI.Genres.GenresActivity
import com.example.movieapp.UI.Actors.ActorsActivity
import com.example.movieapp.UI.Genres.GenreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OnboardingActivity : AppCompatActivity() {

    private val genreRepository = GenreRepository.instance
    private val actorRepository = ActorRepository.instance

    companion object {
        fun open(context: Context) {
            context.startActivity(Intent(context, OnboardingActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboardingscreen)
        setClickListeners()
    }

    private fun setClickListeners() {
        val genresButton = findViewById<Button>(R.id.btnGenres)

        genresButton.setOnClickListener {
            startActivity(Intent(this, GenresActivity::class.java))
        }

        val actorsButton = findViewById<Button>(R.id.btnActors)

        actorsButton.setOnClickListener {
            startActivity(Intent(this, ActorsActivity::class.java))
        }

    }

    override fun onResume(){
        super.onResume()
        verifyIfFilterIsSelected()
    }

    private fun verifyIfFilterIsSelected(){
        GlobalScope.launch ( Dispatchers.IO ){
            val genreCount = genreRepository.getCount()
            val actorCount = actorRepository.getCount()

            withContext(Dispatchers.Main){
                if(genreCount >0 && actorCount > 0){
                        SearchActivity.open(this@OnboardingActivity)
                }
            }
        }
    }

}