package com.example.lab5solution.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*
import kotlin.collections.ArrayList

//movie_id, plot_summary, duration, genre, rating, release_date, plot_synopsis

@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey val movie_id: String = "",
    val plot_summary: String = "",
    val duration: String = "",
    val genre: ArrayList<Any>? = null,
    val rating: String = "",
    val release_date: String = "",
    val plot_synopsis: String = ""
)