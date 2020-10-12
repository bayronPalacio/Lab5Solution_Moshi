package com.example.lab5solution

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.lab5solution.data.Movie
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_add_movie.*
import kotlinx.android.synthetic.main.activity_search_movie.*
import kotlinx.android.synthetic.main.activity_update_movie.*

class UpdateMovie : AppCompatActivity() {

    private var dataBaseMovie: MovieDatabase? = null
    private var movieDao: MovieDao? = null
    var movieToUpdate: Movie? = null
    var newMovie: Movie? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_movie)

        btnGetMovie.setOnClickListener {
            Observable.fromCallable {
                //An instance of the Database and movieDao are created
                dataBaseMovie = MovieDatabase.getAppDataBase(context = this)
                movieDao = dataBaseMovie?.movieDao()

                //A Movie object is created with the information from the jsonFile
                with(movieDao) {
                    //The Movie object is added to the database through the movieDao with the insertMovie() method
                    movieToUpdate = this?.getMovieById(inputId = inputUpdateId.text.toString())
                    if(movieToUpdate != null)
                    {
                        Log.i("SEARCH",movieToUpdate.toString())
                    }
                }
            }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
            Thread.sleep(300)
            if(movieToUpdate != null){
                Toast.makeText(this,"Movie has been found", Toast.LENGTH_LONG).show()
                updateDuration.setText(movieToUpdate!!.duration)
                updateReleaseDate.setText(movieToUpdate!!.release_date)
                updateRating.setText(movieToUpdate!!.rating)
            }
            else{
                Toast.makeText(this,"Movie does not exist in DB", Toast.LENGTH_LONG).show()
            }
        }

        btn_update_movie.setOnClickListener {
            Observable.fromCallable {
                //An instance of the Database and movieDao are created
                dataBaseMovie = MovieDatabase.getAppDataBase(context = this)
                movieDao = dataBaseMovie?.movieDao()
                newMovie = Movie(
                    movie_id = movieToUpdate!!.movie_id, plot_summary = movieToUpdate!!.plot_summary, duration = updateDuration.text.toString(),
                    genre = movieToUpdate?.genre, rating = updateRating.text.toString(), release_date = updateReleaseDate.text.toString(),
                    plot_synopsis = movieToUpdate!!.plot_synopsis)

                //A Movie object is created with the information from the jsonFile
                with(movieDao) {
                    //The Movie object is added to the database through the movieDao with the insertMovie() method
                     this?.updateMovie(newMovie!!)
                }
            }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
            Thread.sleep(300)
            Toast.makeText(this,"Movie with id " + newMovie!!.movie_id + " has been updated", Toast.LENGTH_LONG).show()
            var goToMenu = Intent(this,MainMenu::class.java)
            startActivity(goToMenu)
        }
    }
}

/////////////Below copy on top of the grandle file, below the old ones
//apply plugin: 'kotlin-kapt'
//
/////////////Below copy inside dependencies
//
//implementation 'com.squareup.moshi:moshi:1.8.0'
//implementation "com.squareup.moshi:moshi-adapters:1.8.0"
//implementation "com.squareup.moshi:moshi-kotlin:1.8.0"
//
//implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.0.0")
//
//def room_version = "2.2.0-rc01"
//
//implementation "androidx.room:room-runtime:$room_version"
//kapt "androidx.room:room-compiler:$room_version"
//
//implementation 'com.google.code.gson:gson:2.2.4'
//implementation 'io.reactivex.rxjava2:rxandroid:2.0.2'
//implementation 'io.reactivex.rxjava2:rxjava:2.0.4'