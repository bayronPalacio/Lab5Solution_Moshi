package com.example.lab5solution

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import com.example.lab5solution.data.Movie
import com.example.lab5solution.ui.MainView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.io.Serializable

class MainActivity : AppCompatActivity() {
    private val FILENAME = "jsonFileMovies.json"

    private var dataBaseMovie: MovieDatabase? = null
    private var movieDao: MovieDao? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mv  = MainView(this, FILENAME)

        var allMovies : List<Movie>? = null

        Observable.fromCallable {
            //An instance of the Database and movieDao are created
            dataBaseMovie = MovieDatabase.getAppDataBase(context = this)
            movieDao = dataBaseMovie?.movieDao()

            //A Movie object is created with the information from the jsonFile
            with(movieDao) {
                //The Movie object is added to the database through the movieDao with the insertMovie() method
                allMovies = this?.getAllMovies()
            }
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
        Thread.sleep(500)

        btnMain.setOnClickListener {
            var goToNextPage = Intent(this,MainMenu::class.java)
            //The code below allows to send the list of movies to the next page using Serializable
//            goToNextPage.putExtra("data",allMovies as Serializable)
            startActivity(goToNextPage)
        }
    }
}