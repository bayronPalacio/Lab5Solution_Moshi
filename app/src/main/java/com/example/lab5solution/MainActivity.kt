package com.example.lab5solution

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lab5solution.ui.MainView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val FILENAME = "jsonFileMovies.json"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txtResult.setText("")
        val mv = MainView(this, FILENAME)
    }
}