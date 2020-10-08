package com.example.lab5solution

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lab5solution.ui.MainView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val FILENAME = "jsonFileMovies.json"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mv = MainView(this, FILENAME)

        btnMain.setOnClickListener {
            var goToNextPage = Intent(this,MainMenu::class.java)
            startActivity(goToNextPage)
        }
    }
}