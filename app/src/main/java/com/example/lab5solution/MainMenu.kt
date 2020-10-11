package com.example.lab5solution

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import com.example.lab5solution.data.Movie
import kotlinx.android.synthetic.main.activity_main_menu.*
import java.io.Serializable

class MainMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

//        var listOfMovies : Array<out Parcelable>? = intent.getParcelableArrayExtra("movies")
////        listOfMovies = intent.getSerializableExtra("movies") as List<Movie>
//        for(movie in listOfMovies!!)
//        {
//            Log.i("MOVIE",movie.toString())
//        }

        var receiveFromMain = intent.getStringExtra("passString")
        Log.i("data",receiveFromMain.toString())

        btnAdd.setOnClickListener {
            var goToAddPage = Intent(this,AddMovie::class.java)
            startActivity(goToAddPage)
        }

        btnDelete.setOnClickListener {
            var goToDeletePage = Intent(this,DeleteMovie::class.java)
            startActivity(goToDeletePage)
        }

        btnSearch.setOnClickListener {
            var goToSearchPage = Intent(this,SearchMovie::class.java)
            startActivity(goToSearchPage)
        }

        btnUpdate.setOnClickListener {
            var goToUpdatePage = Intent(this,UpdateMovie::class.java)
            startActivity(goToUpdatePage)
        }
    }
}