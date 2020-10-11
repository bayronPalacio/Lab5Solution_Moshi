package com.example.lab5solution

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab5solution.data.Movie
import kotlinx.android.synthetic.main.activity_main_menu.*

class MainMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        var listOfMovies : List<Movie> = intent.getSerializableExtra("data") as List<Movie>

        var recycleViewMovie : RecyclerView = findViewById(R.id.recycleMovie)

        val gm = GridLayoutManager(this, 2)
        recycleViewMovie.layoutManager = gm
        val myMovieAdapter = MovieAdapter(listOfMovies, this)
        recycleViewMovie.setAdapter(myMovieAdapter)

        btnAdd.setOnClickListener {
            var goToAddPage = Intent(this, AddMovie::class.java)
            startActivity(goToAddPage)
        }

        btnDelete.setOnClickListener {
            var goToDeletePage = Intent(this, DeleteMovie::class.java)
            startActivity(goToDeletePage)
        }

        btnSearch.setOnClickListener {
            var goToSearchPage = Intent(this, SearchMovie::class.java)
            startActivity(goToSearchPage)
        }

        btnUpdate.setOnClickListener {
            var goToUpdatePage = Intent(this, UpdateMovie::class.java)
            startActivity(goToUpdatePage)
        }
    }
}