package com.example.lab5solution.utilities

import android.content.Context
import com.example.lab5solution.data.Movie
import java.io.BufferedReader

class FileHelper {
    companion object {
        // accepts Context and filename
        fun getDataFromAssets(context: Context, fileName: String) : String {
            return context.assets.open(fileName).use {
                it.bufferedReader().use { bf: BufferedReader ->
                    bf.readText()
                }
            }
        }
    }
}