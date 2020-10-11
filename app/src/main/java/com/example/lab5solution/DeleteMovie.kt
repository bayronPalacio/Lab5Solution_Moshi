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
import kotlinx.android.synthetic.main.activity_delete_movie.*

class DeleteMovie : AppCompatActivity() {

    private var dataBaseMovie: MovieDatabase? = null
    private var movieDao: MovieDao? = null
    var movieToDelete : Movie? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_movie)

        btn_delete_movieid.setOnClickListener {
            Observable.fromCallable {
                //An instance of the Database and movieDao are created
                dataBaseMovie = MovieDatabase.getAppDataBase(context = this)
                movieDao = dataBaseMovie?.movieDao()

                //A Movie object is created with the information from the jsonFile
                with(movieDao) {
                    //The Movie object is added to the database through the movieDao with the insertMovie() method
                    movieToDelete = this?.getMovieById(inputId = deleteInput.text.toString())
                    if(movieToDelete != null)
                    {
                        Log.i("DELETE",movieToDelete.toString())
                        this?.deleteMovie(movieToDelete!!)
                    }
                }
            }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
            Thread.sleep(300)
            if(movieToDelete != null){
                Toast.makeText(this,"Movie has been deleted",Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(this,"Movie does not exist in DB",Toast.LENGTH_LONG).show()
            }
            var goToMenu = Intent(this,MainMenu::class.java)
            startActivity(goToMenu)
        }
    }
}