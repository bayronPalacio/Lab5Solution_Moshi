package com.example.lab5solution.ui

import android.app.Activity
import androidx.room.TypeConverter
import com.example.lab5solution.Converters
import com.example.lab5solution.MovieDao
import com.example.lab5solution.MovieDatabase
import com.example.lab5solution.data.Movie
import com.example.lab5solution.utilities.FileHelper
import com.google.gson.Gson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainView (ctx : Activity, fileName: String){

    private var dataBaseMovie: MovieDatabase? = null
    private var movieDao: MovieDao? = null
    private val myType = Types.newParameterizedType(List::class.java, Movie::class.java)

    init {
        val text = FileHelper.getDataFromAssets(ctx,fileName)

        // Using data class Movie
        val moshi: Moshi = Moshi.Builder().build()
        val adapter : JsonAdapter<List<Movie>> = moshi.adapter(myType)
        val empList = adapter.fromJson(text)

        // ?: Elvis Operator
        for (e in empList ?: emptyList() ) {
            Observable.fromCallable {
                //An instance of the Database and movieDao are created
                dataBaseMovie = MovieDatabase.getAppDataBase(context = ctx)
                movieDao = dataBaseMovie?.movieDao()

                //A Movie object is created with the information from the jsonFile
                var newMovie = Movie(
                    movie_id = e.movie_id, plot_summary = e.plot_summary, duration = e.duration,
                    genre = e.genre, rating = e.rating, release_date = e.release_date, plot_synopsis = e.plot_synopsis )

                with(movieDao) {
                    //The Movie object is added to the database through the movieDao with the insertMovie() method
                    this?.insertMovie(newMovie)
                }
            }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        }

    }
}