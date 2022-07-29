package com.example.movieapp.UI.onboardingScreen

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.movieapp.R
import com.example.movieapp.UI.Genres.GenresActivity
import com.example.movieapp.UI.Actors.ActorsActivity

class OnboardingActivity : AppCompatActivity() {

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

}