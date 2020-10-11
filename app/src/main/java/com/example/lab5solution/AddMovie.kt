package com.example.lab5solution

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.lab5solution.data.Movie
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_add_movie.*

class AddMovie : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_movie)

        var dataBaseMovie: MovieDatabase?
        var movieDao: MovieDao?

        btn_add_movie.setOnClickListener {
            var genreFinal = arrayListOf<String>(genre_movie.text.toString())

            var newMovie = Movie(
                movie_id = id_movie.text.toString(), plot_summary = plot_movie.text.toString(), duration = dur_movie.text.toString(),
                genre = genreFinal, rating = rat_movie.text.toString(), release_date = release_date.text.toString(), plot_synopsis = syn_movie.text.toString())

            Observable.fromCallable {
                //An instance of the Database and movieDao are created
                dataBaseMovie = MovieDatabase.getAppDataBase(context = this)
                movieDao = dataBaseMovie?.movieDao()

                //A Movie object is created with the information from the jsonFile
                Log.i("NEW_MOVIE",newMovie.toString())
                with(movieDao) {
                    //The Movie object is added to the database through the movieDao with the insertMovie() method
                    this?.insertMovie(newMovie)
                }
            }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
            var goToMainMenu = Intent(this,MainMenu::class.java)
            startActivity(goToMainMenu)
        }
    }
}