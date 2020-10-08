package com.example.lab5solution.ui

import android.app.Activity
import android.util.Log
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
        Log.i("MainView", "init")
        val text = FileHelper.getDataFromAssets(ctx,fileName)
        Log.i("MainView", text)

        // Using data class Movie
        val moshi: Moshi = Moshi.Builder().build()
        val adapter : JsonAdapter<List<Movie>> = moshi.adapter(myType)
        val empList = adapter.fromJson(text)
//        if (empList != null) {
//            println("size "+ empList.size.toString())
//        }
        // ?: Elvis Operator
        for (e in empList ?: emptyList() ) {
////            Observable.fromCallable {
////                var genreString : String = ""
////                var genreList : Array<String> = e.genre as Array<String>
////                if(genreList.size > 0)
////                {
////                    for(item in genreList){
////                        genreString += "$item, "
////                    }
////                }
////                //An instance of the Database and movieDao are created
////                dataBaseMovie = MovieDatabase.getAppDataBase(context = ctx)
////                movieDao = dataBaseMovie?.movieDao()
////
////                //A Movie object is created with the information from the jsonFile
////                var newMovie = Movie(movie_id = e.movie_id, plot_summary = e.plot_summary, duration = e.duration,
////                    genre = genreString, rating = e.rating, release_date = e.release_date, plot_synopsis = e.plot_synopsis )
////
////                with(movieDao) {
////                    //The Movie object is added to the database through the movieDao with the insertMovie() method
////                    this?.insertMovie(newMovie)
////                }
////            }.subscribeOn(Schedulers.io())
////                .observeOn(AndroidSchedulers.mainThread())
////                .subscribe()
            Log.i("JSON", "Movie: ID: ${e.movie_id} \n summary: ${e.plot_summary} \n duration ${e.duration} \n genre $e" +
                    "\n rating ${e.rating} \n release date ${e.release_date} \n plot synopsis ${e.plot_synopsis}")
        }

    }
}