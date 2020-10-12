package com.example.lab5solution

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab5solution.data.Movie
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main_menu.*

class MainMenu : AppCompatActivity() {

    private var dataBaseMovie: MovieDatabase? = null
    private var movieDao: MovieDao? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)


// The code below allows to get the List of movies that is sent by the Main Activity
//        var listOfMovies : List<Movie> = intent.getSerializableExtra("data") as List<Movie>

        var listOfMovies : List<Movie> ?= null

        Observable.fromCallable {
            //An instance of the Database and movieDao are created
            dataBaseMovie = MovieDatabase.getAppDataBase(context = this)
            movieDao = dataBaseMovie?.movieDao()

            //A Movie object is created with the information from the jsonFile
            with(movieDao) {
                //The Movie object is added to the database through the movieDao with the insertMovie() method
                listOfMovies = this?.getAllMovies()
            }
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
        Thread.sleep(500)

        var recycleViewMovie : RecyclerView = findViewById(R.id.recycleMovie)

        val gm = GridLayoutManager(this, 2)
        recycleViewMovie.layoutManager = gm
        val myMovieAdapter = listOfMovies?.let { MovieAdapter(it, this) }
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