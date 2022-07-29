package com.example.movieapp.UI.Actors

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.UI.Genres.Genre
import com.example.movieapp.UI.onboardingScreen.OnboardingActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ActorsActivity : AppCompatActivity() {

    private var actors: List<Actor> = emptyList()
    private val actorRepository = ActorRepository.instance

    private fun getActors(){
        GlobalScope.launch (Dispatchers.IO){
            actors = actorRepository.getAllRemoteActors()
            withContext(Dispatchers.Main){
                preselectSavedActors()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actors)

        setOnClickListeners()
        getActors()
    }

    private fun setOnClickListeners(){
        val btnSave = findViewById<FloatingActionButton>(R.id.btnSave)
        btnSave.setOnClickListener{
            saveActors()
        }
    }

    private fun getSelectedActors(): List<Actor>{
        return actors.filter{actor -> actor.isSelected}
    }

    private fun saveActors(){
        GlobalScope.launch(Dispatchers.IO){
            actorRepository.deleteAllLocal()
            actorRepository.saveAllLocal(getSelectedActors())
        }
        OnboardingActivity.open(this)
    }

    private fun setupRecyclerView() {
        val rvActors = findViewById<RecyclerView>(R.id.rvActors)
        rvActors.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvActors.adapter = ActorsAdapter(actors)
    }

    private fun preselectSavedActors(){
        GlobalScope.launch(Dispatchers.IO){
            val savedActors: List<Actor> = actorRepository.getAllLocalActors()
            withContext(Dispatchers.Main){
                actors.forEach{actor -> actor.isSelected = savedActors.contains(actor)}
                setupRecyclerView()
            }
        }
    }
}